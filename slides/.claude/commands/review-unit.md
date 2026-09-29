---
description: Review a full unit for consistency issues
---

Review the unit file below:

$ARGUMENTS

First run `npm run preview -- screen <unit>` and `npm run preview -- print <unit>` and look at every image. Then check, slide by slide:

* **Fit**: anything cut off, overlapping or wrapping badly, on screen or in the print version.
* **Load**: more than ~5-6 cards or items, or code blocks over ~15-18 lines, that should be split.
* **Structure**: a title and a subtitle on every slide; the contents list and its repeated slides matching the sections; the summary matching what each section teaches.
* **Style**: only palette colors and classes from `theme/custom.css`, no inline styles; no bullet lists where cards fit; American English.
* **Code and emphasis**: code blocks with a language; code, files and commands in code font; bold only on the symbol a card teaches; step highlighting where it helps; synced cards and code steps counting from 0.
* **Speaker notes**: one paragraph per note, no hard line breaks, and saying what the slide actually shows.
* **Components**: something rebuilt by hand that an existing component in `CLAUDE.md` already covers.
* **Code lesson**: mismatches with the unit's lesson in `../code/` (for the full check, `/sync-unit`).

Give me a short list of issues found (slide → issue → suggestion), without fixing anything yet unless I explicitly ask you to.
