# Programming Fundamentals II

This repository contains the slides and the code for **Programming Fundamentals II**, a second-semester course in the Computer Science degree program.

---

## Course context

This course builds upon Programming Fundamentals I, where students learned procedural programming in C, by transitioning to Java and introducing key object-oriented principles.

Through Java, students learn to structure larger applications and apply reusable design strategies in practical software development.

---

## About this repository

This repository brings together the lectures and the programming components of the course, so that each unit's slides, examples and exercises live side by side. It is organized in two folders:

* `slides/`: beamer project with the lecture slides
* `code/`: JetBrains Academy project with the exercises and assignments

### `slides/`

The lecture slides, one beamer document per unit, compiled with XeLaTeX (TeX Live). It contains:

* **Units**: the content of each lecture, with its speaker notes
* **Theme**: the shared visual style and the components of the whole course
* **Scripts**: the build of every unit to two PDF versions and the index page that links them

Both PDF versions are generated from the same slides:

* **Class**: one page per step, as shown during the lecture
* **Print**: one page per slide, every step visible, for students to print and study

Both PDFs of every unit are published on GitHub Pages on every push, from an index page with a button for each, together with the statement of every exercise and laboratory assignment of `code/`, compiled from its `task.md` in the same style as the slides.

> See `slides/README.md` for how to view, edit and export the slides.

### `code/`

A Gradle project in JetBrains Academy format, with one lesson per unit. It contains:

* **Exercises and assignments** (edu tasks) checked automatically with JUnit tests
* **Output tasks** that compare the program's output with the expected one
* **Course configuration**: `course-info.yaml`, a `lesson-info.yaml` per lesson and a `task-info.yaml` and `task.md` per task

> These materials are designed to be opened in IntelliJ IDEA with the JetBrains Academy plugin.

> Each folder's README (`slides/README.md` and `code/README.md`) lists its own technical requirements.

---

## Modules covered

| Unit   | Topic                           |
|--------|---------------------------------|
| Unit 1 | Introduction to Java            |
| Unit 2 | Classes, objects, and methods   |
| Unit 3 | Inheritance                     |
| Unit 4 | Exceptions                      |
| Unit 5 | Collections                     |
| Unit 6 | Event-driven programming        |

Each unit contains a combination of:

* **Slides** for the lecture
* **Examples** focused on each concept
* **Exercises** with guidance
* **Assignments** based on realistic problems
