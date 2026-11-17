---
description: Review a full unit for consistency issues
---

Review this unit (its folder in `beamer/units/`, e.g. `unit_1-introduction_java`):

$ARGUMENTS

First build it (`npm run pdf`), check its log for overfull boxes, and run `npm run preview -- class <unit>` and `npm run preview -- print <unit>`; look at every page. Then check, frame by frame:

* **Fit**: anything cut off, overlapping or wrapping badly, in either version.
* **Load**: more than ~5-6 cards or items, or code blocks over ~15-18 lines, that should be split.
* **Structure**: a title and a subtitle on every frame; a `\section` before each section; the summary matching what each section teaches.
* **Style**: only the components and macros of `style.sty` and `commands.tex`, nothing styled by hand; no bullet lists where cards fit; American English.
* **Code and emphasis**: code blocks with a language; code, files and commands in `\code` inside a sentence, and in plain code font as a label of a diagram or component; `\key` only on the key terms, never on whole sentences; steps where they help; code and cards or notes stepping on the same overlays.
* **Speaker notes**: one `\note` paragraph per frame, saying what the slide actually shows.
* **Components**: something rebuilt by hand that an existing component in `CLAUDE.md` already covers.
* **Code lesson**: mismatches with the unit's lesson in `../code/` (for the full check, `/sync-unit`).

Give me a short list of issues found (frame → issue → suggestion), without fixing anything yet unless I explicitly ask you to.
