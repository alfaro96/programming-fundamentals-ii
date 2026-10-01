# Repository context

Everything for **Programming Fundamentals II** (Java, second semester of the Computer Science degree; students come from C). Two projects, each with its own `CLAUDE.md` and `README.md` (each has its own commands in `.claude/`: `slides/` to build the slides, `code/` to turn an assignment into LaTeX):

* `slides/`: the lecture slides, a reveal.js project. Read `slides/CLAUDE.md` before touching anything in it.
* `code/`: the JetBrains Academy course with the exercises and assignments. Read `code/CLAUDE.md` before touching anything in it.

At this level there is only what spans both: `README.md` (the course and the repository), `LICENSE` (MIT, for both), `.gitignore`, and `.github/workflows/pages.yml`, which builds the web version of the slides (`npm run build` in `slides/`) and publishes it to GitHub Pages on every push to `main`, or by hand. The PDFs are exported manually (`npm run pdf`), not by the workflow.

## Language

Everything is written in American English, in both projects: slides, speaker notes, code (identifiers, comments, strings and output), `task.md` statements and every README or `CLAUDE.md` (e.g. "color", "behavior", "initialize", never "colour", "behaviour", "initialise").

## Keeping `slides/` and `code/` in step

Both projects follow the same unit order (see the table in `README.md`). How they map:

* **Unit to lesson**: `slides/content/unit_N-*.md` is the lesson in `code/` whose folder name is the unit's cover title without `Unit N:` (e.g. `unit_1-introduction_java.md` is `code/Introduction to Java`).
* **Exercises**: the examples live only in the slides; `code/` holds no copy of them. The `edu` and `output` tasks are not paired with slides one to one. They are problems of their own, each solvable once the slides it relies on have been seen (a given slide or the whole unit), so they may only use what those slides introduce; neither their names nor their number follow the slides, and a change to the slides never renames or reorders them. A slide may list the exercises that can be solved from that point, each with a link to where it can be found; renaming a task folder may mean updating those links.

A change to the slides has to be carried over to `code/` in the same turn when it touches what `code/` depends on: a unit's cover title (rename the lesson folder and update `content` in `course-info.yaml`), a unit added, removed or reordered, or a change to what a slide introduces that an exercise relies on (an exercise must not use something the slides no longer teach by that point). Changes to the slides' examples, explanations or layout need nothing in `code/`. The other way round, renaming an exercise's folder means updating the slides that link to it. When something can't be kept in step, say so instead of leaving the two out of step.
