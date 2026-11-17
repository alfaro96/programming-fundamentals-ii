---
description: Adjust the global visual style (colors, typography, sizes) of all slides
---

The instructor wants to change the global visual style of the slides. Request:

$ARGUMENTS

1. Edit ONLY `beamer/style.sty`. Within that file, change only the values under "Deck geometry", "Palette" and the size sections; don't touch the templates or the components unless the request is explicitly about those.
2. Don't touch any unit in `beamer/units/`: the styling must stay centralized in one place.
3. Reach for the palette first. When a color the palette lacks is really needed, define one that fits with it as a new named color, never a loose value in a rule, and keep the same color for the same role across slides.
4. If the request is ambiguous (e.g. "make it look more professional" with no further detail), propose 2-3 concrete options built from the palette before applying any, and wait for the instructor to pick one.
5. Check the result: `npm run pdf`, then `npm run preview` in both versions.
6. When done, summarize which values you changed or added and to what, as a short list.
