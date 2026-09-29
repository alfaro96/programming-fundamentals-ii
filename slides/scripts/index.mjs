// Writes the index page of the web version: one card per unit, opening its slides
//
// src: the folder with the unit files
// site: the web version's folder, where index.html goes

import { readdir, readFile, writeFile } from "node:fs/promises";
import path from "node:path";

const [src, site] = process.argv.slice(2);

const escape = (text) => String(text).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");

// The course, the author and the degree come from the one place they are defined
const cover = await readFile("theme/cover.js", "utf8");
const field = (name) => cover.match(new RegExp(`${name}: "([^"]+)"`))[1];
const course = { name: field("name"), author: field("author"), degree: field("degree") };

// The unit_N prefix gives the order; each card reads the unit's cover and first contents slide
const units = [];
for (const name of (await readdir(src)).sort()) {
  if (!/^unit_.*\.md$/.test(name)) continue;
  const base = name.replace(/\.md$/, "");
  const text = await readFile(path.join(src, name), "utf8");
  const heading = text.match(/^## (.+)$/m)?.[1] ?? base;
  const [, number, title] = heading.match(/^Unit (\d+):\s*(.+)$/) ?? [, "", heading];
  const summary = text.match(/^### (.+)$/m)?.[1] ?? "";
  const contents = text.split(/class="contents"/)[1]?.split(/\n---/)[0] ?? "";
  const sections = [...contents.matchAll(/^\d+\.\s+(.+)$/gm)].map((m) => m[1]);
  units.push({ base, number, title, summary, sections });
}

const card = (unit) => `
        <li>
          <a class="site-unit" href="${unit.base}.html">
            <span class="site-unit-number">${escape(unit.number)}</span>
            <div class="site-unit-body">
              <p class="site-unit-label">Unit ${escape(unit.number)}</p>
              <h2>${escape(unit.title)}</h2>
              <p class="site-unit-summary">${escape(unit.summary)}</p>
              <ol class="site-unit-sections">
${unit.sections.map((s, i) => `                <li><span>${i + 1}</span>${escape(s)}</li>`).join("\n")}
              </ol>
            </div>
          </a>
        </li>`;

await writeFile(
  path.join(site, "index.html"),
  `<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="${escape(course.name)}: the slides of every unit" />
    <title>${escape(course.name)}</title>
    <link rel="icon" href="favicon.ico" />
    <link rel="stylesheet" href="_assets/theme/custom.css" />
  </head>
  <body class="site-index">
    <header class="site-hero">
      <div class="site-wrap">
        <p class="site-degree">${escape(course.degree)}</p>
        <h1>${escape(course.name)}</h1>
        <p class="site-author">${escape(course.author)}</p>
      </div>
    </header>
    <main class="site-wrap">
      <ol class="site-units">${units.map(card).join("")}
      </ol>
    </main>
  </body>
</html>
`
);
