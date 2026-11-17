---
description: Create a new slide from the instructor's notes
---

The instructor gives you raw notes about what they want to cover in a Programming Fundamentals II (Java) slide. These can be loose bullets, a half-formed idea, or code they've already written and want to show.

Notes:
$ARGUMENTS

Follow `CLAUDE.md`, above all "Slide types", "Components" and "Expected workflow":

1. Identify which unit this belongs to (`beamer/units/unit_N-*/main.tex`) and which section. If it's unclear, or the unit doesn't exist yet, ask before creating anything.
2. Pick the slide type and copy the frame of that type from a slide that already uses it. If a component from "Components" fits the need, reuse it; build something new in `style.sty` only when nothing fits.
3. Write the frame with a title and a subtitle, only the components and macros of `style.sty` and `commands.tex`, nothing styled by hand, and its speaker notes as one `\note{...}` paragraph.
4. Place it inside its section, before the summary, never after it.
5. If it opens a new section, put its `\section{...}` right before it: the contents slides follow.
6. If it changes what a section teaches, update that section's sentence in the summary (its `\recapitem`).
7. Check the unit's lesson in `../code/`, as the repository's `CLAUDE.md` describes: its exercises only need to stay within what the slides introduce, so change them only if this slide alters something they rely on.
8. Build it (`npm run pdf`) and look at it with `npm run preview` (class and print), then say in one sentence which slide type or component you used and why, what you changed in `../code/`, and what you couldn't check.
