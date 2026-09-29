# Repository context

Everything for **Programming Fundamentals II** (Java, second semester of the Computer Science degree; students come from C). Two independent projects, each with its own `CLAUDE.md`, `.claude/` commands and `README.md`:

* `slides/`: the lecture slides, a reveal.js project. Read `slides/CLAUDE.md` before touching anything in it.
* `code/`: the JetBrains Academy course with the examples, exercises and assignments. Read `code/CLAUDE.md` before touching anything in it.

At this level there is only what spans both: `README.md` (the course and the repository), `LICENSE` (MIT, for both), `.gitignore`, and `.github/workflows/pages.yml`, which publishes the slides and their PDFs to GitHub Pages on every push to `main`, or by hand.

## Keeping `slides/` and `code/` in step

Both projects follow the same unit order (see the table in `README.md`). How they map:

* **Unit to lesson**: `slides/content/unit_N-*.md` is the lesson in `code/` whose folder name is the unit's cover title without `Unit N:` (`unit_1-introduction_java.md` is `code/Introduction to Java`).
* **Topic to theory task**: each topic the slides teach has a `theory` task named as the title of its slide, whose `src/` holds that slide's example and whose `task.md` explains it in the same terms.
* **Order**: the tasks in the lesson's `lesson-info.yaml` follow the order of the slides.
* **Exercises**: the `edu` tasks practise what the unit taught, so they may only use what the slides have already introduced.

Every change to what the slides teach has to be carried over to `code/` in the same turn, not only renames: a unit or slide title, a topic added, removed or reordered, an example's code, and also the content itself (an explanation, a rule, a definition, a note that changes what students should understand). Rename the lesson or task folder, update `content` in `course-info.yaml` or `lesson-info.yaml`, and update `src/` and `task.md` so they say the same as the slide, following `code/CLAUDE.md` (edited placeholders need their offsets fixed). Purely visual changes (layout, colors, animation steps) need nothing in `code/`. The same goes the other way when `code/` changes first. When something can't be mirrored exactly, say so instead of leaving the two out of step.
