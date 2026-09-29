// Screenshots one unit of the static site, with the Puppeteer in PUPPETEER_DIR (preview.sh finds it)
//
// html: the unit's page in the static site
// outDir: the folder the images go to
// mode: screen (every slide with all its steps, the default), print or class (every PDF page)

import path from "node:path";
import { pathToFileURL } from "node:url";
import { readConfig } from "./config.mjs";

const [html, outDir, mode = "screen"] = process.argv.slice(2);
const { default: puppeteer } = await import(
  path.join(process.env.PUPPETEER_DIR, "lib/esm/puppeteer/puppeteer.js")
);

// Chrome now and then fails to start: retry
let browser;
for (let attempt = 1; !browser; attempt++) {
  try {
    browser = await puppeteer.launch({ headless: true });
  } catch (error) {
    if (attempt === 3) throw error;
  }
}

const page = await browser.newPage();
// The window is the canvas set in reveal-md.json5
const { width, height } = (await readConfig()).revealOptions;
await page.setViewport({ width, height });
const url = pathToFileURL(path.resolve(html)).href;
const pad = (n, digits) => String(n).padStart(digits, "0");

if (mode === "screen") {
  await page.goto(url, { waitUntil: "load", timeout: 60000 });
  await page.waitForFunction(() => window.Reveal && Reveal.isReady());
  await page.evaluate(() => document.fonts.ready);
  await page.evaluate(() => Reveal.configure({ transition: "none" }));
  const slides = await page.evaluate(() =>
    Reveal.getSlides().map((s) => {
      const i = Reveal.getIndices(s);
      return [i.h, i.v || 0];
    })
  );
  for (const [h, v] of slides) {
    await page.evaluate(([h, v]) => {
      Reveal.slide(h, v);
      while (Reveal.nextFragment()) {}
    }, [h, v]);
    await new Promise((r) => setTimeout(r, 400));
    await page.screenshot({ path: path.join(outDir, `slide-${pad(h + 1, 2)}-${v + 1}.png`) });
  }
} else {
  // The print view of reveal.js, with a page per step for class, as pdf.sh exports them
  const query = mode === "class" ? "?view=print&pdfSeparateFragments=true" : "?view=print";
  await page.emulateMediaType("print");
  await page.goto(url + query, { waitUntil: "load", timeout: 60000 });
  await page.waitForSelector(".pdf-page", { timeout: 60000 });
  await page.evaluate(() => document.fonts.ready);
  await new Promise((r) => setTimeout(r, 1500));
  const pages = await page.$$(".pdf-page");
  for (let i = 0; i < pages.length; i++) {
    await pages[i].screenshot({ path: path.join(outDir, `page-${pad(i + 1, 3)}.png`) });
  }
}

await browser.close();
