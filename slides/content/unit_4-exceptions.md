<!-- .slide: class="cover" -->

## Unit 4: Exceptions

### When something goes wrong while the program runs

Note:
They write hierarchies of classes, with private attributes, overriding, polymorphism, abstract classes and interfaces (unit 3). More than once the course has left aside what happens when something goes wrong: a withdrawal with no money in unit 2, a brightness of 250% and a wrong cast in unit 3. This unit is about that: the errors that only show while the program runs, how a program survives them, and how a method reports one. The example is still the smart home app of unit 3, with its lamp, thermostat and speaker.

---

<!-- .slide: class="contents" data-section="1" -->

1. Exceptions
2. Catching exceptions
3. Throwing exceptions
4. Checked and unchecked exceptions
5. Custom exceptions

Note:
Walk through the index quickly; the details come in each section.

---

<div class="stage">

## It compiles, and then...

### What stops each line?

<table class="data-table stage-body">
<thead>
<tr><th>Line</th><th>When</th><th>Stops with</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>levels[3] = 50;</code></th><td><code>levels</code> has 3 elements</td><td class="fragment custom" data-fragment-index="0"><code>ArrayIndexOutOfBoundsException</code></td></tr>
<tr><th scope="row"><code>name.length()</code></th><td><code>name</code> is <code>null</code></td><td class="fragment custom" data-fragment-index="1"><code>NullPointerException</code></td></tr>
<tr><th scope="row"><code>(Light) device</code></th><td><code>device</code> is a thermostat</td><td class="fragment custom" data-fragment-index="2"><code>ClassCastException</code></td></tr>
<tr><th scope="row"><code>total / count</code></th><td>Both <code>int</code>, and <code>count</code> is 0</td><td class="fragment custom" data-fragment-index="3"><code>ArithmeticException</code></td></tr>
<tr><th scope="row"><code>name.charAt(10)</code></th><td><code>name</code> is <code>"Lamp"</code></td><td class="fragment custom" data-fragment-index="4"><code>StringIndexOutOfBoundsException</code></td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="5">
Every line <strong>compiles</strong>: the error only shows <strong>while the program runs</strong>, and an <strong>exception</strong> stops it.
</blockquote>

</div>

Note:
Five lines that compile, each with the situation in which it runs. Ask about each one before filling in its row; they have met the first three already. An index past the end of an array, unit 1: Java checks every index, where C would read or write whatever is there. A method called on null, unit 2. A cast to the wrong class, unit 3. Dividing two integers by zero: with doubles it gives Infinity, with ints there is no possible result. A character past the end of a String, unit 2's charAt. The compiler cannot catch any of them, because whether they fail depends on the values when the program runs. Last, the takeaway: each of these names is an exception, the subject of the unit.

--

<div class="stage">

## What Java prints

### Reading a stack trace

<div class="code-over-cards code-compact code-dense stage-top">
<div class="columns">
<div class="column">

<!-- Code and cards step together from 0: the line that throws, the trace back to main, the line that never runs -->
<pre><code class="java" data-trim data-line-numbers="|11|4,11|5|" data-fragment-index="0">
public class Main {
    public static void main(String[] args) {
        int[] levels = {80, 30, 50};
        System.out.println(average(levels));
        System.out.println("Done");
    }

    public static int average(int[] levels) {
        int total = 0;
        for (int i = 0; i &amp;lt;= levels.length; i++) {
            total += levels[i];
        }
        return total / levels.length;
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule fragment" data-fragment-index="0">
<p><strong>The exception</strong>Its class, with its package, and after the colon its message: what went wrong.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>The stack trace</strong>The methods that were running, the last one first, with file and line.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="2">
<p><strong>The rest does not run</strong>The program stops there: <code>Done</code> is never printed.</p>
</div>
</div>
</div>
<div class="console-error fragment" data-fragment-index="0">
<pre><code class="nohighlight hljs console" data-noescape>Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
<span class="fragment" data-fragment-index="1">    at Main.average(Main.java:11)
    at Main.main(Main.java:4)</span></code></pre>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
An uncaught exception <strong>stops the program</strong> and prints its <strong>class</strong>, its <strong>message</strong> and <strong>where</strong> it happened.
</blockquote>

</div>

Note:
The first line of the table, inside a real program. On entry, the code: ask what it prints, and let them find the mistake, the <= in the loop. Step 1, the first line of the output, with the line that throws marked: when i is 3, levels[3] does not exist. Java prints the class of the exception with its package, java.lang, and after the colon the message: index 3, length 3, enough to see the off-by-one. Step 2, the stack trace, the calls that were running, read from the top: average, at line 11 of Main.java, called by main, at line 4. In a real program it can be twenty lines long; the first line that is their own code is usually where to look. Step 3, in orange: the program stops there, so neither the average nor Done is ever printed. IntelliJ shows each file and line as a link to it. Last, the takeaway.

--

<div class="stage">

## Exceptions

### An object that describes what went wrong

<div class="rules stage-top">
<div class="rule">
<p><strong>An abnormal event</strong>Something goes wrong while the program runs, and it cannot go on as written.</p>
</div>
<div class="rule fragment">
<p><strong>An object</strong>Java creates one, of a class that names the error, with a message that gives the details.</p>
</div>
<div class="rule fragment">
<p><strong>Thrown</strong>The method stops at that line, and so does each method that called it, up to <code>main</code>.</p>
</div>
<div class="rule fragment">
<p><strong>Or caught</strong>Unless one of those methods catches it: then the program goes on from there.</p>
</div>
</div>

<blockquote class="stage-note fragment">
An <strong>exception</strong> is an <strong>object</strong> that describes an error: once <strong>thrown</strong>, it stops every method until one <strong>catches</strong> it.
</blockquote>

</div>

Note:
What the two slides above have in common, with the words Java uses. An exception is an abnormal event, something the code did not plan for, such as an index that does not exist. Java represents it with an object, as everything else: its class says what kind of error it is, ArrayIndexOutOfBoundsException, and the object carries a message with the details. Java throws it: the normal flow is interrupted, and the methods that were running stop one after another, as the stack trace showed. Unless one of them catches it, which is the next section. Some are thrown by Java itself, like these; a method can also throw one on purpose, in the third section. Last, the takeaway.

--

<div class="stage">

## `Throwable`

### Exceptions are classes too

<div class="code-over-cards stage-body">
<!-- The top of the tree, a class per card, gives way to the whole tree with the note -->
<div class="output-swap">
<div class="swap-center fragment fade-out" data-fragment-index="2">
<div class="hierarchy hierarchy-large">
<div class="class-box">
<p class="class-name"><code>Throwable</code></p>
</div>
<div class="hierarchy-children fragment" data-fragment-index="0">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Error</code></p>
</div>
</div>
<div class="hierarchy fragment" data-fragment-index="1">
<div class="class-box">
<p class="class-name"><code>Exception</code></p>
</div>
</div>
</div>
</div>
</div>
<div class="hierarchy hierarchy-full fragment" data-fragment-index="2">
<div class="class-box">
<p class="class-name"><code>Throwable</code></p>
</div>
<div class="hierarchy-children">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Error</code></p>
</div>
<div class="hierarchy-children">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>LinkageError</code></p>
</div>
<ul class="hierarchy-list">
<li><code>BootstrapMethodError</code></li>
<li><code>ClassCircularityError</code></li>
<li><code>ClassFormatError</code>
<ul class="hierarchy-list">
<li><code>UnsupportedClassVersionError</code></li>
</ul>
</li>
<li><code>ExceptionInInitializerError</code></li>
<li><code>IncompatibleClassChangeError</code>
<ul class="hierarchy-list">
<li><code>AbstractMethodError</code></li>
<li><code>IllegalAccessError</code></li>
<li><code>InstantiationError</code></li>
<li><code>NoSuchFieldError</code></li>
<li><code>NoSuchMethodError</code></li>
</ul>
</li>
<li><code>NoClassDefFoundError</code></li>
<li><code>UnsatisfiedLinkError</code></li>
<li><code>VerifyError</code></li>
</ul>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>AssertionError</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>ThreadDeath</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>VirtualMachineError</code></p>
</div>
<ul class="hierarchy-list">
<li><code>InternalError</code></li>
<li><code>OutOfMemoryError</code></li>
<li><code>StackOverflowError</code></li>
<li><code>UnknownError</code></li>
</ul>
</div>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Exception</code></p>
</div>
<div class="hierarchy-children">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>IOException</code></p>
</div>
<ul class="hierarchy-list">
<li><code>FileNotFoundException</code></li>
<li><code>SocketException</code>
<ul class="hierarchy-list">
<li><code>ConnectException</code></li>
</ul>
</li>
<li><code>UnknownHostException</code></li>
</ul>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>ReflectiveOperationException</code></p>
</div>
<ul class="hierarchy-list">
<li><code>ClassNotFoundException</code></li>
<li><code>IllegalAccessException</code></li>
<li><code>InstantiationException</code></li>
<li><code>InvocationTargetException</code></li>
<li><code>NoSuchFieldException</code></li>
<li><code>NoSuchMethodException</code></li>
</ul>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>RuntimeException</code></p>
</div>
<ul class="hierarchy-list hierarchy-list-columns">
<li><code>ArithmeticException</code></li>
<li><code>ArrayStoreException</code></li>
<li><code>ClassCastException</code></li>
<li><code>ConcurrentModificationException</code></li>
<li><code>EnumConstantNotPresentException</code></li>
<li><code>IllegalArgumentException</code>
<ul class="hierarchy-list">
<li><code>IllegalThreadStateException</code></li>
<li><code>NumberFormatException</code></li>
</ul>
</li>
<li><code>IllegalMonitorStateException</code></li>
<li><code>IllegalStateException</code></li>
<li><code>IndexOutOfBoundsException</code>
<ul class="hierarchy-list">
<li><code>ArrayIndexOutOfBoundsException</code></li>
<li><code>StringIndexOutOfBoundsException</code></li>
</ul>
</li>
<li><code>NegativeArraySizeException</code></li>
<li><code>NoSuchElementException</code>
<ul class="hierarchy-list">
<li><code>InputMismatchException</code></li>
</ul>
</li>
<li><code>NullPointerException</code></li>
<li><code>SecurityException</code></li>
<li><code>TypeNotPresentException</code></li>
<li><code>UnsupportedOperationException</code></li>
</ul>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>CloneNotSupportedException</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>InterruptedException</code></p>
</div>
</div>
</div>
</div>
</div>
</div>
</div>

<div class="rules rules-grid rules-three">
<div class="rule">
<p><strong><code>Throwable</code></strong>Its methods are in every exception.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>Error</code></strong>The JVM's own problems: not to be handled.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>Exception</code></strong>What a program can expect and handle.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Every exception is an object of a subclass of <strong><code>Throwable</code></strong>: a <strong>hierarchy</strong> like those of unit 3.
</blockquote>

</div>

Note:
Exceptions are objects, so they have classes, and the classes form a hierarchy, as in unit 3. On entry, the top: Throwable, in java.lang, whose methods every exception inherits; getMessage returns the message, and printStackTrace prints what they saw two slides above, as the third section shows. Its two subclasses split the tree. Step 1, Error: something wrong with the JVM itself, such as OutOfMemoryError or StackOverflowError (a method that calls itself forever); there is nothing sensible a program can do about them, so they are not caught. Step 2, Exception: what a program can expect and recover from, the exceptions of this unit. Last, the takeaway, and the tree as Java has it, in small: under Exception, RuntimeException, the superclass of the exceptions in the table (ArithmeticException, NullPointerException, ClassCastException...), InputMismatchException from Scanner, and IOException, which the fourth section uses. It is the main part of what Java itself has, not all of it: every Java library adds classes of its own. They do not have to learn it; the point is the shape, and where the exceptions of the unit sit. Why RuntimeException has a branch of its own comes in the fourth section.

---

<!-- .slide: class="contents" data-section="2" -->

Note:
They know what an exception is and how to read one. So far every exception has stopped the program. Now how a program catches one and goes on.

---

<div class="stage">

## Reading from the keyboard

### And a user who types a word

<div class="columns code-compact code-dense stage-top">
<div class="column code-over-cards code-compact">

<!-- Code and cards step together from 0; the run with 80 gives way to the run with abc -->
<pre><code class="java" data-trim data-line-numbers="|1,5|6-7|8|7|" data-fragment-index="0">
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Brightness: ");
        int brightness = scanner.nextInt();
        System.out.println("Set to " + brightness + "%");
    }
}
</code></pre>
<div class="output-swap">
<div class="fragment fade-out" data-fragment-index="3">
<div class="fragment" data-fragment-index="2">

```console
Brightness: 80
Set to 80%
```

</div>
</div>
<div class="console-error fragment" data-fragment-index="3">

```console
Brightness: abc
Exception in thread "main" java.util.InputMismatchException
    at java.base/java.util.Scanner.throwFor(Scanner.java:977)
    at java.base/java.util.Scanner.next(Scanner.java:1632)
    at java.base/java.util.Scanner.nextInt(Scanner.java:2297)
    at java.base/java.util.Scanner.nextInt(Scanner.java:2251)
    at Main.main(Main.java:7)
```

</div>
</div>

</div>
<div class="column rules">
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>Scanner</code></strong>Reads what the user types: <code>System.in</code> is the keyboard.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>nextInt()</code></strong>Waits for the user and returns the integer typed; <code>nextDouble()</code> and <code>nextLine()</code> read a decimal and a line of text.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="3">
<p><strong>Not a number</strong>If the user types a word, <code>nextInt()</code> throws an <code>InputMismatchException</code>.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="4">
What the user types is <strong>outside the program's control</strong>: one wrong word is enough to <strong>stop</strong> it.
</blockquote>

</div>

Note:
The console version of the brightness setting, to see where exceptions come from in a real program: the user. On entry, the whole program. Step 1, Scanner, a class of java.util, so it needs an import, as in the packages of unit 3; System.in is the keyboard, as System.out is the screen. Step 2, nextInt waits until the user types something and presses Enter, and returns it as an int. Step 3, a run where the user types 80: it works. Step 4, a second run where the user types abc: ask what they think happens. nextInt cannot turn abc into an int, so it throws an InputMismatchException, from java.util. The trace starts inside Scanner, Java's own code; the line that is theirs is the last one, Main.java:7. A trap for the exercises: nextInt reads the number but not the Enter after it, so a nextLine right after returns an empty line; a scanner.nextLine() after each nextInt clears it. Last, the takeaway: the code has no bug, and still the program stops.

--

<div class="stage">

## `try` and `catch`

### Catching an exception

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="3-5|6-8|9|" data-fragment-index="0">
Scanner scanner = new Scanner(System.in);
System.out.print("Brightness: ");
try {
    int brightness = scanner.nextInt();
    System.out.println("Set to " + brightness + "%");
} catch (InputMismatchException e) {
    System.out.println("That is not a number");
}
System.out.println("Back to the home screen");
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>try</code></strong>A block with the code that may throw an exception.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>catch</code></strong>The class it catches and a name for the object: its block runs only if the <code>try</code> throws one of them.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>And then</strong>Caught, or never thrown, the program goes on after the <code>catch</code>.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
An exception thrown in <strong><code>try</code></strong> goes to its <strong><code>catch</code></strong>, and the program <strong>goes on</strong> instead of stopping.
</blockquote>

</div>

Note:
The same lines, now inside main with an exception handler; InputMismatchException is in java.util too, so the file also has import java.util.InputMismatchException. On entry, try: the braces hold the code that might fail, here the line that reads and the one that uses what was read. Step 1, catch, right after it: in parentheses, the class of exception it handles and a variable for the exception object, e by custom, like a parameter; its block only runs if that exception is thrown inside the try. Step 2, after both, the program goes on as usual, whether the exception happened or not. Last, the takeaway; what runs in each case is below.

--

<div class="stage">

## What runs?

### When the user types 80, and when it types abc

<div class="code-over-cards code-compact code-dense stage-top">

<!-- Each row's line is marked as its answers fill in; plain with the note -->
<pre><code class="java" data-trim data-line-numbers="|4|5|7|9|" data-fragment-index="0">
Scanner scanner = new Scanner(System.in);
System.out.print("Brightness: ");
try {
    int brightness = scanner.nextInt();
    System.out.println("Set to " + brightness + "%");
} catch (InputMismatchException e) {
    System.out.println("That is not a number");
}
System.out.println("Back to the home screen");
</code></pre>

<table class="data-table verdict-table">
<thead>
<tr><th>Line</th><th class="mark-head">80</th><th class="mark-head">abc</th><th>Why</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>int brightness = scanner.nextInt();</code></th><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="fragment custom" data-fragment-index="0">With <code>abc</code>, it throws the exception</td></tr>
<tr><th scope="row"><code>System.out.println("Set to " + brightness + "%");</code></th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-none fragment custom" data-fragment-index="1">✗</td><td class="fragment custom" data-fragment-index="1">The rest of the <code>try</code> is skipped</td></tr>
<tr><th scope="row"><code>System.out.println("That is not a number");</code></th><td class="mark mark-none fragment custom" data-fragment-index="2">✗</td><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="fragment custom" data-fragment-index="2">Only when the exception is thrown</td></tr>
<tr><th scope="row"><code>System.out.println("Back to the home screen");</code></th><td class="mark mark-ok fragment custom" data-fragment-index="3">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="3">✓</td><td class="fragment custom" data-fragment-index="3">After the <code>try</code> and <code>catch</code>: always</td></tr>
</tbody>
</table>
</div>

<blockquote class="stage-note fragment" data-fragment-index="4">
An exception <strong>skips</strong> the rest of the <code>try</code> and runs the <strong><code>catch</code></strong>; with no exception, the <code>catch</code> is <strong>skipped</strong>.
</blockquote>

</div>

Note:
The code of the slide above, on screen again so they can follow it, and its four reading and printing lines, one per row, in both runs. Ask about each row before filling it in; its line is marked in the code as it fills in. nextInt runs in both; with abc it is the line that throws. The println right after it only runs with 80: once an exception is thrown, the rest of the try is skipped, there is no going back to it. The catch is the other way round: only with abc. The line after them runs in both cases, which is the point: the program survives. A common question: can the program ask again? Yes, with the try inside a loop that repeats until the number is valid, which is what the exercises ask for. Last, the takeaway.

---

<div class="stage">

## Several `catch` blocks

### One for each kind of error

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="2-3|4,6|8|" data-fragment-index="0">
try {
    int index = scanner.nextInt();
    System.out.println(devices[index]);
} catch (InputMismatchException e) {
    System.out.println("That is not a number");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("There is no such device");
} catch (Exception e) {
    System.out.println("Something else went wrong");
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>Two exceptions</strong>A word for <code>nextInt()</code>, or a number past the end of the array.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>One <code>catch</code> each</strong>Tried in order: only the first that matches the exception runs.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>A superclass</strong>A class catches its subclasses too: <code>Exception</code>, last, takes any other exception.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <code>try</code> can have <strong>several <code>catch</code></strong> blocks: the <strong>first</strong> one that matches the exception runs.
</blockquote>

</div>

Note:
The app asks for the number of a device, to show it; devices is the array of the three devices from unit 3. On entry, the two lines that can fail, in two different ways: ask how. A word, as before, or a number such as 7, past the end of an array of three: ArrayIndexOutOfBoundsException. Step 1, one catch for each, one after the other; Java compares the exception with each class in order and runs only the first that matches, then goes on after the last catch. Step 2, the third catch: a catch matches the class it names and all its subclasses, as a Device variable holds a Light. Exception is the superclass of both, so catch (Exception e) would catch them too, and almost anything else; at the end, it only gets what the two above let through, such as a NullPointerException if devices were null. It is tempting to write it everywhere, and usually a bad idea: it also catches bugs the program should not hide. Checking the index with an if would work here too; it is written this way to show two exceptions in one try. Last, the takeaway; the order matters, below.

--

<div class="stage">

## In the wrong order

### Does it compile?

<div class="code-run code-compact stage-top">

<!-- the last step marks both catch lines: the error under the code shows with it -->
```java [|4,6]
try {
    int index = scanner.nextInt();
    System.out.println(devices[index]);
} catch (Exception e) {
    System.out.println("Something went wrong");
} catch (InputMismatchException e) {
    System.out.println("That is not a number");
}
```

<div class="code-run-output console-error">

```console
Main.java:6: error: exception InputMismatchException has already been caught
```

</div>
</div>

<blockquote class="stage-note code-run-output">
The <code>catch</code> blocks go from the <strong>most specific</strong> class to the <strong>most general</strong>, or a later one could <strong>never run</strong>.
</blockquote>

</div>

Note:
The same try, with a general catch first. On entry, ask whether it compiles, and let them think about what the slide above said: the first catch that matches runs. Step 1, the answer: it does not compile. Every InputMismatchException is an Exception, so the first catch takes it, and the second one could never run; Java rejects code that can never run, with exception InputMismatchException has already been caught (the compiler's message, shortened). The fix is to swap them: the subclass first, the general one last, if it is there at all. Last, the takeaway.

--

<div class="stage">

## One `catch`, several exceptions

### When they are handled the same way

<div class="code-over-cards code-compact stage-top">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="4|4|4" data-fragment-index="0">
try {
    int index = scanner.nextInt();
    System.out.println(devices[index]);
} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
    System.out.println("Type the number of a device");
}
</code></pre>

<div class="rules rules-grid rules-pair">
<div class="rule">
<p><strong>Classes joined by <code>|</code></strong>In one <code>catch</code>: its block runs for any of them.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="0">
<p><strong>Unrelated classes</strong>If one is a subclass of another, it does not compile: the superclass alone is enough.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="1">
<strong><code>|</code></strong> joins several classes in <strong>one <code>catch</code></strong>, when every one of them is handled <strong>the same way</strong>.
</blockquote>

</div>

Note:
When two catch blocks would do exactly the same, they can be one. On entry, the vertical bar, read as "or": the block runs if the exception is an InputMismatchException or an ArrayIndexOutOfBoundsException, so the code in it is written once. Step 1, in red: the classes cannot be related, Alternatives in a multi-catch statement cannot be related by subclassing; InputMismatchException | Exception makes no sense, since Exception already covers the other. When each one needs its own message, as on the slide above, separate catch blocks are still the way. Last, the takeaway.

---

<div class="stage">

## `finally`

### Code that always runs

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="7-9|7-9|8|" data-fragment-index="0">
Scanner scanner = new Scanner(System.in);
try {
    int brightness = scanner.nextInt();
    System.out.println("Set to " + brightness + "%");
} catch (InputMismatchException e) {
    System.out.println("That is not a number");
} finally {
    scanner.close();
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>finally</code></strong>After the <code>catch</code> blocks, or right after the <code>try</code>: its block runs whatever happens in the <code>try</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Even if not caught</strong>With an exception no <code>catch</code> takes, or a <code>return</code>, it runs before the method is left.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>To clean up</strong>The place to close what was opened, such as a <code>Scanner</code>.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
The <strong><code>finally</code></strong> block runs in <strong>every case</strong>: for what has to happen whatever goes wrong.
</blockquote>

</div>

Note:
A third block a try can have. On entry, finally, after the catch blocks; a try with a finally and no catch at all is also valid. Its block runs when the try ends, whichever way it ends. Step 1, even in the cases where the code after the try would not run: an exception that none of its catch blocks takes, which goes on up after the finally, or a return inside the try. Step 2, what it is for: releasing what the code opened, whatever happened. A Scanner should be closed when the program no longer needs it, and here it is closed even if reading failed; files and network connections are the usual case. Java also has try (Scanner scanner = new Scanner(System.in)) { ... }, try-with-resources, which closes it by itself; they will meet it with files. Last, the takeaway; the cases are below.

--

<div class="stage">

## When does it run?

### The same `try`, four ways to end

<table class="data-table verdict-table stage-body">
<thead>
<tr><th>In the <code>try</code></th><th class="mark-head"><code>catch</code></th><th class="mark-head"><code>finally</code></th><th class="mark-head">After <code>try</code>-<code>catch</code>-<code>finally</code></th></tr>
</thead>
<tbody>
<tr><th scope="row">No exception</th><td class="mark mark-none fragment custom" data-fragment-index="0">✗</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td></tr>
<tr><th scope="row">An exception it catches</th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td></tr>
<tr><th scope="row">An exception it does not catch</th><td class="mark mark-none fragment custom" data-fragment-index="2">✗</td><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="mark mark-none fragment custom" data-fragment-index="2">✗</td></tr>
<tr><th scope="row">A <code>return</code></th><td class="mark mark-none fragment custom" data-fragment-index="3">✗</td><td class="mark mark-ok fragment custom" data-fragment-index="3">✓</td><td class="mark mark-none fragment custom" data-fragment-index="3">✗</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="4">
Whatever ends the <code>try</code>, the <strong><code>finally</code></strong> block <strong>always</strong> runs.
</blockquote>

</div>

Note:
Four ways the try of the slide above can end, and what runs in each. Ask about each row before filling it in. No exception: the catch is skipped, finally and the code after it run. An exception it catches: all three. An exception it does not catch, say a NullPointerException: finally runs, and then the exception goes on up to whoever called the method, so the code after it does not run. A return inside the try: finally runs just before the method returns. The finally column is all ticks: that is all it promises. (Only stopping the JVM itself, with System.exit, skips it.) Last, the takeaway.

---

<!-- .slide: class="contents" data-section="3" -->

Note:
So far every exception came from Java: an index, a null, a word for nextInt. Now the other side: a method of their own that reports an error by throwing one.

---

<div class="stage">

## Brightness: 250%, again

### Ignored without a word

<div class="code-over-cards code-compact code-dense stage-top">
<div class="columns">
<div class="column">

#### `Light.java`

<!-- Both blocks and the cards step together from 0; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="||||7-9||" data-fragment-index="0">
public class Light extends Device implements Adjustable {
    private int brightness;

    // the rest of the class, as before

    public void setBrightness(int brightness) {
        if (brightness >= 0 &amp;&amp; brightness &amp;lt;= 100) {
            this.brightness = brightness;
        }
    }
}
</code></pre>

</div>
<div class="column code-over-cards code-compact">
<div>

#### `Main.java`

<pre><code class="java" data-trim data-line-numbers="1|2|3|4|4|4|" data-fragment-index="0">
Light lamp = new Light("Living room lamp");
lamp.setBrightness(80);
lamp.setBrightness(250);
System.out.println(lamp.getBrightness());
</code></pre>

</div>
<div class="fragment" data-fragment-index="3">

```console
80
```

</div>
</div>
</div>
<div class="rules rules-grid rules-pair">
<div class="rule rule-warn fragment" data-fragment-index="3">
<p><strong>Ignored</strong>An invalid value leaves the brightness as it was.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="4">
<p><strong>Nobody knows</strong>The caller goes on as if it had worked.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="5"><span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="1"></span><span class="fragment" data-fragment-index="2"></span>
A method that <strong>cannot</strong> do what it is asked should <strong>say so</strong>, not stay silent.
</blockquote>

</div>

Note:
Unit 3 left this open: setBrightness checks the value, and with 250 it does nothing. On entry, the lamp, which its constructor creates at 100%. Step 1, set to 80, a valid value. Step 2, the call with 250: ask what happens. Step 3, the println: ask what it prints. Step 4, 80, with the check marked: 250 was rejected without a word, and the brightness is still the one before. Step 5: the code that called it has no way to know; it could show the user 250% on the screen, or go on with a lamp that never changed. Ask them how they would report the error in C. The answer is below, and then Java's.

--

<div class="stage">

## In C, an error code

### A return value the caller can ignore

<div class="columns code-compact stage-top">
<div class="column code-over-cards">
<div>

<!-- Both blocks and the cards step together from 0 -->
<pre><code class="c" data-trim data-line-numbers="3,6||" data-fragment-index="0">
int set_brightness(struct light *light, int brightness) {
    if (brightness &amp;lt; 0 || brightness > 100) {
        return -1;
    }
    light->brightness = brightness;
    return 0;
}
</code></pre>

</div>
<div>

<pre><code class="c" data-trim data-line-numbers="|1|1|" data-fragment-index="0">
set_brightness(&amp;lamp, 250);
printf("%d\n", lamp.brightness);
</code></pre>

</div>
</div>
<div class="column rules">
<div class="rule">
<p><strong>A return code</strong>The function returns -1 when it fails and 0 when it works, as many C library functions do.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="0">
<p><strong>Easy to ignore</strong>Nothing forces the caller to check it: this call compiles and runs.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>Mixed with the result</strong>A function that returns a value has to keep one, such as -1 or <code>NULL</code>, to mean an error.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
In C, an error is a <strong>return value</strong>: nothing forces the caller to <strong>check</strong> it.
</blockquote>

</div>

Note:
How they did it in Programming Fundamentals I. On entry, the function, with its two returns marked: -1 for an invalid brightness, 0 when it worked, the same convention as scanf's count or fopen's NULL. Step 1, a caller that never looks at it: it compiles without a warning, and the program goes on with a lamp that did not change, exactly as in Java on the slide above. Step 2: when the function already returns something, such as a position or a pointer, one of its values has to be given up to mean failure, -1 or NULL, and every caller has to remember which. Last, the takeaway; Java's answer is below.

--

<div class="stage">

## `throw`

### Reporting an error with an exception

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="8-9|8-9|7-10|4|" data-fragment-index="0">
public class Light extends Device implements Adjustable {
    private int brightness;

    // the rest of the class, as before

    public void setBrightness(int brightness) {
        if (brightness &amp;lt; 0 || brightness > 100) {
            throw new IllegalArgumentException(
                    "Brightness out of range: " + brightness);
        }
        this.brightness = brightness;
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>throw</code></strong>Throws an exception: the method stops there, and the exception goes to whoever called it.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>An object</strong>Created with <code>new</code>, like any other, with a message that describes the error.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Java's own classes</strong><code>IllegalArgumentException</code> for a wrong argument, <code>IllegalStateException</code> for an object that cannot do it now.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>In a constructor too</strong>If a constructor throws, the object is never created.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
<strong><code>throw</code></strong> stops the method with an <strong>exception</strong>: the caller can no longer miss the error.
</blockquote>

</div>

Note:
Java's answer, with the check turned around: if the value is wrong, throw. On entry, throw: like return, it ends the method on that line, but instead of a value it sends an exception to the code that called it. Step 1, what is thrown is an object, created with new as in unit 2; its constructor takes the message, here with the wrong value in it, which is what getMessage will return. Step 2, which class: Java has one for most situations, so there is no need to invent one. IllegalArgumentException, for an argument the method cannot accept; IllegalStateException, for an object that is not in a state to do it, such as setting the volume of a speaker that is off. The line after the if only runs with a valid value. Step 3, with the constructor's comment marked: a constructor can throw in the same way, for instance with a name that is null, and then new never returns an object, so an invalid one can never exist. Last, the takeaway: with 250, Main now stops with an IllegalArgumentException and its stack trace, instead of going on silently.

--

<div class="stage">

## Up the calls

### Who receives the exception?

<div class="stage-body flow-layers">
<div class="flow flow-wide flow-offset">
<div class="flow-box">
<strong><code>main</code></strong>
<code>lamp.setLevel(250)</code>
</div>
<div class="flow-arrow fragment" data-fragment-index="0">
<span class="flow-label">Calls</span>
<span class="flow-shaft"></span>
</div>
<div class="flow-box fragment" data-fragment-index="0">
<strong><code>setLevel</code></strong>
<code>setBrightness(250)</code>
</div>
<div class="flow-arrow fragment" data-fragment-index="1">
<span class="flow-label">Calls</span>
<span class="flow-shaft"></span>
</div>
<div class="flow-box flow-error fragment" data-fragment-index="1">
<strong><code>setBrightness</code></strong>
<code>throw</code>
</div>
</div>
<p class="flow-divider fragment" data-fragment-index="2">The exception</p>
<!-- From the right: each method either catches it (down) or passes it on (left) -->
<div class="flow flow-wide">
<div class="flow-box flow-error fragment" data-fragment-index="7">
<strong>The program stops</strong>
<span class="flow-detail">with the stack trace</span>
</div>
<div class="flow-arrow fragment" data-fragment-index="7">
<span class="flow-label">No</span>
<span class="flow-shaft flow-shaft-back"></span>
</div>
<div class="flow-fork">
<div class="flow-box fragment" data-fragment-index="5">
<strong><code>main</code></strong>
<span class="flow-detail">Has a <code>catch</code>?</span>
</div>
<div class="flow-arrow-down fragment" data-fragment-index="6">
<span class="flow-label">Yes</span>
<span class="flow-shaft-down"></span>
</div>
<div class="flow-box flow-ok fragment" data-fragment-index="6">
<strong><code>main</code></strong>
<span class="flow-detail">Goes on after the <code>catch</code></span>
</div>
</div>
<div class="flow-arrow fragment" data-fragment-index="5">
<span class="flow-label">No</span>
<span class="flow-shaft flow-shaft-back"></span>
</div>
<div class="flow-fork">
<div class="flow-box fragment" data-fragment-index="3">
<strong><code>setLevel</code></strong>
<span class="flow-detail">Has a <code>catch</code>?</span>
</div>
<div class="flow-arrow-down fragment" data-fragment-index="4">
<span class="flow-label">Yes</span>
<span class="flow-shaft-down"></span>
</div>
<div class="flow-box flow-ok fragment" data-fragment-index="4">
<strong><code>setLevel</code></strong>
<span class="flow-detail">Goes on after the <code>catch</code></span>
</div>
</div>
<div class="flow-arrow fragment" data-fragment-index="3">
<span class="flow-label">Goes up</span>
<span class="flow-shaft flow-shaft-back"></span>
</div>
<div class="flow-box flow-error fragment" data-fragment-index="2">
<strong><code>setBrightness</code></strong>
<span class="flow-detail">Stops</span>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="8">
An exception goes <strong>up the calls</strong>, stopping each method, until one <strong>catches</strong> it; if none does, the <strong>program stops</strong>.
</blockquote>

</div>

Note:
Where the exception goes once thrown. The level slider of unit 3 calls setLevel, which calls setBrightness. On entry, main, which calls setLevel(250). Step 1, setLevel, which passes the value on to setBrightness. Step 2, setBrightness, which throws, and the way back below: setBrightness stops on the throw line. Step 3, the exception arrives at setLevel, in the middle of its call: does setLevel have a catch for it, around that call? Step 4, if it does, that catch runs and setLevel goes on after it, as if nothing had happened for main. Step 5, if it does not, setLevel stops too, and the exception goes on to its caller, main, with the same question. Step 6, if main catches it, main goes on after its catch. Step 7, if not, nobody is left: the program stops and prints the stack trace, whose lines are these very calls, setBrightness, setLevel, main. In the code of the unit, setLevel has no catch, so the exception goes through it to main; a method in the middle does not have to do anything for the exception to pass through it. Last, the takeaway.

--

<div class="stage">

## What an exception carries

### Inside `catch (IllegalArgumentException e)`

<div class="runs runs-fit code-dense stage-body">
<div class="rule">
<p><strong><code>getMessage()</code></strong>The message given to its constructor.</p>
</div>
<div>

```java
System.out.println(e.getMessage());
```

</div>
<div class="fragment" data-fragment-index="0">

```console
Brightness out of range: 250
```

</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>toString()</code></strong>Its class and its message: what <code>println(e)</code> prints.</p>
</div>
<div class="fragment" data-fragment-index="1">

```java
System.out.println(e);
```

</div>
<div class="fragment" data-fragment-index="2">

```console
java.lang.IllegalArgumentException: Brightness out of range: 250
```

</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong><code>printStackTrace()</code></strong>The class, the message and the stack trace, and the program goes on.</p>
</div>
<div class="fragment" data-fragment-index="3">

```java
e.printStackTrace();
```

</div>
<div class="fragment" data-fragment-index="4">

```console
java.lang.IllegalArgumentException: Brightness out of range: 250
    at Light.setBrightness(Light.java:8)
    at Light.setLevel(Light.java:16)
    at Main.main(Main.java:5)
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="5">
The exception is an <strong>object</strong>: its methods, inherited from <code>Throwable</code>, say <strong>what</strong> went wrong and <strong>where</strong>.
</blockquote>

</div>

Note:
Inside the catch, e is the exception object that was thrown, and it has the methods of Throwable from the first section. Each method comes with its call first: ask what it prints before showing the output. getMessage, the text passed to the constructor of the exception in setBrightness: what to show the user. toString, which println calls on any object, as in unit 3: the full class name and the message. printStackTrace, what Java prints when nothing catches an exception, but the program does not stop; it writes to System.err, the error output, which IntelliJ shows in red. It is useful while developing; a finished app shows the user a message instead. Last, the takeaway.

---

<!-- .slide: class="contents" data-section="4" -->

Note:
Their methods can now throw exceptions, and the code that calls them can catch them, or not. Some errors, though, are not bugs at all, and Java treats them differently: the compiler insists that they are handled.

---

<div class="stage">

## The speaker stops answering

### Not a bug in the app

<div class="phone-beside stage-body">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M12 3 2 12h3v8h5v-6h4v6h5v-8h3z"/></svg></span>
</div>
<div>
<p class="phone-greeting">Good evening,</p>
<p class="phone-name">My home</p>
</div>
<div class="phone-tiles">
<div class="phone-tile">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a7 7 0 0 0-4 12.74V17a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1v-2.26A7 7 0 0 0 12 2zM9 20h6v2H9z"/></svg></span>
<p class="phone-tile-name">Living room lamp</p>
<p class="phone-label">On, 100%</p>
</div>
<div class="phone-tile">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path fill-rule="evenodd" d="M12 2a3 3 0 0 0-3 3v8.26a5 5 0 1 0 6 0V5a3 3 0 0 0-3-3zm0 2a1 1 0 0 1 1 1v9.32l.4.3a3 3 0 1 1-2.8 0l.4-.3V5a1 1 0 0 1 1-1z"/></svg></span>
<p class="phone-tile-name">Hallway thermostat</p>
<p class="phone-label">On, 21.5 °C</p>
</div>
<div class="phone-tile">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M3 9v6h4l5 4V5L7 9zm13.5 3a4.5 4.5 0 0 0-2.5-4v8a4.5 4.5 0 0 0 2.5-4z"/></svg></span>
<p class="phone-tile-name">Kitchen speaker</p>
<p class="phone-label"><span class="phone-swap"><span class="fragment fade-out" data-fragment-index="0">Off</span><span class="phone-changed fragment" data-fragment-index="0">Not responding</span></span></p>
</div>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main phone-button-wide"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>All off</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone-beside-cards">
<div class="rule fragment" data-fragment-index="0">
<p><strong>Outside the program</strong>The speaker has lost its Wi-Fi: no line of the app is wrong.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Bound to happen</strong>Any device can stop answering, at any time: the app has to be ready for it.</p>
</div>
<blockquote class="fragment" data-fragment-index="2">
Some errors are <strong>not bugs</strong>: they come from <strong>outside</strong> the program, and the code has to be <strong>ready</strong> for them.
</blockquote>
</div>
</div>

</div>

Note:
The home screen of unit 3, with its three tiles. On entry, the house as usual. Step 1, the speaker's tile: it no longer answers the app. Ask them whose fault it is: nobody's in the code. Someone unplugged it, or the Wi-Fi went down. The index past the end, the null and the 250% were mistakes in the program, which can be fixed so that they never happen again; this cannot be fixed, it will happen again. Step 2: so turning a device on can fail even in a perfect program, and the app has to be ready for it, here by showing Not responding instead of crashing. Last, the takeaway. How that looks in the code is below.

--

<div class="stage">

## Online or not

### What the app knows of each device

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="4|6-9|11-13|4" data-fragment-index="0">
public abstract class Device {
    private String name;
    private boolean on;
    private boolean online;

    public Device(String name) {
        this.name = name;
        this.online = true;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    // the other methods, as before
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>online</code></strong>Whether the device answers the app: a new private attribute.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Online from the start</strong>The constructor sets it: a new device answers.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>setOnline</code></strong>The app calls it when a device stops answering, or answers again.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Each device knows whether it is <strong>online</strong>, so <strong><code>turnOn</code></strong> can check it first.
</blockquote>

</div>

Note:
What the app needs to know before it can tell a device that answers from one that does not. On entry, a new attribute of Device, online, private like the others. Step 1, the constructor of unit 3 sets it to true: a device that has just been added answers. Step 2, a setter, the one the app calls when a device stops answering, as the speaker on the slide above, or answers again; there is no getter, since only Device needs to read it. Last, the takeaway; what turnOn does with it is below.

--

<div class="stage">

## Throwing an `IOException`

### Does it compile?

<div class="code-over-cards code-compact code-dense stage-top">

<!-- The code plain on entry, for the question; the throw marked with the error, and kept with the note -->
<pre><code class="java" data-trim data-line-numbers="|14|14" data-fragment-index="0">
package home.devices;

import java.io.IOException;

public abstract class Device {
    private String name;
    private boolean on;
    private boolean online;

    // constructor and the other methods, as before

    public void turnOn() {
        if (!this.online) {
            throw new IOException(this.name + " is not responding");
        }
        this.on = true;
    }
}
</code></pre>

<div class="console-error fragment" data-fragment-index="0">

```console
Device.java:14: error: unreported exception IOException; must be caught or declared to be thrown
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="1">
Some exceptions have to be <strong>caught</strong> or <strong>declared</strong>, and the <strong>compiler</strong> checks it.
</blockquote>

</div>

Note:
turnOn now checks online, from the slide above, and if the device is offline it throws an exception. Which class? IOException, from java.io: Java's exception for input and output that fails, a file that cannot be read or a connection that drops, which is what happened. On entry, the code: it looks like setBrightness on the slide before, so ask them whether it compiles. Step 1, the answer: it does not. unreported exception IOException; must be caught or declared to be thrown. The same throw with IllegalArgumentException compiled; the difference is the class. Last, the takeaway; which exceptions those are comes below, and what declared means after that.

--
<div class="stage">

## Checked and unchecked

### Which ones the compiler checks

<div class="code-over-cards stage-body">
<!-- The top of the tree, a branch per card, gives way to the whole tree with the note -->
<div class="output-swap">
<div class="swap-center fragment fade-out" data-fragment-index="2">
<div class="hierarchy hierarchy-large">
<div class="class-box">
<p class="class-name"><code>Exception</code></p>
</div>
<div class="hierarchy-children fragment" data-fragment-index="0">
<div class="hierarchy fragment" data-fragment-index="1">
<div class="class-box">
<p class="class-name"><code>IOException</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>RuntimeException</code></p>
</div>
</div>
</div>
</div>
</div>
<div class="hierarchy hierarchy-full fragment" data-fragment-index="2">
<div class="class-box">
<p class="class-name"><code>Exception</code></p>
</div>
<div class="hierarchy-children">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>IOException</code></p>
</div>
<ul class="hierarchy-list">
<li><code>FileNotFoundException</code></li>
<li><code>SocketException</code>
<ul class="hierarchy-list">
<li><code>ConnectException</code></li>
</ul>
</li>
<li><code>UnknownHostException</code></li>
</ul>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>ReflectiveOperationException</code></p>
</div>
<ul class="hierarchy-list">
<li><code>ClassNotFoundException</code></li>
<li><code>IllegalAccessException</code></li>
<li><code>InstantiationException</code></li>
<li><code>InvocationTargetException</code></li>
<li><code>NoSuchFieldException</code></li>
<li><code>NoSuchMethodException</code></li>
</ul>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>RuntimeException</code></p>
</div>
<ul class="hierarchy-list hierarchy-list-columns">
<li><code>ArithmeticException</code></li>
<li><code>ArrayStoreException</code></li>
<li><code>ClassCastException</code></li>
<li><code>ConcurrentModificationException</code></li>
<li><code>EnumConstantNotPresentException</code></li>
<li><code>IllegalArgumentException</code>
<ul class="hierarchy-list">
<li><code>IllegalThreadStateException</code></li>
<li><code>NumberFormatException</code></li>
</ul>
</li>
<li><code>IllegalMonitorStateException</code></li>
<li><code>IllegalStateException</code></li>
<li><code>IndexOutOfBoundsException</code>
<ul class="hierarchy-list">
<li><code>ArrayIndexOutOfBoundsException</code></li>
<li><code>StringIndexOutOfBoundsException</code></li>
</ul>
</li>
<li><code>NegativeArraySizeException</code></li>
<li><code>NoSuchElementException</code>
<ul class="hierarchy-list">
<li><code>InputMismatchException</code></li>
</ul>
</li>
<li><code>NullPointerException</code></li>
<li><code>SecurityException</code></li>
<li><code>TypeNotPresentException</code></li>
<li><code>UnsupportedOperationException</code></li>
</ul>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>CloneNotSupportedException</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>InterruptedException</code></p>
</div>
</div>
</div>
</div>
</div>

<div class="rules rules-grid rules-pair">
<div class="rule fragment" data-fragment-index="0">
<p><strong>Unchecked</strong><code>RuntimeException</code> and its subclasses: usually a mistake in the code, which the compiler does not check.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Checked</strong>The rest of <code>Exception</code>, such as <code>IOException</code>: out of the program's control, so caught or declared, or it does not compile.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Under <strong><code>RuntimeException</code></strong> the exceptions are <strong>unchecked</strong>; the rest of <code>Exception</code> is <strong>checked</strong>.
</blockquote>

</div>

Note:
Which exceptions the compiler insists on, from the hierarchy of the first section. On entry, Exception. Step 1, RuntimeException, and with it the rule's exception. These usually mean a mistake in the code, an index too big, a null, a wrong argument, which the code should avoid rather than catch: it and everything under it, IllegalArgumentException, NullPointerException, InputMismatchException and all the exceptions of the first two sections, are unchecked; any method may throw them without saying so, which is why setBrightness compiled with its throw. Step 2, IOException, and the rule. These come from outside, a file, a network, a device, which no correct code can prevent: an Exception that is not under RuntimeException is checked, and every method that may throw it has to catch it or declare it, with the word in the header that comes next. Last, the takeaway, and the whole of Exception: everything under RuntimeException is unchecked; IOException and its subclasses, ReflectiveOperationException, CloneNotSupportedException and InterruptedException are checked. (Errors are not checked either, but they are not exceptions a program handles.) An unchecked exception can still be caught, and declared, as InputMismatchException was caught; the compiler just does not insist.

--

<div class="stage">

## `throws`

### What a method may throw

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="8|8|10|8" data-fragment-index="0">
package home.devices;

import java.io.IOException;

public abstract class Device {
    // attributes, constructor and the other methods, as before

    public void turnOn() throws IOException {
        if (!this.online) {
            throw new IOException(this.name + " is not responding");
        }
        this.on = true;
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>throws</code></strong>After the parameters, the exceptions the method may throw, separated by commas.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>A warning for the caller</strong>Whoever calls it knows it may fail, and the compiler makes it deal with that.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Not a <code>catch</code></strong>The method handles nothing: the exception still goes up to its caller.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
<strong><code>throws</code></strong> declares the exceptions a method may throw: dealing with them is the <strong>caller's</strong> job.
</blockquote>

</div>

Note:
The fix: throws IOException in the header, after the parameters; note the s, throw throws one exception and throws declares them. On entry, the header: the method now says it may end with an IOException, and Device compiles. Several classes go separated by commas, throws IOException, InterruptedException. Step 1, the point: the header is part of the contract, as the parameters are, so whoever calls turnOn knows it may fail and, as the next slide shows, cannot just ignore it. Step 2: throws does not handle anything; the throw is still there, and the exception still goes up the calls as in the third section. A constructor can declare throws too. Last, the takeaway, with the header marked.

--

<div class="stage">

## Catch it or pass it on

### What the caller can do

<div class="code-over-cards code-compact code-dense stage-top">
<div class="columns code-diff">
<div class="column">

#### Catch it

<!-- The first version alone on entry, the second at step 0 with its header marked; both plain with the note -->
<pre><code class="java" data-trim data-line-numbers="9-13|9-13|9-13|" data-fragment-index="0">
package home.app;

// the imports of home.devices, as before
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Device speaker = new Speaker("Kitchen speaker");
        try {
            speaker.turnOn();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### Pass it on

<pre><code class="java" data-trim data-line-numbers="7|7|" data-fragment-index="1">
package home.app;

// the imports of home.devices, as before
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Device speaker = new Speaker("Kitchen speaker");
        speaker.turnOn();
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-three">
<div class="rule">
<p><strong>Catch it</strong>Here, when this method knows what to do.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Pass it on</strong>With <code>throws</code> in its own header.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>Out of <code>main</code></strong>It compiles, and the program stops.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong>checked exception</strong> has to be <strong>caught</strong> or <strong>declared</strong>, method after method: never ignored by accident.
</blockquote>

</div>

Note:
Without either of these, the call speaker.turnOn() is the next compile error, the same unreported exception, now in Main. Main is in home.app, as since the packages of unit 3, so it keeps its imports of home.devices, and adds the one for IOException. On entry, the first option, with the try marked: catch it where the program knows how to react, here by printing the message; the app would mark the tile as not responding. Step 1, the second option: declare it again, in the header of the caller, and let its own caller decide. In a bigger program, a method in the middle that cannot do anything useful about it passes it on, and the one that can, usually the one closest to the user, catches it. Step 2, in orange, the trap: main can pass it on too, and it compiles, but main is called by the JVM, so nobody catches it, and the program stops with the stack trace, Exception in thread "main" java.io.IOException: Kitchen speaker is not responding. Writing throws everywhere to make the compiler quiet only moves the crash. Last, the takeaway: this is what makes an exception checked.

---

<div class="stage">

## Checked or unchecked?

### What each one usually means

<table class="data-table compare-table stage-body">
<thead>
<tr><th></th><th>Checked</th><th>Unchecked</th></tr>
</thead>
<tbody>
<tr><th scope="row">The compiler</th><td class="fragment custom" data-fragment-index="0">Insists: caught or declared</td><td class="fragment custom" data-fragment-index="0">Does not check it</td></tr>
<tr class="fragment" data-fragment-index="1"><th scope="row">The cause</th><td class="fragment custom" data-fragment-index="2">Outside the program: a device, a file, the network</td><td class="fragment custom" data-fragment-index="2">A bug, or a wrong argument</td></tr>
<tr class="fragment" data-fragment-index="3"><th scope="row">Can it be avoided?</th><td class="fragment custom" data-fragment-index="4">No: it can happen to correct code</td><td class="fragment custom" data-fragment-index="4">Yes: by checking first</td></tr>
<tr class="fragment" data-fragment-index="5"><th scope="row">What to do</th><td class="fragment custom" data-fragment-index="6">Catch it and recover: retry, or tell the user</td><td class="fragment custom" data-fragment-index="6">Fix the code that caused it</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="7">
The program has to be <strong>ready</strong> for a <strong>checked</strong> exception, and to <strong>avoid</strong> an <strong>unchecked</strong> one.
</blockquote>

</div>

Note:
The two kinds side by side; each row shows its question first: ask for both answers before filling them in. The compiler: checked ones must be caught or declared, unchecked ones are up to the programmer. The cause: a checked exception usually comes from outside the program, the speaker offline, a missing file, a dropped connection; an unchecked one, from a mistake in the code, an index too big, a null, a brightness of 250. Can it be avoided: no amount of care keeps the Wi-Fi up, while an if before the call avoids every unchecked exception of this unit. What to do: a checked one is caught and the program recovers; an unchecked one is a bug to fix, not to catch. Java itself is not always consistent: InputMismatchException is unchecked although it comes from the user. Last, the takeaway.

---

<!-- .slide: class="contents" data-section="5" -->

Note:
They throw and catch Java's own exceptions, and know which ones the compiler checks. But IOException says little about what happened in the app. Now exceptions of their own.

---

<div class="stage">

## An exception of our own

### `DeviceOfflineException`

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="1|2-4|1|" data-fragment-index="0">
public class DeviceOfflineException extends Exception {
    public DeviceOfflineException(String deviceName) {
        super(deviceName + " is not responding");
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>A class like any other</strong>It extends <code>Exception</code>, so it is checked; extending <code>RuntimeException</code>, it would be unchecked.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Its constructor</strong>Takes what it needs and passes the message to <code>super(...)</code>: <code>getMessage()</code> returns it.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Its name</strong>Says what went wrong, and ends in <code>Exception</code>: a <code>catch</code> can single it out.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong>custom exception</strong> is a subclass of <strong><code>Exception</code></strong> or <strong><code>RuntimeException</code></strong> named after <strong>what went wrong</strong>.
</blockquote>

</div>

Note:
A catch for IOException catches any input or output problem: a device offline, but also a file that cannot be read, or anything else the libraries throw; and the only way to tell them apart is the text of the message. A class of their own says exactly what happened. On entry, the header: an exception is a class like those of unit 3, so it extends one; the superclass decides whether it is checked, and a device offline is the checked kind, from the slides before. Step 1, the constructor, inheritance as in unit 3: super(...) calls Exception's constructor with the message, and getMessage, inherited, returns it; the caller only gives the name of the device. Step 2, the name: by convention it ends in Exception, and it says the problem, so a catch (DeviceOfflineException e) catches exactly that and nothing else. It is a class, so it can also have attributes and getters, such as the device itself, for the code that catches it. Last, the takeaway.

--

<div class="stage">

## Throwing and catching it

### Every device that answers is turned on

<div class="columns code-compact code-dense stage-top">
<div class="column">

#### `Device.java`

<!-- Both blocks step together from 0: Device first, then Main; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="4|6|" data-fragment-index="0">
public abstract class Device {
    // attributes, constructor and the other methods, as before

    public void turnOn() throws DeviceOfflineException {
        if (!this.online) {
            throw new DeviceOfflineException(this.name);
        }
        this.on = true;
    }
}
</code></pre>

</div>
<div class="column code-over-cards code-compact">
<div>

#### `Main.java`

<pre><code class="java" data-trim data-line-numbers="||1-5|7-10|11-13|" data-fragment-index="0">
Device[] devices = {
        new Light("Living room lamp"),
        new Thermostat("Hallway thermostat", 21.5),
        new Speaker("Kitchen speaker") // has lost its Wi-Fi
};

for (Device device : devices) {
    try {
        device.turnOn();
        System.out.println(device);
    } catch (DeviceOfflineException e) {
        System.out.println(e.getMessage());
    }
}
</code></pre>

</div>
<div class="fragment" data-fragment-index="3">
<pre><code class="nohighlight hljs console" data-noescape>Living room lamp: On, 100%
Hallway thermostat: On, 21.5 °C
Kitchen speaker is not responding</code></pre>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="4"><span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="1"></span><span class="fragment" data-fragment-index="2"></span>
A custom exception is <strong>thrown</strong>, <strong>declared</strong> and <strong>caught</strong> like any other, by <strong>its own name</strong>.
</blockquote>

</div>

Note:
The new class in use. Device needs no import, as the exception is in its package, home.devices; Main, in home.app, imports it like the other classes. On entry, the header of turnOn: it now declares DeviceOfflineException, and no longer IOException. Step 1, the throw: a new object of the class, with the device's name, which Device can read because it is its own private attribute. Step 2, the app's All on button, in Main: the array of the three devices of unit 3, where the speaker has lost its Wi-Fi. Step 3, a loop over them that turns each one on and prints it. Ask what it prints. Step 4, the catch for that one class, and the output: a device that does not answer prints its message and the loop goes on with the next, because the try is inside the loop. The lamp and the thermostat are on, and the speaker is reported, with no crash and the other devices untouched. With the try outside the loop, the first device offline would stop the whole loop. Last, the takeaway.

--

<div class="stage">

## `Exception` or `RuntimeException`?

### Which one to extend

<table class="data-table stage-body">
<thead>
<tr><th>The error</th><th>Extends</th><th>Kind</th><th>Because</th></tr>
</thead>
<tbody>
<tr><th scope="row">A device stops answering</th><td class="fragment custom" data-fragment-index="0"><code>Exception</code></td><td class="fragment custom" data-fragment-index="0">Checked</td><td class="fragment custom" data-fragment-index="0">Nobody can prevent it: every caller has to be ready</td></tr>
<tr><th scope="row">A brightness of 250</th><td class="fragment custom" data-fragment-index="1"><code>RuntimeException</code></td><td class="fragment custom" data-fragment-index="1">Unchecked</td><td class="fragment custom" data-fragment-index="1">The caller can check the value first: a bug</td></tr>
<tr><th scope="row">A schedule at 25:00, typed by the user</th><td class="fragment custom" data-fragment-index="2"><code>Exception</code></td><td class="fragment custom" data-fragment-index="2">Checked</td><td class="fragment custom" data-fragment-index="2">It will happen: the app has to ask again</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="3">
<strong>Checked</strong> for what the caller <strong>must be ready for</strong>; <strong>unchecked</strong> for what it <strong>can avoid</strong>.
</blockquote>

</div>

Note:
The question every custom exception raises: which class to extend. Ask about each row before filling it in, using the table of the fourth section. A device that stops answering: it can happen to the best code, so the compiler should make every caller deal with it, checked. A brightness of 250: the code that calls setBrightness can, and should, check the value first; when it does not, it is a bug, so unchecked, which is why Java's IllegalArgumentException is a RuntimeException. A time typed by the user that does not exist: no check in the code prevents a user from typing it, so the app must be ready to ask again, checked. When in doubt, ask whether a correct program could still get it: if so, checked. Last, the takeaway.

---

<div class="stage">

## Exercises

### The whole unit

<div class="rules rules-grid rules-pair stage-top">
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Exceptions/Exercise%201">Exercise 1</a></strong>Housing: checked exceptions for a negative price or size, thrown by the constructors and caught in <code>main</code>.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Exceptions/Exercise%202">Exercise 2</a></strong>Antiques: checked and unchecked exceptions, and a keyboard menu that keeps running after any error.</p>
</div>
</div>

</div>

Note:
The exercises of the unit, which need all of it: throwing, catching, checked and unchecked exceptions of their own. Both build on exercises of unit 3, the housing agency and the antiques collection. They are in the JetBrains Academy course, in the Exceptions lesson; each title opens it on GitHub.

---

<div class="stage">

## Unit summary

### What to take away from Exceptions

<div class="rules rules-grid stage-body">
<div class="rule">
<p><strong>Exceptions</strong>An <strong>object</strong> that describes an error while the program runs; uncaught, it stops it with a <strong>stack trace</strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Catching exceptions</strong><strong><code>try</code></strong> and <strong><code>catch</code></strong>, from the most specific class to the most general, and <strong><code>finally</code></strong>, which always runs.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Throwing exceptions</strong><strong><code>throw</code></strong> reports an error; the exception goes <strong>up the calls</strong> until one catches it.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Checked and unchecked exceptions</strong>A <strong>checked</strong> one is caught or declared with <strong><code>throws</code></strong>; an <strong>unchecked</strong> one, under <code>RuntimeException</code>, need not be.</p>
</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong>Custom exceptions</strong>A subclass of <strong><code>Exception</code></strong> or <strong><code>RuntimeException</code></strong> named after what went wrong.</p>
</div>
</div>

</div>

Note:
Ask the class to recall an example of each section before showing the next one: the table of exceptions and the stack trace of average; the word typed for nextInt; setBrightness with 250 and the calls it goes up; the speaker that stops answering and the compiler's unreported exception; DeviceOfflineException and the All on loop. The next unit is about collections: lists and maps that grow as needed, instead of arrays of a fixed size, some of whose methods throw the exceptions of this unit.
