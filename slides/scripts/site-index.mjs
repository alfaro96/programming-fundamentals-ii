// Writes the public site's index.html: one line per unit, linking its slides and its two PDFs (class, a page per step, and print, a page per slide)
//
// src: the folder with the unit files
// site: the site folder, where index.html goes

import { readdir, readFile, writeFile } from "node:fs/promises";
import path from "node:path";

const [src, site] = process.argv.slice(2);

// The course name comes from the one place it is defined
const course = (await readFile("theme/cover.js", "utf8")).match(/name: "([^"]+)"/)[1];

// The unit_N prefix of the file names gives the order
const items = [];
for (const name of (await readdir(src)).sort()) {
  if (!/^unit_.*\.md$/.test(name)) continue;
  const base = name.replace(/\.md$/, "");
  const title = (await readFile(path.join(src, name), "utf8")).match(/^## (.+)$/m)?.[1] ?? base;
  items.push(
    `      <li><a href="${base}.html">${title}</a> · ` +
      `<a href="pdf/class/${base}.pdf">class PDF</a> · ` +
      `<a href="pdf/print/${base}.pdf">print PDF</a></li>`
  );
}

await writeFile(
  path.join(site, "index.html"),
  `<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>${course}</title>
  </head>
  <body>
    <h1>${course}</h1>
    <ul>
${items.join("\n")}
    </ul>
  </body>
</html>
`
);
