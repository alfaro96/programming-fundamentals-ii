---
description: Export every unit to its two PDFs (one page per step, as shown in class, and one page per slide for students to print) and check them
---

Export the slides and check the result. Notes from the instructor, if any:

$ARGUMENTS

1. Run `npm run pdf`, which builds both versions: class, with a page per step as shown in class, and print, with every step of a slide on one page for students to print.
2. Check that every unit in `content/` has its PDF in `production/class/` and in `production/print/`: Chrome sometimes fails to start and reveal-md does not always say so.
3. Check every PDF: pages of the canvas size in `reveal-md.json5`, as many print pages as the unit has slides, and at least as many class pages as print pages.
4. Look at the pages of the units whose file changed (`git status` tells which) with `npm run preview -- print <unit>` and `npm run preview -- class <unit>`, `<unit>` being the file name without `.md`.
5. Report as a short table (unit, class pages, print pages, size) and say plainly anything that failed or couldn't be checked.
