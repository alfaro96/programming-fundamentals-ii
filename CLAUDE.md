# Repository context

Everything for **Programming Fundamentals II** (Java, second semester of the Computer Science degree; students come from C). Two projects, each with its own `CLAUDE.md` and `README.md` (each has its own commands in `.claude/`: `slides/` to build the slides, `code/` to turn an assignment into LaTeX):

* `slides/`: the lecture slides, a beamer project built with latexmk and XeLaTeX (TeX Live). Read `slides/CLAUDE.md` before touching anything in it.
* `code/`: the JetBrains Academy course with the exercises and assignments. Read `code/CLAUDE.md` before touching anything in it.

At this level there is only what spans both: `README.md` (the course and the repository), `LICENSE` (MIT, for both), `.gitignore`, and `.github/workflows/pages.yml`, which builds the class and print PDFs of every unit and the statement PDFs of its exercises and assignments in a TeX Live image (`scripts/beamer.sh` and `scripts/statements.sh` in `slides/`, the statements from the `latex/` that `/latex` writes in each task of `code/`) and an index page linking them (`npm run build`), and publishes them to GitHub Pages on every push to `main`, or by hand.

## Language

Everything is written in American English, in both projects: slides, speaker notes, code (identifiers, comments, strings and output), `task.md` statements and every README or `CLAUDE.md` (e.g. "color", "behavior", "initialize", never "colour", "behaviour", "initialise").

## Keeping `slides/` and `code/` in step

Both projects follow the same unit order (see the table in `README.md`). How they map:

* **Unit to lesson**: `slides/beamer/units/unit_N-*/main.tex` is the lesson in `code/` whose folder name is the unit's cover title without `Unit N:` (e.g. `unit_1-introduction_java` is `code/Introduction to Java`).
* **Exercises**: the examples live only in the slides; `code/` holds no copy of them. The `edu` and `output` tasks are not paired with slides one to one. They are problems of their own, each solvable once the slides it relies on have been seen (a given slide or the whole unit), so they build on what those slides introduce; they may also need small things the slides do not show (joining text with `+`, a method that returns a value or takes an object, a two-dimensional array), which students are meant to find out by practicing, with the hints the instructor gives: never add slides just to cover them. Neither their names nor their number follow the slides, and a change to the slides never renames or reorders them. A slide may list the exercises that can be solved from that point, each with a link to where it can be found; renaming a task folder may mean updating those links.

A change to the slides has to be carried over to `code/` in the same turn when it touches what `code/` depends on: a unit's cover title (rename the lesson folder and update `content` in `course-info.yaml`), a unit added, removed or reordered, or a change to what a slide introduces that an exercise relies on (an exercise must not use something the slides no longer teach by that point). Changes to the slides' examples, explanations or layout need nothing in `code/`. The other way round, renaming an exercise's folder means updating the slides that link to it. When something can't be kept in step, say so instead of leaving the two out of step.
