---
description: Bring a unit's lesson in `code/` in line with its slides
---

Unit to check (a file in `content/`, e.g. `unit_1-introduction_java.md`):
$ARGUMENTS

Follow "Keeping `slides/` and `code/` in step" in the repository's `CLAUDE.md` and the rules in `../code/CLAUDE.md`.

1. Find the unit's lesson in `../code/` and read its `lesson-info.yaml`, every task's `task-info.yaml`, `task.md` and `src/`.
2. List every mismatch, slide by slide: lesson or task names that no longer match a slide title, topics with no task or tasks with no topic, order, examples whose code differs from the slide, `task.md` text that explains something the slide no longer says, and exercises that use what the unit hasn't introduced.
3. Show that list and wait for the instructor's go-ahead before changing anything in `../code/`.
4. Apply the changes: rename folders, update `content` in `course-info.yaml` and `lesson-info.yaml`, and update `src/` and `task.md`. Say plainly which placeholders need their offsets fixed from EduTools, if any.
