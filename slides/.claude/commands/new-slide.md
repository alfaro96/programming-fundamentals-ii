---
description: Create a new reveal.js slide from the instructor's notes
---

The instructor gives you raw notes about what they want to cover in a Programming Fundamentals II (Java) slide. These can be loose bullets, a half-formed idea, or code they've already written and want to show.

Notes:
$ARGUMENTS

Follow `CLAUDE.md`, above all "Slide types", "Components" and "Expected workflow":

1. Identify which unit this belongs to (`content/unit_N-*.md`) and which section. If it's unclear, or the unit doesn't exist yet, ask before creating anything.
2. Pick the slide type and start from its template in `templates/`. If a component from "Components" fits the need, reuse its markup from the slide named there; build something new only when nothing fits.
3. Write the slide with a title and a subtitle, only palette colors and the classes in `theme/custom.css`, no inline styles, and speaker notes as one paragraph each, without hard line breaks.
4. Place it inside its section, before the summary, never after it.
5. If it opens a new section: add the section to the list in the first contents slide, and put a contents slide with `data-section` set to it right before the section.
6. If it changes what a section teaches, update that section's card in the summary.
7. Check the unit's lesson in `../code/`, as the repository's `CLAUDE.md` describes: its exercises only need to stay within what the slides introduce, so change them only if this slide alters something they rely on.
8. Check it with `npm run preview` (and the print version if it has fragments, highlights or a bottom note), then say in one sentence which template or component you used and why, what you changed in `../code/`, and what you couldn't check.
