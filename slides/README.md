# Programming Fundamentals II: Slides

Course slides built with [beamer](https://ctan.org/pkg/beamer) and compiled with latexmk and XeLaTeX from [TeX Live](https://tug.org/texlive/), designed to be built up gradually by dictating content to Claude Code. Each unit is published as two PDFs:

* **Class**: 16:9, one page per step, as shown during the lecture
* **Print**: 16:9, one page per slide with every step visible, for students to print and study

## Requirements

* [TeX Live](https://tug.org/texlive/) ([MacTeX](https://tug.org/mactex/) on macOS), full scheme: it brings `latexmk`, `xelatex`, beamer, the Inter font and every package the slides load.
* [Node.js](https://nodejs.org/), for the index page of the web version.
* [Claude Code](https://claude.com/product/claude-code) for the workflow described below (not required just to build the slides).

All the commands below run from this `slides/` folder:

```bash
cd slides
```

## Build

```bash
npm run pdf                  # both PDFs of every unit, into production/class/ and production/print/, and the statements of
                             # the exercises and assignments in ../code/, into production/exercises/ and production/assignments/
                             # (auxiliary files in production/aux/)
npm run preview -- print     # the pages of a version as PNG, in production/preview/<version>/<unit>/
npm run build                # production/web/: an index page with a button for each PDF and a link for each statement
```

`npm run preview` takes the version (`class` or `print`) and, optionally, one unit (e.g. `npm run preview -- class unit_1-introduction_java`). Each PDF is named after its unit's folder (e.g. `unit_1-introduction_java.pdf`).

To check a unit while editing it, build it in its own folder:

```bash
cd beamer/units/unit_1-introduction_java
latexmk -xelatex main.tex  # main.pdf here, 16:9; Git ignores it and its auxiliary files
```

## Publish on GitHub Pages

`.github/workflows/pages.yml` (at the repository root) runs `scripts/beamer.sh` and `scripts/statements.sh` in a TeX Live image (`xu-cheng/texlive-action`), then `npm run build` on every push to `main`, and publishes `production/web/` to GitHub Pages. It needs, once, *Settings → Pages → Source: GitHub Actions* in the GitHub repository.

## Layout

```
Programming Fundamentals II/   ← repository root
├── README.md                  ← the course and the repository
├── CLAUDE.md                  ← what each folder is, for Claude Code
├── .github/workflows/         ← pages.yml, which builds and publishes the slides
├── code/                      ← JetBrains Academy course
└── slides/                    ← this folder
    ├── CLAUDE.md              ← instructions Claude Code follows here
    ├── .claude/commands/      ← /new-slide, /new-unit, /review-unit, etc.
    ├── beamer/
    │   ├── packages.tex       ← the packages every unit loads
    │   ├── palette.tex        ← the palette, shared with the statements
    │   ├── codefont.tex       ← the code font, JetBrains Mono, shared with the statements too
    │   ├── style.sty          ← ALL the rest of the slides' styling: fonts, sizes, slide parts and components
    │   ├── commands.tex       ← the course's name, author and degree, and the inline macros
    │   ├── cover.tex          ← the cover of every unit
    │   ├── fonts/             ← JetBrains Mono, for the code (Inter comes with TeX Live)
    │   └── units/             ← one folder per unit, with its main.tex (and its images, if any)
    ├── statements/            ← exercise.tex and assignment.tex, with statement.sty, for the statements of ../code/
    ├── site/site.css          ← the style of the index page
    ├── scripts/               ← the PDF build, the previews and the index page
    └── production/            ← generated: class/, print/, exercises/, assignments/, web/ and preview/
```

Start Claude Code from this folder, so it loads this `CLAUDE.md` and the commands in `.claude/` from the first message.

## Workflow with Claude Code

1. Open this folder with Claude Code (`claude` from `slides/`). On startup it reads `CLAUDE.md` automatically, so it already knows the structure, style, and content rules for the course.

2. For a new unit:
   ```
   /new-unit 6 Event-driven programming
   ```

3. To keep adding slides, feed it your notes exactly as you jotted them down:
   ```
   /new-slide explain that a subclass inherits fields and methods from the superclass, use an Animal/Dog example, Dog inherits makeSound() but overrides it
   ```
   Claude Code decides whether that's a concept slide, a code slide, or several, and shows you the result before moving on.

4. To change the look of the **whole** course at once:
   ```
   /change-style I want a blue accent instead of orange, and a bit more contrast in the background
   ```
   This only touches `beamer/style.sty`; no unit gets modified.

5. Every so often, to check a unit hasn't drifted, or that its lesson in `code/` still matches it:
   ```
   /review-unit unit_1-introduction_java
   /sync-unit unit_1-introduction_java
   ```

6. To build both PDF versions and check them:
   ```
   /export
   ```

You can also skip the commands entirely and just paste notes into the conversation: the project's `CLAUDE.md` guides Claude Code either way, the commands just make it faster to repeat.
