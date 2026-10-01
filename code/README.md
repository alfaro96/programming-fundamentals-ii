# Programming Fundamentals II: code

The JetBrains Academy course for **Programming Fundamentals II**, a second-semester course in the Computer Science degree program. It holds the exercises and assignments of each unit; the lecture slides that go with them, with the examples, are in the `slides/` folder of the same repository.

---

## Course context

This course builds upon Programming Fundamentals I, where students learned procedural programming in C, by transitioning to Java and introducing key object-oriented principles.

Through Java, students learn to structure larger applications and apply reusable design strategies in practical software development.

---

## About this project

A Gradle project in JetBrains Academy format, with one lesson per unit. It contains:

* **Exercises and assignments** (edu tasks): checked automatically with JUnit tests
* **Output tasks**: checked by comparing the program's output with the expected one
* **Course configuration**: `course-info.yaml`, a `lesson-info.yaml` per lesson, and a `task-info.yaml` and `task.md` per task

> These materials are designed to be opened in IntelliJ IDEA with the JetBrains Academy plugin.

### Assignment PDFs

Each assignment is also handed out as a PDF, compiled in Overleaf, and the file students start from (`template/` in its task folder). With Claude Code started from this folder:

```
/latex Introduction to Java/First program in Java
```

writes `goals.tex`, `parameters.tex` and `tasks.tex` in the task's `latex/` folder, converted from its `task.md`, ready to paste into the Overleaf template.

---

## Modules covered

| Unit   | Topic                           |
|--------|---------------------------------|
| Unit 1 | Introduction to Java            |
| Unit 2 | Classes, objects, and methods   |
| Unit 3 | Inheritance                     |
| Unit 4 | Exceptions                      |
| Unit 5 | Collections                     |
| Unit 6 | Event-Driven Programming        |

Each unit contains a combination of:

* **Exercises** with guidance
* **Assignments** based on realistic problems

---

## Technical requirements

To run and explore the course material:

* Java 17 or later
* IntelliJ IDEA with the JetBrains Academy plugin
* JetBrains Academy account (optional but recommended)
