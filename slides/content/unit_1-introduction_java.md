<!-- .slide: class="cover" -->

## Unit 1: Introduction to Java

### Specific and main elements of Java language

Note:
First real unit. They come from Programming Fundamentals I in C, so every new idea here is framed against what they already know from C.

---

<!-- .slide: class="contents" data-section="1" -->

1. Execution
2. Development elements
3. Elements of a program
4. Primitive data types
5. Arrays
6. Control structures

Note:
Walk through the index quickly; the details come in each section.

---

<div class="stage">

## Understanding platform dependency

### The limitations of direct translation

<div class="flow stage-body">
<div class="flow-box flow-source">
<strong>Source code</strong>
<code>hello.c</code>
</div>
<div class="flow-arrow fragment" data-fragment-index="1">
<span class="flow-label">Compiling</span>
<span class="flow-shaft"></span>
<code class="flow-command">gcc hello.c -o hello</code>
</div>
<div class="flow-box flow-output fragment" data-fragment-index="1">
<strong>Machine code</strong>
<code>hello</code>
</div>
<div class="flow-arrow fragment" data-fragment-index="2">
<span class="flow-label">Running on the same architecture</span>
<span class="flow-shaft"></span>
<code class="flow-command">./hello</code>
</div>
<div class="flow-box flow-ok fragment" data-fragment-index="2">
<strong>Successful</strong>
<code>Hello, world!</code>
</div>
<div class="flow-elbow flow-dashed fragment" data-fragment-index="3"></div>
<div class="flow-arrow flow-dashed flow-lower fragment" data-fragment-index="3">
<span class="flow-label">Running on a different architecture</span>
<span class="flow-shaft"></span>
<code class="flow-command">./hello</code>
</div>
<div class="flow-box flow-error flow-lower fragment" data-fragment-index="3">
<strong>Error</strong>
<code>Exec format error</code>
</div>
</div>
<blockquote class="stage-note fragment" data-fragment-index="4">
The primary drawback of a <strong>compiled language</strong> is that a <strong>binary file</strong> only works on <strong>one particular architecture</strong>.
</blockquote>

</div>

Note:
Start from what they know: a C file. Step 1, gcc translates it straight into machine code for the processor it runs on, here an ordinary x86-64 laptop. Step 2, run it on that same kind of machine and it works. Step 3, take that very same file to a machine with a different processor (an ARM board, a Mac with Apple Silicon) and it does not even start: the instructions inside are not ones that processor understands. Step 4, the takeaway: to run on another architecture you have to recompile for it, once per platform.

--

## Same binary, different machine

### The C program on two machines

<div class="terminals">
<div>

```console
laptop$ gcc hello.c -o hello
laptop$ ./hello
Hello, world!
laptop$ scp hello desktop:
```

</div>
<div class="fragment">

```console
desktop$ ./hello
-bash: ./hello: cannot execute binary file: Exec format error
```

</div>
</div>

Note:
The same thing the diagram shows, as they would see it in a terminal. hello.c is the classic hello world from Programming Fundamentals I. The laptop is x86-64; the desktop has a different processor (ARM), so the kernel refuses to load the binary. The fix in C is recompiling on the desktop: the source is portable, the binary is not.

---

<div class="stage">

## Achieving platform independence

### The role of bytecode and the "Write Once, Run Anywhere" philosophy

<div class="flow stage-body">
<div class="flow-box flow-source">
<strong>Source code</strong>
<code>Hello.java</code>
</div>
<div class="flow-arrow fragment" data-fragment-index="1">
<span class="flow-label">Compiling</span>
<span class="flow-shaft"></span>
<code class="flow-command">javac Hello.java</code>
</div>
<div class="flow-box flow-output fragment" data-fragment-index="1">
<strong>Bytecode</strong>
<code>Hello.class</code>
</div>
<div class="flow-arrow fragment" data-fragment-index="2">
<span class="flow-label">Running on the same architecture</span>
<span class="flow-shaft"></span>
<code class="flow-command">java Hello</code>
</div>
<div class="flow-box flow-ok fragment" data-fragment-index="2">
<strong>Successful</strong>
<code>Hello, world!</code>
</div>
<div class="flow-elbow fragment" data-fragment-index="3"></div>
<div class="flow-arrow flow-lower fragment" data-fragment-index="3">
<span class="flow-label">Running on a different architecture</span>
<span class="flow-shaft"></span>
<code class="flow-command">java Hello</code>
</div>
<div class="flow-box flow-ok flow-lower fragment" data-fragment-index="3">
<strong>Successful</strong>
<code>Hello, world!</code>
</div>
</div>
<blockquote class="stage-note fragment" data-fragment-index="4">
<strong>Write Once, Run Anywhere</strong>: <code>javac</code> produces <strong>bytecode</strong>, not machine code, and the same <code>.class</code> file runs on any platform with a <strong>Java Virtual Machine</strong>.
</blockquote>

</div>

Note:
Same diagram as before, on purpose: only two things change. Step 1, javac does not produce machine code but bytecode, instructions for a machine that does not physically exist, the Java Virtual Machine. Step 2, running it (`java Hello`) starts the JVM installed on that computer, which translates the bytecode for the real processor. Step 3, the key moment: on a different architecture the very same Hello.class works, because that machine has its own JVM. What is platform-specific now is the JVM, not our program. Step 4, the slogan.

--

## Same bytecode, different machine

### The Java program on two machines

<div class="terminals">
<div>

```console
laptop$ javac Hello.java
laptop$ java Hello
Hello, world!
laptop$ scp Hello.class desktop:
```

</div>
<div class="fragment">

```console
desktop$ java Hello
Hello, world!
```

</div>
</div>

Note:
The Java version of the terminal they saw for C. javac turns Hello.java into Hello.class; `java Hello` starts the JVM and runs it. Only Hello.class travels to the desktop, which has a different processor (ARM), and it runs unchanged, as long as that machine has a JVM installed. Contrast with C: there we had to recompile on the desktop, here we don't.

---

<!-- .slide: class="contents" data-section="2" -->

Note:
Execution is done: they know why Java compiles to bytecode. Now the tools they will actually type every day.

---

<div class="stage">

## Working with the Java Development Kit

### The complete toolbox for building Java applications

<div class="toolbox stage-body">
<div class="toolbox-head">
<p class="toolbox-label">Java Development Kit<span>JDK</span></p>
<p class="toolbox-desc">Essential for <strong>developers</strong>: the <strong>tools</strong> needed to <strong>create</strong> Java software.</p>
</div>
<div class="toolbox-group fragment" data-fragment-index="1">
<p class="toolbox-group-name">Build and run</p>
<div class="tool"><code>javac</code><span>Compiles <code>.java</code> source into <code>.class</code> bytecode</span></div>
<div class="tool"><code>java</code><span>Starts the JVM and runs the program</span></div>
</div>
<div class="toolbox-group fragment" data-fragment-index="2">
<p class="toolbox-group-name">Share</p>
<div class="tool"><code>jar</code><span>Packages the <code>.class</code> files into a single <code>.jar</code></span></div>
<div class="tool"><code>javadoc</code><span>Builds HTML documentation from comments</span></div>
</div>
<div class="toolbox-group fragment" data-fragment-index="3">
<p class="toolbox-group-name">Explore and fix</p>
<div class="tool"><code>jshell</code><span>Tries out Java code line by line, instantly</span></div>
<div class="tool"><code>jdb</code><span>Debugs a running program step by step</span></div>
</div>
</div>

</div>

Note:
The JDK is what they install to write Java, not only to run it. Step 1, the two they already met: javac and java. Step 2, the tools to hand the program to someone else: jar bundles everything into one file, javadoc turns the /**
*/ comments into a web page like the official API docs. Step 3, jshell to test a line without writing a whole program, jdb to stop and inspect a program (in practice they will debug from the IDE, which uses the same machinery).

---

<div class="stage">

## The JDK and the JRE

### What you need to develop and what you need to run

<div class="toolbox stage-body">
<p class="toolbox-label">Java Development Kit<span>JDK</span></p>
<div class="toolbox-runtime fragment" data-fragment-index="1">
<div class="toolbox-head">
<p class="toolbox-label">Java Runtime Environment<span>JRE</span></p>
<p class="toolbox-desc">The <strong>runtime</strong> part: what is needed to <strong>run</strong> applications.</p>
</div>
<div class="tool fragment" data-fragment-index="2"><strong>JVM</strong><span>Runs the bytecode on the real processor</span></div>
<div class="tool fragment" data-fragment-index="3"><strong>Standard library</strong><span>Ready-made code to reuse, like <code>printf</code> or <code>sqrt</code> in C</span></div>
</div>
</div>
<blockquote class="stage-note fragment" data-fragment-index="4">
To <strong>run</strong> a Java program you only need the <strong>JRE</strong>; to <strong>develop</strong> one you need the <strong>JDK</strong>, which already contains it.
</blockquote>

</div>

Note:
Start from the JDK they just saw; its tools are the previous slide. Step 1, inside it there is a smaller block, the JRE: the part needed only to run programs. Step 2, the JVM from the Execution section, which executes the bytecode. Step 3, the standard library, ready-made code their programs use without them having to write it. Anchor it in C: they never wrote printf (stdio.h) or sqrt (math.h), they came with the language. Java has the same idea, only much bigger. Step 4, the takeaway. Since Java 11 Oracle no longer ships a separate JRE; some distributions (Eclipse Temurin, for instance) still offer one, but for this course they only need to install the JDK, and the JRE comes inside.

---

<!-- .slide: class="contents" data-section="3" -->

Note:
They know the tools; now they write, compile and run their first Java program, step by step.

---

<div class="stage">

## The elements of a Java program

### What we write, what the compiler generates, and what the user sees

<div class="flow stage-body">
<div class="flow-stack">
<div class="flow-box flow-source">
<strong>Source code</strong>
<code>Hello.java</code>
</div>
<p class="flow-caption">Plain text file that we write. It must contain a <code>main</code>, as in C.</p>
</div>
<div class="flow-arrow fragment" data-fragment-index="1">
<span class="flow-label">Compiling</span>
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
<span class="flow-label">Running</span>
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

</div>

Note:
The same pipeline as in the Execution section, now as the three files and commands they will use in the lab. Start with the source: a plain text file they can write in any editor, and like a C program it starts in main. Step 1, javac compiles it into Hello.class, the bytecode. Step 2, java Hello starts the JVM and runs it: note it is `java Hello`, without `.class`. Next, the actual code.

--

<div class="stage">

## Our first program: `Hello.java`

### The classic first program, now in Java

<div class="stage-top">

```java [1,5|2,4|3]
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
```

</div>

<blockquote class="stage-note">
For now, the <strong>class</strong> takes the <strong>name of its file</strong>, starting in <strong>uppercase</strong>: <code>Hello</code> goes in <code>Hello.java</code>. More on classes next unit.
</blockquote>

</div>

Note:
Walk through it against the C version they know. Line 1 and 5: the class wrapping everything; do not explain classes yet, just the naming rule: Hello in the code, Hello.java on disk. Lines 2 and 4: main, where the program starts, like int main in C. Line 3: System.out.println is Java's printf, and it adds the line break by itself.

--

## Compiling and running it

### Two commands, from source code to output

```console
$ javac Hello.java
$ java Hello
Hello, world!
```

Note:
First line: javac prints nothing if all goes well, and leaves Hello.class next to the source. Then java takes the class name, not the file name, so no `.class`. If the file were called hello.java while the class is Hello, javac would complain: that is why the naming rule matters from day one.

---

<div class="stage">

## Basic syntax rules

### Blocks and scope

<div class="columns stage-top">
<div class="column">

<!-- Steps count from 0: reveal renumbers the cards from 0 before the code steps exist -->
<pre><code class="java" data-trim data-line-numbers="1,6|2,5|3-4" data-fragment-index="0">
public class Scope {
    public static void main(String[] args) {
        System.out.println("Hello,");
        System.out.println("world!");
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>Blocks</strong>Every <strong><code>{</code></strong> opens a block and its <strong><code>}</code></strong> closes it.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Nesting</strong>Blocks go inside blocks: <code>main</code> sits inside the class.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Scope</strong>The code a block covers: everything between its <strong><code>{ }</code></strong>.</p>
</div>
</div>
</div>

</div>

Note:
Now that they have seen a program, the rules behind its shape. Good news first: all of this works exactly as in C. On entry, the class lines: its braces come in pairs and mark a block. Step 1, main's lines: its block sits inside the class block, blocks nest. Step 2, scope: the two println lines are in the same scope, main's, because they sit between the same pair of braces. When they meet variables, this is what will decide where each one can be used. Each card arrives with the lines it talks about.

---

<div class="stage">

## Free format

### What the compiler ignores, and why we still indent

<div class="columns stage-top">
<div class="column">

#### Indented: the blocks are visible at a glance

```java
public class Scope {
    public static void main(String[] args) {
        System.out.println("Hello,");
        System.out.println("world!");
    }
}
```

</div>
<div class="column fragment" data-fragment-index="1">

#### The same program: it compiles, but it is unreadable

```java
public class Scope{public static
void main(String[] args){System.out.
println("Hello,");System.out.println(
"world!");}}
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Java is <strong>free-format</strong>: spaces, tabs and blank lines are ignored. <strong>Indentation</strong> is for people: it shows each <strong>scope</strong> at a glance.
</blockquote>

</div>

Note:
Same program as before on the left. Step 1, the right column: exactly the same program with the layout squeezed out. It compiles and prints the same, because the compiler only looks at the braces and semicolons, never at spaces, tabs or line breaks; again, just like C. Step 2, the takeaway: we indent one level per block so that the scope is visible without counting braces. The IDE does it for them, but they must be able to read it.

---

<div class="stage">

## Comments

### Notes for people that the compiler ignores

<div class="columns stage-top">
<div class="column">

<!-- Steps count from 0: reveal renumbers the cards from 0 before the code steps exist -->
<pre><code class="java" data-trim data-line-numbers="6|8-9|1-3" data-fragment-index="0">
/**
 * Prints a greeting.
 */
public class Comments {
    public static void main(String[] args) {
        // Up to the end of the line
        System.out.println("Hello,");
        /* Everything between
           the two marks */
        System.out.println("world!");
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>Single-line <code>//</code></strong>From <strong><code>//</code></strong> to the end of the line.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Multi-line <code>/* */</code></strong>Everything between <strong><code>/*</code></strong> and <strong><code>*/</code></strong>, over several lines.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Documentation <code>/** */</code></strong>Turned into documentation by <code>javadoc</code>.</p>
</div>
</div>
</div>

</div>

Note:
Comments are one more thing the compiler ignores, like the spaces in the previous slide: they are notes for whoever reads the code. On entry, the single-line comment, as in C: from // to the end of the line. Step 1, the multi-line one, also as in C: everything between /* and */. Step 2, the new one: /** */ above the class. It is still a comment for the compiler, but javadoc, the JDK tool they saw, reads it to generate web documentation like the official Java API pages.

---

<div class="stage">

## Naming variables

### Rules and conventions

<div class="columns stage-top">
<div class="column rules">

#### Rules: break one and it does not compile

<div class="rule">
<p><strong>Characters</strong>Letters, digits, <strong><code>_</code></strong> and <strong><code>$</code></strong>, never spaces: <code>total2</code>, not <code>total 2</code>.</p>
</div>
<div class="rule">
<p><strong>First character</strong>Never a digit: <code>2nd</code> is not a valid name.</p>
</div>
<div class="rule">
<p><strong>Reserved words</strong>Java keeps some words for itself: they cannot be used as names.</p>
</div>
<div class="rule">
<p><strong>Case-sensitive</strong><code>total</code> and <code>Total</code> are two different names.</p>
</div>

</div>
<div class="column rules fragment" data-fragment-index="0">

#### Conventions: it compiles, but professionals follow them

<div class="rule">
<p><strong>Variables</strong><strong><code>camelCase</code></strong>: first word lowercase, the rest capitalized: <code>totalPrice</code>.</p>
</div>
<div class="rule">
<p><strong>Constants</strong><strong><code>UPPER_SNAKE_CASE</code></strong>: all capitals, words joined by <code>_</code>: <code>MAX_SPEED</code>.</p>
</div>
<div class="rule">
<p><strong>Meaningful names</strong><code>price</code> says what it stores; <code>p</code> does not.</p>
</div>

</div>
</div>

</div>

Note:
The names we give to variables, as they did in C. Left column, the rules: the compiler checks them, and breaking one is a compile error; they are the same as in C, plus $. The reserved words get their own slide next. Step 1, right column, the conventions: the compiler does not care, but every Java programmer follows them, so code written by anyone reads the same. The same rules apply to every name in a program, which they will see as the course goes on.

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

<div class="verdicts stage-top">
<div class="verdict verdict-ok">
<code>totalPrice</code>
<span class="verdict-mark fragment" data-fragment-index="0">✓</span>
<span class="verdict-reason fragment" data-fragment-index="0">Valid: the convention for variables.</span>
</div>
<div class="verdict verdict-error fragment" data-fragment-index="1">
<code class="fragment custom verdict-strike" data-fragment-index="2">2ndPlace</code>
<span class="verdict-mark fragment" data-fragment-index="2">✗</span>
<span class="verdict-reason fragment" data-fragment-index="2">Not valid: it starts with a digit.</span>
</div>
<div class="verdict verdict-error fragment" data-fragment-index="3">
<code class="fragment custom verdict-strike" data-fragment-index="4">class</code>
<span class="verdict-mark fragment" data-fragment-index="4">✗</span>
<span class="verdict-reason fragment" data-fragment-index="4">Not valid: it is a reserved word.</span>
</div>
<div class="verdict verdict-error fragment" data-fragment-index="5">
<code class="fragment custom verdict-strike" data-fragment-index="6">first name</code>
<span class="verdict-mark fragment" data-fragment-index="6">✗</span>
<span class="verdict-reason fragment" data-fragment-index="6">Not valid: it contains a space.</span>
</div>
<div class="verdict verdict-ok fragment" data-fragment-index="7">
<code>player2</code>
<span class="verdict-mark fragment" data-fragment-index="8">✓</span>
<span class="verdict-reason fragment" data-fragment-index="8">Valid: digits are fine after the first character.</span>
</div>
<div class="verdict verdict-warn fragment" data-fragment-index="9">
<code>total_price</code>
<span class="verdict-mark fragment" data-fragment-index="10">!</span>
<span class="verdict-reason fragment" data-fragment-index="10">Compiles, but the convention is <code>totalPrice</code>.</span>
</div>
<div class="verdict verdict-ok fragment" data-fragment-index="11">
<code>MAX_SPEED</code>
<span class="verdict-mark fragment" data-fragment-index="12">✓</span>
<span class="verdict-reason fragment" data-fragment-index="12">Valid: the convention for constants.</span>
</div>
<div class="verdict verdict-warn fragment" data-fragment-index="13">
<code>a</code>
<span class="verdict-mark fragment" data-fragment-index="14">!</span>
<span class="verdict-reason fragment" data-fragment-index="14">Compiles, but says nothing about what it stores.</span>
</div>
</div>

</div>

Note:
Ask the class before each verdict: valid? and if valid, does it follow the convention? Three outcomes: a tick when it is valid and conventional, a cross (and the name struck out) when it breaks a rule and does not compile, and an exclamation mark when it compiles but a professional would not write it that way. The last two warnings are the important nuance: the compiler accepting a name does not make it a good one.

---

<!-- .slide: class="contents" data-section="4" -->

Note:
They can write and name things; now, what kinds of values a Java program works with, and how to move a value from one type to another.

---

<div class="stage">

## The eight primitive types

### The basic values Java works with

<table class="data-table stage-body">
<thead>
<tr><th>Category</th><th>Type</th><th>Storage</th><th>Range</th><th>Note</th></tr>
</thead>
<tbody>
<tr><td>Logical</td><th scope="row"><code>boolean</code></th><td>1 bit</td><td><code>true</code> or <code>false</code></td><td>Not a number: <code>0</code> and <code>1</code> are not booleans, unlike C</td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="0">
<tr><td>Very small</td><th scope="row"><code>byte</code></th><td>8 bits</td><td>−2<sup>7</sup> to 2<sup>7</sup> − 1 (−128 to 127)</td><td></td></tr>
<tr><td>Small</td><th scope="row"><code>short</code></th><td>16 bits</td><td>−2<sup>15</sup> to 2<sup>15</sup> − 1 (−32,768 to 32,767)</td><td></td></tr>
<tr><td>Standard</td><th scope="row"><code>int</code></th><td>32 bits</td><td>−2<sup>31</sup> to 2<sup>31</sup> − 1 (about ±2.1 billion)</td><td>The default for whole numbers: <code>42</code></td></tr>
<tr><td>Large</td><th scope="row"><code>long</code></th><td>64 bits</td><td>−2<sup>63</sup> to 2<sup>63</sup> − 1 (about ±9.2 × 10<sup>18</sup>)</td><td>Values end in <code>L</code>: <code>42L</code></td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="1">
<tr><td>Single precision</td><th scope="row"><code>float</code></th><td>32 bits</td><td>About ±3.4 × 10<sup>38</sup>, 7 significant digits</td><td>Values end in <code>f</code>: <code>3.14f</code></td></tr>
<tr><td>Double precision</td><th scope="row"><code>double</code></th><td>64 bits</td><td>About ±1.8 × 10<sup>308</sup>, 15 significant digits</td><td>The default for decimals: <code>3.14</code></td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="2">
<tr><td>Character</td><th scope="row"><code>char</code></th><td>16 bits</td><td>One Unicode character, from 0 to 65,535</td><td>16 bits, not 8 as in C: <code>'A'</code>, <code>'ñ'</code></td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="3">
Unlike C, every size is <strong>fixed</strong>: an <code>int</code> has 32 bits on <strong>every platform</strong>, which is part of Write Once, Run Anywhere.
</blockquote>

</div>

Note:
Eight types, the same family as in C. On entry, boolean: a real type of its own, not an int in disguise; its size in memory is up to the JVM, but it holds one bit of information. Step 1, the four integers, from smallest to largest; int is the one they will use by default, long when the number does not fit. Step 2, the two decimals; double is the default, float only when memory matters. Step 3, char: 16 bits because it stores Unicode, so accents and ñ fit. Step 4, the link with the first section: in C an int can be 16 or 32 bits depending on the machine; in Java it never changes.

---

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
Nothing new if they remember C: the type, the name, and optionally = and a value, ending in a semicolon. Step by step, one line per group of the table. Step 2, integers: the L at the end of population, because that number does not fit in an int. Step 3, decimals: the f of float, because a decimal without it is a double. Step 4, char: single quotes, one character.

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
<div class="rule fragment" data-fragment-index="1">
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

<div class="verdicts stage-top">
<div class="verdict verdict-ok">
<code>int age = 20;</code>
<span class="verdict-mark fragment" data-fragment-index="0">✓</span>
<span class="verdict-reason fragment" data-fragment-index="0">Declaration and value, as in C.</span>
</div>
<div class="verdict verdict-ok fragment" data-fragment-index="1">
<code>double height = age;</code>
<span class="verdict-mark fragment" data-fragment-index="2">✓</span>
<span class="verdict-reason fragment" data-fragment-index="2">Widening: <code>int</code> fits in <code>double</code>, so it becomes <code>20.0</code>.</span>
</div>
<div class="verdict verdict-ok fragment" data-fragment-index="3">
<code>long total = age;</code>
<span class="verdict-mark fragment" data-fragment-index="4">✓</span>
<span class="verdict-reason fragment" data-fragment-index="4">Widening: a larger integer, converted automatically.</span>
</div>
<div class="verdict verdict-error fragment" data-fragment-index="5">
<code class="fragment custom verdict-strike" data-fragment-index="6">int price = 19.99;</code>
<span class="verdict-mark fragment" data-fragment-index="6">✗</span>
<span class="verdict-reason fragment" data-fragment-index="6">Narrowing without a cast: it does not compile.</span>
</div>
<div class="verdict verdict-warn fragment" data-fragment-index="7">
<code>int price = (int) 19.99;</code>
<span class="verdict-mark fragment" data-fragment-index="8">!</span>
<span class="verdict-reason fragment" data-fragment-index="8">Compiles, but the decimals are cut off: <code>19</code>.</span>
</div>
<div class="verdict verdict-warn fragment" data-fragment-index="9">
<code>byte small = (byte) 200;</code>
<span class="verdict-mark fragment" data-fragment-index="10">!</span>
<span class="verdict-reason fragment" data-fragment-index="10">Compiles, but 200 exceeds 127 and wraps around: 200 − 2<sup>8</sup> = <code>-56</code>.</span>
</div>
<div class="verdict verdict-error fragment" data-fragment-index="11">
<code class="fragment custom verdict-strike" data-fragment-index="12">boolean done = 1;</code>
<span class="verdict-mark fragment" data-fragment-index="12">✗</span>
<span class="verdict-reason fragment" data-fragment-index="12">A <code>boolean</code> is not a number: only <code>true</code> or <code>false</code>.</span>
</div>
<div class="verdict verdict-ok fragment" data-fragment-index="13">
<code>int code = 'A';</code>
<span class="verdict-mark fragment" data-fragment-index="14">✓</span>
<span class="verdict-reason fragment" data-fragment-index="14">A <code>char</code> is a number underneath: <code>65</code>.</span>
</div>
</div>

</div>

Note:
Same game as with the names: ask before each verdict. age is declared in the first line and reused in the next two; each line is judged on its own. A tick is a safe conversion, a cross does not compile, and an exclamation mark compiles with a cast but changes the value. The byte case is the surprising one: a byte keeps only 8 bits, and 200 in binary is 11001000; read as a signed byte those bits mean 200 - 2^8 = -56, since 8 bits hold 2^8 = 256 values. Past the maximum, the value starts again from the minimum, like a car odometer.

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
<tr><th scope="row">Declaration</th><td><code>int numbers[5];</code></td><td><code>int[] numbers = new int[5];</code></td></tr>
<tr class="fragment" data-fragment-index="0"><th scope="row">With values</th><td><code>int numbers[] = {1, 2, 3};</code></td><td><code>int[] numbers = {1, 2, 3};</code></td></tr>
<tr class="fragment" data-fragment-index="1"><th scope="row">Size</th><td>When compiling, or at runtime with <code>malloc</code></td><td>When running: <code>new int[n]</code>, then fixed</td></tr>
<tr class="fragment" data-fragment-index="2"><th scope="row">Length</th><td>With <code>sizeof</code>, only where it is declared</td><td>Always available: <code>numbers.length</code></td></tr>
<tr class="fragment" data-fragment-index="3"><th scope="row">Initial values</th><td>Local arrays hold garbage</td><td>Default values: <code>0</code>, <code>0.0</code>, <code>false</code></td></tr>
<tr class="fragment" data-fragment-index="4"><th scope="row">Index out of range</th><td>Undefined behavior: other memory is read or overwritten</td><td>The program stops with an error</td></tr>
<tr class="fragment" data-fragment-index="5"><th scope="row">Freeing memory</th><td>By hand: <code>free</code> after <code>malloc</code></td><td>Automatic: the garbage collector</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="6">
A Java array <strong>always knows its length</strong> and <strong>never lets us step outside it</strong>.
</blockquote>

</div>

Note:
The concept is the one they know from C: several values of the same type, one after another, reached by an index from 0. What changes is how much Java does for them. Row by row: the brackets go after the type, and new creates the array (int numbers[] also compiles, but int[] is the Java convention). Initializing with braces is the same. The size can come from a variable, but once created it never changes. In C, the sizeof trick only works where the array is declared: passed to a function it becomes a pointer, sizeof gives the pointer's size, and the length has to travel in another parameter. In Java, length is always there. Values start at their defaults (0, 0.0, false) instead of garbage. An index out of range stops the program with ArrayIndexOutOfBoundsException instead of silently corrupting memory. And there is no free: the garbage collector releases the array once nothing uses it.

---

<!-- .slide: class="contents" data-section="6" -->

Note:
Last section: the control structures. Almost everything is as in C, so the slides put both versions side by side and point at what changes.

---

<div class="stage">

## The `if` statement

### Choosing between two paths

<div class="columns code-diff stage-top">
<div class="column">

#### C

```c [1]
int passed = 1;
if (passed) {
    printf("Well done\n");
} else {
    printf("Try again\n");
}
```

</div>
<div class="column">

#### Java

```java [1]
boolean passed = true;
if (passed) {
    System.out.println("Well done");
} else {
    System.out.println("Try again");
}
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="0">
The condition must be a <strong><code>boolean</code></strong>: <code>if (1)</code> compiles in C, but <strong>not in Java</strong>.
</blockquote>

</div>

Note:
The shape is identical: parentheses, braces, else. Both versions are on screen, and the yellow marks the only real change: the type of passed. Step 1, why it matters: in C any number works as a condition, 0 is false and the rest true; Java only accepts true or false, so a classic C slip like if (x = 5) does not even compile.

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
<div class="column">

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

<blockquote class="stage-note fragment" data-fragment-index="0">
<strong>Identical</strong> to C, and so is the <code>do-while</code> loop.
</blockquote>

</div>

Note:
Nothing new: both print 3, 2, 1, and the loop itself is the same, so nothing is marked. Only the output line is written differently. Step 1: do-while works exactly as in C too. println prints any value and adds the line break; if they miss printf, Java also has System.out.printf with the same format codes.

---

<div class="stage">

## The `for` loop

### Repeating a known number of times

<div class="columns code-diff stage-top">
<div class="column">

#### C

```c [2]
int numbers[] = {4, 8, 15};
for (int i = 0; i < 3; i++) {
    printf("%d\n", numbers[i]);
}
```

</div>
<div class="column">

#### Java

```java [2]
int[] numbers = {4, 8, 15};
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="0">
The same three parts; the limit comes from the array itself: <code>numbers.length</code>.
</blockquote>

</div>

Note:
Start, condition, update: the same three parts as in C, and the variable declared inside the for, as in modern C. The yellow marks the change that matters, the limit, where the array section pays off: the loop asks the array for its length instead of repeating a 3 that could fall out of date. Step 1, the takeaway. Below this slide, a loop Java has and C does not.

--

<div class="stage">

## The for-each loop

### Every element, without an index

<div class="columns code-diff stage-top">
<div class="column">

#### Classic `for`: through the index

```java [1-2]
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

</div>
<div class="column">

#### For-each: element by element

```java [1-2]
for (int number : numbers) {
    System.out.println(number);
}
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="0">
Read the <strong><code>:</code></strong> as "in": <strong>for each</strong> <code>number</code> <strong>in</strong> <code>numbers</code>. Use it when the index is not needed.
</blockquote>

</div>

Note:
C has no equivalent. Same array, same output, 4, 8, 15. The yellow lines: there is no i, no condition and no update; on each pass, number takes the next value of the array. Step 1, how to read it, and when: whenever they only need the values. When they need the position, or to change the array, the classic for is still the tool.

---

<div class="stage">

## The `switch` statement

### Choosing among several values

<div class="columns code-diff stage-top">
<div class="column">

#### C

```c [1,3-4]
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
<div class="column">

#### Java

```java [1,3-4]
String day = "Saturday";
switch (day) {
    case "Saturday":
    case "Sunday":
        System.out.println("Weekend");
        break;
    default:
        System.out.println("Weekday");
}
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="0">
Same rules as in C, <code>break</code> included, but Java can also switch on <strong>text</strong>.
</blockquote>

</div>

Note:
The C version they know: cases, falling through from 6 to 7, break to stop, default for the rest. The Java version has the same structure, break included, and forgetting it falls through exactly as in C. The difference is what it can compare: besides numbers and characters, text in double quotes, which C cannot do: that is what the yellow marks. Step 1, the takeaway. Recent Java versions also have a shorter form with arrows and no break; it is worth knowing it exists.

---

<div class="stage">

## Unit summary

### What to take away from Introduction to Java

<div class="rules rules-grid stage-body">
<div class="rule">
<p><strong>Execution</strong><code>javac</code> turns the source code into <strong>bytecode</strong>, and the JVM runs it on any platform.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Development elements</strong>The <strong>JDK</strong> has the tools to build programs; the <strong>JRE</strong> inside it runs them.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Elements of a program</strong>Blocks, comments and names follow the rules of C, plus the <strong>conventions</strong> of Java.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Primitive data types</strong>Eight types with <strong>fixed sizes</strong>: widening is automatic, narrowing needs a cast.</p>
</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong>Arrays</strong>An array <strong>knows its length</strong> and never lets us step outside it.</p>
</div>
<div class="rule fragment" data-fragment-index="4">
<p><strong>Control structures</strong>As in C, but conditions are <strong><code>boolean</code></strong>, and there is a for-each loop.</p>
</div>
</div>

</div>

Note:
One card per section, in the order they were seen; reveal them one by one and ask the class to recall an example of each before showing the next. The thread through the whole unit: Java keeps what they know from C and adds safety, fixed sizes and portability. Next unit: what a class is, the piece they have been writing around Hello without explaining it.
