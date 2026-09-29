// Builds every slide marked class="cover" from the COURSE object below, the only place those strings are defined
//
// data-course, data-author, data-degree: on one slide, replace that field of COURSE, e.g. <!-- .slide: class="cover" data-degree="Master in Computer Science" -->
//
// Generated structure, styled by theme/custom.css:
//   section.cover
//     div.cover-frame
//       div.cover-head
//         p.cover-course
//         h2
//         h3
//       div.cover-meta
//         p.cover-author
//         p.cover-degree

const COURSE = {
  name: "Programming Fundamentals II",
  author: "Juan Carlos Alfaro Jiménez",
  degree: "Degree in Computer Science",
  contents: "Contents" // subtitle of every contents slide, see theme/contents.js
};

(function () {
  function line(className, text) {
    const p = document.createElement("p");
    p.className = className;
    p.textContent = text;
    return p;
  }

  function buildCover(section) {
    if (section.dataset.coverBuilt) return;
    section.dataset.coverBuilt = "true";

    const head = document.createElement("div");
    head.className = "cover-head";
    head.appendChild(line("cover-course", section.dataset.course || COURSE.name));

    const title = section.querySelector("h2");
    const subtitle = section.querySelector("h3");
    if (title) head.appendChild(title);
    if (subtitle) head.appendChild(subtitle);

    const meta = document.createElement("div");
    meta.className = "cover-meta";
    meta.appendChild(line("cover-author", section.dataset.author || COURSE.author));
    meta.appendChild(line("cover-degree", section.dataset.degree || COURSE.degree));

    // The frame fills the canvas and centers the text with grid
    const frame = document.createElement("div");
    frame.className = "cover-frame";
    frame.appendChild(head);
    frame.appendChild(meta);
    section.insertBefore(frame, section.firstChild);
  }

  function buildAll() {
    document.querySelectorAll(".reveal section.cover").forEach(buildCover);

    // layout() measured the slides before they were built
    if (window.Reveal && typeof Reveal.layout === "function") Reveal.layout();
  }

  // The sections only exist once reveal.js is ready: reveal-md turns the markdown into slides during Reveal.initialize
  if (window.Reveal && typeof Reveal.on === "function") Reveal.on("ready", buildAll);
})();
