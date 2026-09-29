// Builds every slide marked class="contents": the unit title from the cover, the COURSE.contents subtitle (theme/cover.js, loaded first) and the list of sections, with the one about to start highlighted
//
// data-section: the number of the section about to start; the list is written only in the unit's first contents slide and the others reuse it, e.g. <!-- .slide: class="contents" data-section="2" -->
//
// Generated structure, styled by theme/custom.css:
//   section.contents
//     h2
//     h3
//     ol.contents-focus
//       li.current

(function () {
  function heading(tag, text) {
    const h = document.createElement(tag);
    h.textContent = text;
    return h;
  }

  function buildContents(section, source, unitTitle) {
    if (section.dataset.contentsBuilt) return;
    section.dataset.contentsBuilt = "true";

    let list = section.querySelector("ol");
    if (!list && source) {
      list = source.cloneNode(true);
      section.insertBefore(list, section.firstChild);
    }

    const current = Number(section.dataset.section);
    if (list && current) {
      list.classList.add("contents-focus");
      Array.from(list.children).forEach(function (item, i) {
        item.classList.toggle("current", i + 1 === current);
      });
    }

    section.insertBefore(heading("h3", COURSE.contents), section.firstChild);
    if (unitTitle) section.insertBefore(heading("h2", unitTitle), section.firstChild);
  }

  function buildAll() {
    const source = document.querySelector(".reveal section.contents ol");
    const cover = document.querySelector(".reveal section.cover h2");
    const unitTitle = cover ? cover.textContent : "";

    document.querySelectorAll(".reveal section.contents").forEach(function (section) {
      buildContents(section, source, unitTitle);
    });

    if (window.Reveal && typeof Reveal.layout === "function") Reveal.layout();
  }

  // Same hook as theme/cover.js
  if (window.Reveal && typeof Reveal.on === "function") Reveal.on("ready", buildAll);
})();
