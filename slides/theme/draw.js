// Drawing on the slides with the chalkboard plugin (theme/vendor/chalkboard/): a pen and a
// whiteboard, opened with the buttons at the bottom left or with the C and B keys

(function () {
  // The pens, as palette variables of theme/custom.css
  const pens = ["--color-text", "--color-heading", "--color-accent-red", "--color-accent-orange", "--color-accent-teal"];

  const css = (name) => getComputedStyle(document.documentElement).getPropertyValue(name).trim();
  const svg = (paths) => `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">${paths}</svg>`;
  const image = (markup) => "data:image/svg+xml," + encodeURIComponent(markup);

  // A #rrggbb palette color with some transparency, since the plugin paints on a canvas
  function tint(hex, alpha) {
    const [r, g, b] = [1, 3, 5].map((i) => parseInt(hex.slice(i, i + 2), 16));
    return `rgba(${r}, ${g}, ${b}, ${alpha})`;
  }

  const icons = {
    pen: svg('<path d="M12 20h9"/><path d="M16.5 3.5a2.1 2.1 0 0 1 3 3L7 19l-4 1 1-4z"/>'),
    board: svg('<rect x="3" y="3" width="18" height="13" rx="1"/><path d="m8 21 4-5 4 5"/>'),
    eraser: svg('<path d="m7 21-4.3-4.3a1 1 0 0 1 0-1.4l10-10a1 1 0 0 1 1.4 0l5.6 5.6a1 1 0 0 1 0 1.4L11 21z"/><path d="M22 21H7"/><path d="m5 11 9 9"/>')
  };

  // Sets the plugin's options before it starts, since it reads them when it starts
  Reveal.registerPlugin({
    id: "draw",
    init(deck) {
      const heading = css("--color-heading");
      const board = `<svg xmlns="http://www.w3.org/2000/svg" width="1" height="1"><rect width="1" height="1" fill="${css("--color-bg")}"/></svg>`;
      const eraser = icons.eraser.replace("currentColor", heading);
      deck.getConfig().chalkboard = {
        theme: "whiteboard",
        boardHandle: false, // one whiteboard per slide, so no arrows to switch among several
        boardmarkerWidth: 4,
        background: [tint(heading, 0.04), image(board)],
        grid: { color: tint(heading, 0.08), distance: 80, width: 2 },
        eraser: { src: image(eraser), radius: 20 },
        boardmarkers: pens.map((name) => ({ color: css(name), cursor: "crosshair" }))
      };
      // The whiteboard picks its pens from chalks, whatever the theme
      deck.getConfig().chalkboard.chalks = deck.getConfig().chalkboard.boardmarkers;
    }
  });
  Reveal.registerPlugin(RevealChalkboard);

  // The two buttons, pressed while their canvas is open, whichever way it was opened
  function addButtons() {
    const tools = document.createElement("div");
    tools.className = "draw-tools";
    const buttons = [
      ["notescanvas", "Draw on the slide (C)", icons.pen, () => RevealChalkboard.toggleNotesCanvas(), (c) => c.style.pointerEvents === "auto"],
      ["chalkboard", "Whiteboard (B)", icons.board, () => RevealChalkboard.toggleChalkboard(), (c) => c.style.visibility === "visible"]
    ];
    for (const [id, title, icon, toggle, isOpen] of buttons) {
      const button = document.createElement("button");
      button.type = "button";
      button.title = title;
      button.setAttribute("aria-label", title);
      button.innerHTML = icon;
      button.addEventListener("click", toggle);
      tools.appendChild(button);

      const canvas = document.getElementById(id);
      const update = () => button.setAttribute("aria-pressed", String(isOpen(canvas)));
      new MutationObserver(update).observe(canvas, { attributes: true, attributeFilter: ["style"] });
      update();
    }
    Reveal.getRevealElement().appendChild(tools);
  }

  // Not in the PDFs: nobody draws on paper
  Reveal.on("ready", () => {
    if (!Reveal.isPrintView()) addButtons();
  });
})();
