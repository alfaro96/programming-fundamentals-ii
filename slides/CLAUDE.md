# Project context

You're helping build the slides for **Programming Fundamentals II** (Java), in reveal.js. The instructor will feed you loose notes, jotted-down ideas, or descriptions of what they want to cover, and your job is to turn that into well-formed slides that stay consistent in style and structure with the rest of the course.

You're not just a text-to-markdown converter: you decide which slide type fits best, whether the content should be split across several slides, and where it belongs within the unit. If something is ambiguous, ask before inventing technical content (code that wasn't requested, examples that weren't mentioned, etc.).

## Project layout

This is the `slides/` project of a repository that also holds `code/`, the JetBrains Academy course with the examples and exercises (it has its own `CLAUDE.md`). Every path below is relative to this `slides/` folder, and every command runs from it.

* `content/unit_N-short_name.md`: one file per unit/lecture, e.g. `unit_1-introduction_java.md`. This naming is fixed: lowercase `unit_`, a single-digit number, a hyphen, and a short `snake_case` name. `reveal-md` concatenates the files in alphabetical order, so the number is what keeps the units in order. A single digit allows `unit_0` to `unit_9`; a two-digit unit would sort right after `unit_1`, so if a tenth-plus unit ever appears, say so instead of inventing `unit_10`.
* `content/assets/`: images, one subfolder per unit named as its file (`content/assets/unit_1-introduction_java/`), referenced from the slides as `assets/unit_1-introduction_java/image.png`. Anything shared by every unit goes straight in `content/assets/`. Keep the folder even when empty (`.gitkeep`): `reveal-md.json5` copies it and the build fails without it.
* Within each file, slides are separated by `---` (horizontal) and `--` (vertical, for a sub-slide within the same point, e.g. question + solution).
* `theme/custom.css`: ALL visual styling (colors, background, typography, code block styling) lives here as CSS variables in `:root`. Never put loose styling inside a slide `.md` file; if the instructor asks for a visual change, edit only this file.
* `theme/*.js`: what the slides build at runtime: the cover, the contents slides, the print handout and the drawing buttons (`theme/draw.js`, which sets up the chalkboard plugin).
* `theme/vendor/`: third-party plugins copied as they are, with their license; never edit them, adjust them from `theme/draw.js` and `theme/custom.css`.
* `templates/`: one mold per common slide type in this course. Use them as a starting point and adapt the content; don't copy them literally with placeholders left in.

## Current visual style

Defined in `theme/custom.css`. Everything sits on the slide background, code blocks and terminals included (the slides go through a projector). The instructor's palette is defined once in `:root`, under "Palette", and every other color points at it through a variable named for its role (e.g. `--color-heading` for titles, `--color-accent` for the main accent, `--color-accent-2` for code). The palette may change or grow, so read that block for the current colors instead of assuming them, and refer to colors by role, not by name. Reach for the palette first; when a color it lacks is really needed, define one that fits with it as a named variable in `:root`, never a loose hex in a rule. If the instructor hasn't given styling instructions yet, don't change it: only touch it when explicitly asked. If they ask for a change ("lighter", "different accent color", "make it match X"), edit only the variables in `theme/custom.css`'s `:root`, never rewrite the whole file or touch the structural rules.

## Slide types (`templates/`)

Every slide has a title and a one-line subtitle (`##` then `###`).

1. **Title** (`title.md`): opens a unit with `## Unit N: Unit name` and a subtitle.
2. **Concept** (`concept.md`): an idea or definition as rule cards, one idea per card; never a bullet list.
3. **Code** (`code.md`): the code block is the protagonist. Use step-by-step line highlighting (`[1|3-5]`) when it makes sense to walk through the code incrementally, instead of surrounding it with explanatory text.
4. **Comparison** (`comparison.md`): two versions side by side, all visible at once, with only the lines that differ highlighted (typically C and Java).
5. **Exercise** (`exercise.md`): a challenge or question for the class; the solution goes in a vertical sub-slide (`--`) below it, not on the same slide.
6. **Summary** (`summary.md`): unit wrap-up as a grid of cards, one per section.
7. **Contents** (`contents.md`): the unit's index, repeated before every section with that section highlighted. The list is written once, in the first one; `theme/contents.js` builds the title, the subtitle and the copies.

If the notes don't make it clear which slide type fits, pick the best match and say so explicitly when you're done (e.g. "I built this as a code slide because the focus is on the loop, not the concept"). Don't decide silently.

## Components

Built for a specific need, with no template on purpose: reuse one when the same need comes back, and don't force it where it doesn't fit. Its classes are in `theme/custom.css`; copy the markup from a slide that already uses it (the ones named here are examples from unit 1; if one has changed, search `content/` for the class).

* **Stage** (`stage`, `stage-body`, `stage-top`, `stage-note`): the slide fills the canvas, its block is centered (`stage-body`) or kept at the top (`stage-top`), and the takeaway sits at the bottom (`stage-note`). Used by almost every slide.
* **Code with cards**: a card appears with the lines it explains. The code goes as raw `<pre><code data-fragment-index="0">` and the cards count from 0 too (see the comment in the slide). E.g. "Basic syntax rules" and "Comments".
* **Flow diagram** (`flow`): boxes joined by arrows, with an optional branch. E.g. "Understanding platform dependency" and "The elements of a Java program".
* **Terminals** (`terminals`): consoles of different machines, one under the other, no line numbers. E.g. "Same binary, different machine".
* **Toolbox** (`toolbox`): a block of cards, with an optional block nested inside. E.g. "Working with the Java Development Kit" and "The JDK and the JRE".
* **Verdicts** (`verdicts`): examples judged one by one with ✓, ✗ (struck out) or !. E.g. "Valid or not?" and "Safe or not?".
* **Data table** (`data-table`, `compare-table`): rows revealed by group or one by one. E.g. "The eight primitive types" and "Arrays in C and Java".
* **Keyword grid** (`keywords`): a list of words in columns, some crossed out. E.g. "Reserved words".
* **Widening chain** (`widening`): types joined by arrows in both directions. E.g. "Type conversion".

## Content rules

* Keep text light per slide: if you notice you're adding more than ~5-6 cards or items, or a code block over ~15-18 lines, consider splitting it into several slides (vertical `--` if it's a continuation of the same point, horizontal `---` if it's a new point) instead of shrinking the font or cramming the layout.
* All example Java code must compile and make sense as shown; avoid pseudocode unless the instructor explicitly asks for it.
* Use speaker notes (`Note:` at the end of a slide) for the extended explanation the instructor will say out loud but doesn't want on screen. It's the natural place to park context from raw notes that shouldn't appear on the slide itself.

## Implementation rules (no patches, no anti-patterns)

These are non-negotiable. If a request seems to need one of the things forbidden here, the structure is wrong: stop and say so instead of forcing it.

**Content never lives in CSS.** Never use `content: "some text"` or `content: var(--something)` to put words on a slide. Text set that way can't be selected or copied, doesn't exist for a screen reader, and exports unpredictably to PDF. Slide text belongs in the markdown; text repeated across every unit (e.g. course name, author, degree) belongs in the single config object in `theme/cover.js`. `content: ""` for a purely decorative shape is fine.

**Never fight reveal.js.** Do not use `!important`, do not inflate a selector to outrank a reveal.js rule, and do not override `position`/`display` to undo what reveal.js set. Do not style reveal's own internals (`.reveal .slides > section::after`, `.slide-background`, `.progress`...) beyond the variables already wired up here. Needing any of these means the markup is wrong: add your own class to the slide and style that instead.

**Real layout, not tuned spacing.** Position blocks with flex or grid. A stack of hand-tuned `margin-top` values that happens to land in the right place is a patch: it breaks the moment the text length or the canvas changes.

**No magic numbers.** Any value that positions something (e.g. `29%`, `63px`) gets a named variable in `:root` with a comment saying what it's for. Sizes are `em` against `--slide-font-size` so the deck rescales as one; the only absolute values in the whole theme are the ones under "Deck geometry" and the `--cover-*` sizes and gaps that lay out the cover.

**Comment and formatting style.** Keep comments short: one line saying what something is or warning about a trap, never wrapped by hand and never a paragraph explaining the reasoning. One space before a trailing comment, never padding to align them in a column. Section headers are plain (`/* Base */`) with a blank line either side, not ruled off with dashes. Double quotes, not single. No em dashes.

**One source of truth.** Anything that appears on more than one slide is defined once. Never solve repetition by copy-pasting into each unit file.

**Deck geometry is not CSS.** Canvas size and reveal.js options go in `reveal-md.json5`. Keep that file as JSON plus `//` comments explaining each setting (no trailing commas): `scripts/config.mjs` reads it to size the PDFs and screenshots. If the canvas changes, rescale the values under "Deck geometry" and the `--cover-*` sizes and gaps by that same factor: they are the only absolute lengths in the theme, and everything else is an em of them.

**Verify, then say what you couldn't verify.** Before claiming a change works, look at it: `npm run preview` screenshots every slide in its final state into `production/preview/screen/<unit>/`, and `npm run preview -- print <unit>` or `class` shows the PDF pages. Read the images that the change touches, and check the print version too whenever a slide has fragments, highlights or a bottom note. What screenshots can't show (animation between steps, other browsers) stays unverified: say so plainly and say what to look for.

**`reveal-md.json5` is read once, at startup.** Watch mode doesn't pick it up. Any change to the canvas, scripts or css means restarting `npm run dev`.

## Expected workflow

1. The instructor gives you loose notes or dictates what they want to cover, sometimes via `/new-slide`, sometimes pasted directly in chat.
2. You decide the unit, the slide type, and generate it in its place within the unit: inside its section, before the summary (if the unit doesn't exist yet, create it with `/new-unit` or ask for its number/name before improvising one).
3. The instructor reviews locally (`npm run dev`, see `README.md`) and asks for specific tweaks, to content or style (`/change-style`).
4. In the same turn, bring the unit's lesson in `../code/` in line with what changed, as "Keeping `slides/` and `code/` in step" in the repository's `CLAUDE.md` describes, and say what you changed there. `/sync-unit` checks a whole unit at once.

Don't renumber or reorder existing slides without being explicitly asked: the instructor may already have rehearsed the lecture around that order.
