---
description: Turn a task's `task.md` into the three LaTeX files of its PDF (goals, parameters and tasks), ready to paste into Overleaf
---

Task to convert (its folder, e.g. `Introduction to Java/First program in Java`), and notes from the instructor, if any:

$ARGUMENTS

The instructor compiles each assignment's PDF in an Overleaf template that `\input`s three files, and hands it out on the virtual campus with the template from `template/`. Write those three files from `task.md`, with nothing else in them (no preamble, no `\begin{document}`), so their content can be pasted as it is.

1. Read the task's `task.md`. If it has no `## Goals` or `## Tasks` section, say so and stop.
2. Write `latex/` in the task folder (replacing what is there) with:
   * `parameters.tex`: exactly these two lines, with the `#` title of `task.md`:
     ```latex
     % Define document metadata variables for easy reuse
     \def\name{First program in Java} % Assignment name
     ```
   * `goals.tex`: the content of `## Goals`, without its heading.
   * `tasks.tex`: the content of `## Tasks`, without its heading.
3. Make sure `latex/` of that task is listed in `.courseignore` (one line, `<lesson>/<task>/latex`), as `template/` is, so the plugin leaves it out of the course. It is ignored by git: `task.md` is what is kept.
4. Check the result: every `\begin` has its `\end`, every brace is closed, and every LaTeX special character outside math is escaped. Show `goals.tex` and say where the files are.

## How the Markdown becomes LaTeX

The text is the same as in `task.md`, in the same order and with the same case: do not add, drop or reword sentences. Only the markup changes.

**Structure**

* A paragraph followed by a list that introduces it (as in Goals: "...translating basic algorithms." then "By the end of this session...") ends with ` \\` before the blank line.
* `### Heading` becomes `\subsection{Heading}`, with a blank line before and after.
* `1.` lists become `enumerate` and `*` lists become `itemize`, nested as in the Markdown.
* Layout of every list: a blank line after `\begin{...}`, between `\item`s and before `\end{...}`. A top-level `\begin` goes at column 0 and its `\item`s 4 spaces in; a nested `\begin`/`\end` goes 4 spaces further than the `\item` it belongs to, and its `\item`s 4 more. Indent with spaces, never tabs. Paragraphs under a `\subsection` start at column 0.

**Inline markup**

* `` `code` `` becomes `\texttt{code}`: file and folder names, identifiers, keywords, literals, expressions, Java syntax (`\texttt{public static}`, `\texttt{\{3, 4, 13\}}`, `\texttt{/** ... */}`).
* Things the student clicks or types into the IDE, written in backticks in `task.md`, become `\uibutton{...}` instead of `\texttt`: menus, menu items, buttons, options and values typed in a dialog (`\uibutton{New Project}`, `\uibutton{Java}`, `\uibutton{Tools}`, `\uibutton{Generate JavaDoc...}`, `\uibutton{Create}`, the project name `\uibutton{Laboratory assignment 0}`). Paths and file names stay in `\texttt` (`\texttt{/doc}`, `\texttt{index.html}`).
* `<https://...>` and bare URLs become `\url{...}`.
* `$...$` becomes `\(...\)`, keeping the LaTeX inside as it is: `\(n\)`, `\(sum1(n) = \sum_{i = 0}^{n} i\)`. Inside a bold phrase it becomes `\(\boldsymbol{...}\)`, so it is bold too (`**from $0$ to $n$**` gives `\textbf{from} \(\boldsymbol{0}\) \textbf{to} \(\boldsymbol{n}\)`).
* `**bold**` becomes `\textbf{bold}`, and only what `task.md` bolds is bold: add no emphasis of your own. A bold phrase containing code or math is split around it, since `\texttt`, `\uibutton` and `\url` never go inside `\textbf`.
* Escape outside math, also inside `\texttt` and `\uibutton`: `\{ \}`, `\%`, `\_`, `\&`, `\#`, `\$`, `\textasciitilde{}`, `\textasciicircum{}`, and a backslash as `\textbackslash{}` (`\texttt{\textbackslash{}n}`, `\texttt{\%d}`, `\texttt{\{@code sum1\}}`).
* Typographic quotes as ``` ``...'' ```; keep `...` and `--` as written.

**Code blocks**

* `task.md` files have none so far. If one does, ask the instructor which environment the Overleaf template uses before writing it.

## Example

This `## Goals` section:

```markdown
In this laboratory assignment you'll take your first steps in Java, moving from C to the Java development environment. The main focus is on **setting up the workspace** and **translating basic algorithms**.

By the end of this session, you will be able to:

* Configure and navigate through the IDE.
* Print to the console with `System.out.printf`, as in C.
```

becomes this `goals.tex`:

```latex
In this laboratory assignment you'll take your first steps in Java, moving from C to the Java development environment. The main focus is on \textbf{setting up the workspace} and \textbf{translating basic algorithms}. \\

By the end of this session, you will be able to:

\begin{itemize}

    \item Configure and navigate through the IDE.

    \item Print to the console with \texttt{System.out.printf}, as in C.

\end{itemize}
```

And a nested list in `## Tasks`, with a bold phrase that holds math:

```markdown
Write a program that calculates the **sum of the integers from $0$ to $n$** in two ways.

1. **Iterative sum**: write a method named `sum1` that receives an `int` $n$ and returns the sum of the integers from $0$ to $n$: $sum1(n) = \sum_{i = 0}^{n} i$.
    * **Logic**: translate the implementation from C. Initialize a variable to $0$ and use a `for` loop to add the values from $0$ to $n$.
```

```latex
Write a program that calculates the \textbf{sum of the integers from} \(\boldsymbol{0}\) \textbf{to} \(\boldsymbol{n}\) in two ways.

\begin{enumerate}

    \item \textbf{Iterative sum}: write a method named \texttt{sum1} that receives an \texttt{int} \(n\) and returns the sum of the integers from \(0\) to \(n\): \(sum1(n) = \sum_{i = 0}^{n} i\).

        \begin{itemize}

            \item \textbf{Logic}: translate the implementation from C. Initialize a variable to \(0\) and use a \texttt{for} loop to add the values from \(0\) to \(n\).

        \end{itemize}

\end{enumerate}
```
