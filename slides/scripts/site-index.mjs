// Writes the public site's index.html: a card per unit with its sections, linking its slides and its two PDFs (class, a page per step, and print, a page per slide), styled by theme/custom.css
//
// src: the folder with the unit files
// site: the site folder, where index.html goes

import { readdir, readFile, writeFile } from "node:fs/promises";
import path from "node:path";

const [src, site] = process.argv.slice(2);

const escape = (text) => text.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");

// The course, the author and the degree come from the one place they are defined
const cover = await readFile("theme/cover.js", "utf8");
const field = (name) => cover.match(new RegExp(`${name}: "([^"]+)"`))[1];
const course = field("name");
const author = field("author");
const degree = field("degree");

// Line icons drawn with the text color
const icon = (paths) => `<svg class="site-icon" viewBox="0 0 24 24" aria-hidden="true">${paths}</svg>`;
const icons = {
  slides: icon('<rect x="2" y="3" width="20" height="14" rx="2"/><path d="M8 21h8M12 17v4"/>'),
  class: icon('<path d="m12 2-10 5 10 5 10-5-10-5z"/><path d="m2 17 10 5 10-5"/><path d="m2 12 10 5 10-5"/>'),
  print: icon('<path d="M6 9V2h12v7"/><path d="M6 18H4a2 2 0 0 1-2-2v-5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-2"/><rect x="6" y="14" width="12" height="8"/>'),
  code: icon('<path d="m16 18 6-6-6-6M8 6l-6 6 6 6"/>')
};

// What each link gives, shown once above the units
const versions = [
  ["slides", "Slides", "In the browser, step by step, as in class"],
  ["class", "class PDF", "One page per step, as shown in class"],
  ["print", "print PDF", "One page per slide, every step visible, to print"]
];

// The unit_N prefix of the file names gives the order
const units = [];
for (const name of (await readdir(src)).sort()) {
  if (!/^unit_.*\.md$/.test(name)) continue;
  const base = name.replace(/\.md$/, "");
  const text = await readFile(path.join(src, name), "utf8");
  const heading = text.match(/^## (.+)$/m)?.[1] ?? base;
  const [, number, title] = heading.match(/^Unit (\d+):\s*(.+)$/) ?? [, "", heading];
  const summary = text.match(/^### (.+)$/m)?.[1] ?? "";
  // The sections are the list of the unit's first contents slide
  const contents = text.split(/class="contents"/)[1]?.split(/\n---/)[0] ?? "";
  const sections = [...contents.matchAll(/^\d+\.\s+(.+)$/gm)].map((m) => m[1]);
  units.push({ base, number, title, summary, sections });
}

const card = ({ base, number, title, summary, sections }) => `
      <li class="site-unit">
        <span class="site-unit-number">${escape(number)}</span>
        <div class="site-unit-body">
          <p class="site-unit-label">Unit ${escape(number)}</p>
          <h2>${escape(title)}</h2>
          <p class="site-unit-summary">${escape(summary)}</p>
          <ol class="site-unit-sections">
${sections.map((s, i) => `            <li><span>${i + 1}</span>${escape(s)}</li>`).join("\n")}
          </ol>
          <nav class="site-unit-links" aria-label="Unit ${escape(number)}">
            <a class="site-link site-link-main" href="${base}.html">${icons.slides}Slides</a>
            <a class="site-link" href="pdf/class/${base}.pdf">${icons.class}class PDF</a>
            <a class="site-link" href="pdf/print/${base}.pdf">${icons.print}print PDF</a>
          </nav>
        </div>
      </li>`;

// On GitHub Actions, a link back to the repository
const repository = process.env.GITHUB_REPOSITORY ? `${process.env.GITHUB_SERVER_URL}/${process.env.GITHUB_REPOSITORY}` : "";

await writeFile(
  path.join(site, "index.html"),
  `<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="${escape(`${course} slides: web version and PDFs of every unit`)}" />
    <title>${escape(course)}</title>
    <link rel="icon" href="favicon.ico" />
    <link rel="stylesheet" href="_assets/theme/custom.css" />
  </head>
  <body class="site-index">
    <header class="site-hero">
      <div class="site-wrap">
        <p class="site-degree">${escape(degree)}</p>
        <h1>${escape(course)}</h1>
        <p class="site-author">${escape(author)}</p>
      </div>
    </header>
    <main class="site-wrap">
      <ul class="site-versions">
${versions.map(([key, name, text]) => `        <li>${icons[key]}<p><strong>${name}</strong>${text}</p></li>`).join("\n")}
      </ul>
      <ol class="site-units">${units.map(card).join("")}
      </ol>
    </main>
    <footer class="site-footer site-wrap">
      ${repository ? `<a href="${repository}">${icons.code}Source on GitHub</a>` : ""}
    </footer>
  </body>
</html>
`
);
