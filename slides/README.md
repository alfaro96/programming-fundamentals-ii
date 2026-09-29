# Programming Fundamentals II: Slides

Course slides built with [reveal.js](https://revealjs.com/) via [`reveal-md`](https://github.com/webpro/reveal-md), designed to be built up gradually by dictating content to Claude Code.

## Requirements

* [Node.js](https://nodejs.org/) installed (needed for `npx`).
* A Chromium-based browser for the PDF export (Puppeteer downloads its own).
* [Claude Code](https://claude.com/product/claude-code) for the workflow described below (not required just to view or export the slides).

All the commands below run from this `slides/` folder:

```bash
cd slides
```

## View the slides locally

```bash
npm run dev
```

Starts a local dev server with hot reload: any change saved in `content/` or `theme/custom.css` updates automatically.

## Drawing on the slides

The two round buttons at the bottom left open a pen to draw over the slide and a whiteboard (also the C and B keys), so it works on a tablet with no keyboard. While either is open, a palette on the left picks the color or the eraser. What is drawn stays on its slide until the page is reloaded, and never reaches the PDFs.

## Settings

`reveal-md.json5` holds the deck settings, each with a comment saying what it is for: the canvas size (which the slides, the PDF pages and the screenshots all take), the reveal.js options, the scripts in `theme/` and the images folder. It is read only once, when `npm run dev` starts, so restart it after changing anything there. The scripts read it too, through `scripts/config.mjs`, so keep it as JSON plus `//` comments, with no trailing commas.

## Export

```bash
npm run pdf        # both PDF versions below, one file per unit, at the canvas size in reveal-md.json5
npm run pdf:class  # production/class/: one page per step, as shown in class
npm run pdf:print  # production/print/: one page per slide, every step visible, for students
npm run build      # static web version in production/web/, host it anywhere
```

Each PDF is named after its unit file (e.g. `unit_1-introduction_java.pdf`).

To check how a unit looks without opening a browser, `npm run preview` screenshots every slide into `production/preview/screen/<unit>/`; `npm run preview -- print <unit>` or `npm run preview -- class <unit>` does the same with the PDF pages.

The PDFs and the screenshots use the Chrome that Puppeteer downloads. If it does not start on your machine, point `PUPPETEER_EXECUTABLE_PATH` at a Chrome that does in a `.env` file in this folder, which the scripts read and Git ignores:

```bash
PUPPETEER_EXECUTABLE_PATH="/path/to/chrome"
```

## Publish on GitHub Pages

```bash
npm run site  # production/site/: web slides, both PDFs and an index page
```

`.github/workflows/pages.yml` (at the repository root) runs that same script on every push to `main` and publishes `production/site/` to GitHub Pages. It needs, once, *Settings → Pages → Source: GitHub Actions* in the GitHub repository.

## Layout

```
Programming Fundamentals II/  ← repository root
├── README.md                 ← the course and the repository
├── CLAUDE.md                 ← what each folder is, for Claude Code
├── code/                     ← JetBrains Academy course
└── slides/                   ← this folder
    ├── CLAUDE.md             ← instructions Claude Code follows here
    ├── reveal-md.json5       ← deck settings: canvas size, reveal.js options
    ├── .claude/commands/     ← /new-slide, /new-unit, etc.
    ├── content/              ← one .md file per unit (unit_1-*.md, unit_2-*.md...) and assets/, their images
    ├── theme/custom.css      ← ALL visual styling, in a single file
    ├── theme/*.js            ← cover, contents, print handout and drawing helpers
    ├── theme/reveal.html     ← the page around the slides: reveal-md's template, ready for tablets
    ├── theme/vendor/         ← third-party plugins, copied as they are (the chalkboard, for drawing)
    ├── templates/            ← one markdown mold per slide type
    ├── scripts/              ← PDF export, GitHub Pages site, screenshots and the settings reader
    └── production/           ← generated: class/, print/, web/, site/ and preview/
```

Start Claude Code from this folder, so it loads this `CLAUDE.md` and the commands in `.claude/` from the first message.

## Workflow with Claude Code

1. Open this folder with Claude Code (`claude` from `slides/`). On startup it reads `CLAUDE.md` automatically, so it already knows the structure, style, and content rules for the course.

2. For a new unit:
   ```
   /new-unit 7 Inheritance and polymorphism
   ```

3. To keep adding slides, feed it your notes exactly as you jotted them down:
   ```
   /new-slide explain that a subclass inherits fields and methods from
   the superclass, use an Animal/Dog example, Dog inherits makeSound()
   but overrides it
   ```
   Claude Code decides whether that's a concept slide, a code slide, or several, and shows you the result before moving on.

4. To change the look of the **whole** course at once:
   ```
   /change-style I want a blue accent instead of orange, and a bit more
   contrast in the background
   ```
   This only touches `theme/custom.css`; no content slide gets modified.

5. Every so often, to check a unit hasn't drifted, or that its lesson in `code/` still matches it:
   ```
   /review-unit content/unit_7-inheritance.md
   /sync-unit content/unit_7-inheritance.md
   ```

6. To export both PDF versions and check them:
   ```
   /export
   ```

You can also skip the commands entirely and just paste notes into the conversation: the project's `CLAUDE.md` guides Claude Code either way, the commands just make it faster to repeat.
