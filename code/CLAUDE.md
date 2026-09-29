# Project context

This is the `code/` project of the **Programming Fundamentals II** repository: the JetBrains Academy course with the examples, exercises and assignments of each unit. The lecture slides are in `../slides/`, with their own `CLAUDE.md`. Every path below is relative to this `code/` folder.

Students open it in IntelliJ IDEA Edu, or IntelliJ IDEA with the EduTools plugin.

## Project layout

* `course-info.yaml`: the course: title, summary, and `content`, the ordered list of lessons.
* One folder per unit (lesson), named after it (`Introduction to Java/`, `Classes, objects, and methods/`...), with a `lesson-info.yaml` whose `content` orders its tasks.
* One folder per task inside each lesson, each with:
  * `task.md`: what the student reads.
  * `task-info.yaml`: the task type and its files.
  * `src/`: the Java sources.
  * `test/`: JUnit tests, for the tasks that are checked.
* `build.gradle`, `settings.gradle`, `gradle/`: Gradle build: Java 17, JUnit 4.12, `src/` and `test/` as source folders of every task.

## Task types

* `theory`: an example to read and run, not checked.
* `edu`: an exercise or assignment checked by the tests in `test/`, which are hidden from the student (`visible: false`).
* `output`: checked by comparing the program's output with the expected one.

## Rules

* **Placeholders are character offsets.** In `edu` tasks, `task-info.yaml` marks the part the student writes with an `offset` and a `length` counted in characters of the source file. Editing that file outside the IDE shifts them: update `offset`, `length` and `placeholder_text` in the same change, or say plainly that the instructor must fix them from EduTools.
* A new task or lesson has to be listed in the `content` of its `lesson-info.yaml` or `course-info.yaml`, or EduTools will not show it.
* All Java code must compile with Java 17 and every `edu` task must pass its own tests with the reference solution.
* Keep the level of the slides: students come from C and meet Java syntax in unit 1. Do not use constructs the unit has not introduced yet.
* `build/`, `out/`, `.gradle/` and `.idea/` are generated and ignored by git; never edit them.

Each lesson mirrors a unit of the slides: see "Keeping `slides/` and `code/` in step" in the repository's `CLAUDE.md` before renaming, adding or reordering anything.

To be completed as work on the code starts.
