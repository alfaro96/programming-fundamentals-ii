---
description: Create a new unit with its cover, sections and summary
---

Create a new unit for Programming Fundamentals II.

Argument (unit number and name, e.g. "6 Event-driven programming"):
$ARGUMENTS

1. Create `beamer/units/unit_N-short_name/main.tex` following the naming convention in `CLAUDE.md`: `unit_`, single-digit number, hyphen, short `snake_case` name (e.g. `unit_6-event_driven_programming`).
2. Start it with the head of unit 1, its `\title{Unit N: Unit name}`, a `\subtitle` and `\input{cover}`; then a `\section` for each section the instructor gives and, last, the summary frame (`\begin{frame}{Unit summary}{What to take away from this unit}` with `\begin{recap}`, one `\recapitem` per section). Don't invent sections.
3. Find its lesson in `../code/`: the folder named as the cover title without `Unit N:`. Say whether it exists, and if its name differs from the cover title, point it out instead of renaming anything yet.
4. If the instructor also gave notes for the first slides, add them following the `/new-slide` flow.
5. Build it (`npm run pdf`) and confirm in one line: folder created, cover title, and the matching lesson in `../code/`.
