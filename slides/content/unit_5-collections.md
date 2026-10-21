<!-- .slide: class="cover" -->

## Unit 5: Collections

### Groups of objects that grow as needed

Note:
They write hierarchies of classes (unit 3) and report and handle errors with exceptions (unit 4). Every group of objects so far has been an array, whose length is fixed when it is created. This unit is about Java's collections: lists, sets and maps that grow as elements are added, written once in java.util for any class of element. The example is still the smart home app of units 3 and 4, with its lamp, thermostat and speaker.

---

<!-- .slide: class="contents" data-section="1" -->

1. Lists
2. Generics
3. Iterators
4. Sets and maps
5. Sorting

Note:
Walk through the index quickly; the details come in each section.

---

<div class="stage">

## A fourth device

### Where does it go?

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
<p class="phone-label">Off</p>
</div>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main phone-button-wide phone-tap fragment custom" data-fragment-index="0"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Add device</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone-beside-cards">
<div class="rule fragment" data-fragment-index="0">
<p><strong>A new device</strong>The user has bought a bedroom lamp and adds it to the app.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>Room for three</strong>The app keeps its devices in an array, whose length was fixed when it was created.</p>
</div>
<blockquote class="fragment" data-fragment-index="2">
An array has a <strong>fixed length</strong>: the app needs a group of devices that <strong>grows</strong>.
</blockquote>
</div>
</div>

</div>

Note:
The home screen of units 3 and 4, with its three tiles and, instead of All off, the button to add a device. On entry, the house as usual. Step 1, the user taps Add device: a bedroom lamp, just bought. Ask them where the app puts it: the devices are in the array of unit 3, Device[] devices, created with the three of them. Step 2, the problem: the length of an array is decided when it is created, as unit 1 said, and never changes. The app cannot know how many devices a house will have: three today, twenty next year. Last, the takeaway; what happens if the code tries anyway is below.

--

<div class="stage">

## No room for a fourth

### What happens?

<div class="code-run code-compact code-dense stage-top">

<!-- the last step marks the assignment: the error under the code shows with it -->
```java [|12]
package home.app;

// the imports of home.devices, as before

public class Main {
    public static void main(String[] args) {
        Device[] devices = {
                new Light("Living room lamp"),
                new Thermostat("Hallway thermostat", 21.5),
                new Speaker("Kitchen speaker")
        };
        devices[3] = new Light("Bedroom lamp");
    }
}
```

<div class="code-run-output console-error">

```console
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
    at home.app.Main.main(Main.java:12)
```

</div>
</div>

<blockquote class="stage-note code-run-output">
It <strong>compiles</strong>, and stops when it runs: an array's <strong>length</strong> never changes.
</blockquote>

</div>

Note:
The array of the app, as unit 3 built it, and the fourth device put after the last one. On entry, the code: ask whether it compiles, and what happens when it runs. Step 1, the answer: it compiles, since the compiler does not know the length of the array, and when it runs Java checks the index and throws the first exception of unit 4's table, ArrayIndexOutOfBoundsException, index 3 for length 3. In C it would have written past the end of the array without a word. Last, the takeaway; how to make room by hand is below.

--

<div class="stage">

## Growing it by hand

### A bigger array, every time it fills up

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="2-3|10|11-15|17-18|" data-fragment-index="0">
public class Home {
    private Device[] devices;
    private int count;

    public Home() {
        this.devices = new Device[3];
    }

    public void add(Device device) {
        if (this.count == this.devices.length) {
            Device[] bigger = new Device[2 * this.devices.length];
            for (int i = 0; i &amp;lt; this.count; i++) {
                bigger[i] = this.devices[i];
            }
            this.devices = bigger;
        }
        this.devices[this.count] = device;
        this.count++;
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>An array and a count</strong>The array is the room; the count, how much of it is used.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Full?</strong>When the count reaches the length, there is no room left.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Twice as big</strong>A new array, every device copied into it, and the attribute pointing at it.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Then it goes in</strong>In the first free place, and the count goes up.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
It works, but <strong>every class</strong> that keeps a group would write it <strong>again</strong>, and more for removing, inserting or searching.
</blockquote>

</div>

Note:
What they would write with what they know: a class Home that keeps the devices of the house. On entry, its two attributes: the array, created with room for three, and count, the number of places in use, 0 at first. Step 1, add checks whether the array is full. Step 2, if it is, it creates an array twice as long, copies every device into it, and keeps the new one in the attribute; nothing refers to the old one any more, so the garbage collector frees it. Step 3, either way the device goes into the first free place, devices[count], and count goes up. It is what they did in C with realloc. Ask them what else Home would need: removing a device from the middle means moving all the ones after it, and so do inserting and searching. Last, the takeaway: all of that, written again for every group of every class. Java has it written once, which is what comes next.

--

<div class="stage">

## Collections

### Groups of objects, ready-made

<div class="rules stage-top">
<div class="rule">
<p><strong>A collection</strong>An object that holds a group of other objects, its elements.</p>
</div>
<div class="rule fragment">
<p><strong>Grows and shrinks</strong>It makes room as elements are added, as <code>Home</code> did, without the code that uses it noticing.</p>
</div>
<div class="rule fragment">
<p><strong>Ready-made</strong>In <code>java.util</code>: interfaces that say what a collection can do, and classes that implement them.</p>
</div>
<div class="rule fragment">
<p><strong>Several kinds</strong>Lists, sets and maps: each one keeps its elements its own way.</p>
</div>
</div>

<blockquote class="stage-note fragment">
A <strong>collection</strong> is an object that holds a <strong>group of objects</strong> and <strong>grows</strong> as needed.
</blockquote>

</div>

Note:
What Java offers instead of Home. On entry, the word: a collection is an object whose job is to hold other objects, called its elements. Step 1, its main promise: it grows as needed, and shrinks when elements are removed, doing inside what Home did, so the code that uses it never sees an array or a count. Step 2, they are already written, in the package java.util, the same as Scanner: some interfaces, as in unit 3, which say what each kind can do, and classes that implement them. Step 3, there are several kinds, each with its own rules, which come in this unit: lists, the most used, then sets and maps. Last, the takeaway; the whole family is below.

--

<div class="stage">

## The collections framework

### Interfaces, and the classes that implement them

<div class="code-over-cards stage-body">
<div class="hierarchy-row">
<div class="hierarchy">
<div class="class-box class-box-interface">
<p class="class-name"><span class="class-keyword">interface</span><code>Collection</code></p>
</div>
<div class="hierarchy-children fragment" data-fragment-index="0">
<div class="hierarchy">
<div class="class-box class-box-interface">
<p class="class-name"><span class="class-keyword">interface</span><code>List</code></p>
</div>
<ul class="hierarchy-list fragment" data-fragment-index="1">
<li><code>ArrayList</code></li>
<li><code>LinkedList</code></li>
</ul>
</div>
<div class="hierarchy">
<div class="class-box class-box-interface">
<p class="class-name"><span class="class-keyword">interface</span><code>Set</code></p>
</div>
<ul class="hierarchy-list fragment" data-fragment-index="1">
<li><code>HashSet</code></li>
<li><code>LinkedHashSet</code></li>
<li><code>TreeSet</code></li>
</ul>
</div>
<div class="hierarchy">
<div class="class-box class-box-interface">
<p class="class-name"><span class="class-keyword">interface</span><code>Queue</code></p>
</div>
<ul class="hierarchy-list fragment" data-fragment-index="1">
<li><code>ArrayDeque</code></li>
<li><code>PriorityQueue</code></li>
</ul>
</div>
</div>
</div>
<div class="hierarchy fragment" data-fragment-index="2">
<div class="class-box class-box-interface">
<p class="class-name"><span class="class-keyword">interface</span><code>Map</code></p>
</div>
<ul class="hierarchy-list">
<li><code>HashMap</code></li>
<li><code>LinkedHashMap</code></li>
<li><code>TreeMap</code></li>
</ul>
</div>
</div>

<div class="rules rules-grid rules-three">
<div class="rule fragment" data-fragment-index="0">
<p><strong>Interfaces</strong>Each kind of collection, and what it can do: <code>Collection</code> is what they all share.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Classes</strong>Each one a way of storing its elements: <code>ArrayList</code> keeps them in an array.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong><code>Map</code>, apart</strong>Pairs of a key and a value: not a <code>Collection</code>, but part of the family.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
The <strong>interfaces</strong> say what each kind of collection <strong>does</strong>; the <strong>classes</strong>, how it <strong>stores</strong> its elements.
</blockquote>

</div>

Note:
The family, in the class boxes of unit 3, with the interfaces dashed. On entry, Collection, the interface every collection implements: add, remove, size, contains, the methods any group of objects has. Step 1, the three kinds under it, each an interface that extends it. A List keeps its elements in order, by position, and allows duplicates; a Set never has the same element twice; a Queue gives its elements back in a given order, the first one in or the most urgent. Step 2, the classes that implement each one: ArrayList, the list of this unit, keeps its elements in an array, as Home did; LinkedList, in a chain of nodes like the linked lists of C. HashSet and TreeSet come in the fourth section. Queues are left for later courses. Step 3, Map, on its own: it stores pairs, a key and its value, such as a name and its device, so it does not extend Collection, although everyone counts it in. Last, the takeaway. The arrows are simplified: there are some abstract classes and more interfaces in between, which they do not need.

---

<div class="stage">

## `ArrayList`

### A list that grows by itself

<div class="columns code-compact code-dense stage-top">
<div class="column code-over-cards code-compact">

<!-- Each println alone first, to ask, then its line of output; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="4,8|9-12|13|13|14|14|" data-fragment-index="0">
package home.app;

// the imports of home.devices, as before
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList devices = new ArrayList();
        devices.add(new Light("Living room lamp"));
        devices.add(new Thermostat("Hallway thermostat", 21.5));
        devices.add(new Speaker("Kitchen speaker"));
        devices.add(new Light("Bedroom lamp"));
        System.out.println(devices.size());
        System.out.println(devices.get(3));
    }
}
</code></pre>
<div class="fragment" data-fragment-index="2">
<pre><code class="nohighlight hljs console" data-noescape>4
<span class="fragment" data-fragment-index="4">Bedroom lamp: Off, 100%</span></code></pre>
</div>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>ArrayList</code></strong>A class of <code>java.util</code>: imported, and created empty, with no length.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>add</code></strong>Puts an element at the end: the list makes room by itself.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong><code>size()</code></strong>How many elements it holds: the <code>length</code> of an array.</p>
</div>
<div class="rule fragment" data-fragment-index="4">
<p><strong><code>get(i)</code></strong>The element at position <code>i</code>, from 0: the <code>[i]</code> of an array.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="5"><span class="fragment" data-fragment-index="1"></span><span class="fragment" data-fragment-index="3"></span>
An <strong><code>ArrayList</code></strong> grows as elements are <strong>added</strong>: <code>size()</code> and <code>get(i)</code> do what <code>length</code> and <code>[i]</code> did.
</blockquote>

</div>

Note:
The four devices again, in a list instead of an array. On entry, the import, as with Scanner in unit 4, and the list: new ArrayList() creates it with no elements and no length to choose. Step 1, add, four times: each device goes after the last one, and the list makes room when it needs it, the fourth one included. Inside, it is an array that is replaced by a bigger one, as in Home; ArrayList makes it half as big again each time, not twice. Then each println alone, to ask what it prints, and its line of output on the next step. size() gives 4: it is a method, with parentheses, as length() of a String, and counts elements, not room. get(3) gives the fourth device, printed with the toString of unit 3; the lamp is off, since nobody turned it on. An index out of range throws an IndexOutOfBoundsException, as an array would. Last, the takeaway.

--

<div class="stage">

## Its methods

### What a list can do

<table class="data-table stage-body">
<thead>
<tr><th>To</th><th>Method</th><th>What it does</th></tr>
</thead>
<tbody>
<tr><td rowspan="2">Add</td><th scope="row"><code>add(e)</code></th><td>Puts the element <code>e</code> at the end</td></tr>
<tr><th scope="row"><code>add(i, e)</code></th><td>Puts the element <code>e</code> at the index <code>i</code>, moving the rest one place on</td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="0">
<tr><td rowspan="2">Read</td><th scope="row"><code>get(i)</code></th><td>The element at the index <code>i</code></td></tr>
<tr><th scope="row"><code>size()</code>, <code>isEmpty()</code></th><td>How many elements it has, and whether it has none</td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="1">
<tr><td>Change</td><th scope="row"><code>set(i, e)</code></th><td>Puts the element <code>e</code> in place of the one at the index <code>i</code></td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="2">
<tr><td rowspan="2">Remove</td><th scope="row"><code>remove(i)</code>, <code>remove(o)</code></th><td>The element at the index <code>i</code>, or the first one equal to the object <code>o</code>, moving the rest back</td></tr>
<tr><th scope="row"><code>clear()</code></th><td>Every element</td></tr>
</tbody>
<tbody class="fragment" data-fragment-index="3">
<tr><td>Search</td><th scope="row"><code>contains(o)</code>, <code>indexOf(o)</code></th><td>Whether an element is equal to the object <code>o</code>, and the index of the first one, or -1 if there is none</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="4">
A list keeps its elements <strong>in order</strong>, by <strong>position</strong>: adding or removing one <strong>moves</strong> the ones after it.
</blockquote>

</div>

Note:
The methods of a list they will use most, by what they are for; they are all in the List interface, so every list has them. On entry, adding: at the end, or at a position, where the element at that position and all the ones after it move one place on, the work Home would have had to write. Step 1, reading: get and size, from the slide above, and isEmpty, which is size() == 0. Step 2, changing: set replaces, so the size stays the same. Step 3, removing: remove takes out an element, by its position or by an object equal to it, and the ones after it move back, so the size goes down. clear empties it. Step 4, searching, which compares the elements with equals, as the next slides show. The whole list is in Java's documentation, the page of java.util.ArrayList. Last, the takeaway.

--

<div class="stage">

## What does it print?

### A list of rooms

<div class="code-run code-compact stage-top">

<!-- Each println alone first, to ask, then its line of output; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1-4|5|5|6-7|8|8|9|9|5,8" data-fragment-index="0">
ArrayList rooms = new ArrayList();
rooms.add("Kitchen");
rooms.add("Hallway");
rooms.add(0, "Living room");
System.out.println(rooms);
rooms.set(2, "Bedroom");
rooms.remove(0);
System.out.println(rooms);
System.out.println(rooms.indexOf("Bedroom"));
</code></pre>

<div class="fragment" data-fragment-index="1">
<pre><code class="nohighlight hljs console" data-noescape>[Living room, Kitchen, Hallway]
<span class="fragment" data-fragment-index="4">[Kitchen, Bedroom]</span>
<span class="fragment" data-fragment-index="6">1</span></code></pre>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="7"><span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="2"></span><span class="fragment" data-fragment-index="3"></span><span class="fragment" data-fragment-index="5"></span>
<code>println</code> shows a list's elements <strong>in order</strong>, between brackets; positions <strong>shift</strong> as elements come and go.
</blockquote>

</div>

Note:
A list of strings, the rooms of the house, to practice the methods of the table. On entry, three adds: Kitchen, Hallway, and Living room at position 0, which pushes the other two on. Then each println alone, to ask what it prints, and its line of output on the next step. The first: the list prints itself, because ArrayList overrides toString, as Device does: the elements, each with its own toString, between brackets and separated by commas. Step 3, set(2, "Bedroom") replaces Hallway, at position 2, and remove(0) takes out Living room, so Kitchen moves back to 0 and Bedroom to 1. Ask for the second println, then for indexOf: Bedroom is now at position 1. Last, the takeaway, with the two prints of the list marked.

--

<div class="stage">

## Is the lamp in the list?

### `contains` asks `equals`

<div class="code-run code-compact stage-top">

<!-- Each println alone first, to ask, then its line of output; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1-2|3|3|4|4|3-4" data-fragment-index="0">
ArrayList devices = new ArrayList();
devices.add(new Light("Living room lamp"));
System.out.println(devices.contains(new Light("Living room lamp")));
System.out.println(devices.indexOf(new Speaker("Living room lamp")));
</code></pre>

<div class="fragment" data-fragment-index="1">
<pre><code class="nohighlight hljs console" data-noescape>true
<span class="fragment" data-fragment-index="3">-1</span></code></pre>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="4"><span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="2"></span>
<code>contains(o)</code>, <code>indexOf(o)</code> and <code>remove(o)</code> compare with <strong><code>equals</code></strong>: the one <code>Device</code> overrides in unit 3.
</blockquote>

</div>

Note:
How a list decides that an element is there. On entry, a list with one lamp. Step 1, contains, with another lamp of the same name, a different object: ask what it prints. Step 2, true: contains does not compare references, it calls equals on each element, and Device overrides equals in unit 3 to compare the class and the name. Without that override it would use Object's, which compares references, and print false. Step 3, indexOf with a speaker of the same name: ask again. Step 4, -1: equals checks the class first, and a speaker is never equal to a lamp. remove(o) works the same way: it removes the first element equal to o. Last, the takeaway.

--

<div class="stage">

## The `Collections` class

### On `rooms`: `[Living room, Kitchen, Hallway, Bedroom]`

<div class="runs runs-fit code-dense stage-body">
<div class="rule">
<p><strong><code>sort</code></strong>Puts the elements in their natural order: alphabetical, for strings.</p>
</div>
<div>

```java
Collections.sort(rooms);
System.out.println(rooms);
```

</div>
<div class="fragment" data-fragment-index="0">

```console
[Bedroom, Hallway, Kitchen, Living room]
```

</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>reverse</code></strong>Turns the order the other way round.</p>
</div>
<div class="fragment" data-fragment-index="1">

```java
Collections.reverse(rooms);
System.out.println(rooms);
```

</div>
<div class="fragment" data-fragment-index="2">

```console
[Living room, Kitchen, Hallway, Bedroom]
```

</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong><code>max</code> and <code>min</code></strong>The last and the first element in that order.</p>
</div>
<div class="fragment" data-fragment-index="3">

```java
System.out.println(Collections.max(rooms));
```

</div>
<div class="fragment" data-fragment-index="4">

```console
Living room
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="5">
<strong><code>Collections</code></strong>, with an s, has <strong>static methods</strong> that work on any list, as <code>Math</code> does on numbers.
</blockquote>

</div>

Note:
The subtitle gives what rooms holds before the first call: Living room, Kitchen, Hallway and Bedroom, in that order, with the four rooms of the house, not the two the slide before left. Collections, with an s, is a class of java.util, so it needs an import too, and it is not the Collection interface: it only has static methods, as Math, which take the list as an argument. Each method comes with its call first: ask what it prints before showing the output. sort puts the list in order, alphabetical for strings, and changes the list itself, it does not return a new one. reverse turns it round. max and min give the last and the first element in that same order, without changing anything. Others they may meet: shuffle, which mixes the list, and frequency, which counts the elements equal to one. If they ask about other objects: numbers, Integer or Double, go from smallest to largest, and String and the wrappers have a natural order of their own, which is what sort uses; a class of their own, such as Device, has none yet, so with a list of devices sort does not work: with a List<Device> it does not compile, and with a list with no type it compiles and throws a ClassCastException, Light cannot be cast to Comparable, when it runs. How a class gets that natural order is the last section of the unit. Last, the takeaway.

--

<div class="stage">

## Everything is an `Object`

### What the list lets in, and what comes out

<div class="code-over-cards code-compact stage-top">
<div class="columns">
<div class="column">

<!-- Each line with its card; the cast that fails shows its exception. The empty span in the note holds an index only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1-2|3|4|5|5|3,5" data-fragment-index="0">
ArrayList devices = new ArrayList();
devices.add(new Light("Living room lamp"));
devices.add("Kitchen speaker");
Device lamp = (Device) devices.get(0);
Device speaker = (Device) devices.get(1);
</code></pre>

</div>
<div class="column rules">
<div class="rule fragment" data-fragment-index="0">
<p><strong>Any object goes in</strong><code>add</code> takes an <code>Object</code>: a <code>String</code> gets in, and it compiles.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>An <code>Object</code> comes out</strong><code>get</code> returns an <code>Object</code>: without the cast, it does not compile.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="3">
<p><strong>The wrong cast</strong>It fails while the program runs, at line 5, far from the mistake, at line 3.</p>
</div>
</div>
</div>
<div class="console-error fragment" data-fragment-index="3">

```console
Exception in thread "main" java.lang.ClassCastException: class java.lang.String cannot be cast to class home.devices.Device
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="4"><span class="fragment" data-fragment-index="2"></span>
A list like this takes <strong>any object</strong> and gives back an <strong><code>Object</code></strong>: the compiler can no longer <strong>check the types</strong>.
</blockquote>

</div>

Note:
The catch of the lists so far. On entry, a list with a lamp. Step 1, someone adds the name of the speaker instead of the speaker: ask whether it compiles. It does: ArrayList was written for any class of element, so add takes an Object, and every object is one, as in unit 3. Step 2, the other side: get returns an Object too, so Device lamp = devices.get(0) does not compile, incompatible types: Object cannot be converted to Device, and every element needs a downcast. Step 3, the cast of the second element: ask what happens. Step 4, the wrong cast of unit 3, a ClassCastException, because that element is a String (the message goes on with the modules, which can be ignored). The mistake was at line 3 and it shows up at line 5, in a real program possibly in another class and much later. Last, the takeaway. IntelliJ underlines new ArrayList() with a warning, raw use of parameterized class: the way to tell the list what it holds is the next section.

---

<!-- .slide: class="contents" data-section="2" -->

Note:
A list grows as needed, but takes anything and gives back an Object. Now how to tell a collection what it holds, so the compiler checks it again.

---

<div class="stage">

## `ArrayList<Device>`

### A list of one type

<div class="code-over-cards code-dense stage-top">
<div class="columns code-diff">
<div class="column">

#### Any object

<!-- The first version alone on entry, the second at step 0; then both mark the lines that differ, plain with the note -->
<pre><code class="java" data-trim data-line-numbers="|1|3|4|" data-fragment-index="0">
ArrayList devices = new ArrayList();
devices.add(new Light("Living room lamp"));
devices.add("Kitchen speaker");
Device lamp = (Device) devices.get(0);
</code></pre>

</div>
<div class="column code-over-cards code-compact fragment" data-fragment-index="0">
<div>

#### Only devices

<pre><code class="java" data-trim data-line-numbers="|1|3|4|" data-fragment-index="0">
ArrayList&amp;lt;Device> devices = new ArrayList&amp;lt;Device>();
devices.add(new Light("Living room lamp"));
devices.add("Kitchen speaker");
Device lamp = devices.get(0);
</code></pre>

</div>
<div class="console-error fragment" data-fragment-index="1">

```console
Main.java:3: error: incompatible types: String cannot be converted to Device
```

</div>

</div>
</div>
<div class="rules rules-grid rules-three">
<div class="rule fragment" data-fragment-index="0">
<p><strong>The type in <code>&amp;lt;&amp;gt;</code></strong>The class of its elements, after the name of the collection.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="1">
<p><strong>Anything else</strong>Does not compile: the error is at the line with the mistake.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>No cast</strong><code>get</code> returns a <code>Device</code>, ready to use.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
A <strong>generic</strong> collection says the type of its <strong>elements</strong>: the compiler checks what goes <strong>in</strong>, and what comes <strong>out</strong> needs no cast.
</blockquote>

</div>

Note:
The fix for the slide before, side by side. On entry, the list of the first section. Step 1, the same lines with the type of the elements in angle brackets, after ArrayList, both in the variable and after new: a list of Device. ArrayList is a generic class, written in Java as ArrayList<E>, where E, for element, stands for the type each list chooses, as a parameter stands for a value. Step 2, the name of the speaker no longer gets in: the error, under the version with the type, is at line 3, while the version on the left still compiles, the line with the mistake, while compiling, not at line 5 while the program runs. A Light, a Thermostat or a Speaker are fine, since each is a Device. Step 3, get returns a Device, so the cast goes. Last, the takeaway. The lists of the first section are called raw: they exist because Java had collections before generics, in Java 5, and code from then still works; new code always gives the type.

--

<div class="stage">

## `List<Device>`

### The interface as the type

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="5,9|9|9|" data-fragment-index="0">
package home.app;

// the imports of home.devices, as before
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List&amp;lt;Device> devices = new ArrayList&amp;lt;>();
        devices.add(new Light("Living room lamp"));
        devices.add(new Speaker("Kitchen speaker"));
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>List&amp;lt;Device&amp;gt;</code></strong>The variable's type is the interface: its methods are all the code needs.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>The diamond <code>&amp;lt;&amp;gt;</code></strong>After <code>new</code>, the type can be left out: Java takes it from the variable.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>One word to change</strong>Another kind of list, such as a <code>LinkedList</code>, changes only what follows <code>new</code>.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
The variable is a <strong><code>List</code></strong>, the interface; the object, an <strong><code>ArrayList</code></strong>: from here on, every list is written this way.
</blockquote>

</div>

Note:
How lists are usually written. On entry, the import of List, an interface of java.util, and a variable of type List<Device>: as in unit 3, an interface is a type, and its variables refer to an object of any class that implements it, here an ArrayList. The code can only call the methods of List, which are all the methods of the table. Step 1, the empty angle brackets after new, called the diamond: the type is already in the variable, so there is no need to write it twice. Step 2, why the interface: if a LinkedList turned out to suit the app better, only the new would change, and so would nothing else in the program; the same goes for the parameters of methods, which take a List and accept any list. Last, the takeaway.

--

<div class="stage">

## No `int` in a list

### Wrapper classes

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="1|1|2-3|4-5|" data-fragment-index="0">
List&amp;lt;Integer> levels = new ArrayList&amp;lt;>();
levels.add(80);
levels.add(30);
int first = levels.get(0);
System.out.println(first + levels.get(1));
</code></pre>

</div>
<div class="column rules">
<div class="rule rule-error">
<p><strong>Not a primitive type</strong><code>List&amp;lt;int&amp;gt;</code> does not compile: a collection holds objects.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Wrapper classes</strong>An object for each primitive type, in <code>java.lang</code>: <code>Integer</code>, <code>Double</code>, <code>Boolean</code>, <code>Character</code>, etc.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Autoboxing</strong>Java turns an <code>int</code> into an <code>Integer</code> on the way in...</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Unboxing</strong>...and an <code>Integer</code> back into an <code>int</code> on the way out.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
For a primitive type, a collection uses its <strong>wrapper class</strong>; Java <strong>boxes</strong> and <strong>unboxes</strong> the values on its own.
</blockquote>

</div>

Note:
The brightness levels the app has used, in a list. On entry, in red: List<int> does not compile, unexpected type, required: reference, found: int. The type of the elements has to be a class, because a collection keeps references to objects. Step 1, the way out: each of the eight primitive types has a class whose objects hold one value of it, in java.lang, so with no import: Byte, Short, Integer, Long, Float, Double, Character and Boolean; only int and char change their name. Step 2, levels.add(80): 80 is an int, and Java turns it into an Integer by itself, which is called autoboxing. Step 3, the way back: levels.get(0) returns an Integer, and Java turns it into an int to store it in first, or to add it, unboxing. It prints 110. Two traps: an Integer can be null, and unboxing null throws a NullPointerException; and in a List<Integer>, remove(1) removes the element at position 1, not the number 1, which is remove(Integer.valueOf(1)). Last, the takeaway.

---

<div class="stage">

## A list of lights

### Does it compile?

<div class="code-run code-compact code-dense stage-top">

<!-- the last step marks the parameter and the call: the error under the code shows with it -->
```java [|2,13]
public class Main {
    public static double totalWatts(List<Device> devices) {
        double total = 0.0;
        for (Device device : devices) {
            total += device.watts();
        }
        return total;
    }

    public static void main(String[] args) {
        List<Light> lights = new ArrayList<>();
        lights.add(new Light("Living room lamp"));
        System.out.println(totalWatts(lights));
    }
}
```

<div class="code-run-output console-error">

```console
Main.java:13: error: incompatible types: List<Light> cannot be converted to List<Device>
```

</div>
</div>

<blockquote class="stage-note code-run-output">
A <code>List&amp;lt;Light&amp;gt;</code> is <strong>not</strong> a <code>List&amp;lt;Device&amp;gt;</code>, although every <code>Light</code> <strong>is</strong> a <code>Device</code>.
</blockquote>

</div>

Note:
A method that adds up the power of a list of devices, the total of unit 3, now with a list, and a call with a list of lights. On entry, the whole program: ask whether it compiles. Every Light is a Device, and a Light goes into a List<Device> without trouble, so most of them will say yes. Step 1, the answer: it does not. incompatible types: List<Light> cannot be converted to List<Device>. Upcasting works for the elements, not for the lists that hold them. Last, the takeaway; the reason is below.

--

<div class="stage">

## Why not?

### What the method could do with it

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="3|8|9|" data-fragment-index="0">
public class Main {
    public static void addThermostat(List&amp;lt;Device> devices) {
        devices.add(new Thermostat("Hallway thermostat", 21.5));
    }

    public static void main(String[] args) {
        List&amp;lt;Light> lights = new ArrayList&amp;lt;>();
        addThermostat(lights);
        Light lamp = lights.get(0);
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>Fine for a list of devices</strong>A thermostat is a device: adding one compiles.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="0">
<p><strong>If <code>lights</code> were accepted</strong>The thermostat would end up in a list of lights.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="1">
<p><strong>And come out as a <code>Light</code></strong><code>get</code> promises a <code>Light</code>, and gives a thermostat.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Java rejects the call so that a list never holds an element of the <strong>wrong type</strong>: generics are <strong>invariant</strong>.
</blockquote>

</div>

Note:
Why Java says no. On entry, another method that takes a List<Device>: it adds a thermostat, which is perfectly legal, since a thermostat is a device. Step 1, suppose the call with the list of lights compiled. The method has no way of knowing that the list it got is a list of lights, and the thermostat goes in. Step 2, the list still says Light, so get returns the thermostat as a Light, and calling setBrightness on it would fail where nothing can be checked: exactly what generics were meant to prevent. Java cannot look inside every method to see whether it adds something, so it rejects every such call. Last, the takeaway: the word for this rule is invariance, List<Light> and List<Device> are unrelated types. Arrays do allow it, a Light[] is a Device[], and pay for it with an ArrayStoreException when the program runs. A method that only reads, as totalWatts, can still take any list of devices, which is below.

--

<div class="stage">

## `? extends`

### Any list of devices, to read

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="2|2|4-5|2|" data-fragment-index="0">
public class Main {
    public static double totalWatts(List&amp;lt;? extends Device> devices) {
        double total = 0.0;
        for (Device device : devices) {
            total += device.watts();
        }
        return total;
    }

    // main, as before
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>A wildcard</strong><code>?</code> is a type the method does not know; <code>extends Device</code>, that it is <code>Device</code> or a subclass of it.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Any of them</strong>A <code>List&amp;lt;Light&amp;gt;</code>, a <code>List&amp;lt;Speaker&amp;gt;</code> or a <code>List&amp;lt;Device&amp;gt;</code>: <code>totalWatts(lights)</code> compiles.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Read as a <code>Device</code></strong>Whatever the type is, each element is a device.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="2">
<p><strong>No adding</strong><code>devices.add(...)</code> does not compile: it could be a list of lights, or of speakers.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
<strong><code>? extends Device</code></strong> accepts a list of <code>Device</code> or of <strong>any subclass</strong>, to <strong>read</strong> its elements as devices.
</blockquote>

</div>

Note:
The fix for totalWatts. On entry, the parameter: a question mark, called a wildcard, stands for a type the method does not know, and extends Device bounds it from above, Device or something under it. Step 1, the call of the slide before now compiles, and so would one with a list of speakers. Step 2, inside, the for-each reads each element as a Device, which is true whatever the list is, so watts() can be called on it. Step 3, in red, the price: adding does not compile, not even a Light, because the list could be a List<Speaker>; the compiler's message speaks of a capture, CAP#1, its name for that unknown type. It is the thermostat of the slide before, stopped where it is written. Last, the takeaway. A method that needs to add is the other way round, below.

--

<div class="stage">

## `? super`

### Any list a light fits in, to add

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="2|3|2|" data-fragment-index="0">
public class Main {
    public static void addLamp(List&amp;lt;? super Light> devices) {
        devices.add(new Light("Bedroom lamp"));
    }

    // main, as before
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>super Light</code></strong><code>Light</code> or a superclass of it: a <code>List&amp;lt;Light&amp;gt;</code>, a <code>List&amp;lt;Device&amp;gt;</code> or a <code>List&amp;lt;Object&amp;gt;</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Adding a light</strong>It fits in any of them: it compiles.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>Read as an <code>Object</code></strong>The only type all of them share: <code>get</code> returns an <code>Object</code>.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
<strong><code>? super Light</code></strong> accepts a list of <code>Light</code> or of <strong>any superclass</strong>, to <strong>add</strong> lights to it.
</blockquote>

</div>

Note:
The other bound. On entry, the parameter: super bounds the wildcard from below, Light or something above it, Device, or Object. Step 1, adding a Light compiles: a lamp can go into a list of lights, of devices or of objects. A Thermostat would not compile, since the list could be a List<Light>. Step 2, in orange, the price this time: what comes out could be a Light, a Device or any object, so get returns an Object. Last, the takeaway; both, side by side, below.

--

<div class="stage">

## Which lists can it take?

### Three parameters, compared

<table class="data-table verdict-table stage-body">
<thead>
<tr><th>Parameter</th><th class="mark-head">Takes a <code>List&amp;lt;Light&amp;gt;</code></th><th class="mark-head">Takes a <code>List&amp;lt;Device&amp;gt;</code></th><th class="mark-head">Reads a <code>Device</code></th><th class="mark-head">Adds a <code>Light</code></th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>List&amp;lt;Device&amp;gt;</code></th><td class="mark mark-error fragment custom" data-fragment-index="0">✗</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td></tr>
<tr><th scope="row"><code>List&amp;lt;? extends Device&amp;gt;</code></th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-error fragment custom" data-fragment-index="1">✗</td></tr>
<tr><th scope="row"><code>List&amp;lt;? super Light&amp;gt;</code></th><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="mark mark-error fragment custom" data-fragment-index="2">✗</td><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="3">
<strong><code>extends</code></strong> to <strong>read</strong> from a list of any subclass; <strong><code>super</code></strong> to <strong>add</strong> to a list of any superclass.
</blockquote>

</div>

Note:
The three parameters of this section, each asked the same four questions; ask about each row before filling it in. List<Device>: only a list of exactly Device, but it can do everything with it. List<? extends Device>: any list of devices, read as devices, and nothing added. List<? super Light>: any list a light fits in, lights added, and nothing read as a Device, only as an Object. A rule of thumb to remember it: a list the method takes elements out of is extends; a list it puts elements into is super; one it does both with is neither. Last, the takeaway.

---

<div class="stage">

## Exercises

### What you can solve so far

<div class="rules rules-grid rules-three stage-top">
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Collections/Exercise%201">Exercise 1</a></strong>Colors: the methods of a list that takes any object, step by step.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Collections/Exercise%202">Exercise 2</a></strong>Colors again, in a list of strings: what the compiler now checks.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Collections/Exercise%203">Exercise 3</a></strong>Dogs and cats in one list, told apart with <code>instanceof</code>.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Collections/Exercise%204">Exercise 4</a></strong>Pets: a list of the superclass, and the methods each subclass adds.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Collections/Exercise%205">Exercise 5</a></strong>Pets: methods that take lists of any kind of pet, with <code>? extends</code> and <code>? super</code>.</p>
</div>
</div>

</div>

Note:
The exercises that can be solved with lists, generics and wildcards. They are in the JetBrains Academy course, in the Collections lesson; each title opens it on GitHub.

---

<!-- .slide: class="contents" data-section="3" -->

Note:
Lists now hold one type, checked by the compiler. They have been gone through with a for-each, as arrays. Now what a for-each does underneath, and what happens when the list changes while it runs.

---

<div class="stage">

## Going through a list

### Three ways

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="1-3|5-7|9|10-11|" data-fragment-index="0">
for (int i = 0; i &amp;lt; devices.size(); i++) {
    System.out.println(devices.get(i));
}

for (Device device : devices) {
    System.out.println(device);
}

Iterator&amp;lt;Device> iterator = devices.iterator();
while (iterator.hasNext()) {
    Device device = iterator.next();
    System.out.println(device);
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>By position</strong>Only in a list: sets and maps have no positions.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>For-each</strong>Any collection, as with an array: the usual way.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>Iterator</code></strong>An object that goes through a collection: <code>iterator()</code> gives one, at the start.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong><code>hasNext()</code> and <code>next()</code></strong>Whether there is an element left, and the next one, moving forward.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
A for-each goes through a collection with an <strong><code>Iterator</code></strong>: the same loop, written out.
</blockquote>

</div>

Note:
Three loops that print the same devices. On entry, the loop by position, as with an array in unit 1, with size() and get(i): it only works with lists. Step 1, the for-each of unit 1, which works with any collection, and the one to use when the position does not matter. Step 2, what Java turns the for-each into: every collection has a method iterator(), which returns an object that implements the interface Iterator, from java.util, placed before the first element. Step 3, its two methods: hasNext() says whether there is an element left, and next() returns it and moves on; calling next() with none left throws a NoSuchElementException. The while loop is the for-each written out, with the same output. Last, the takeaway. Nobody writes this loop to print; the iterator is worth it for one thing, below.

--

<div class="stage">

## Removing in a for-each

### Does it work?

<div class="code-run code-compact stage-top">

<!-- the last step marks the for: the error under the code shows with it -->
```java [|3|1]
for (Device device : devices) {
    if (!device.isOn()) {
        devices.remove(device);
    }
}
```

<div class="code-run-output console-error">

```console
Exception in thread "main" java.util.ConcurrentModificationException
    at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
    at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
    at Main.main(Main.java:9)
```

</div>
</div>

<blockquote class="stage-note code-run-output">
Changing a collection <strong>in a for-each</strong> over it stops the program with a <strong><code>ConcurrentModificationException</code></strong>.
</blockquote>

</div>

Note:
A list of the devices that are on, for the power screen of the app: the ones that are off have to go. devices holds the speaker, the thermostat, the living room lamp, which is on, and the bedroom lamp. On entry, the loop: ask whether it works. Step 1, the line that removes: it compiles, and looks right. Step 2, the answer when it runs: the speaker is removed, and the next turn of the loop throws a ConcurrentModificationException, from unit 4's hierarchy, an unchecked one. The trace goes through ArrayList's iterator, Itr, and ends at the line of the for, in Main: the hidden next() of the for-each. The iterator noticed that the list changed behind its back, and stops at once rather than skip elements or read past the end; Java's collections are fail-fast. Sometimes the loop ends quietly instead, when the element removed is the second to last one: worse, since the last one is never checked. Last, the takeaway; the fix is below.

--

<div class="stage">

## `iterator.remove()`

### Removing through the iterator

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="5|1-3|3,5|" data-fragment-index="0">
Iterator&amp;lt;Device> iterator = devices.iterator();
while (iterator.hasNext()) {
    Device device = iterator.next();
    if (!device.isOn()) {
        iterator.remove();
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>The iterator removes it</strong><code>remove()</code> takes out of the collection the element <code>next()</code> just returned.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>And knows it</strong>The iterator stays in step with the collection: no exception.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>Once per <code>next()</code></strong>Called twice in a row, or before <code>next()</code>, it throws an <code>IllegalStateException</code>.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
To remove while going through a collection, the <strong>iterator</strong> removes with its own <strong><code>remove()</code></strong>.
</blockquote>

</div>

Note:
The loop written out, which is what makes the fix possible. On entry, the line that changes: the iterator, not the list, removes the element, the last one next() returned. Step 1, the loop around it, as on the first slide of the section: the iterator removes the element and moves its own position back, so it is still in step with the list, and the next next() returns the element that came after the removed one. Step 2, in orange, the rule: one remove() per next(); calling it before any next(), or twice in a row, throws an IllegalStateException, from unit 4's hierarchy too. It works with any collection, sets included. Since Java 8, devices.removeIf(...) does the same in one line, but it needs syntax this course does not cover. Last, the takeaway.

--

<div class="stage">

## Which ones work?

### Removing the devices that are off

<table class="data-table verdict-table stage-body">
<thead>
<tr><th>The loop</th><th class="mark-head">Compiles?</th><th class="mark-head">Works?</th><th>Why</th></tr>
</thead>
<tbody>
<tr><th scope="row">For-each, <code>devices.remove(device)</code></th><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-error fragment custom" data-fragment-index="0">✗</td><td class="fragment custom" data-fragment-index="0">A <code>ConcurrentModificationException</code></td></tr>
<tr><th scope="row">By position from 0, <code>devices.remove(i)</code></th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="1">✗</td><td class="fragment custom" data-fragment-index="1">The next element moves back to <code>i</code>, and is skipped</td></tr>
<tr><th scope="row">By position from the end, <code>devices.remove(i)</code></th><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="fragment custom" data-fragment-index="2">Only the elements already checked move</td></tr>
<tr><th scope="row"><code>Iterator</code>, <code>iterator.remove()</code></th><td class="mark mark-ok fragment custom" data-fragment-index="3">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="3">✓</td><td class="fragment custom" data-fragment-index="3">The iterator stays in step with the list</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="4">
Remove with the <strong>iterator</strong>, or by position <strong>from the end</strong>: never with the list's own <code>remove</code> in a for-each.
</blockquote>

</div>

Note:
Four ways to write the same loop, all of which compile; ask about each row before filling it in. The for-each, from two slides before: the exception. By position, from 0 up to size(): when the element at i is removed, the next one moves back to i, and i++ jumps over it, so two devices in a row that are off leave the second one in the list, with no error at all, in orange. By position from the end, from size() - 1 down to 0: removing the element at i only moves the ones after it, which have already been checked, so nothing is skipped. With the iterator: the slide before. For a list, the last two are both right; for a set, which has no positions, only the iterator works. Last, the takeaway.

---

<div class="stage">

## Exercises

### The section on iterators

<div class="rules rules-grid rules-pair stage-top">
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Collections/Exercise%206">Exercise 6</a></strong>Pets: going through a list with an iterator, and removing from it while going through it.</p>
</div>
</div>

</div>

Note:
The exercise that needs this section: iterators, and removing elements in the two ways that work. It is in the JetBrains Academy course, in the Collections lesson; the title opens it on GitHub.

---

<!-- .slide: class="contents" data-section="4" -->

Note:
Lists keep their elements in order and go through them in several ways. But a list lets the same device in twice, and finding a device by its name means going through all of them. Now the other two kinds of the family: sets and maps.

---

<div class="stage">

## `Set`

### No duplicates

<div class="columns code-compact stage-top">
<div class="column code-over-cards code-compact">

<!-- Each println alone first, to ask, then its line of output; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1-2|3|3|4|4|1|" data-fragment-index="0">
Set&amp;lt;Device> devices = new HashSet&amp;lt;>();
Device lamp = new Light("Living room lamp");
System.out.println(devices.add(lamp));
System.out.println(devices.add(lamp));
</code></pre>
<div class="fragment" data-fragment-index="1">
<pre><code class="nohighlight hljs console" data-noescape>true
<span class="fragment" data-fragment-index="3">false</span></code></pre>
</div>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>Set</code></strong>A collection with no element twice, and no positions: no <code>get(i)</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>add</code> says whether it went in</strong><code>true</code>: it was not there.</p>
</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong>Already there</strong><code>false</code>: the set is left as it was.</p>
</div>
<div class="rule fragment" data-fragment-index="4">
<p><strong>Three classes</strong><code>HashSet</code>, in no order; <code>LinkedHashSet</code>, in the order added; <code>TreeSet</code>, sorted.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="5"><span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="2"></span>
A <strong><code>Set</code></strong> never holds the <strong>same element twice</strong>: adding one that is there changes nothing.
</blockquote>

</div>

Note:
The app must not have the same device twice, which unit 3 already worried about. On entry, a Set of devices, written as lists are, the interface for the variable and a class for the object, both in java.util, and a lamp. Then each println alone, to ask what it prints, and its line of output on the next step. add returns a boolean, which lists ignore because they always add: true, the lamp went in. The same lamp again: false, it was already there, so the set still has one element. A set has no positions, so no get(i) and no add(i, e); it is gone through with a for-each or an iterator, and asked with contains. Step 5, the three classes that implement it, which differ in the order a for-each gives: HashSet, the fastest, in an order that looks random; LinkedHashSet, in the order the elements were added; TreeSet, sorted, which needs the last section. Last, the takeaway; but the same means equal, which is below.

--

<div class="stage">

## Two equal lamps, again

### How many does it keep?

<div class="code-run code-compact stage-top">

<!-- The println alone first, to ask, then its output; the empty span in the note holds an index only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1-3|4|4|2-3" data-fragment-index="0">
Set&amp;lt;Device> devices = new HashSet&amp;lt;>();
devices.add(new Light("Living room lamp"));
devices.add(new Light("Living room lamp"));
System.out.println(devices.size());
</code></pre>

<div class="fragment" data-fragment-index="1">
<pre><code class="nohighlight hljs console" data-noescape>2</code></pre>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2"><span class="fragment" data-fragment-index="0"></span>
<code>equals</code> says they are <strong>equal</strong>, and the <code>HashSet</code> keeps <strong>both</strong>: it looks at something else first.
</blockquote>

</div>

Note:
The two lamps of unit 3, created apart with the same name, which equals says are equal since unit 3 overrode it. On entry, both added to a set. Step 1, the println: ask what it prints; they will say 1. Step 2, 2: the set kept both. A HashSet does not compare each new element with all the others, which would be slow with thousands; it first asks the element for a number, its hash code, which tells it where to look, and only compares with equals the elements it finds there. Device does not override that method yet, so each lamp gets Object's, a different number for each object, and the set never even compares them. Last, the takeaway, with the two adds marked; what to override is below.

--

<div class="stage">

## Overriding `hashCode`

### Equal objects, equal codes

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="5|6|4-7|" data-fragment-index="0">
public abstract class Device {
    // attributes, constructor and methods, as before

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>hashCode()</code></strong>From <code>Object</code>: an <code>int</code> for each object, which <code>HashSet</code> and <code>HashMap</code> use to find it.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>The attributes of <code>equals</code></strong>Two equal devices have the same name, so the same code.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>Both, or neither</strong>A class that overrides <code>equals</code> alone breaks every hash collection: IntelliJ generates the two.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
<strong>Equal</strong> objects must have <strong>equal hash codes</strong>: a class that overrides <code>equals</code> overrides <strong><code>hashCode</code></strong> too.
</blockquote>

</div>

Note:
The rule unit 3 left for this unit. On entry, hashCode, another method of Object, which every class inherits and can override: it returns an int that a HashSet or a HashMap uses to decide where an object goes. Step 1, the rule: two objects that equals says are equal must return the same number, or the set looks for one in the wrong place. Device's equals compares the name, so its hashCode is the name's own, which String already computes from its characters. Two different objects may share a code, the set then compares them with equals, so the code does not have to be unique, only consistent. Step 2, in orange: it compiles and runs without it, and the set just keeps duplicates, a bug that is hard to see; IntelliJ writes both with Code, Generate, equals() and hashCode(). With it, the set of the slide before prints 1. Last, the takeaway, with the method marked.

---

<div class="stage">

## `Map`

### A value for each key

<div class="columns code-compact code-dense stage-top">
<div class="column code-over-cards code-compact">

<!-- Each println alone first, to ask, then its line of output; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1|2-3|4|4|5|5|" data-fragment-index="0">
Map&amp;lt;String, Device> byName = new HashMap&amp;lt;>();
byName.put("Living room lamp", new Light("Living room lamp"));
byName.put("Kitchen speaker", new Speaker("Kitchen speaker"));
System.out.println(byName.get("Kitchen speaker"));
System.out.println(byName.get("Garage door"));
</code></pre>
<div class="fragment" data-fragment-index="2">
<pre><code class="nohighlight hljs console" data-noescape>Kitchen speaker: Off
<span class="fragment" data-fragment-index="4">null</span></code></pre>
</div>

</div>
<div class="column rules">
<div class="rule">
<p><strong>Two types</strong>The key's and the value's: here, a name and its device.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>put</code></strong>Adds a pair: a key appears once, so putting it again replaces its value.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong><code>get</code></strong>The value of a key, found at once, with no loop.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="4">
<p><strong>Not there: <code>null</code></strong><code>containsKey</code> asks first whether a key is in the map.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="5"><span class="fragment" data-fragment-index="1"></span><span class="fragment" data-fragment-index="3"></span>
A <strong><code>Map</code></strong> stores <strong>pairs</strong>: it finds the <strong>value</strong> of a <strong>key</strong> without going through the rest.
</blockquote>

</div>

Note:
The user says the name of a device, and the app has to find it: with a list, that is a loop comparing names. On entry, a Map from String to Device, two types in the angle brackets, the key's and the value's; HashMap, its usual class, uses the hash codes of the keys, as HashSet. Step 1, put adds a pair, a key and its value; the keys are a set, each one once, so a second put with the same name would replace the device. Then each println alone, to ask what it prints, and its line of output on the next step. get with a key returns its value, found without going through the others. get with a key that is not there returns null, which is the NullPointerException of unit 4 waiting to happen if the code calls a method on it; containsKey(key) says whether it is there, and remove(key) takes the pair out. Last, the takeaway.

--

<div class="stage">

## Going through a map

### Its keys, its values

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="1|6|1,6|" data-fragment-index="0">
for (String name : byName.keySet()) {
    System.out.println(name);
}

double total = 0.0;
for (Device device : byName.values()) {
    total += device.watts();
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>keySet()</code></strong>Its keys, as a <code>Set</code>: none twice.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>values()</code></strong>Its values, as a <code>Collection</code>: they can repeat.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>In what order</strong><code>HashMap</code>, none; <code>LinkedHashMap</code>, the order added; <code>TreeMap</code>, its keys sorted.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A map is <strong>not a collection</strong>, but its <strong>keys</strong> and its <strong>values</strong> are: a for-each goes through them.
</blockquote>

</div>

Note:
A map has no iterator of its own, since it is not a Collection; it gives its keys and its values as collections. On entry, keySet: the keys, as a Set, since there are no repeated keys; here, the names of the devices. Step 1, values: the values, as a Collection, since two keys could have the same value; here, the power of the whole house, as in unit 3. Step 2, the order they come in depends on the class, as with sets: none for a HashMap, the order they were added for a LinkedHashMap, sorted by key for a TreeMap. To go through the pairs, entrySet() gives each key with its value, getKey() and getValue(). Last, the takeaway.

---

<div class="stage">

## List, set or map?

### Which one the app needs

<table class="data-table compare-table stage-body">
<thead>
<tr><th></th><th>List</th><th>Set</th><th>Map</th></tr>
</thead>
<tbody>
<tr><th scope="row">Order</th><td class="fragment custom" data-fragment-index="0">By position, from 0</td><td class="fragment custom" data-fragment-index="0">None, or sorted</td><td class="fragment custom" data-fragment-index="0">None, or sorted by key</td></tr>
<tr class="fragment" data-fragment-index="1"><th scope="row">Duplicates</th><td class="fragment custom" data-fragment-index="2">Allowed</td><td class="fragment custom" data-fragment-index="2">Never</td><td class="fragment custom" data-fragment-index="2">Keys never, values allowed</td></tr>
<tr class="fragment" data-fragment-index="3"><th scope="row">Finds an element by</th><td class="fragment custom" data-fragment-index="4">Its position, or a loop</td><td class="fragment custom" data-fragment-index="4">Itself, at once: <code>contains</code></td><td class="fragment custom" data-fragment-index="4">Its key, at once: <code>get</code></td></tr>
<tr class="fragment" data-fragment-index="5"><th scope="row">In the app</th><td class="fragment custom" data-fragment-index="6">The tiles, in the order the user chose</td><td class="fragment custom" data-fragment-index="6">The devices, none twice</td><td class="fragment custom" data-fragment-index="6">A device by its name</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="7">
A <strong>list</strong> for an <strong>order</strong>, a <strong>set</strong> for <strong>no duplicates</strong>, a <strong>map</strong> to find by a <strong>key</strong>.
</blockquote>

</div>

Note:
The three kinds side by side; each row shows its question first: ask for all three answers before filling them in. Order: a list by position; a set or a map in none, unless it is a Tree one, sorted, or a Linked one, in the order added. Duplicates: a list allows them, a set never, a map never in its keys, though two keys can have the same value. Finding an element: a list by position, or going through it; a set answers contains at once, a map get, with no loop, thanks to the hash codes. In the app: the home screen, whose tiles the user puts in an order, is a list; the devices of the house, none twice, a set; finding one by the name the user says, a map. Last, the takeaway.

---

<!-- .slide: class="contents" data-section="5" -->

Note:
They choose the collection that fits, and its elements come out in an order that depends on the class. Collections.sort put the rooms in alphabetical order in the first section. Now how a class says what order its objects go in.

---

<div class="stage">

## Sorting the devices

### Does it compile?

<div class="code-run code-compact stage-top">

<!-- the last step marks the sort: the error under the code shows with it -->
```java [|4]
List<Device> devices = new ArrayList<>();
devices.add(new Speaker("Kitchen speaker"));
devices.add(new Light("Living room lamp"));
Collections.sort(devices);
```

<div class="code-run-output console-error">

```console
Main.java:4: error: no suitable method found for sort(List<Device>)
```

</div>
</div>

<blockquote class="stage-note code-run-output">
To sort, Java has to know <strong>which of two elements goes first</strong>: strings know it, devices do not.
</blockquote>

</div>

Note:
The rooms were sorted in the first section with Collections.sort; now the devices, to show them in order on the screen. On entry, the code: ask whether it compiles. Step 1, the answer: it does not, no suitable method found for sort(List<Device>); the rest of the message, about inference variables and bounds, says that sort only takes a list of something comparable. Ask them which device should go first: by name, by power, by kind? Java cannot guess; String has an order of its own, alphabetical, and Device has none yet. Last, the takeaway; how to give it one is below.

--

<div class="stage">

## `Comparable`

### The natural order of a class

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="1|5|6|" data-fragment-index="0">
public abstract class Device implements Comparable&amp;lt;Device> {
    // attributes, constructor and methods, as before

    @Override
    public int compareTo(Device other) {
        return this.name.compareTo(other.name);
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>Comparable&amp;lt;T&amp;gt;</code></strong>An interface of <code>java.lang</code> with one method, which <code>String</code> implements.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>compareTo</code></strong>Negative if <code>this</code> goes first, 0 if they are equal, positive if it goes after: as <code>strcmp</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>By name</strong><code>String</code>'s own <code>compareTo</code> does the work: alphabetical order.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Implementing <strong><code>Comparable</code></strong> gives a class its <strong>natural order</strong>: the one <code>sort</code> uses.
</blockquote>

</div>

Note:
How a class says its order: by implementing an interface, as Light implements Adjustable in unit 3. On entry, the header: Device implements Comparable<Device>, a generic interface of java.lang, so with no import, whose type says what devices are compared with, other devices. Step 1, its one method: compareTo compares this device with another and returns an int, negative if this goes first, 0 if they are equal, positive if it goes after, exactly as strcmp in C. Only the sign matters. Step 2, the body: the devices go by name, and the names are strings, which already implement Comparable, so their compareTo does the comparing. Every subclass inherits it, so lamps, thermostats and speakers can be sorted together. Last, the takeaway.

--

<div class="stage">

## What does `compareTo` return?

### Comparing strings

<table class="data-table stage-body">
<thead>
<tr><th>The call</th><th>Returns</th><th>Goes first</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>"Bedroom lamp".compareTo("Kitchen speaker")</code></th><td class="fragment custom" data-fragment-index="0">-9, negative</td><td class="fragment custom" data-fragment-index="0"><code>"Bedroom lamp"</code></td></tr>
<tr><th scope="row"><code>"Kitchen speaker".compareTo("Hallway thermostat")</code></th><td class="fragment custom" data-fragment-index="1">3, positive</td><td class="fragment custom" data-fragment-index="1"><code>"Hallway thermostat"</code></td></tr>
<tr><th scope="row"><code>"Kitchen speaker".compareTo("Kitchen speaker")</code></th><td class="fragment custom" data-fragment-index="2">0</td><td class="fragment custom" data-fragment-index="2">Neither: they are equal</td></tr>
<tr><th scope="row"><code>"bedroom lamp".compareTo("Kitchen speaker")</code></th><td class="fragment custom" data-fragment-index="3">23, positive</td><td class="fragment custom" data-fragment-index="3"><code>"Kitchen speaker"</code>: capitals go before</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="4">
Only the <strong>sign</strong> matters: <strong>negative</strong>, the first goes first; <strong>positive</strong>, the second; <strong>0</strong>, they are equal.
</blockquote>

</div>

Note:
The comparison Device relies on, with some of its names; ask about each row before filling it in. String's compareTo looks for the first character that differs and returns the difference of their Unicode codes, from unit 1. B is 66 and K is 75, so -9: Bedroom lamp goes first. K and H, 75 and 72, 3: the thermostat goes first. The same text, 0. The last one is the trap: b is 98, so it goes after K, 75; every capital letter goes before every lowercase one. compareToIgnoreCase ignores the case. They should not rely on the exact numbers, only on the sign. Last, the takeaway.

--

<div class="stage">

## Sorted by name

### What does it print?

<div class="code-run code-compact stage-top">

<!-- the last step repeats the loop: the output under the code shows with it -->
```java [1-5|6|7-9|7-9]
List<Device> devices = new ArrayList<>();
devices.add(new Speaker("Kitchen speaker"));
devices.add(new Thermostat("Hallway thermostat", 21.5));
devices.add(new Light("Living room lamp"));
devices.add(new Light("Bedroom lamp"));
Collections.sort(devices);
for (Device device : devices) {
    System.out.println(device);
}
```

<div class="code-run-output">

```console
Bedroom lamp: Off, 100%
Hallway thermostat: Off, 21.5 °C
Kitchen speaker: Off
Living room lamp: Off, 100%
```

</div>
</div>

<blockquote class="stage-note code-run-output">
<code>Collections.sort</code>, <strong><code>TreeSet</code></strong> and <strong><code>TreeMap</code></strong> keep their elements in their <strong>natural order</strong>, by <code>compareTo</code>.
</blockquote>

</div>

Note:
The four devices, added in no particular order. On entry, the list. Step 1, the sort that did not compile three slides before: it does now, since Device implements Comparable. Step 2, a for-each that prints them: ask what it prints. Step 3, in alphabetical order of their names, whatever their kind. A TreeSet<Device> or a TreeMap with Device keys would keep them in this order as they are added, with no sort at all, using the same compareTo. Another order for another screen, say by power, is given with a Comparator, an object passed to sort, which they will meet in later courses. Last, the takeaway.

---

<div class="stage">

## Unit summary

### What to take away from Collections

<div class="rules rules-grid stage-body">
<div class="rule">
<p><strong>Lists</strong>An <strong><code>ArrayList</code></strong> keeps its elements <strong>by position</strong> and grows as needed: <code>add</code>, <code>get</code>, <code>set</code>, <code>remove</code>, <code>size</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Generics</strong><strong><code>List&amp;lt;Device&amp;gt;</code></strong> holds only devices, checked by the compiler; <strong><code>? extends</code></strong> to read, <strong><code>? super</code></strong> to add.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Iterators</strong>A for-each uses an <strong><code>Iterator</code></strong>; to remove while going through, its <strong><code>remove()</code></strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Sets and maps</strong>A <strong><code>Set</code></strong> has no duplicates, with <strong><code>equals</code></strong> and <strong><code>hashCode</code></strong>; a <strong><code>Map</code></strong> finds a value by its <strong>key</strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong>Sorting</strong>A class that implements <strong><code>Comparable</code></strong> has a <strong>natural order</strong>, which <code>Collections.sort</code> uses.</p>
</div>
</div>

</div>

Note:
Ask the class to recall an example of each section before showing the next one: the fourth device, Home and the list of rooms; the name of the speaker that got into the list, and the list of lights that was not a list of devices; the devices that were off and the ConcurrentModificationException; the two equal lamps in a HashSet, and the devices by name; the devices sorted by name. The next unit changes how a program runs: instead of going from the first line of main to the last, it waits for the user and reacts to what they do, event-driven programming.
