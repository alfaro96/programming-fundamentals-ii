---
description: Create a new unit file with its cover, contents and summary
---

Create a new unit for Programming Fundamentals II.

Argument (unit number and name, e.g. "7 Inheritance and polymorphism"):
$ARGUMENTS

1. Create `content/unit_N-short_name.md` following the naming convention in `CLAUDE.md`: `unit_`, single-digit number, hyphen, short `snake_case` name (e.g. `unit_7-inheritance_polymorphism.md`).
2. Build it from the templates: the cover (`templates/title.md`, `## Unit N: Unit name` and a subtitle), the first contents slide (`templates/contents.md`) and, last, the summary (`templates/summary.md`). Leave the contents list and the summary cards for the sections the instructor gives; don't invent sections.
3. If the unit will have images, create `content/assets/unit_N-short_name/` for them.
4. Find its lesson in `../code/`: the folder named as the cover title without `Unit N:`. Say whether it exists, and if its name differs from the cover title, point it out instead of renaming anything yet.
5. If the instructor also gave notes for the first slides, add them following the `/new-slide` flow.
6. Confirm in one line: file created, cover heading, and the matching lesson in `../code/`.
