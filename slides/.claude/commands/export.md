---
description: Build every unit's two PDFs (one page per step, as shown in class, and one page per slide for students to print) and check them
---

Build the slides and check the result. Notes from the instructor, if any:

$ARGUMENTS

1. Run `npm run pdf`, which builds both versions of every unit in `beamer/units/`: class (16:9, a page per step) into `production/class/` and print (16:9 too, every step of a slide on one page) into `production/print/`, and the statement of every exercise and assignment in `../code/` into `production/exercises/` and `production/assignments/`.
2. Check that every unit has its PDF in both folders, and that the build log shows no errors or overfull boxes.
3. Check every PDF: every page 16:9, as many print pages as the unit has frames (and the cover), and at least as many class pages as print pages.
4. Look at the pages of the units whose `main.tex` changed (`git status` tells which) with `npm run preview -- class <unit>` and `npm run preview -- print <unit>`.
5. Run `npm run build` and check that `production/web/index.html` has a card with both buttons for every unit, and a link for each of its statements.
6. Report as a short table (unit, class pages, print pages, size) and say plainly anything that failed or couldn't be checked.
