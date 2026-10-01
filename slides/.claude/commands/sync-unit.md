---
description: Bring a unit's lesson in `code/` in line with its slides
---

Unit to check (a file in `content/`, e.g. `unit_1-introduction_java.md`):
$ARGUMENTS

Follow "Keeping `slides/` and `code/` in step" in the repository's `CLAUDE.md` and the rules in `../code/CLAUDE.md`.

1. Find the unit's lesson in `../code/` and read its `lesson-info.yaml`, every task's `task-info.yaml`, `task.md` and `src/`.
2. List every mismatch: a lesson name that no longer matches the unit's cover title, links from slides to exercises that no longer exist, and exercises (`edu` and `output`) that use something the unit doesn't introduce by the slide they rely on. Exercises are not paired with slides, so never flag them for their name, their number or their order.
3. Show that list and wait for the instructor's go-ahead before changing anything in `../code/`.
4. Apply the changes: rename the lesson folder and update `content` in `course-info.yaml`, fix the links, and adapt the exercises' `src/`, `test/` and `task.md` (and `template/`, if there is one). Say plainly which placeholders need their offsets fixed from the JetBrains Academy plugin, if any.
