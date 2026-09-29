// Marks the print view with every step on one page (the print PDF), so theme/custom.css can drop
// the step highlights there; the class PDF and the screen keep them

(function () {
  function mark() {
    if (Reveal.isPrintView() && Reveal.getConfig().pdfSeparateFragments === false) {
      document.documentElement.classList.add("print-handout");
    }
  }

  // Same hook as theme/cover.js
  if (window.Reveal && typeof Reveal.on === "function") Reveal.on("ready", mark);
})();
