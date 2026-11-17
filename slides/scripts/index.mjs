// Writes the web version: an index page with one card per unit, with the unit's two PDFs, built by
// scripts/beamer.sh into production/class and production/print, and the statements of its exercises and
// laboratory assignments, built by scripts/statements.sh into production/exercises and production/assignments,
// all copied next to it
//
// site: the web version's folder, emptied first (production/web)

import { copyFile, mkdir, readdir, readFile, rm, writeFile } from "node:fs/promises";
import path from "node:path";

const [site] = process.argv.slice(2);
const units = "beamer/units";
const versions = ["class", "print"];
const statements = ["exercises", "assignments"];
const code = "../code";

const escape = (text) => String(text).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");

// LaTeX to plain text: \code{x} and the like keep their argument, \\ and ~ become spaces
const plain = (tex) =>
  tex
    .replace(/\\[a-zA-Z]+\{([^{}]*)\}/g, "$1")
    .replace(/\\([#$%&_{}])/g, "$1")
    .replace(/\\\\|~/g, " ")
    .trim();

// The argument of the first \name{...} in a file (one level of nested braces)
const argument = (text, name) => text.match(new RegExp(`\\\\${name}\\{((?:[^{}]|\\{[^{}]*\\})*)\\}`))?.[1];

// The course, the author and the degree come from the one place they are defined
const commands = await readFile("beamer/commands.tex", "utf8");
const course = {
  name: plain(commands.match(/\\newcommand\{\\coursename\}\{([^}]*)\}/)[1]),
  author: plain(argument(commands, "author")),
  degree: plain(argument(commands, "institute"))
};

await rm(site, { recursive: true, force: true });
for (const version of versions) await mkdir(path.join(site, version), { recursive: true });

// The statements of a unit, by number: production/<kind>/<unit>/<N>.pdf, copied to the same path in the site
const numbered = async (kind, base) => {
  const dir = path.join("production", kind, base);
  const files = await readdir(dir).catch(() => []);
  const numbers = files.filter((f) => /^\d+\.pdf$/.test(f)).map((f) => Number.parseInt(f, 10)).sort((a, b) => a - b);
  if (numbers.length) await mkdir(path.join(site, kind, base), { recursive: true });
  for (const n of numbers) await copyFile(path.join(dir, `${n}.pdf`), path.join(site, kind, base, `${n}.pdf`));
  return numbers;
};

// The names of a lesson's assignments, in its order: the \name of each task's latex/parameters.tex
const assignmentNames = async (lesson) => {
  const info = await readFile(path.join(code, lesson, "lesson-info.yaml"), "utf8").catch(() => "");
  const tasks = [...info.matchAll(/^ *- *"?([^"\n]+?)"?$/gm)].map((m) => m[1]);
  const names = [];
  for (const task of tasks) {
    const parameters = await readFile(path.join(code, lesson, task, "latex", "parameters.tex"), "utf8").catch(() => null);
    if (parameters) names.push(plain(parameters.match(/\\def\\name\{([^}]*)\}/)?.[1] ?? task));
  }
  return names;
};

// The unit_N prefix gives the order; each card reads the unit's title, subtitle and sections
const cards = [];
for (const base of (await readdir(units)).sort()) {
  if (!/^unit_/.test(base)) continue;
  const text = await readFile(path.join(units, base, "main.tex"), "utf8");
  const heading = plain(argument(text, "title") ?? base);
  const [, number, title] = heading.match(/^Unit (\d+):\s*(.+)$/) ?? [, "", heading];
  const summary = plain(argument(text, "subtitle") ?? "");
  const sections = [...text.matchAll(/^\\section\{(.+)\}/gm)].map((m) => plain(m[1]));
  const exercises = await numbered(statements[0], base);
  const assignments = await numbered(statements[1], base);
  const names = await assignmentNames(title);
  for (const version of versions) {
    const pdf = path.join("production", version, `${base}.pdf`);
    await copyFile(pdf, path.join(site, version, `${base}.pdf`)).catch(() => {
      throw new Error(`${pdf} is missing: build the PDFs first with npm run pdf`);
    });
  }
  cards.push(`
        <li class="site-unit">
          <span class="site-unit-number">${escape(number)}</span>
          <div class="site-unit-body">
            <p class="site-unit-label">Unit ${escape(number)}</p>
            <h2>${escape(title)}</h2>
            <p class="site-unit-summary">${escape(summary)}</p>
            <ol class="site-unit-sections">
${sections.map((s, i) => `              <li><span>${i + 1}</span>${escape(s)}</li>`).join("\n")}
            </ol>
            <p class="site-unit-pdfs">
              <a href="class/${base}.pdf">Class slides</a>
              <a href="print/${base}.pdf">Print slides</a>
            </p>${exercises.length ? `
            <p class="site-unit-statements">
              <span>Exercises</span>
${exercises.map((n) => `              <a href="exercises/${base}/${n}.pdf">${n}</a>`).join("\n")}
            </p>` : ""}${assignments.map((n, i) => `
            <p class="site-unit-statements">
              <span>Laboratory assignment ${n}</span>
              <a href="assignments/${base}/${n}.pdf">${escape(names[i] ?? "Statement")}</a>
            </p>`).join("")}
          </div>
        </li>`);
}

await copyFile("site/site.css", path.join(site, "site.css"));
await writeFile(
  path.join(site, "index.html"),
  `<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="${escape(course.name)}: the slides of every unit" />
    <title>${escape(course.name)}</title>
    <link rel="stylesheet" href="site.css" />
  </head>
  <body>
    <header class="site-hero">
      <div class="site-wrap">
        <p class="site-degree">${escape(course.degree)}</p>
        <h1>${escape(course.name)}</h1>
        <p class="site-author">${escape(course.author)}</p>
      </div>
    </header>
    <main class="site-wrap">
      <ol class="site-units">${cards.join("")}
      </ol>
    </main>
  </body>
</html>
`
);
