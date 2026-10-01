<!-- .slide: class="cover" -->

## Unit 1: Introduction to Java

### The main elements of the Java language

Note:
First real unit. They come from Programming Fundamentals I in C, so every new idea here is framed against what they already know from C.

---

<!-- .slide: class="contents" data-section="1" -->

1. How Java runs
2. Development tools
3. A first Java program
4. Variables and primitive types
5. Arrays
6. Control structures

Note:
Walk through the index quickly; the details come in each section.

---

<div class="stage">

## Platform dependency

### The limits of compiling to machine code

<div class="stage-body flow-layers">
<div class="flow">
<div class="flow-box flow-source">
<strong>Source code</strong>
<code>hello.c</code>
</div>
<div class="flow-arrow flow-long fragment" data-fragment-index="1">
<span class="flow-label"><svg class="flow-icon" viewBox="0 0 24 24" role="img" aria-label="Run"><path d="M7 4v16l13-8z"/></svg></span>
<span class="flow-shaft"></span>
</div>
<div class="flow-box flow-ok fragment" data-fragment-index="1">
<strong>Successful</strong>
<code>Hello, world!</code>
</div>
</div>
<p class="flow-divider fragment" data-fragment-index="2">Under the hood</p>
<div class="flow">
<div class="flow-box flow-source fragment" data-fragment-index="2">
<strong>Source code</strong>
<code>hello.c</code>
</div>
<div class="flow-arrow fragment" data-fragment-index="3">
<span class="flow-label">Compile</span>
<span class="flow-shaft"></span>
<code class="flow-command">gcc hello.c -o hello</code>
</div>
<div class="flow-box flow-output fragment" data-fragment-index="3">
<strong>Machine code</strong>
<code>hello</code>
</div>
<div class="flow-arrow fragment" data-fragment-index="4">
<span class="flow-label">Run</span>
<span class="flow-shaft"></span>
<code class="flow-command">./hello</code>
</div>
<div class="flow-box flow-ok fragment" data-fragment-index="4">
<strong>Successful</strong>
<code>Hello, world!</code>
</div>
<div class="flow-elbow flow-dashed fragment" data-fragment-index="5"></div>
<div class="flow-arrow flow-dashed flow-lower fragment" data-fragment-index="5">
<span class="flow-label">Run on a different architecture</span>
<span class="flow-shaft"></span>
<code class="flow-command">./hello</code>
</div>
<div class="flow-box flow-error flow-lower fragment" data-fragment-index="6">
<strong>Error</strong>
<code>Exec format error</code>
</div>
</div>
</div>
<blockquote class="stage-note fragment" data-fragment-index="7">
The primary drawback of a <strong>compiled language</strong> is that a <strong>binary file</strong> only works on <strong>one particular architecture</strong>.
</blockquote>

</div>

Note:
Start from what they already do: hello.c. Step 1, in their IDE they press the green Run button and "Hello, world!" appears, as if running the source file directly. Step 2, under the hood that button does two things. Step 3, first it compiles: gcc translates the C file straight into machine code for the processor it runs on, here an ordinary x86-64 laptop, and leaves an executable file. Step 4, then it runs that file, and on the same kind of machine it works. Step 5, take that very same executable to a machine with a different processor (an ARM board, a Mac with Apple Silicon) and ask them what they think will happen. Step 6, it does not even start: the instructions inside are not ones that processor understands. Step 7, the takeaway: to run on another architecture you have to recompile for it, once per platform.

---

<div class="stage">

## Platform independence

### Compiling once for a virtual machine

<div class="flow stage-body">
<div class="flow-box flow-source">
<strong>Source code</strong>
<span class="flow-detail">Written once</span>
</div>
<div class="flow-arrow fragment" data-fragment-index="1">
<span class="flow-label">Compile</span>
<span class="flow-shaft"></span>
</div>
<div class="flow-box flow-output fragment" data-fragment-index="1">
<strong>Bytecode</strong>
<span class="flow-detail">For a virtual machine</span>
</div>
<div class="flow-arrow fragment" data-fragment-index="2">
<span class="flow-label">Run</span>
<span class="flow-shaft"></span>
</div>
<div class="flow-box flow-ok fragment" data-fragment-index="2">
<strong>Successful</strong>
<code>Hello, world!</code>
</div>
<div class="flow-elbow flow-dashed fragment" data-fragment-index="3"></div>
<div class="flow-arrow flow-dashed flow-lower fragment" data-fragment-index="3">
<span class="flow-label">Run on a different architecture</span>
<span class="flow-shaft"></span>
</div>
<div class="flow-box flow-ok flow-lower fragment" data-fragment-index="4">
<strong>Successful</strong>
<code>Hello, world!</code>
</div>
</div>
<blockquote class="stage-note fragment" data-fragment-index="5">
<strong>Bytecode</strong> runs on every platform with its <strong>virtual machine</strong>: compile once, run anywhere.
</blockquote>

</div>

Note:
Same diagram as for C, on purpose, and valid for any language that works this way. Step 1, the compiler does not produce machine code but bytecode: instructions for a machine that does not physically exist, a virtual machine. Step 2, running it starts the virtual machine installed on that computer, which translates the bytecode for the real processor. Step 3, the same file goes to a machine with a different processor: ask them what they expect after the C case. Step 4, this time it works, because that machine has its own virtual machine; what is platform-specific now is the virtual machine, not our program. Step 5, the takeaway.

---

<div class="stage">

## Java's execution model

### Source code, bytecode and the JVM

<div class="stage-body flow-layers">
<div class="flow">
<div class="flow-stack">
<div class="flow-box flow-source">
<strong>Source code</strong>
<code>Hello.java</code>
</div>
<p class="flow-caption">Plain text file that we write. It must contain a <code>main</code>, as in C.</p>
</div>
<div class="flow-arrow fragment" data-fragment-index="1">
<span class="flow-label">Compile</span>
<span class="flow-shaft"></span>
<code class="flow-command">javac Hello.java</code>
</div>
<div class="flow-stack fragment" data-fragment-index="1">
<div class="flow-box flow-output">
<strong>Bytecode</strong>
<code>Hello.class</code>
</div>
<p class="flow-caption">Platform-independent instructions, generated by <code>javac</code>.</p>
</div>
<div class="flow-arrow fragment" data-fragment-index="2">
<span class="flow-label">Run</span>
<span class="flow-shaft"></span>
<code class="flow-command">java Hello</code>
</div>
<div class="flow-stack fragment" data-fragment-index="2">
<div class="flow-box flow-ok">
<strong>Output</strong>
<code>Hello, world!</code>
</div>
<p class="flow-caption">The result the user sees.</p>
</div>
</div>
<p class="flow-divider fragment" data-fragment-index="3">On the surface</p>
<div class="flow">
<div class="flow-box flow-source fragment" data-fragment-index="3">
<strong>Source code</strong>
<code>Hello.java</code>
</div>
<div class="flow-arrow flow-long fragment" data-fragment-index="3">
<span class="flow-label"><svg class="flow-icon" viewBox="0 0 24 24" role="img" aria-label="Run"><path d="M7 4v16l13-8z"/></svg></span>
<span class="flow-shaft"></span>
</div>
<div class="flow-box flow-ok fragment" data-fragment-index="3">
<strong>Output</strong>
<code>Hello, world!</code>
</div>
</div>
</div>

</div>

Note:
The model of the previous slide, with Java's names: the three files and the two commands they will use in the lab. Start with the source: a plain text file they can write in any editor, and like a C program it starts in main. Step 1, javac compiles it into Hello.class, the bytecode. Step 2, java Hello starts the JVM, Java's virtual machine, and runs it: note it is `java Hello`, without `.class`. Step 3, now the other way round from the first slide, what they see on the surface: the Run button of the IDE goes straight from Hello.java to the output, but it does the two steps above for them, so the bytecode is generated even if they never look at it (IntelliJ IDEA leaves Hello.class in the project's output folder). What goes inside Hello.java comes in the third section.

---

<!-- .slide: class="contents" data-section="2" -->

Note:
The first section is done: they know why bytecode for a virtual machine makes a program portable, and that Java works this way. Now the tools they will actually type every day.

---

<div class="stage">

## The Java Development Kit

### The tools for developing Java programs

<div class="toolbox stage-body">
<div class="toolbox-head">
<p class="toolbox-label">Java Development Kit<span>JDK</span></p>
<p class="toolbox-desc">Everything needed to <strong>write, build and run</strong> Java programs, in one install.</p>
</div>
<div class="toolbox-group fragment" data-fragment-index="1">
<p class="toolbox-group-name">Build and run</p>
<div class="tool"><code>javac</code><span>Compiles <code>.java</code> source into <code>.class</code> bytecode</span></div>
<div class="tool"><code>java</code><span>Starts the JVM and runs the program</span></div>
</div>
<div class="toolbox-group fragment" data-fragment-index="2">
<p class="toolbox-group-name">Share</p>
<div class="tool"><code>jar</code><span>Bundles the program into one file (<code>.jar</code>)</span></div>
<div class="tool"><code>javadoc</code><span>Turns comments into documentation web pages</span></div>
</div>
<div class="toolbox-group fragment" data-fragment-index="3">
<p class="toolbox-group-name">Explore and fix</p>
<div class="tool"><code>jshell</code><span>Tries out Java code line by line, instantly</span></div>
<div class="tool"><code>jdb</code><span>Debugs a running program step by step</span></div>
</div>
</div>

</div>

Note:
The JDK is what they install to write Java, not only to run it. Step 1, the two they already met: javac and java. Step 2, the tools to hand the program to someone else: jar bundles everything into one file, javadoc turns the /** */ comments into a web page like the official API docs. Step 3, jshell to test a line without writing a whole program, jdb to stop and inspect a program (in practice they will debug from the IDE, which uses the same machinery).

---

<div class="stage">

## The JDK and the JRE

### What you need to develop and what you need to run

<div class="toolbox stage-body">
<p class="toolbox-label">Java Development Kit<span>JDK</span></p>
<div class="toolbox-runtime fragment" data-fragment-index="1">
<div class="toolbox-head">
<p class="toolbox-label">Java Runtime Environment<span>JRE</span></p>
<p class="toolbox-desc">Everything needed to <strong>run</strong> Java programs, but not to build them.</p>
</div>
<div class="tool fragment" data-fragment-index="2"><strong>JVM</strong><span>The virtual machine: runs the bytecode on the real processor</span></div>
<div class="tool fragment" data-fragment-index="3"><strong>Standard library</strong><span>Ready-made code to reuse, like <code>printf</code> or <code>sqrt</code> in C</span></div>
</div>
</div>
<blockquote class="stage-note fragment" data-fragment-index="4">
To <strong>run</strong> a Java program you only need the <strong>JRE</strong>; to <strong>develop</strong> one you need the <strong>JDK</strong>, which already contains it.
</blockquote>

</div>

Note:
Start from the JDK they just saw; its tools are the previous slide. Step 1, inside it there is a smaller block, the JRE: the part needed only to run programs. Step 2, the JVM from the first section, which executes the bytecode. Step 3, the standard library, ready-made code their programs use without them having to write it. Anchor it in C: they never wrote printf (stdio.h) or sqrt (math.h), they came with the language. Java has the same idea, only much bigger. Step 4, the takeaway. Since Java 11 Oracle no longer ships a separate JRE; some distributions (Eclipse Temurin, for instance) still offer one, but for this course they only need to install the JDK, and the JRE comes inside.

---

<!-- .slide: class="contents" data-section="3" -->

Note:
They know the tools; now they write their first Java program, next to the C version they know: printing and comments.

---

<div class="stage">

## Our first program: `Hello.java`

### The same program in C and in Java

<div class="code-over-cards stage-top">
<div class="columns code-pair">
<div class="column">

#### C: `hello.c`

<!-- C alone on entry, then Java whole at step 0; from step 1 both blocks and the cards go together -->
<pre><code class="c" data-trim data-line-numbers="||3,5-6|1,4" data-fragment-index="1">
#include &amp;lt;stdio.h&amp;gt;

int main(void) {
    printf("Hello, world!\n");
    return 0;
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### Java: `Hello.java`

<pre><code class="java" data-trim data-line-numbers="|1,5|2,4|3" data-fragment-index="1">
public class Hello {
    public static void main(String[] args) {
        System.out.printf("Hello, world!\n");
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-pair">
<div class="rule fragment" data-fragment-index="1">
<p><strong>The class</strong>New in Java: the program sits in a <strong>class</strong> named as its <strong>file</strong>, capitals included (<code>Hello.java</code>, not <code>hello.java</code>). What a class is, next unit.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong><code>main</code></strong>The <strong>entry point</strong>, as in C, but with <strong>no <code>return 0</code></strong>. Always written the same.</p>
</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong><code>printf</code></strong><strong>The same as in C</strong>, through <code>System.out</code> and with no <code>#include</code>.</p>
</div>
</div>
</div>

</div>

Note:
The program they wrote on their first day of C, next to its Java version: the same output, and the differences are what they have to learn. Each step lights up the lines that go together in both programs. On entry, only the C program they know: let them read it and say what each line does. Step 1, its Java version appears whole: this is the Java code, now let's go step by step. Step 2, the class: Java wraps everything in a class, C has nothing like it, so the whole C side is dimmed. For now the only thing they need is the naming rule, Hello in the code and Hello.java on disk, with the same capital letter: Java is case-sensitive, so a class Hello saved as hello.java does not compile. What a class is, the whole next unit. Step 3, main: where the program starts in both, but Java's returns nothing (void), so there is no return 0; the words around it (public, static, String[] args) come later too, for now its first line is copied as it is. Step 4, printing: the very same printf, format and \n included, only called through System.out; in C it needs the #include of stdio.h, in Java System.out is always available. The next slide shows that Java has two more ways to print, and which one is used most.

--

<div class="stage">

## Printing to the screen

### Three ways to write with `System.out`

<div class="runs stage-body">
<div class="rule">
<p><strong><code>print</code></strong>Prints the text and <strong>stays on the same line</strong>.</p>
</div>
<div>

```java
System.out.print("Hello, ");
System.out.print("world!");
```

</div>
<div class="fragment" data-fragment-index="1">

```console
Hello, world!
```

</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong><code>println</code></strong>Prints the text and <strong>ends the line</strong>.</p>
</div>
<div class="fragment" data-fragment-index="2">

```java
System.out.println("Hello,");
System.out.println("world!");
```

</div>
<div class="fragment" data-fragment-index="3">

```console
Hello,
world!
```

</div>
<div class="rule fragment" data-fragment-index="4">
<p><strong><code>printf</code></strong>Prints with a <strong>format</strong>, as in C.</p>
</div>
<div class="fragment" data-fragment-index="4">

```java
System.out.printf("%d + %d = %d\n", 2, 3, 2 + 3);
```

</div>
<div class="fragment" data-fragment-index="5">

```console
2 + 3 = 5
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="6">
Three ways, but the one used most is <strong><code>println</code></strong>: it <strong>ends the line</strong> with no <code>\n</code>.
</blockquote>

</div>

Note:
The three ways they will use to write to the console; the lines go inside main, like the printf of Hello.java. Each method comes with its code first: ask them what it prints before showing the output. On entry, print: it writes the text and the cursor stays right after it. Step 1, its output: the space inside "Hello, " is the only thing between the two words, because print adds nothing. Step 2, println, the one they will use most: the same, plus a line break at the end, so they never write \n by hand. Step 3, the same two texts on two lines. Step 4, printf, C's printf with the same placeholders, filled in order with the values after the text; the line break is \n, as in C (Java also has %n, which writes the right one for each operating system). Step 5, 2 + 3 is worked out before printing. Step 6, the takeaway: printf is the one they know from C, but the everyday choice in Java is println, since most of the time they just want a line of text.

---

<div class="stage">

## Comments

### The two from C, plus one for documentation

<div class="code-over-cards stage-top">
<div class="columns code-pair">
<div class="column">

#### C: `comments.c`

<!-- Both blocks and the cards step together from 0; C has nothing to mark in the last step -->
<pre><code class="c" data-trim data-line-numbers="4|6-7|" data-fragment-index="0">
#include &amp;lt;stdio.h&amp;gt;

int main(void) {
    // Up to the end of the line
    printf("Hello, ");
    /* Everything between
       the two marks */
    printf("world!\n");
    return 0;
}
</code></pre>

</div>
<div class="column">

#### Java: `Comments.java`

<pre><code class="java" data-trim data-line-numbers="4|6-7|1" data-fragment-index="0">
/** Prints a greeting. */
public class Comments {
    public static void main(String[] args) {
        // Up to the end of the line
        System.out.print("Hello, ");
        /* Everything between
           the two marks */
        System.out.println("world!");
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-three">
<div class="rule">
<p><strong>Single-line <code>//</code></strong>From <strong><code>//</code></strong> to the end of the line, <strong>as in C</strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Multi-line <code>/* */</code></strong>Everything between <strong><code>/*</code></strong> and <strong><code>*/</code></strong>, <strong>as in C</strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Documentation <code>/** */</code></strong>New in Java: <code>javadoc</code> turns it into <strong>web documentation</strong>.</p>
</div>
</div>
</div>

</div>

Note:
Comments are something the compiler ignores, like spaces and indentation: they are notes for whoever reads the code. The same program in C and in Java, compared from the start. On entry, the single-line comment in both: from // to the end of the line, exactly as in C. Step 1, the multi-line one, also the same in both: everything between /* and */. Step 2, the new one, only in Java, so the C side dims: /** */ above the class. It is still a comment for the compiler, but javadoc, the JDK tool they saw, reads it to generate web documentation like the official Java API pages.

---

<!-- .slide: class="contents" data-section="4" -->

Note:
They can write a first program; now the values it works with: the primitive types, how to declare and name variables, and how to move a value from one type to another.

---

<div class="stage">

## The eight primitive types

### The basic values Java works with

<table class="data-table stage-body">
<thead>
<tr><th>Category</th><th>Type</th><th>Storage</th><th>Range</th><th>Note</th></tr>
</thead>
<tbody>
<tr><td>Logical</td><th scope="row"><code>boolean</code></th><td>Up to the JVM</td><td><code>true</code> or <code>false</code></td><td class="fragment custom" data-fragment-index="0">Not a number: <code>0</code> and <code>1</code> are not booleans, unlike C</td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="1">
<tr><td rowspan="4">Integer</td><th scope="row"><code>byte</code></th><td>8 bits</td><td>−2<sup>7</sup> to 2<sup>7</sup> − 1 (−128 to 127)</td><td class="fragment custom" data-fragment-index="2">Saves memory in large arrays</td></tr>
<tr><th scope="row"><code>short</code></th><td>16 bits</td><td>−2<sup>15</sup> to 2<sup>15</sup> − 1 (−32,768 to 32,767)</td><td class="fragment custom" data-fragment-index="2">Saves memory in large arrays</td></tr>
<tr><th scope="row"><code>int</code></th><td>32 bits</td><td>−2<sup>31</sup> to 2<sup>31</sup> − 1 (about ±2.1 billion)</td><td>The default for whole numbers</td></tr>
<tr><th scope="row"><code>long</code></th><td>64 bits</td><td>−2<sup>63</sup> to 2<sup>63</sup> − 1 (about ±9.2 × 10<sup>18</sup>)</td><td>Values end in <code>L</code></td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="3">
<tr><td rowspan="2">Decimal</td><th scope="row"><code>float</code></th><td>32 bits</td><td>About ±3.4 × 10<sup>38</sup>, 7 significant digits</td><td>Values end in <code>f</code></td></tr>
<tr><th scope="row"><code>double</code></th><td>64 bits</td><td>About ±1.8 × 10<sup>308</sup>, 15 significant digits</td><td>The default for decimals</td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="4">
<tr><td>Character</td><th scope="row"><code>char</code></th><td>16 bits</td><td>One Unicode character, from 0 to 65,535</td><td>A number underneath: its <a href="https://symbl.cc/en/unicode-table/">Unicode code</a></td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="5">
Unlike C, every size is <strong>fixed</strong>: an <code>int</code> has 32 bits on <strong>every platform</strong>, which is part of Write Once, Run Anywhere.
</blockquote>

</div>

Note:
Eight types, the same family as in C. On entry, boolean and its two values: a real type of its own, not an int in disguise; its size in memory is up to the JVM. Step 1, why it matters: in C, 0 and 1 work as false and true, in Java they do not, so `if (1)` will not compile. Step 2, the four integers, from smallest to largest; int is the one they will use by default, long when the number does not fit. Ask them why byte and short exist if int is the default. Step 3, the answer: memory, when there are many values, as in a large array. Step 4, the two decimals; double is the default, float only when memory matters. Step 5, char: 16 bits, not 8 as in C, because it stores Unicode, so accents and ñ fit, and underneath it is just a number, its code in the Unicode table: open the link and show that 'A' is 65, 'a' is 97 and 'ñ' is 241 (the same codes as ASCII for the first 128). That is why a char converts to an int, as Type conversion shows. Step 6, the link with the first section: in C an int can be 16 or 32 bits depending on the machine; in Java it never changes.

--

## Declaring variables

### Type, name and value, as in C

```java [1|2-4|5-6|7]
boolean passed = true;
int age = 20;
long population = 8100000000L;
byte level = 3;
double price = 19.99;
float ratio = 0.5f;
char grade = 'A';
```

Note:
Nothing new if they remember C: the type, the name, and optionally = and a value, ending in a semicolon. Step by step, one line per group of the table. The names already follow Java's conventions, which the next slide explains. Step 2, integers: the L at the end of population, because that number does not fit in an int. Step 3, decimals: the f of float, because a decimal without it is a double. Step 4, char: single quotes, one character.

---

<div class="stage">

## Naming variables

### Rules and conventions

<div class="columns stage-top">
<div class="column rules">

#### Rules: break one and it does not compile

<div class="rule">
<p><strong>Characters</strong>Letters, digits, <strong><code>_</code></strong> and <strong><code>$</code></strong>, never spaces.</p>
</div>
<div class="rule">
<p><strong>First character</strong>Never a digit.</p>
</div>
<div class="rule">
<p><strong>Reserved words</strong>Java keeps some words for itself: they cannot be used as names.</p>
</div>
<div class="rule">
<p><strong>Case-sensitive</strong>Uppercase and lowercase letters are different.</p>
</div>

</div>
<div class="column rules fragment" data-fragment-index="0">

#### Conventions: it compiles, but professionals follow them

<div class="rule">
<p><strong>Variables</strong><strong><code>camelCase</code></strong>: first word lowercase, the rest capitalized.</p>
</div>
<div class="rule">
<p><strong>Constants</strong><strong><code>UPPER_SNAKE_CASE</code></strong>: all capitals, words joined by <code>_</code>.</p>
</div>
<div class="rule">
<p><strong>Meaningful names</strong>The name says what the variable stores.</p>
</div>

</div>
</div>

</div>

Note:
They have just declared variables; now the rules and conventions behind their names, as they did in C. Left column, the rules: the compiler checks them, and breaking one is a compile error; they are the same as in C, plus $. The reserved words get their own slide next. Step 1, right column, the conventions: the compiler does not care, but every Java programmer follows them, so code written by anyone reads the same. The same rules apply to every name in a program, which they will see as the course goes on.

--

<div class="stage">

## Reserved words

### Words that Java keeps for itself

<ul class="keywords stage-body">
<li><code>abstract</code></li>
<li class="fragment custom keyword-unused" data-fragment-index="0"><code>assert</code></li>
<li><code>boolean</code></li>
<li><code>break</code></li>
<li><code>byte</code></li>
<li><code>case</code></li>
<li><code>catch</code></li>
<li><code>char</code></li>
<li><code>class</code></li>
<li class="fragment custom keyword-unused" data-fragment-index="0"><code>const</code></li>
<li><code>continue</code></li>
<li><code>default</code></li>
<li><code>do</code></li>
<li><code>double</code></li>
<li><code>else</code></li>
<li><code>enum</code></li>
<li><code>extends</code></li>
<li><code>final</code></li>
<li><code>finally</code></li>
<li><code>float</code></li>
<li><code>for</code></li>
<li class="fragment custom keyword-unused" data-fragment-index="0"><code>goto</code></li>
<li><code>if</code></li>
<li><code>implements</code></li>
<li><code>import</code></li>
<li><code>instanceof</code></li>
<li><code>int</code></li>
<li><code>interface</code></li>
<li><code>long</code></li>
<li class="fragment custom keyword-unused" data-fragment-index="0"><code>native</code></li>
<li><code>new</code></li>
<li><code>package</code></li>
<li><code>private</code></li>
<li><code>protected</code></li>
<li><code>public</code></li>
<li><code>return</code></li>
<li><code>short</code></li>
<li><code>static</code></li>
<li class="fragment custom keyword-unused" data-fragment-index="0"><code>strictfp</code></li>
<li><code>super</code></li>
<li><code>switch</code></li>
<li class="fragment custom keyword-unused" data-fragment-index="0"><code>synchronized</code></li>
<li><code>this</code></li>
<li><code>throw</code></li>
<li><code>throws</code></li>
<li class="fragment custom keyword-unused" data-fragment-index="0"><code>transient</code></li>
<li><code>try</code></li>
<li><code>void</code></li>
<li class="fragment custom keyword-unused" data-fragment-index="0"><code>volatile</code></li>
<li><code>while</code></li>
</ul>

<blockquote class="stage-note fragment" data-fragment-index="0">
The crossed-out words exist, but we will <strong>not use</strong> them in this course.
</blockquote>

</div>

Note:
No need to memorize the table: the IDE colors these words, and the compiler complains if one is used as a name. Most of them will appear during the course. Step 1: the ones crossed out are for concurrency, native code or other advanced uses we will not cover, plus goto and const, which are reserved but Java never gives them a meaning: writing them does not compile. Constants are declared with final instead. Not in the table but also off limits: true, false and null, which are literal values, and _ (a single underscore) since Java 9.

--

<div class="stage">

## Valid or not?

### Does it compile? Would a professional write it?

<table class="data-table verdict-table stage-body">
<thead>
<tr><th>Name</th><th>Compiles?</th><th>Convention?</th><th>Why</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>totalPrice</code></th><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="fragment custom" data-fragment-index="0">The convention for variables</td></tr>
<tr><th scope="row"><code>2ndPlace</code></th><td class="mark mark-error fragment custom" data-fragment-index="1">✗</td><td class="mark mark-none fragment custom" data-fragment-index="1">–</td><td class="fragment custom" data-fragment-index="1">Starts with a digit</td></tr>
<tr><th scope="row"><code>class</code></th><td class="mark mark-error fragment custom" data-fragment-index="2">✗</td><td class="mark mark-none fragment custom" data-fragment-index="2">–</td><td class="fragment custom" data-fragment-index="2">A reserved word</td></tr>
<tr><th scope="row"><code>first name</code></th><td class="mark mark-error fragment custom" data-fragment-index="3">✗</td><td class="mark mark-none fragment custom" data-fragment-index="3">–</td><td class="fragment custom" data-fragment-index="3">Contains a space</td></tr>
<tr><th scope="row"><code>player2</code></th><td class="mark mark-ok fragment custom" data-fragment-index="4">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="4">✓</td><td class="fragment custom" data-fragment-index="4">Digits are fine after the first character</td></tr>
<tr><th scope="row"><code>total_price</code></th><td class="mark mark-ok fragment custom" data-fragment-index="5">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="5">✗</td><td class="fragment custom" data-fragment-index="5">The convention is <code>totalPrice</code></td></tr>
<tr><th scope="row"><code>MAX_SPEED</code></th><td class="mark mark-ok fragment custom" data-fragment-index="6">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="6">✓</td><td class="fragment custom" data-fragment-index="6">The convention for constants</td></tr>
<tr><th scope="row"><code>a</code></th><td class="mark mark-ok fragment custom" data-fragment-index="7">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="7">✗</td><td class="fragment custom" data-fragment-index="7">Says nothing about what it stores</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="8">
In this course, names that break the <strong>conventions</strong> are <strong>penalized</strong>, even if they compile.
</blockquote>

</div>

Note:
All the names are on screen from the start; ask the class about each one before filling in its row: does it compile? and if it does, does it follow the convention? A cross under Compiles means it breaks a rule and the compiler rejects it, so the convention does not even apply. A cross under Convention, in orange, means it compiles but a professional would not write it that way. Those two rows are the important nuance: the compiler accepting a name does not make it a good one. Step 8, the course rule: in assignments and exams, names that break the conventions lose marks.

---

<div class="stage">

## Type conversion

### From one primitive type to another

<div class="stage-body">
<div class="widening">
<code>byte</code><span class="widening-arrows"><span class="flow-shaft widening-safe fragment" data-fragment-index="0"></span><span class="flow-shaft flow-shaft-back widening-cast fragment" data-fragment-index="1"></span></span><code>short</code><span class="widening-arrows"><span class="flow-shaft widening-safe fragment" data-fragment-index="0"></span><span class="flow-shaft flow-shaft-back widening-cast fragment" data-fragment-index="1"></span></span><code>int</code><span class="widening-arrows"><span class="flow-shaft widening-safe fragment" data-fragment-index="0"></span><span class="flow-shaft flow-shaft-back widening-cast fragment" data-fragment-index="1"></span></span><code>long</code><span class="widening-arrows"><span class="flow-shaft widening-safe fragment" data-fragment-index="0"></span><span class="flow-shaft flow-shaft-back widening-cast fragment" data-fragment-index="1"></span></span><code>float</code><span class="widening-arrows"><span class="flow-shaft widening-safe fragment" data-fragment-index="0"></span><span class="flow-shaft flow-shaft-back widening-cast fragment" data-fragment-index="1"></span></span><code>double</code>
</div>

<div class="rules rules-grid rules-pair">
<div class="rule fragment" data-fragment-index="0">
<p><strong>Widening: automatic</strong>The value always fits, so Java converts it for us.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>Narrowing: a cast</strong>The value may not fit, so we write the target type in parentheses. Data may be lost.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
As in C, but <strong>stricter</strong>: C narrows silently, Java refuses to <strong>compile</strong> without the cast.
</blockquote>

</div>

Note:
The chain goes from the type that holds the least to the one that holds the most. Step 1, widening, the arrows to the right: always safe, so it happens on its own. Step 2, narrowing, the arrows to the left: the value may not fit, so Java makes us say it on purpose with a cast, the type in parentheses, the same syntax as in C. char is off the chain but follows the same rule, since a char is a number underneath: char to int widens on its own ('A' becomes 65), int to char needs a cast ((char) 65 becomes 'A'). Step 3, the difference: in C `int x = 3.9;` compiles and quietly drops the decimals; in Java it is a compile error. boolean is outside the chain: it never converts to or from a number.

--

<div class="stage">

## Safe or not?

### Does it compile? Does it keep the value?

<table class="data-table verdict-table stage-body">
<thead>
<tr><th>Statement</th><th>Compiles?</th><th>Keeps the value?</th><th>Why</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>int age = 20;</code></th><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="fragment custom" data-fragment-index="0">Declaration and value, as in C</td></tr>
<tr><th scope="row"><code>double height = age;</code></th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="fragment custom" data-fragment-index="1">Widening: <code>age</code> becomes <code>20.0</code></td></tr>
<tr><th scope="row"><code>long total = age;</code></th><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="fragment custom" data-fragment-index="2">Widening: a larger integer</td></tr>
<tr><th scope="row"><code>int price = 19.99;</code></th><td class="mark mark-error fragment custom" data-fragment-index="3">✗</td><td class="mark mark-none fragment custom" data-fragment-index="3">–</td><td class="fragment custom" data-fragment-index="3">Narrowing without a cast</td></tr>
<tr><th scope="row"><code>int price = (int) 19.99;</code></th><td class="mark mark-ok fragment custom" data-fragment-index="4">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="4">✗</td><td class="fragment custom" data-fragment-index="4">The decimals are cut off: <code>19</code></td></tr>
<tr><th scope="row"><code>byte small = (byte) 200;</code></th><td class="mark mark-ok fragment custom" data-fragment-index="5">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="5">✗</td><td class="fragment custom" data-fragment-index="5">Past 127 it wraps around: <code>-56</code></td></tr>
<tr><th scope="row"><code>boolean done = 1;</code></th><td class="mark mark-error fragment custom" data-fragment-index="6">✗</td><td class="mark mark-none fragment custom" data-fragment-index="6">–</td><td class="fragment custom" data-fragment-index="6">A <code>boolean</code> is not a number</td></tr>
<tr><th scope="row"><code>int code = 'A';</code></th><td class="mark mark-ok fragment custom" data-fragment-index="7">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="7">✓</td><td class="fragment custom" data-fragment-index="7">A <code>char</code> is a number underneath: <code>65</code></td></tr>
</tbody>
</table>

</div>

Note:
Same game as with the names: all the statements are on screen, ask before filling in each row. age is declared in the first one and reused in the next two; each line is judged on its own. A cross under Compiles, in red, does not compile, so whether it keeps the value does not even apply. A cross under Keeps the value, in orange, compiles thanks to the cast but changes the value. The byte case is the surprising one: a byte keeps only 8 bits, and 200 in binary is 11001000; read as a signed byte those bits mean 200 - 2^8 = -56, since 8 bits hold 2^8 = 256 values. Past the maximum, the value starts again from the minimum, like a car odometer.

---

<!-- .slide: class="contents" data-section="5" -->

Note:
They know the primitive types; now how to keep many values of one type together.

---

<div class="stage">

## Arrays in C and Java

### The same idea, with safer rules

<table class="data-table compare-table stage-body">
<thead>
<tr><th>Feature</th><th>C</th><th>Java</th></tr>
</thead>
<tbody>
<tr><th scope="row">Declaration</th><td class="fragment custom" data-fragment-index="0"><code>int numbers[5];</code></td><td class="fragment custom" data-fragment-index="1"><code>int[] numbers = new int[5];</code></td></tr>
<tr class="fragment" data-fragment-index="2"><th scope="row">With values</th><td class="fragment custom" data-fragment-index="3"><code>int numbers[] = {1, 2, 3};</code></td><td class="fragment custom" data-fragment-index="4"><code>int[] numbers = {1, 2, 3};</code></td></tr>
<tr class="fragment" data-fragment-index="5"><th scope="row">Size</th><td class="fragment custom" data-fragment-index="6">When compiling, or at runtime with <code>malloc</code></td><td class="fragment custom" data-fragment-index="7">When running: <code>new int[n]</code>, then fixed</td></tr>
<tr class="fragment" data-fragment-index="8"><th scope="row">Length</th><td class="fragment custom" data-fragment-index="9">With <code>sizeof</code>, only where it is declared</td><td class="fragment custom" data-fragment-index="10">Always available: <code>numbers.length</code></td></tr>
<tr class="fragment" data-fragment-index="11"><th scope="row">Initial values</th><td class="fragment custom" data-fragment-index="12">Local arrays hold garbage</td><td class="fragment custom" data-fragment-index="13">Default values: <code>0</code>, <code>0.0</code>, <code>false</code>, etc.</td></tr>
<tr class="fragment" data-fragment-index="14"><th scope="row">Index out of range</th><td class="fragment custom" data-fragment-index="15">Undefined behavior: other memory is read or overwritten</td><td class="fragment custom" data-fragment-index="16">The program stops with an error</td></tr>
<tr class="fragment" data-fragment-index="17"><th scope="row">Freeing memory</th><td class="fragment custom" data-fragment-index="18">By hand: <code>free</code> after <code>malloc</code></td><td class="fragment custom" data-fragment-index="19">Automatic: the garbage collector</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="20">
A Java array <strong>always knows its length</strong> and <strong>never lets us step outside it</strong>.
</blockquote>

</div>

Note:
The concept is the one they know from C: several values of the same type, one after another, reached by an index from 0. What changes is how much Java does for them. Each row comes in three steps: the feature, how it is in C, and then Java; ask them how they did it in C before showing it, and what they expect from Java. Row by row: the brackets go after the type, and new creates the array (int numbers[] also compiles, but int[] is the Java convention). Initializing with braces is the same. The size can come from a variable, but once created it never changes. In C, the sizeof trick only works where the array is declared: passed to a function it becomes a pointer, sizeof gives the pointer's size, and the length has to travel in another parameter. In Java, length is always there. Values start at their defaults (0 for integers, 0.0 for decimals, false for booleans, and so on for the other types) instead of garbage. An index out of range stops the program with ArrayIndexOutOfBoundsException instead of silently corrupting memory. And there is no free: the garbage collector releases the array once nothing uses it.

---

<!-- .slide: class="contents" data-section="6" -->

Note:
Last section: the control structures. Almost everything is as in C, so each slide shows the C version first, then the Java one next to it, and then points at what changes.

---

<div class="stage">

## The `if` statement

### Choosing between two paths

<div class="columns code-diff stage-top">
<div class="column">

#### C

<!-- C alone on entry, Java at step 0; from step 1 both blocks step together -->
<pre><code class="c" data-trim data-line-numbers="|1|" data-fragment-index="1">
int passed = 1;
if (passed) {
    printf("Well done\n");
} else {
    printf("Try again\n");
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### Java

<pre><code class="java" data-trim data-line-numbers="|1|2" data-fragment-index="1">
boolean passed = true;
if (passed) {
    System.out.println("Well done");
} else {
    System.out.println("Try again");
}
</code></pre>

</div>
</div>

<!-- Its two halves go with the two highlights: indices with no gap, as reveal renumbers them before the code steps exist -->
<blockquote class="stage-note fragment" data-fragment-index="1">
The condition must be a <strong><code>boolean</code></strong>.<span class="fragment" data-fragment-index="2"> Comparing a <code>boolean</code> with <code>true</code> or <code>false</code> adds nothing and is <strong>penalized</strong>.</span>
</blockquote>

</div>

Note:
On entry, the C version they know; ask them how it would look in Java. Step 1, the Java version: the shape is identical, parentheses, braces and else. Step 2, the only real change, marked in both: the type of passed, a boolean with true instead of an int with 1, and the first half of the note: in C any number works as a condition; in Java it has to be a boolean. Step 3, the habit to avoid: passed is already true or false, so comparing it with true adds nothing; write if (passed), and if (!passed) for the opposite. In this course, if (passed == true) and the like lose marks.

---

<div class="stage">

## The `switch` statement

### Choosing among several values

<div class="columns code-diff stage-top">
<div class="column">

#### C

```c []
int day = 6;
switch (day) {
    case 6:
    case 7:
        printf("Weekend\n");
        break;
    default:
        printf("Weekday\n");
}
```

</div>
<div class="column fragment" data-fragment-index="0">

#### Java

```java []
int day = 6;
switch (day) {
    case 6:
    case 7:
        System.out.println("Weekend");
        break;
    default:
        System.out.println("Weekday");
}
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="1">
<strong>Identical</strong> to C.
</blockquote>

</div>

Note:
On entry, the C switch they know. Step 1, the Java version: nothing new, the same switch, so nothing is marked; only the output line is written differently. Cases, falling through from 6 to 7, break to stop, default for the rest. Step 2, the takeaway: it works exactly as in C, falling through included. Recent Java versions also have a shorter form with arrows and no break; it is worth knowing it exists.

---

<div class="stage">

## The `while` loop

### Repeating while a condition holds

<div class="columns code-diff stage-top">
<div class="column">

#### C

```c []
int count = 3;
while (count > 0) {
    printf("%d\n", count);
    count--;
}
```

</div>
<div class="column fragment" data-fragment-index="0">

#### Java

```java []
int count = 3;
while (count > 0) {
    System.out.println(count);
    count--;
}
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="1">
<strong>Identical</strong> to C, and so is the <code>do-while</code> loop.
</blockquote>

</div>

Note:
On entry, the C loop they know. Step 1, the Java version: nothing new, both print 3, 2, 1, and the loop itself is the same, so nothing is marked. Only the output line is written differently. Step 2: do-while works exactly as in C too. println prints any value, numbers included, and adds the line break.

---

<div class="stage">

## The `for` loop

### Repeating a known number of times

<div class="columns code-diff stage-top">
<div class="column">

#### C

<!-- C alone on entry, Java at step 0; at step 1 both blocks mark the loop line -->
<pre><code class="c" data-trim data-line-numbers="|2" data-fragment-index="1">
int numbers[] = {4, 8, 15};
for (int i = 0; i &amp;lt; 3; i++) {
    printf("%d\n", numbers[i]);
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### Java

<pre><code class="java" data-trim data-line-numbers="|2" data-fragment-index="1">
int[] numbers = {4, 8, 15};
for (int i = 0; i &amp;lt; numbers.length; i++) {
    System.out.println(numbers[i]);
}
</code></pre>

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="1">
As in C, but <code>.length</code> gives the <strong>size of the array</strong>: no need to keep track of it by hand.
</blockquote>

</div>

Note:
On entry, the C loop they know; ask them how they would write it in Java. Step 1, the Java version. Step 2, start, condition, update: the same three parts as in C, and the variable declared inside the for, as in modern C. The yellow marks the change that matters, the limit, where the array section pays off: the loop asks the array for its length instead of repeating a 3 that could fall out of date: numbers.length, which they saw in the arrays table. Below this slide, a loop Java has and C does not.

--

<div class="stage">

## The for-each loop

### Every element, without an index

<div class="columns code-diff stage-top">
<div class="column">

#### Classic `for`: through the index

<!-- The classic for alone on entry, the for-each at step 0; at step 1 both blocks mark their loop -->
<pre><code class="java" data-trim data-line-numbers="|1-2" data-fragment-index="1">
for (int i = 0; i &amp;lt; numbers.length; i++) {
    System.out.println(numbers[i]);
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### For-each: element by element

<pre><code class="java" data-trim data-line-numbers="|1-2" data-fragment-index="1">
for (int number : numbers) {
    System.out.println(number);
}
</code></pre>

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="1">
Read the <strong><code>:</code></strong> as "<strong>in</strong>". Use it when the <strong>index</strong> is not needed.
</blockquote>

</div>

Note:
C has no equivalent. On entry, the classic for they just saw. Step 1, the for-each: same array, same output, 4, 8, 15. Step 2, the yellow lines: there is no i, no condition and no update; on each pass, number takes the next value of the array. And how to read it, for each number in numbers, and when to use it: whenever they only need the values. When they need the position, or to change the array, the classic for is still the tool.

---

<div class="stage">

## Unit summary

### What to take away from Introduction to Java

<div class="rules rules-grid stage-body">
<div class="rule">
<p><strong>How Java runs</strong><code>javac</code> turns the source code into <strong>bytecode</strong>, and the JVM runs it on any platform.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Development tools</strong>The <strong>JDK</strong> has the tools to develop programs; the <strong>JRE</strong> inside it runs them.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>A first Java program</strong>It sits in a <strong>class</strong> named as its file, starts in <strong><code>main</code></strong>, and prints with <code>System.out</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Variables and primitive types</strong>Eight types with <strong>fixed sizes</strong>, names that follow the <strong>conventions</strong>, and a <strong>cast</strong> to narrow.</p>
</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong>Arrays</strong>An array <strong>knows its length</strong> and never lets us step outside it.</p>
</div>
<div class="rule fragment" data-fragment-index="4">
<p><strong>Control structures</strong>As in C, but conditions are <strong><code>boolean</code></strong>, and there is a for-each loop.</p>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="5">
Penalized in this course: names that break the <strong>conventions</strong>, and comparing a <strong><code>boolean</code></strong> with <code>true</code> or <code>false</code>.
</blockquote>

</div>

Note:
One card per section, in the order they were seen; reveal them one by one and ask the class to recall an example of each before showing the next. The thread through the whole unit: Java keeps what they know from C and adds safety, fixed sizes and portability. Last step, the two course rules seen along the way, gathered: both compile, both lose marks. Next unit: what a class is, the piece they have been writing around Hello without explaining it.
