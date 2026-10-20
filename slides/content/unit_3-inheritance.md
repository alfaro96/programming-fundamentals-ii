<!-- .slide: class="cover" -->

## Unit 3: Inheritance

### Classes built on other classes

Note:
They write classes with attributes, constructors and methods, use static and final, and overload (unit 2). This unit is about classes that share a part: how to write that part once, in a class the others build on, and how to treat objects of different classes alike. The example of the whole unit is a smart home app that controls the devices of a house from the phone: a lamp, a thermostat and a speaker.

---

<!-- .slide: class="contents" data-section="1" -->

1. Inheritance
2. Access control
3. Overriding
4. Polymorphism
5. Abstract classes and interfaces

Note:
Walk through the index quickly; the details come in each section.

---

<div class="stage">

## Three smart devices

### What do they have in common, and what not?

<div class="phones stage-body">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a7 7 0 0 0-4 12.74V17a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1v-2.26A7 7 0 0 0 12 2zM9 20h6v2H9z"/></svg></span>
</div>
<div>
<p class="phone-greeting">Light</p>
<p class="phone-name">Living room lamp</p>
<p class="phone-power phone-power-on">On</p>
</div>
<div class="phone-account">
<p class="phone-label">Brightness</p>
<p class="phone-balance">80%</p>
<progress class="phone-level" value="80" max="100"></progress>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn on</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn off</span>
<span class="phone-button phone-button-wide"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M3 6h10v2H3zm12-2h2v6h-2zm2 2h4v2h-4zM3 16h4v2H3zm6-2h2v6H9zm4 2h8v2h-8z"/></svg>Set brightness</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path fill-rule="evenodd" d="M12 2a3 3 0 0 0-3 3v8.26a5 5 0 1 0 6 0V5a3 3 0 0 0-3-3zm0 2a1 1 0 0 1 1 1v9.32l.4.3a3 3 0 1 1-2.8 0l.4-.3V5a1 1 0 0 1 1-1z"/></svg></span>
</div>
<div>
<p class="phone-greeting">Thermostat</p>
<p class="phone-name">Hallway thermostat</p>
<p class="phone-power phone-power-on">On</p>
</div>
<div class="phone-account">
<p class="phone-label">Target temperature</p>
<p class="phone-balance">21.5 °C</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn on</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn off</span>
<span class="phone-button phone-button-wide"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M3 6h10v2H3zm12-2h2v6h-2zm2 2h4v2h-4zM3 16h4v2H3zm6-2h2v6H9zm4 2h8v2h-8z"/></svg>Set temperature</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M3 9v6h4l5 4V5L7 9zm13.5 3a4.5 4.5 0 0 0-2.5-4v8a4.5 4.5 0 0 0 2.5-4z"/></svg></span>
</div>
<div>
<p class="phone-greeting">Speaker</p>
<p class="phone-name">Kitchen speaker</p>
<p class="phone-power">Off</p>
</div>
<div class="phone-account">
<p class="phone-label">Volume</p>
<p class="phone-balance">30%</p>
<progress class="phone-level" value="30" max="100"></progress>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn on</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn off</span>
<span class="phone-button phone-button-wide"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M3 6h10v2H3zm12-2h2v6h-2zm2 2h4v2h-4zM3 16h4v2H3zm6-2h2v6H9zm4 2h8v2h-8z"/></svg>Set volume</span>
</div>
<span class="phone-home"></span>
</div>
</div>
</div>

</div>

Note:
The example of the unit: a home app that controls the devices of a house, each one with its own screen. Don't say "inheritance" yet. Ask first what the three screens have in common: a name, whether the device is on or off, and the two buttons to turn it on and off. Then what is different: the lamp has a brightness, the thermostat a target temperature and the speaker a volume, each with its own button to set it. In unit 2 the three accounts were one template with different values; here part of the screen is the same for every device and part depends on the kind of device. The answer comes below.

--

<div class="stage">

## What they share

### And what each kind of device adds

<div class="phone-beside stage-body">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"></span>
</div>
<div>
<p class="phone-greeting">Device</p>
<p class="phone-name phone-slot">Name</p>
</div>
<p class="phone-power phone-slot">On</p>
<p class="phone-own">Its own data and operations</p>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn on</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn off</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone-beside-cards">
<div class="rule rule-state fragment">
<p><strong>Shared data</strong>A name, and whether it is on: every device has them, each with its own values.</p>
</div>
<div class="rule rule-behavior fragment">
<p><strong>Shared operations</strong>Turn on and turn off: they work the same on any device.</p>
</div>
<div class="rule rule-own fragment">
<p><strong>Its own part</strong>Brightness, temperature or volume, and the operation to set it: each kind adds its own.</p>
</div>
<blockquote class="fragment">
Every device has a <strong>shared part</strong>, and each kind adds <strong>its own</strong>.
</blockquote>
</div>
</div>

</div>

Note:
The answer to the slide above. Take away what changes and what is left is the part every device shares. First the data: a name and whether it is on, each device with its own values, as the accounts in unit 2. Then the operations: turning a device on or off works the same whatever the device is. Last, the dashed box, now in blue: the part each kind fills in its own way, a brightness and a button to set it for the lamp, a temperature for the thermostat, a volume for the speaker. Then ask them how they would write the three classes with what they know from unit 2. The answer is below.

--

<div class="stage">

## Two classes, by hand

### With what we know from unit 2

<div class="columns code-diff code-compact code-dense stage-top">
<div class="column">

#### `Light.java`

<!-- Both blocks step together from 0: the shared lines, then its own, then the shared again with the note.
     The empty spans hold indices 0 and 1, which only the code uses: reveal renumbers indices without gaps -->
<span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="1"></span>
<pre><code class="java" data-trim data-line-numbers="|2-3,6-12|4,14-16|2-3,6-12" data-fragment-index="0">
class Light {
    String name;
    boolean on;
    int brightness;

    void turnOn() {
        this.on = true;
    }

    void turnOff() {
        this.on = false;
    }

    void setBrightness(int brightness) {
        this.brightness = brightness;
    }
}
</code></pre>

</div>
<div class="column">

#### `Thermostat.java`

<pre><code class="java" data-trim data-line-numbers="|2-3,6-12|4,14-16|2-3,6-12" data-fragment-index="0">
class Thermostat {
    String name;
    boolean on;
    double temperature;

    void turnOn() {
        this.on = true;
    }

    void turnOff() {
        this.on = false;
    }

    void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
</code></pre>

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Every class repeats the <strong>shared part</strong>: a change to it has to be made in <strong>every copy</strong>.
</blockquote>

</div>

Note:
Their answer, for two of the three devices (the speaker would be a third class, with volume and setVolume). On entry, both whole: ask them what they notice. Step 1, the shared lines: two attributes and two methods, word for word the same in both. Step 2, what each one adds: one attribute and one method. Step 3, the cost: if turning a device on had to record the time it happened, the change would go in three classes, and in every class added later; a copy that is forgotten behaves differently from the rest without anyone noticing. And nothing in the code says that the three are devices: for Java they are three unrelated classes, so there is no way to keep them in one array, something the app will need. Classes can share that part instead of copying it, which is what comes next.

---

<div class="stage">

## Superclass and subclasses

### The shared part, written once

<div class="columns stage-body">
<div class="column">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Device</code></p>
<p class="class-attributes"><code>name</code><code>on</code></p>
<p class="class-methods"><code>turnOn()</code><code>turnOff()</code></p>
</div>
<div class="hierarchy-children fragment" data-fragment-index="0">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Light</code></p>
<p class="class-attributes"><code>brightness</code></p>
<p class="class-methods"><code>setBrightness()</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Thermostat</code></p>
<p class="class-attributes"><code>temperature</code></p>
<p class="class-methods"><code>setTemperature()</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Speaker</code></p>
<p class="class-attributes"><code>volume</code></p>
<p class="class-methods"><code>setVolume()</code></p>
</div>
</div>
</div>
</div>
</div>
<div class="column rules">
<div class="rule">
<p><strong>Superclass</strong>The shared part, in a class of its own: what all its subclasses <strong class="text-state">have</strong> and <strong class="text-behavior">do</strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Subclasses</strong>Each one with only what it adds to its superclass.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Inheritance</strong>A subclass gets every attribute and method of its superclass without writing them again.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
The shared part goes in a <strong>superclass</strong>; each <strong>subclass</strong> inherits it and adds its own.
</blockquote>

</div>

Note:
The fix for the copies. On entry, Device: the part every device shares, two attributes and two methods, written once. Step 1, the three kinds of device under it, each box with only what it adds: a lamp is a device with a brightness. Step 2, the name for it: Light inherits everything Device has, so a Light object has a name, an on and a brightness, and can be turned on, off and set to a brightness. The drawing is a class diagram, the usual way to draw classes: the name, then the attributes, then the methods; the line with the hollow arrowhead means "inherits from", and it points from the subclass to the superclass. Other names they will meet: parent and child class, base and derived class. Last, the takeaway; how it is written in Java comes below.

--

<div class="stage">

## `extends`

### A class built on another

<div class="code-over-cards code-compact code-dense stage-top">
<div class="columns">
<div class="column">

#### `Device.java`

```java []
class Device {
    String name;
    boolean on;

    void turnOn() {
        this.on = true;
    }

    void turnOff() {
        this.on = false;
    }
}
```

</div>
<div class="column fragment" data-fragment-index="0">

#### `Light.java`

<!-- Device alone on entry; Light at step 0 with its own part marked, extends at step 1, plain with the note -->
<pre><code class="java" data-trim data-line-numbers="|2-6|1|" data-fragment-index="0">
class Light extends Device {
    int brightness;

    void setBrightness(int brightness) {
        this.brightness = brightness;
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-three">
<div class="rule">
<p><strong>The superclass</strong>A class like any other: nothing in it says it will have subclasses.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>The subclass</strong>Only what it adds to its superclass.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>extends</code></strong>After the subclass's name, its superclass: the subclass gets everything that class has.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
With <strong><code>extends</code></strong>, a subclass is a kind of its superclass, plus <strong>its own</strong> attributes and methods.
</blockquote>

</div>

Note:
The diagram above, written in Java, each class in its own file, as in unit 2. On entry, Device alone: the shared part, a class like the ones they wrote in unit 2; nothing in it says that other classes will build on it. Step 1, Light, with its body marked: only what it adds, the brightness and its method; turnOn and turnOff are not written in it. Ask them how Java knows that a Light is also a device, with a name, an on and those two methods. Step 2, the answer, the word extends after the name of Light: Light extends Device, so everything Device has, its attributes and its methods, belongs to Light too, without writing it again. Last, the takeaway: a Light is a Device, plus its own attributes and methods.

--

<div class="stage">

## Using a subclass

### Inherited members, as if they were its own

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0: the inherited lines on entry, its own at step 0, plain with the last card,
     all of them with the note -->
<pre><code class="java" data-trim data-line-numbers="4-5|6||4-6" data-fragment-index="0">
class Main {
    public static void main(String[] args) {
        Light lamp = new Light();
        lamp.name = "Living room lamp";
        lamp.turnOn();
        lamp.setBrightness(80);
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>Inherited</strong>The superclass's attributes and methods, used with the dot like any other.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Its own</strong>The ones written in the subclass.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="1">
<p><strong>Not the other way round</strong>A superclass object has nothing its subclasses add: using it does not compile.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A subclass object uses its inherited <strong class="text-state">attributes</strong> and <strong class="text-behavior">methods</strong> exactly as <strong>its own</strong>.
</blockquote>

</div>

Note:
One lamp, created and used with the classes of the slide above; none of them has a constructor yet, so new Light() uses the default one. On entry, the inherited lines: name and turnOn are not written in Light, but a Light has them, and the code cannot tell the difference. Step 1, its own method, setBrightness, written in Light. Step 2, in red: inheritance goes downwards only. A Device knows nothing about its subclasses, so a Device object has no brightness: cannot find symbol. Last, the takeaway, with the three calls marked.

--

<div class="stage">

## One object, every attribute

### What `new Light()` builds

<div class="memory-layout code-compact stage-top">

<!-- Code and diagram on entry, the code plain with the note -->
<pre><code class="java" data-trim data-line-numbers="1|" data-fragment-index="0">
Light lamp = new Light();
</code></pre>

<div class="memory">
<div class="memory-frame memory-stack"></div>
<div class="memory-frame memory-heap"></div>
<p class="memory-title memory-stack">Stack</p>
<p class="memory-title memory-heap">Heap</p>
<div class="memory-var memory-row-1"><span class="memory-cell">lamp</span><span class="memory-link"></span><span class="memory-cell memory-value">0x6c3a</span></div>
<div class="memory-arrow memory-row-1"></div>
<div class="memory-object">
<p>Light</p>
<span class="memory-cell">name</span><span class="memory-link"></span><span class="memory-cell memory-null">null</span>
<span class="memory-cell">on</span><span class="memory-link"></span><span class="memory-cell memory-value">false</span>
<span class="memory-cell">brightness</span><span class="memory-link"></span><span class="memory-cell memory-value">0</span>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="0">
<strong>One object</strong> with every attribute: the <strong>inherited</strong> ones and <strong>its own</strong>.
</blockquote>

</div>

Note:
What new builds for a subclass, drawn as in unit 2. lamp holds a reference to one object of class Light, with three attributes: name and on, inherited from Device, and brightness, its own, each at its starting value: null, false and 0. There is no separate Device object inside it: it is a single object that has everything both classes declare. Last, the takeaway.

---

<div class="stage">

## A constructor in each class

### Does it compile?

<div class="code-over-cards code-compact stage-top">
<div class="columns">
<div class="column">

#### `Device.java`

<!-- Both blocks plain on entry, for the question; both constructors marked with the error, and kept with the note -->
<pre><code class="java" data-trim data-line-numbers="|5-7|5-7" data-fragment-index="0">
class Device {
    String name;
    boolean on;

    Device(String name) {
        this.name = name;
    }

    // turnOn and turnOff, as before
}
</code></pre>

</div>
<div class="column">

#### `Light.java`

<pre><code class="java" data-trim data-line-numbers="|4-7|4-7" data-fragment-index="0">
class Light extends Device {
    int brightness;

    Light(String name) {
        this.name = name;
        this.brightness = 100;
    }

    // setBrightness, as before
}
</code></pre>

</div>
</div>
<div class="console-error fragment" data-fragment-index="0">

```console
Light.java:4: error: constructor Device in class Device cannot be applied to given types;
  required: String
  found:    no arguments
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="1">
A constructor first calls the <strong>superclass's</strong>; if none is called, Java calls <strong><code>super()</code></strong>, with <strong>no arguments</strong>.
</blockquote>

</div>

Note:
Both classes get a constructor, as unit 2 taught: Device's gives the name, and Light's gives the name and a brightness of 100, the lamp at full brightness when it is installed. On entry, the two classes: every line looks right, and Light can use name, which it inherits. Ask them whether it compiles, and let them argue before going on. A hint, if they need one: what did unit 2 say happens to the default constructor once a class has one of its own? Step 1, the answer, with both constructors marked: it does not compile. The compiler's message is shortened (it also points at the line with a caret and gives a reason), and it reports line 4, the header of Light's constructor, because the call that fails is not written anywhere. Last, the takeaway, to explain it: before running a single line of its own, every constructor runs the constructor of its superclass, so that the inherited part of the object gets built first. Light's does not say which one, so Java calls the one with no arguments, written super(); and Device has none, because writing Device(String name) removed the default one, as in unit 2. Found: no arguments; required: a String. The fix comes below.

--

<div class="stage">

## `super(...)`

### Calling the superclass's constructor

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="5|6|5|4-7" data-fragment-index="0">
class Light extends Device {
    int brightness;

    Light(String name) {
        super(name);
        this.brightness = 100;
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>super(...)</code></strong>Calls the superclass's constructor with these arguments: the inherited attributes get their values there.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Then its own</strong>The constructor goes on with the attributes the subclass adds.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="1">
<p><strong>The first line</strong><code>super(...)</code> anywhere else in the constructor does not compile.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A subclass constructor first calls <strong><code>super(...)</code></strong>, then gives <strong>its own</strong> attributes their values.
</blockquote>

</div>

Note:
The fix. On entry, super(name): it calls Device's constructor with the name, so Device sets its own attribute, and Light does not need to know how. new Light("Living room lamp") now runs Device's constructor first, then the rest of Light's. Step 1, the rest: Light only deals with what it adds, the brightness. Step 2, in red: like this(...) in unit 2, super(...) has to be the first statement, call to super must be first statement in constructor, because the inherited part is built before anything else. A constructor can start with this(...) or with super(...), not both. Last, the takeaway. Thermostat's constructor does the same with a name and a temperature, and Speaker's with a name and a volume of 50.

---

<div class="stage">

## Is it a...?

### When a class should extend another

<table class="data-table verdict-table stage-body">
<thead>
<tr><th>Class</th><th class="mark-head">Compiles?</th><th class="mark-head">Is a?</th><th>Why</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>class Light extends Device</code></th><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="fragment custom" data-fragment-index="0">A light is a device</td></tr>
<tr><th scope="row"><code>class Speaker extends Device</code></th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="fragment custom" data-fragment-index="1">A speaker is a device</td></tr>
<tr><th scope="row"><code>class Device extends Light</code></th><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="2">✗</td><td class="fragment custom" data-fragment-index="2">Backwards: not every device is a light</td></tr>
<tr><th scope="row"><code>class Room extends Device</code></th><td class="mark mark-ok fragment custom" data-fragment-index="3">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="3">✗</td><td class="fragment custom" data-fragment-index="3">A room has devices; it is not one</td></tr>
<tr><th scope="row"><code>class Thermostat extends Light</code></th><td class="mark mark-ok fragment custom" data-fragment-index="4">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="4">✗</td><td class="fragment custom" data-fragment-index="4">Only to reuse <code>name</code> and <code>on</code></td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="5">
Extend a class only when the subclass <strong>is a</strong> kind of it, never just to <strong>reuse code</strong>.
</blockquote>

</div>

Note:
All the declarations are on screen: ask about each one before filling in its row, reading it aloud as "a ... is a ...". Every one of them compiles: Java does not judge the design, so the question is the second column, in orange when it fails. Light and Speaker: a light is a device, a speaker is a device. Device extends Light reads backwards: every device would have a brightness. Room extends Device: a room has a name and could be turned off, but it is not a device, it contains devices; that is an attribute, an array of Device in the class Room, not inheritance. Thermostat extends Light is the tempting one: it would get name, on, turnOn and turnOff for free, and a brightness it does not have; the shared part belongs in Device. Last, the takeaway, the "is a" test.

--

<div class="stage">

## Hierarchies

### One superclass, several levels

<div class="columns stage-body">
<div class="column">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Device</code></p>
</div>
<div class="hierarchy-children">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Light</code></p>
<p class="class-attributes"><code>brightness</code></p>
</div>
<div class="hierarchy-children fragment" data-fragment-index="0">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>ColorLight</code></p>
<p class="class-attributes"><code>color</code></p>
</div>
</div>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Thermostat</code></p>
<p class="class-attributes"><code>temperature</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Speaker</code></p>
<p class="class-attributes"><code>volume</code></p>
</div>
</div>
</div>
</div>
</div>
<div class="column rules">
<div class="rule">
<p><strong>Many subclasses</strong>A class can be extended by as many classes as needed.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Several levels</strong>A subclass can be extended too: its subclasses inherit from it and from every class above it.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="1">
<p><strong>One superclass</strong>A class extends one class only: naming two after <code>extends</code> does not compile.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Each class has <strong>one superclass</strong> and any number of <strong>subclasses</strong>: together, a <strong>hierarchy</strong>.
</blockquote>

</div>

Note:
The rules of the tree, with only the attributes in the boxes. On entry, what they know: one superclass, three subclasses, and there could be more. Step 1, a new kind of lamp, one that can also change color: ColorLight extends Light and only adds a color. It is a Light, so it has a brightness, and a Device, so it has a name and an on; its constructor calls Light's with super(...), which calls Device's. Step 2, in red: Java allows one superclass per class, single inheritance, so the tree never has a class with two parents. Ask them about a lamp with a built-in speaker: it is a Light and it is a Speaker, but it can only extend one of them. Some languages, such as C++, allow several, at the cost of conflicts when both parents have the same method; Java solves the need differently, with interfaces, at the end of the unit. Last, the takeaway.

---

<!-- .slide: class="contents" data-section="2" -->

Note:
Classes can now share a part through inheritance. But any class can still change any attribute of any object, as in unit 2. Now how a class decides who can use what, and what that means for its subclasses.

---

<div class="stage">

## Brightness: 250%

### Nothing stops it

<div class="phone-beside stage-body">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a7 7 0 0 0-4 12.74V17a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1v-2.26A7 7 0 0 0 12 2zM9 20h6v2H9z"/></svg></span>
</div>
<div>
<p class="phone-greeting">Light</p>
<p class="phone-name">Living room lamp</p>
<p class="phone-power phone-power-on">On</p>
</div>
<div class="phone-account">
<p class="phone-label">Brightness</p>
<p class="phone-balance"><span class="phone-swap"><span class="fragment fade-out" data-fragment-index="0">80%</span><span class="phone-changed fragment" data-fragment-index="0">250%</span></span></p>
<span class="phone-swap phone-swap-block"><span class="fragment fade-out" data-fragment-index="0"><progress class="phone-level" value="80" max="100"></progress></span><span class="fragment" data-fragment-index="0"><progress class="phone-level" value="100" max="100"></progress></span></span>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn on</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>Turn off</span>
<span class="phone-button phone-button-wide phone-ring fragment custom" data-fragment-index="1"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M3 6h10v2H3zm12-2h2v6h-2zm2 2h4v2h-4zM3 16h4v2H3zm6-2h2v6H9zm4 2h8v2h-8z"/></svg>Set brightness</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone-beside-cards">
<div class="rule">
<p><strong>From any class</strong>In <code>main</code>, <code>lamp.brightness = 250;</code> compiles.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>Nothing checks it</strong><code>setBrightness</code> could check the value, but nothing forces anyone to call it.</p>
</div>
<blockquote class="fragment" data-fragment-index="2">
Any class can change an attribute <strong>directly</strong>, and <strong>no one</strong> checks the value.
</blockquote>
</div>
</div>

</div>

Note:
The problem unit 2 left open, when ana.balance could be set to -500. On entry, the lamp at 80%, and a line someone writes in main. Step 1, the screen after it: 250%, a brightness that cannot exist, and the bar can only show it full. It compiled and ran without a word, because brightness is a plain attribute and any class can write it. Step 2, with the Set brightness button ringed: a setter that checks the value is easy to write, but useless as long as the attribute can be reached without it. Ask them what they would need: a way to forbid the direct access. Last, the takeaway; the fix is below.

--

<div class="stage">

## Private attributes

### Only the class itself can use them

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="2|1,4,9|10-12|" data-fragment-index="0">
public class Light extends Device {
    private int brightness;

    public Light(String name) {
        super(name);
        this.brightness = 100;
    }

    public void setBrightness(int brightness) {
        if (brightness >= 0 &amp;&amp; brightness &amp;lt;= 100) {
            this.brightness = brightness;
        }
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>private</code></strong>Only the code of its own class can use it: from any other class, it does not compile.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>public</code></strong>Any class can use it: a class, a constructor or a method.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>The class decides</strong>The only way in is through its methods, which can check the value.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
<strong>Encapsulation</strong>: attributes <strong><code>private</code></strong>, and the class decides through its <strong>methods</strong> what can be done with them.
</blockquote>

</div>

Note:
The fix. On entry, private before the attribute: it can only be used from inside the class Light. The line in main is now an error, brightness has private access in Light. Step 1, the other end: public, which means anyone; Hello.java had it in unit 1, and main has always been public. A public class has to be in a file with its name, Light.java, which is what they already do. Step 2, the only way left to change the brightness is setBrightness, which only accepts values from 0 to 100; with 250 it does nothing (reporting the mistake comes with exceptions, in unit 4). Last, the takeaway: hiding the data behind methods is called encapsulation. From here on, the course writes every attribute private and the classes, constructors and methods public, unless there is a reason not to.

--

<div class="stage">

## Getters and setters

### Reading and changing through methods

<div class="code-over-cards code-compact code-dense stage-top">
<div class="columns">
<div class="column">

#### `Light.java`

<!-- Both blocks and the cards step together from 0: the getters, the setter, the read-only name; both with the note -->
<pre><code class="java" data-trim data-line-numbers="6-8|10-14||6-14" data-fragment-index="0">
public class Light extends Device {
    private int brightness;

    // constructor, as before

    public int getBrightness() {
        return this.brightness;
    }

    public void setBrightness(int brightness) {
        if (brightness >= 0 &amp;&amp; brightness &amp;lt;= 100) {
            this.brightness = brightness;
        }
    }
}
</code></pre>

</div>
<div class="column">

#### `Device.java`

<pre><code class="java" data-trim data-line-numbers="11-13||7-9|7-13" data-fragment-index="0">
public class Device {
    private String name;
    private boolean on;

    // constructor, turnOn and turnOff, as before

    public String getName() {
        return this.name;
    }

    public boolean isOn() {
        return this.on;
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-three">
<div class="rule">
<p><strong>Getter</strong><code>get</code> and its name, <code>is</code> for a <code>boolean</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Setter</strong><code>set</code> and its name: changes it, after checking it.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Only what is needed</strong>A getter with no setter: read-only from outside.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong>getter</strong> reads a private attribute and a <strong>setter</strong> changes it, under the <strong>class's rules</strong>.
</blockquote>

</div>

Note:
The usual pair of methods for a private attribute; Device follows the rule too, its name and on are private, and it gets getters as well. On entry, the getters: getBrightness, which returns the value without changing it, and the app calls it to draw the screen; for a boolean the name starts with is, as Device's isOn(). Step 1, the setter, the one from the slide above. The names follow a convention that IntelliJ and many libraries rely on: get, set or is, and the attribute's name with a capital. Step 2, Device's getName(): an attribute does not need both. Device has getName() and no setName(), so the name of a device is set by its constructor and can only be read afterwards; a setter is written only when changing the value from outside makes sense. Last, the takeaway, with both methods marked.

--

<div class="stage">

## The other two devices

### The same pattern as `Light`

<div class="columns code-compact code-dense stage-top">
<div class="column">

#### `Thermostat.java`

```java
public class Thermostat extends Device {
    private double temperature;

    public Thermostat(String name, double temperature) {
        super(name);
        this.temperature = temperature;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        if (temperature >= 5 && temperature <= 30) {
            this.temperature = temperature;
        }
    }
}
```

</div>
<div class="column">

#### `Speaker.java`

```java
public class Speaker extends Device {
    private int volume;

    public Speaker(String name) {
        super(name);
        this.volume = 50;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.volume = volume;
        }
    }
}
```

</div>
</div>

<blockquote class="stage-note fragment">
Each kind of device: its own <strong>private</strong> attribute, a constructor that calls <strong><code>super(...)</code></strong>, a <strong>getter</strong> and a <strong>setter</strong> that checks.
</blockquote>

</div>

Note:
The other two kinds of device, written the way Light now is, so that the three classes are complete from here on. Ask them to read them in silence and spot what changes from Light: only the attribute, its range and the constructor. The thermostat takes its starting temperature in the constructor, and only accepts targets from 5 to 30 °C; the speaker starts at volume 50 and accepts 0 to 100, like the brightness. Neither has anything that Light did not already show. Last, the takeaway.

---

<div class="stage">

## Private to subclasses too

### `on` is private in `Device`

<div class="code-over-cards code-compact code-dense stage-top">
<div class="columns code-diff">
<div class="column">

#### Through the attribute

<!-- The first version alone on entry, the second at step 0; at step 1 both mark the line that differs -->
<pre><code class="java" data-trim data-line-numbers="|9" data-fragment-index="1">
public class Light extends Device {
    private int brightness;

    // constructor and getter, as before

    public void setBrightness(int brightness) {
        if (brightness >= 0 &amp;&amp; brightness &amp;lt;= 100) {
            this.brightness = brightness;
            this.on = true;
        }
    }
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### Through a method

<pre><code class="java" data-trim data-line-numbers="|9" data-fragment-index="1">
public class Light extends Device {
    private int brightness;

    // constructor and getter, as before

    public void setBrightness(int brightness) {
        if (brightness >= 0 &amp;&amp; brightness &amp;lt;= 100) {
            this.brightness = brightness;
            this.turnOn();
        }
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-pair">
<div class="rule rule-error fragment" data-fragment-index="1">
<p><strong>Private in the superclass</strong>The subclass's objects have the attribute, but the subclass's code cannot name it.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Through its methods</strong>The subclass uses the superclass's public methods, as any other class would.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
A subclass inherits its superclass's <strong>private</strong> attributes, but reaches them only through its <strong>methods</strong>.
</blockquote>

</div>

Note:
Device follows the rule too now: name and on are private. Setting a brightness should also turn the lamp on. On entry, the obvious way: ask them whether it compiles. Step 1, the version through a method. Step 2, the difference: this.on = true does not compile, on has private access in Device. The attribute is in the object, it was in the memory diagram, but private means the code of Device only, and Light is another class. Step 3, the fix: Device already offers turnOn(), so Light asks Device to do it; if Device ever changes how it turns on, Light does not notice. Last, the takeaway. It is an example of how a subclass reaches a private attribute: the course does not keep that line, and Light's setBrightness stays as on Getters and setters.

--

<div class="stage">

## `protected`

### Open to subclasses, closed to the rest

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="3|3|2-3|2-3" data-fragment-index="0">
public class Device {
    private String name;
    protected boolean on;

    // constructor and methods, as before
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>protected</code></strong>Its subclasses can use it, as well as its own class.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="0">
<p><strong>With care</strong>Any subclass, written by anyone, can then change it without the superclass's rules.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Usually <code>private</code></strong>And methods for what the subclasses need.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
<strong><code>protected</code></strong> opens a member to the <strong>subclasses</strong>; <strong><code>private</code></strong> with methods is usually the better choice.
</blockquote>

</div>

Note:
The other way out, so they recognize it. On entry, protected: the attribute is open to the subclasses of Device, so this.on = true compiles in Light. Step 1, the catch, in orange: it works, but any subclass, including ones written later by someone else, can now put on in a state Device does not expect, and Device can no longer change that attribute without breaking them. Step 2: in this course, attributes stay private, and protected is kept for methods meant only for subclasses. Last, the takeaway, with both attributes marked. protected also opens the member to the classes of the same package, which is what comes next.

---

<div class="stage">

## Packages

### Classes grouped in folders

<div class="columns code-compact stage-top">
<div class="column">

<!-- Each file's row is marked while its code is the one appearing -->
<div class="explorer">
<p class="explorer-title">Explorer</p>
<p class="explorer-row explorer-folder">src</p>
<p class="explorer-row explorer-folder explorer-depth-1">home</p>
<p class="explorer-row explorer-folder explorer-depth-2">devices</p>
<p class="explorer-row explorer-java explorer-depth-3">Device.java</p>
<p class="explorer-row explorer-java explorer-depth-3 fragment custom" data-fragment-index="0">Light.java</p>
<p class="explorer-row explorer-folder explorer-depth-2">app</p>
<p class="explorer-row explorer-java explorer-depth-3 fragment custom" data-fragment-index="1">Main.java</p>
</div>

<div class="rules">
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>package</code></strong>The first line of the file: the folder it is in, with dots.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>import</code></strong>Lets a class use one from another package by its short name.</p>
</div>
</div>

</div>
<div class="column code-over-cards code-dense">

<!-- Each file with its card: package marked in Light, import in Main, and both lines with the note -->
<div class="fragment" data-fragment-index="0">

#### `home/devices/Light.java`

<pre><code class="java" data-trim data-line-numbers="1|1" data-fragment-index="0">
package home.devices;

public class Light extends Device {
    // as before
}
</code></pre>

</div>
<div class="fragment" data-fragment-index="1">

#### `home/app/Main.java`

<pre><code class="java" data-trim data-line-numbers="3|3|1,3" data-fragment-index="1">
package home.app;

import home.devices.Light;

public class Main {
    // main, as before
}
</code></pre>

</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong>package</strong> groups related classes; <strong><code>import</code></strong> brings in a class from another one.
</blockquote>

</div>

Note:
A real app has hundreds of classes, so they go in folders, called packages. On entry, the folders, as IntelliJ or any editor shows them: the device classes in home/devices, the app's in home/app. Step 1, the package line, marked, the first of the file, says which folder the class is in, with dots instead of slashes; Light's full name is home.devices.Light, and it does not need to import Device, which is in its own package. Step 2, Main is in another package, so it imports Light, on the marked line, to use it by its short name; without the import it would have to write home.devices.Light every time. A class from another package can only be used if it is public. With no package line a class goes in the default package, which is where all their classes have been so far. Java's own classes come in packages too: String is java.lang.String, and java.lang is imported on its own. Last, the takeaway.

--

<div class="stage">

## Four access levels

### For attributes, constructors and methods

<!-- Each row: the modifier first, to ask the class; then its four answers at once -->
<table class="data-table verdict-table stage-body">
<thead>
<tr><th>Modifier</th><th class="mark-head">Same class</th><th class="mark-head">Same package</th><th class="mark-head">Subclass elsewhere</th><th class="mark-head">Any class</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>public</code></th><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td></tr>
<tr><th scope="row"><code>protected</code></th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-error fragment custom" data-fragment-index="1">✗</td></tr>
<tr><th scope="row">none</th><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="mark mark-error fragment custom" data-fragment-index="2">✗</td><td class="mark mark-error fragment custom" data-fragment-index="2">✗</td></tr>
<tr><th scope="row"><code>private</code></th><td class="mark mark-ok fragment custom" data-fragment-index="3">✓</td><td class="mark mark-error fragment custom" data-fragment-index="3">✗</td><td class="mark mark-error fragment custom" data-fragment-index="3">✗</td><td class="mark mark-error fragment custom" data-fragment-index="3">✗</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="4">
Attributes <strong><code>private</code></strong>, the rest <strong><code>public</code></strong>: open only what has a <strong>reason</strong> to be open.
</blockquote>

</div>

Note:
The four levels in one table, from the most open to the most closed. Each row starts with the modifier alone: ask the class where it can be used before filling it in. public, everywhere. protected, its own package and the subclasses in other packages. No modifier at all, what every class and attribute had in unit 2: its own package only, called package access; it worked so far because every class was in the same, default, package. private, its own class only. The four go on attributes, constructors and methods alike, such as a private method that only helps the class's own code; a class itself takes only public or nothing. Last, the rule the course follows from now on.

---

<!-- .slide: class="contents" data-section="3" -->

Note:
A subclass inherits everything, and its superclass decides what it can touch. But what it inherits is not always what it needs: now how a subclass changes the behavior of an inherited method.

---

<div class="stage">

## One status per tile

### What should each tile say?

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
<p class="phone-label"><span class="phone-swap"><span class="fragment fade-out" data-fragment-index="1">On</span><span class="phone-changed fragment" data-fragment-index="1">On, 100%</span></span></p>
</div>
<div class="phone-tile">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path fill-rule="evenodd" d="M12 2a3 3 0 0 0-3 3v8.26a5 5 0 1 0 6 0V5a3 3 0 0 0-3-3zm0 2a1 1 0 0 1 1 1v9.32l.4.3a3 3 0 1 1-2.8 0l.4-.3V5a1 1 0 0 1 1-1z"/></svg></span>
<p class="phone-tile-name">Hallway thermostat</p>
<p class="phone-label"><span class="phone-swap"><span class="fragment fade-out" data-fragment-index="1">On</span><span class="phone-changed fragment" data-fragment-index="1">On, 21.5 °C</span></span></p>
</div>
<div class="phone-tile">
<span class="phone-avatar"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M3 9v6h4l5 4V5L7 9zm13.5 3a4.5 4.5 0 0 0-2.5-4v8a4.5 4.5 0 0 0 2.5-4z"/></svg></span>
<p class="phone-tile-name">Kitchen speaker</p>
<p class="phone-label">Off</p>
</div>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main phone-button-wide"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 3h2v9h-2zM7.05 6.05l1.41 1.41a5 5 0 1 0 7.08 0l1.41-1.41a7 7 0 1 1-9.9 0z"/></svg>All off</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone-beside-cards">
<!-- Its two halves go with the tiles: the shared part while they only say On or Off, each one's own when they change -->
<blockquote class="fragment" data-fragment-index="0">
Each kind of device should describe itself with the <strong>same method</strong>, <code>status()</code>: the part <strong>every device</strong> shares<span class="fragment" data-fragment-index="1">, and then <strong>its own</strong></span>.
</blockquote>
</div>
</div>

</div>

Note:
The app's home screen: one tile per device, with a line under the name that comes from a method of Device, status(); for now it can only say On or Off, because that is all Device knows. On entry, the tiles as they are: ask what the app would rather show. Step 1, the first half of the takeaway: every device answers the same call, status(), and the answer starts with what every device has, on or off. Step 2, the tiles as they should be, and the rest of it: the lamp adds its brightness and the thermostat its temperature; the speaker has nothing to add. A statusOfLight() in Light and another in Thermostat would force the app to know the kind of every tile; it has to be the same call, giving each kind's own answer. How, below.

--

<div class="stage">

## Overriding a method

### The subclass writes its own version

<div class="code-over-cards code-compact code-dense stage-top">
<div class="columns">
<div class="column">

#### `Device.java`

<!-- Device's signature with Overriding and with @Override, which checks it; its whole status with super. and the note -->
<pre><code class="java" data-trim data-line-numbers="7-12|7|7-12|7|7-12" data-fragment-index="0">
public class Device {
    private String name;
    private boolean on;

    // constructor and methods, as before

    public String status() {
        if (this.on) {
            return "On";
        }
        return "Off";
    }
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### `Light.java`

<!-- Light at step 0 with its signature, then the super. line, then @Override over it; the whole override with the note -->
<pre><code class="java" data-trim data-line-numbers="7|7|8|6-7|6-9" data-fragment-index="0">
public class Light extends Device {
    private int brightness;

    // constructor, getter and setter, as before

    @Override
    public String status() {
        return super.status() + ", " + this.brightness + "%";
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-three">
<div class="rule fragment" data-fragment-index="0">
<p><strong>Overriding</strong>The same signature as in the superclass: it replaces that method for its objects.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>super.</code></strong>Calls the superclass's version of a method, to add to it instead of writing it again.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong><code>@Override</code></strong>Checks that the superclass has a method with that signature: a typo no longer compiles.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
A subclass <strong>overrides</strong> an inherited method by writing it again with the <strong>same signature</strong>.
</blockquote>

</div>

Note:
On entry, Device's status(), the one every device inherits: On or Off. Step 1, Light next to it, with a status() of its own, and both signatures marked: the same name, parameters and return type. That is overriding, and from now on a Light answers status() with its own version, not Device's. Step 2, what it does, the super.status() line: super followed by a dot reaches the superclass's version of a method, the one on the left, which says On or Off; Light only adds the comma and the brightness, so the shared part is still written once. Light cannot read on itself, it is private in Device, so reusing Device's method is also the only way. Step 3, apart from all that, the line above it, an annotation, with Device's signature marked again because that is what it checks against: it changes nothing in what the program does, it tells the compiler that this method is meant to replace one of the superclass. Without it, a typo such as Status() or a wrong parameter compiles anyway, as a new method, and the tiles keep showing Device's version without any warning; with it, that mistake is a compile error. Overrides or not?, two slides on, shows those cases. Always write it. Only methods of objects are overridden: a static method belongs to its class, and a subclass that declares one with the same signature hides it, it does not override it. Last, the takeaway, with the whole override marked.

--

<div class="stage">

## Each class, its own version

### What does each line print?

<div class="code-run code-compact code-dense stage-top">
<div class="columns">
<div class="column code-over-cards code-compact">
<div>

#### `Thermostat.java`

```java
public class Thermostat extends Device {
    private double temperature;

    // constructor, getter and setter, as before

    @Override
    public String status() {
        return super.status() + ", " + this.temperature + " °C";
    }
}
```

</div>
<div>

#### `Speaker.java`

```java
public class Speaker extends Device {
    private int volume;

    // constructor, getter and setter, as before
}
```

</div>
</div>
<div class="column code-over-cards code-compact">
<div>

#### `Main.java`

<!-- Each println alone first, to ask, then its line of output; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1-3|4-5|6|6|7|7|8|8|6-8" data-fragment-index="0">
Light lamp = new Light("Living room lamp");
Thermostat thermostat = new Thermostat("Hallway thermostat", 21.5);
Speaker speaker = new Speaker("Kitchen speaker");
lamp.turnOn();
thermostat.turnOn();
System.out.println(lamp.status());
System.out.println(thermostat.status());
System.out.println(speaker.status());
</code></pre>

</div>
<div class="fragment" data-fragment-index="2">
<pre><code class="nohighlight hljs console" data-noescape>On, 100%
<span class="fragment" data-fragment-index="4">On, 21.5 °C</span>
<span class="fragment" data-fragment-index="6">Off</span></code></pre>
</div>

</div>
</div>

</div>

<blockquote class="stage-note fragment" data-fragment-index="7"><span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="1"></span><span class="fragment" data-fragment-index="3"></span><span class="fragment" data-fragment-index="5"></span>
Each object runs <strong>its class's version</strong>; a class that does not override it runs the <strong>inherited</strong> one.
</blockquote>

</div>

Note:
On the left, what the other two classes have: Thermostat overrides status() as Light does, adding its temperature; Speaker has no status() of its own. On entry, the three devices, with their constructors: the lamp starts at 100% and the speaker at volume 50. Step 1, the lamp and the thermostat are turned on, the speaker is not. Then each println alone, to ask what it prints, and its line of output on the next step. Light's version, with the brightness; Thermostat's, with the temperature; and the speaker, which has no status() of its own, runs the one it inherits from Device. Last, the takeaway, with the three lines marked.

---

<div class="stage">

## Overrides or not?

### Each method, written in `Light`

<table class="data-table verdict-table stage-body">
<thead>
<tr><th>Method</th><th class="mark-head">Compiles?</th><th class="mark-head">Overrides?</th><th>Why</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>public String status()</code></th><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="fragment custom" data-fragment-index="0">The same signature as in <code>Device</code></td></tr>
<tr><th scope="row"><code>public String status(int digits)</code></th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="1">✗</td><td class="fragment custom" data-fragment-index="1">Other parameters: an overload</td></tr>
<tr><th scope="row"><code>public String Status()</code></th><td class="mark mark-ok fragment custom" data-fragment-index="2">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="2">✗</td><td class="fragment custom" data-fragment-index="2">Another name: a new method</td></tr>
<tr><th scope="row"><code>public int status()</code></th><td class="mark mark-error fragment custom" data-fragment-index="3">✗</td><td class="mark mark-none fragment custom" data-fragment-index="3">–</td><td class="fragment custom" data-fragment-index="3">The same signature, another return type</td></tr>
<tr><th scope="row"><code>String status()</code></th><td class="mark mark-error fragment custom" data-fragment-index="4">✗</td><td class="mark mark-none fragment custom" data-fragment-index="4">–</td><td class="fragment custom" data-fragment-index="4">Less access than in <code>Device</code></td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="5">
With <strong><code>@Override</code></strong>, a method that does not really override one is a <strong>compile error</strong>, not a <strong>silent bug</strong>.
</blockquote>

</div>

Note:
Five attempts at overriding status() in Light, each judged on its own, without @Override. Ask about each before filling in its row. The first is the real one. The second has a parameter, so it is another signature: Light now has two status methods, an overload as in unit 2, and Device's is still the one that runs for status(). The third, with a capital S, is a different name: it compiles, and the tiles keep showing Device's version, a bug that is hard to see. The fourth keeps the signature but changes the return type: status() in Light cannot override status() in Device (the one exception: a method returning an object may return a subclass of it in the override, such as Light instead of Device). The fifth drops public: an override cannot give less access than the method it replaces, because code that calls status() on any device must still be able to. Last, the takeaway: with @Override, the two orange rows would not compile either, method does not override or implement a method from a supertype.

---

<div class="stage">

## `Object`

### The superclass of every class

<div class="columns stage-body">
<div class="column">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Object</code></p>
<p class="class-methods"><code>toString()</code><code>equals(Object)</code><code>getClass()</code></p>
</div>
<div class="hierarchy-children">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Device</code></p>
</div>
<div class="hierarchy-children">
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Light</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Thermostat</code></p>
</div>
</div>
<div class="hierarchy">
<div class="class-box">
<p class="class-name"><code>Speaker</code></p>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<div class="column rules">
<div class="rule">
<p><strong>Implicit</strong>A class with no <code>extends</code> extends <code>Object</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>In every object</strong>Its methods are inherited by every class, through every level.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Made to be overridden</strong>Most of its versions are very basic: classes override them to make sense for their objects.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
<strong><code>Object</code></strong> is at the top of every hierarchy: its methods are in <strong>every object</strong>.
</blockquote>

</div>

Note:
Device seemed to be the top of the tree, but it is not. On entry, Object, a class that comes with Java, in java.lang: any class written without extends, Device included, extends Object, and String, which comes with Java, does too. So every class has a superclass except Object itself. Step 1, what that gives every object: a few methods, among them toString(), which turns it into text, equals(Object), which compares it with another, and getClass(), which says its class. Step 2: Object's versions are very basic; equals, for instance, compares references, as ==. String, not in the drawing, overrides it to compare the characters, which is why unit 2 said to compare strings with equals; Device overrides it after casting, in the next section. getClass() is the exception: it is final, so it always tells the truth. Last, the takeaway.

--

<div class="stage">

## `toString`

### How an object becomes text

<div class="runs runs-fit code-dense stage-body">
<div class="rule">
<p><strong>Inherited from <code>Object</code></strong>The class name and a number: of little use.</p>
</div>
<div>

```java
System.out.println(lamp);
```

</div>
<div class="fragment" data-fragment-index="0">

```console
Light@4e50df2e
```

</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Overridden</strong>The same <code>println</code>: the text the class decides.</p>
</div>
<div class="fragment" data-fragment-index="1">

```java
public class Device {
    // attributes, constructor and methods, as before

    @Override
    public String toString() {
        return this.name + ": " + this.status();
    }
}
```

</div>
<div class="fragment" data-fragment-index="2">

```console
Living room lamp: On, 100%
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
<code>println</code> and <code>+</code> call <strong><code>toString</code></strong>: overriding it decides how an object is <strong>shown</strong>.
</blockquote>

</div>

Note:
The method of Object they will override most. On entry, printing the lamp itself, not its status: ask what it prints. Step 1, Object's toString: the class name, an @ and a number in hexadecimal that changes from run to run. println calls toString on any object it gets, and so does + when it joins an object to a String. Step 2, toString overridden in Device, once for every device: the name, a colon and the status. Step 3, the same println now prints the name and Light's status: Device's toString calls this.status(), and for a lamp that is Light's version, even though the call is written in Device. That is the idea of the next section. Last, the takeaway.

---

<div class="stage">

## `final`, three ways

### What it closes

<!-- The three places stay; each row's example and meaning fill in at once, after asking the class -->
<table class="data-table stage-body">
<thead>
<tr><th>On</th><th>Example</th><th>Means</th></tr>
</thead>
<tbody>
<tr><th scope="row">An attribute</th><td class="fragment custom" data-fragment-index="0"><code>private final String name;</code></td><td class="fragment custom" data-fragment-index="0">Set once, never changed</td></tr>
<tr><th scope="row">A method</th><td class="fragment custom" data-fragment-index="1"><code>public final String getName()</code></td><td class="fragment custom" data-fragment-index="1">No subclass can override it</td></tr>
<tr><th scope="row">A class</th><td class="fragment custom" data-fragment-index="2"><code>public final class String</code></td><td class="fragment custom" data-fragment-index="2">No class can extend it</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="3">
<strong><code>final</code></strong> always means <strong>no more changes</strong>: to a value, to a method, or to a class.
</blockquote>

</div>

Note:
final, which they met on attributes in unit 2, also goes on methods and classes. The three places are there from the start; for each, ask what it could mean there before filling in its row. An attribute: as the IBAN of unit 2, the name of a device is given by its constructor and never changes, so Device can declare it final. A method: Device makes getName final, so no subclass can change what a device's name is; trying it gives getName() in Light cannot override getName() in Device. A class: nobody can extend it, and String is one, which is how Java guarantees that a String never changes: no subclass could add a method that alters it. Last, the takeaway.

---

<!-- .slide: class="contents" data-section="4" -->

Note:
Each kind of device now answers status() and toString() its own way. Now the payoff: treating all the devices as devices, in one array and with one call, and still getting each one's own answer.

---

<div class="stage">

## A `Device` variable

### Does each line compile?

<div class="columns code-compact stage-top">
<div class="column code-over-cards code-compact">

<!-- Each line alone first, to ask about it, then its card (and output); the empty spans in the note hold the
     indices only the code uses. Line 3's output gives way to line 4's error -->
<pre><code class="java" data-trim data-line-numbers="1|1|2|2|3|3|4|4|" data-fragment-index="0">
Device device = new Light("Living room lamp");
device.turnOn();
System.out.println(device.status());
device.setBrightness(50);
</code></pre>
<div class="output-swap">
<div class="fragment fade-out" data-fragment-index="6">
<div class="fragment" data-fragment-index="4">

```console
On, 100%
```

</div>
</div>
<div class="console-error fragment" data-fragment-index="6">

```console
Main.java:4: error: cannot find symbol
  symbol:   method setBrightness(int)
  location: variable device of type Device
```

</div>
</div>

</div>
<div class="column rules">
<div class="rule fragment" data-fragment-index="0">
<p><strong>Upcasting</strong>A superclass variable can refer to a subclass object, with no cast: the object stays as it is.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>The type's methods</strong>Those of the variable's type compile.</p>
</div>
<div class="rule fragment" data-fragment-index="4">
<p><strong>The object's version</strong>The version that runs is that of the object's class, chosen while the program runs.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="6">
<p><strong>Not the subclass's own</strong>The compiler only knows the variable's type: a method only the subclass has does not compile.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="7"><span class="fragment" data-fragment-index="1"></span><span class="fragment" data-fragment-index="3"></span><span class="fragment" data-fragment-index="5"></span>
The variable's <strong>type</strong> decides what <strong>compiles</strong>; the <strong>object</strong> decides which version <strong>runs</strong>.
</blockquote>

</div>

Note:
A variable of a superclass, holding an object of a subclass, one line at a time: each line alone first, to ask about it, then the answer. On entry, the first line: a Device variable gets a new Light; ask them whether it compiles. Step 1, it does: converting a reference to a superclass is called upcasting, up the tree, and it is automatic, as int to double, because every Light is a Device. There is still a single object, and it is still a Light: assigning a reference never changes the object, only which variables reach it. Step 2, turnOn: ask again. Step 3, it compiles, because Device has it; it prints nothing, it just turns the lamp on. Step 4, status(), printed: ask whether it compiles and what it prints. Step 5, Device has a status() too, so it compiles; when it runs, Java looks at the object, a Light, and runs Light's version: On, 100%, not On. Step 6, setBrightness: the object is a Light, and Light has that method, so ask once more. Step 7, it does not compile: the compiler does not run the program, it only reads the type of the variable, Device, which has no such method: cannot find symbol (the compiler's message, shortened). With that line, the whole program no longer compiles, so nothing runs: the output gives way to the error. Last, the takeaway, the two rules of the whole section.

--

<div class="stage">

## Every device in one array

### And each one answers its own way

<div class="code-run code-compact stage-top">

<!-- the last step repeats the println: the output under the code shows with it -->
```java [1-5|7|8|9|9]
Device[] devices = {
        new Light("Living room lamp"),
        new Thermostat("Hallway thermostat", 21.5),
        new Speaker("Kitchen speaker")
};

for (Device device : devices) {
    device.turnOn();
    System.out.println(device);
}
```

<div class="code-run-output">

```console
Living room lamp: On, 100%
Hallway thermostat: On, 21.5 °C
Kitchen speaker: On
```

</div>
</div>

<blockquote class="stage-note code-run-output">
<strong>Polymorphism</strong>: the same call on each element runs <strong>a different version</strong>, its object's.
</blockquote>

</div>

Note:
Why a Device variable is worth it: the app keeps all its devices together, to turn them all off with one button. On entry, an array of Device holding three objects of three classes: every element is a Device, so they all fit, as in the slide above. Step 1, a for-each over all of them, without knowing which is which. Step 2, turnOn, the same method for all, inherited from Device; the app's All off button is this same loop with turnOff. Step 3, printing each one, which calls toString and, inside it, status(): ask what it prints. Step 4, each line in the words of its own class, from one line of code. The name for it is polymorphism, "many forms": one call, a different behavior for each kind of object. Adding a fourth kind of device later means writing its class, and this loop works with it unchanged. Last, the takeaway.

--

<div class="stage">

## Overloading or overriding?

### Who picks the version, and when

<table class="data-table compare-table stage-body">
<thead>
<tr><th></th><th>Overloading</th><th>Overriding</th></tr>
</thead>
<tbody>
<tr><th scope="row">Where</th><td class="fragment custom" data-fragment-index="0">In the same class</td><td class="fragment custom" data-fragment-index="0">In a subclass</td></tr>
<tr class="fragment" data-fragment-index="1"><th scope="row">Signature</th><td class="fragment custom" data-fragment-index="2">A different one</td><td class="fragment custom" data-fragment-index="2">The same one</td></tr>
<tr class="fragment" data-fragment-index="3"><th scope="row">Who picks</th><td class="fragment custom" data-fragment-index="4">The compiler, from the arguments</td><td class="fragment custom" data-fragment-index="4">Java, from the object</td></tr>
<tr class="fragment" data-fragment-index="5"><th scope="row">When</th><td class="fragment custom" data-fragment-index="6">When compiling</td><td class="fragment custom" data-fragment-index="6">When running</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="7">
Overloading is decided at <strong>compile time</strong>; overriding, at <strong>run time</strong>: <strong>dynamic binding</strong>.
</blockquote>

</div>

Note:
Two words that sound alike, side by side; each row shows its question first: ask for both answers before filling them in. Overloading, unit 2: several methods with the same name and different parameters, deposit(double) and deposit(double, String). Overriding, this unit: a subclass writes again a method of its superclass with the same signature. Who picks: for an overload, the compiler, looking at the arguments of the call; for an override, the Java virtual machine, looking at the object while the program runs, which is why device.status() could give Light's answer. That runtime choice is called dynamic binding, and the compile-time one static binding; some books call them dynamic and static polymorphism. Last, the takeaway.

---

<div class="stage">

## Back to a `Light`

### Casting references

<div class="stage-body">
<div class="widening">
<code>Light</code><span class="widening-arrows"><span class="flow-shaft widening-safe fragment" data-fragment-index="0"></span><span class="flow-shaft flow-shaft-back widening-cast fragment" data-fragment-index="1"></span></span><code>Device</code><span class="widening-arrows"><span class="flow-shaft widening-safe fragment" data-fragment-index="0"></span><span class="flow-shaft flow-shaft-back widening-cast fragment" data-fragment-index="1"></span></span><code>Object</code>
</div>

<div class="rules rules-grid rules-pair">
<div class="rule fragment" data-fragment-index="0">
<p><strong>Upcasting: automatic</strong>Towards a superclass: always safe.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>Downcasting: a cast</strong>Towards a subclass, with the type in parentheses: the object might not be of that class.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A cast creates <strong>no new object</strong>: the same object, seen through <strong>another type</strong>.
</blockquote>

</div>

Note:
The chain from unit 1, now with classes: from the most specific to the most general. Step 1, upcasting, the arrows to the right: always safe, so automatic, as int to double. Step 2, downcasting, to the left: to call setBrightness on a lamp kept in a Device variable, the reference has to be turned back into a Light, with a cast, the type in parentheses, as with primitive types. Java asks for it because it might be wrong: device could refer to a thermostat. Step 3, the difference with primitive types: (int) 19.99 builds a new value, 19; (Light) device builds nothing, the object stays exactly as it was, and only the type of the reference changes. What happens when the cast is wrong is below.

--

<div class="stage">

## The wrong cast

### It compiles, and fails when it runs

<div class="code-run code-compact stage-top">

<!-- the last step repeats the cast: the output under the code shows with it -->
```java [1|2|3|2]
Device device = new Thermostat("Hallway thermostat", 21.5);
Light lamp = (Light) device;
lamp.setBrightness(50);
```

<div class="code-run-output console-error">

```console
Exception in thread "main" java.lang.ClassCastException: class Thermostat cannot be cast to class Light (Thermostat and Light are in unnamed module of loader 'app')
```

</div>
</div>

<blockquote class="stage-note code-run-output">
A wrong downcast <strong>compiles</strong>, and stops the program with a <strong><code>ClassCastException</code></strong> when it runs.
</blockquote>

</div>

Note:
On entry, a thermostat in a Device variable. Step 1, a cast to Light: ask whether it compiles. It does: a Device could be a Light, and the compiler only sees types. Step 2, the line that would set its brightness. Step 3, the program never gets there: when the cast runs, Java checks the real object, finds a Thermostat, and stops the program with a ClassCastException on the cast, line 2. The rest of the message, the unnamed module, can be ignored. Exceptions get their own unit, the next one. Last, the takeaway; how to check first comes below.

--

<div class="stage">

## `instanceof`

### Dimming every light, and nothing else

<div class="code-over-cards code-compact stage-top">
<div class="columns code-diff">
<div class="column">

#### Check, then cast

<!-- The left version alone: the check, then the cast; the right one at step 2, its line against the left two -->
<pre><code class="java" data-trim data-line-numbers="|2|3|2-3" data-fragment-index="0">
for (Device device : devices) {
    if (device instanceof Light) {
        Light lamp = (Light) device;
        lamp.setBrightness(30);
    }
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="2">

#### Check and cast at once

<pre><code class="java" data-trim data-line-numbers="2|2" data-fragment-index="2">
for (Device device : devices) {
    if (device instanceof Light lamp) {
        lamp.setBrightness(30);
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-three">
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>instanceof</code></strong><code>true</code> if the object is of that class or of a subclass; <code>false</code> for <code>null</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Then the cast</strong>If it is, the cast cannot fail: a new variable holds the reference, as that class.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>At once</strong>With a variable's name after the class, <code>instanceof</code> checks and declares it, already cast.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
Check with <strong><code>instanceof</code></strong> before a <strong>downcast</strong>; with a variable's name, it does both <strong>at once</strong>.
</blockquote>

</div>

Note:
A button that dims every light in the house to 30% and leaves the other devices alone. On entry, the classic way, the one that shows what happens underneath: ask them what each line does. Step 1, the check: device instanceof Light asks the object, while the program runs, whether it is a Light; a ColorLight would also say yes, since it is a Light too, and null always says no. Step 2, the cast, only inside the if: it can no longer fail, and lamp holds the same reference as a Light, so setBrightness compiles. Step 3, the form Java has had since version 16: a variable's name after the class joins both lines, the check and the cast, into one; lamp only exists inside the if. Either is fine; the second leaves less room for mistakes. A word of advice: a chain of instanceof for every kind of device is usually a sign that the method should be overridden instead. Last, the takeaway.

--

<div class="stage">

## `getClass()`

### Exactly that class, not a subclass

<table class="data-table verdict-table stage-body">
<thead>
<tr><th>The object</th><th class="mark-head"><code>instanceof Light</code></th><th class="mark-head"><code>getClass() == Light.class</code></th><th>Why</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>new Light(…)</code></th><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="mark mark-ok fragment custom" data-fragment-index="0">✓</td><td class="fragment custom" data-fragment-index="0">Exactly that class</td></tr>
<tr><th scope="row"><code>new ColorLight(…)</code></th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="mark mark-warn fragment custom" data-fragment-index="1">✗</td><td class="fragment custom" data-fragment-index="1">A subclass: a <code>Light</code>, but not only</td></tr>
<tr><th scope="row"><code>new Thermostat(…)</code></th><td class="mark mark-warn fragment custom" data-fragment-index="2">✗</td><td class="mark mark-warn fragment custom" data-fragment-index="2">✗</td><td class="fragment custom" data-fragment-index="2">Another branch of the hierarchy</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="3">
<strong><code>instanceof</code></strong> accepts the class and its <strong>subclasses</strong>; <strong><code>getClass()</code></strong> gives the object's <strong>exact class</strong>.
</blockquote>

</div>

Note:
The other way to ask an object its class. getClass(), inherited from Object, returns the class the object was created with, whatever the type of the variable; Light.class is the class Light written as a value, so the two can be compared with ==. Each row is an object, whatever variable holds it, asked both questions. Ask about each row before filling it in. A Light: yes to both. A ColorLight, from the hierarchy slide: it is a Light, so instanceof says yes, but its class is ColorLight, not Light. A Thermostat: no to both. Two objects of the same class: a.getClass() == b.getClass(). To print it, device.getClass().getSimpleName() gives the name, ColorLight. getClass is final in Object: no class can override it, so the answer is always true. On null it throws a NullPointerException, unlike instanceof. Which to use: instanceof when any kind of Light will do, which is almost always; getClass() when it has to be that class and no other, as equals needs, below. Last, the takeaway.

--

<div class="stage">

## Two equal lamps?

### Same class, same name

<div class="code-run code-compact stage-top">

<!-- Each println alone first, to ask, then its line of output; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1-2|3|3|4|4|3-4" data-fragment-index="0">
Device first = new Light("Living room lamp");
Device second = new Light("Living room lamp");
System.out.println(first == second);
System.out.println(first.equals(second));
</code></pre>

<div class="fragment" data-fragment-index="1">
<pre><code class="nohighlight hljs console" data-noescape>false
<span class="fragment" data-fragment-index="3">false</span></code></pre>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="4"><span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="2"></span>
<code>Object</code>'s <strong><code>equals</code></strong> compares <strong>references</strong>, as <code>==</code>: a class decides what makes two of its objects equal by <strong>overriding</strong> it.
</blockquote>

</div>

Note:
The app must not add the same device twice, so it needs to know when two devices are the same: the same kind and the same name. On entry, two lamps created apart, with the same name. Step 1, ==, alone: remind them what == compares and ask what it prints. Step 2, false, as in unit 2: two objects, two references. Step 3, equals, the method strings use: ask what they think it does here. Step 4, false too: Device does not override equals, so the one that runs is Object's, which only compares references, exactly as ==. String overrides it to compare characters, which is why it worked with strings. Last, the takeaway, with both lines marked; the override is below.

--

<div class="stage">

## Overriding `equals`

### When two objects are equal

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0: the parameter, then each check in its order -->
<pre><code class="java" data-trim data-line-numbers="5|6-8|9-11|12-13|" data-fragment-index="0">
public class Device {
    // attributes, constructor and methods, as before

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Device other = (Device) obj;
        return this.name.equals(other.name);
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>An <code>Object</code> parameter</strong>The same signature as in <code>Object</code>: anything can be compared with it.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>The same object</strong>The same reference: always equal.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>The same class</strong><code>null</code> or another class: never equal. After this check, the cast cannot fail.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Its attributes</strong>Compares those that decide when two objects are equal, with <code>equals</code> for the objects among them.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
<strong><code>equals</code></strong>: the <strong>same object</strong>, then the <strong>same class</strong>, then the <strong>attributes</strong> that decide it.
</blockquote>

</div>

Note:
The standard way to write equals, here in Device, once for every device; the checks go in this order in every class. On entry, the header: the parameter is an Object, not a Device, because that is Object's signature; equals(Device other) would be an overload, and @Override would reject it. Step 1, the first check: the same reference means the same object, so it is equal to itself, true, with nothing else to compare. Step 2, the second: null, or an object of another class, can never be equal, false. getClass() and not instanceof Device, so a lamp is never equal to a speaker or to a color lamp with the same name. Step 3, what is left is an object of the same class: the cast cannot fail, and the attributes that decide it are compared, here the name, a String, with equals. With it, first.equals(second) prints true, while first == second still prints false. One more rule, for unit 5: a class that overrides equals must override hashCode too, which collections rely on; IntelliJ generates both (Code, Generate). Last, the takeaway.

---

<div class="stage">

## Exercises

### What you can solve so far

<div class="rules rules-grid rules-pair stage-top">
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Inheritance/Exercise%201">Exercise 1</a></strong>Employees: three subclasses that override the methods of a superclass, kept in one array of the superclass.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Inheritance/Exercise%202">Exercise 2</a></strong>Antiques: <code>protected</code> attributes, <code>toString</code> and <code>instanceof</code>, in a matrix ordered by century.</p>
</div>
</div>

</div>

Note:
The exercises of the course that can be solved with what has been seen up to here: inheritance, access control, overriding and polymorphism. They are in the JetBrains Academy course, in the Inheritance lesson; each title opens it on GitHub. The rest of the unit's exercises need abstract classes, at the end of the next section.

---

<!-- .slide: class="contents" data-section="5" -->

Note:
The hierarchy works: one array, one call, each kind of device with its own behavior. Two questions are left. Does a plain Device, of no kind, make sense? And what about something only some devices can do?

---

<div class="stage">

## A device of no kind

### What would `new Device("Thing")` be?

<table class="data-table verdict-table stage-body">
<thead>
<tr><th>The object</th><th>What you set</th><th>Power it uses</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>new Light(…)</code></th><td class="fragment custom" data-fragment-index="0">Brightness</td><td class="fragment custom" data-fragment-index="0">0.1 W per point of brightness</td></tr>
<tr><th scope="row"><code>new Thermostat(…)</code></th><td class="fragment custom" data-fragment-index="1">Target temperature</td><td class="fragment custom" data-fragment-index="1">3 W while on</td></tr>
<tr><th scope="row"><code>new Speaker(…)</code></th><td class="fragment custom" data-fragment-index="2">Volume</td><td class="fragment custom" data-fragment-index="2">0.05 W per point of volume</td></tr>
<tr><th scope="row"><code>new Device("Thing")</code></th><td class="mark mark-warn mark-start fragment custom" data-fragment-index="3">?</td><td class="mark mark-warn mark-start fragment custom" data-fragment-index="3">?</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="4">
What each subclass fills in, the superclass alone <strong>cannot</strong>: an object of it <strong>makes no sense</strong>, yet <code>new</code> compiles.
</blockquote>

</div>

Note:
Two questions every device in the app has to answer: what its screen lets you set, and how much power it uses, so the app can show what the house is using. Fill in each row after asking. A light: its brightness, and 0.1 W per point of it, a 10 W bulb. A thermostat: the target temperature, and 3 W while it is on. A speaker: its volume, and 0.05 W per point. Before the last row, ask them: and a Device of no kind? There is no answer to either question: nothing to set, and no way to know what it uses. Nothing in the house is just a device, as nothing on a farm is just an animal: Device is the name for what the three kinds share. If someone asks whether it could be a general device, one that only turns on and off: ask what it would be in the house. A smart plug, say; but then it has its own power use and answers both questions, so it is a kind of its own, class Plug extends Device, and Device itself still answers nothing. Last, the takeaway: and yet new Device("Thing") compiles. What this does to the code is below.

--

<div class="stage">

## No honest answer

### How much power does it use?

<div class="columns code-compact code-dense stage-top">
<div class="column code-over-cards code-compact">
<div>

#### `Device.java`

<!-- Both blocks and the cards step together from 0; the empty span in the note holds the index only the code uses -->
<pre><code class="java" data-trim data-line-numbers="4-6||5|4-6|" data-fragment-index="0">
public class Device {
    // attributes, constructor and methods, as before

    public double watts() {
        return 0.0;
    }
}
</code></pre>

</div>
<div>

#### `Main.java`

<pre><code class="java" data-trim data-line-numbers="|2|2||" data-fragment-index="0">
Device thing = new Device("Thing");
System.out.println(thing.watts());
</code></pre>

</div>
<div class="fragment" data-fragment-index="1">
<pre><code class="nohighlight hljs console" data-noescape>0.0</code></pre>
</div>
</div>
<div class="column rules">
<div class="rule">
<p><strong>A method for every subclass</strong>Declared in the superclass, so it can be called on any of them.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="1">
<p><strong>No honest answer</strong>In the superclass it has no possible body: any value it returns is false.</p>
</div>
<div class="rule rule-warn fragment" data-fragment-index="2">
<p><strong>Silent if forgotten</strong>A subclass that does not override it inherits that false value, with no error.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3"><span class="fragment" data-fragment-index="0"></span>
Some methods every subclass needs have <strong>no possible body</strong> in the superclass: whatever they return is <strong>false</strong>.
</blockquote>

</div>

Note:
The power column of the slide above, in code. On entry, the method: every device needs a watts(), and Device has to declare it, so that devices[i].watts() compiles in a loop; each subclass overrides it with its own rule. Step 1, the println alone: ask what it prints, and what it should print. Step 2, 0.0, with Device's body marked: a device of no kind has no true answer, so whatever it returns is a lie. Step 3: worse, a new subclass that forgets to override it will report 0 W without any error. Last, the takeaway: Device should create no objects, and watts() should have no body there; the way to say both in Java is below.

--

<div class="stage">

## Abstract classes

### Only there to be extended

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="1|9|2-7|1,9" data-fragment-index="0">
public abstract class Device {
    private String name;
    private boolean on;

    public Device(String name) {
        this.name = name;
    }

    public abstract double watts();

    // the rest of the class, as before
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>abstract class</code></strong>No objects of its own: <code>new</code> with it does not compile.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>abstract</code> method</strong>A header and a <code>;</code>, no body: it says what every subclass does, not how.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Still a class</strong>Attributes, a constructor for <code>super(...)</code>, and methods with a body.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
An <strong>abstract class</strong> creates no objects; its <strong>abstract methods</strong> are left to its subclasses.
</blockquote>

</div>

Note:
The fix for both problems. On entry, abstract before class: Device is abstract; cannot be instantiated. Device variables and arrays are still fine, Device[] devices included, and so is everything in the previous section: only new Device(...) is forbidden. Step 1, an abstract method: no braces, just a semicolon; every device has a watts() that can be called, and each subclass says how much. A class with an abstract method has to be abstract itself. Step 2, the rest is unchanged: an abstract class can have attributes, constructors, which its subclasses call with super(...), and normal methods, all inherited as before. Last, the takeaway, with the two abstract lines marked.

--

<div class="stage">

## Implementing `watts`

### Every subclass must write it

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="4-10|1|1|" data-fragment-index="0">
public class Light extends Device {
    // as before

    @Override
    public double watts() {
        if (this.isOn()) {
            return 0.1 * this.brightness;
        }
        return 0.0;
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>The contract</strong>Every subclass writes the abstract method, each its own way.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="0">
<p><strong>Or it does not compile</strong>A subclass that leaves out an abstract method is an error.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Unless it is abstract</strong>An abstract subclass can leave it to its own subclasses.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A subclass has to override every <strong>abstract method</strong>, unless it is <strong>abstract</strong> itself.
</blockquote>

</div>

Note:
On entry, Light's watts(): a 10 W bulb, so 0.1 W per point of brightness, and nothing when it is off; it is overriding as before, with @Override. Thermostat and Speaker have their own, on the slide below. Step 1, in red, what happens if Light leaves it out: Light is not abstract and does not override abstract method watts() in Device. The abstract method is a promise every concrete device has to keep, checked by the compiler, unlike the 0.0 of "No honest answer". Step 2: a subclass may stay abstract and leave the method to the classes below it. With that, the app adds up the power of the whole house with a for-each over the array, total += device.watts(): 15.5 W with the three devices on. Last, the takeaway.

--

<div class="stage">

## The other `watts`

### Each device, its own rule

<div class="columns code-compact stage-top">
<div class="column">

#### `Thermostat.java`

```java
public class Thermostat extends Device {
    // as before

    @Override
    public double watts() {
        if (this.isOn()) {
            return 3.0;
        }
        return 0.0;
    }
}
```

</div>
<div class="column">

#### `Speaker.java`

```java
public class Speaker extends Device {
    // as before

    @Override
    public double watts() {
        if (this.isOn()) {
            return 0.05 * this.volume;
        }
        return 0.0;
    }
}
```

</div>
</div>

<blockquote class="stage-note fragment">
The same <strong>abstract method</strong>, a different <strong>body</strong> in each subclass.
</blockquote>

</div>

Note:
The other two devices keep the contract too, each with its own rule: the thermostat uses 3 W whenever it is on, and the speaker 0.05 W per point of volume, 2.5 W at its starting 50. With the lamp at 100%, 10 W, the three on add up to 15.5 W. Last, the takeaway.

---

<div class="stage">

## Not every device has a level

### Brightness and volume, yes; temperature, no

<div class="rules stage-top">
<div class="rule">
<p><strong>The level slider</strong>The app has one slider, from 0 to 100, for the lights and the speaker: <code>setLevel(int level)</code>.</p>
</div>
<div class="rule rule-error fragment">
<p><strong>Abstract, in the superclass</strong><code>public abstract void setLevel(int level);</code> in <code>Device</code>: <code>Thermostat</code> would have to write one too, though it has no level.</p>
</div>
<div class="rule rule-warn fragment">
<p><strong>A superclass in between</strong><code>Light extends AdjustableDevice</code> works, until lights also run on a schedule: <code>Light extends AdjustableDevice, ScheduledDevice</code> does not compile, a class has only one superclass.</p>
</div>
<div class="rule fragment">
<p><strong>A contract apart</strong>Something a class can do, whatever it is: an <strong>interface</strong>.</p>
</div>
</div>

<blockquote class="stage-note fragment">
Some abilities cut across the hierarchy: they do not fit in <strong>one superclass</strong>.
</blockquote>

</div>

Note:
A new feature for the app: one slider that sets the level of anything that has one, the brightness of a light or the volume of the speaker. Where does setLevel go? Ask them before each card. In Device, abstract: then the thermostat must write one, and anything it does is nonsense; an abstract method forces a behavior on every subclass, even those it makes no sense for. A new abstract class, AdjustableDevice, extended by Light and Speaker: it works for now. But the next ability, say devices that run on a schedule, the lights and the thermostat, would cut across it: a ScheduledDevice for them leaves Light needing both, and extends takes only one class. The way out is a contract that is not part of the tree, which is below. Last, the takeaway.

--

<div class="stage">

## Interfaces

### What a class can do

<div class="columns code-compact stage-top">
<div class="column code-over-cards code-compact">
<div>

#### `Adjustable.java`

```java
public interface Adjustable {
    void setLevel(int level);
}
```

</div>
<div class="fragment" data-fragment-index="0">

#### `Light.java`

```java
public class Light extends Device implements Adjustable {
    // as before

    @Override
    public void setLevel(int level) {
        this.setBrightness(level);
    }
}
```

</div>
</div>
<div class="column rules">
<div class="rule">
<p><strong><code>interface</code></strong>Method headers with no body, <code>public</code> and <code>abstract</code> without saying so.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>implements</code></strong>The class promises to write every method of the interface, or it does not compile.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Several at once</strong>A class extends one class, and implements as many interfaces as needed: <code>class Light extends Device implements Adjustable, Schedulable</code>.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
An <strong>interface</strong> says what a class <strong>can do</strong>; a class <strong>implements</strong> it by writing its methods.
</blockquote>

</div>

Note:
On entry, the interface: like an abstract class with nothing but abstract methods, so it needs neither public nor abstract on them. It lives in its own file, as a class. Interfaces are usually named with an adjective, what the class can be or do: Adjustable, and in Java itself Comparable or Runnable. Step 1, Light implements it: it still extends Device, and now also promises a setLevel, which reuses setBrightness. Speaker implements it too, with setVolume, on the slide below; Thermostat does not, and nothing forces it to. Light is not abstract and does not override abstract method setLevel(int) in Adjustable, if it forgets. Step 2: the lamp with a level and a schedule, which did not compile with two superclasses on the slide above, is fine with two interfaces, separated by commas. Last, the takeaway.

--

<div class="stage">

## An interface as a type

### Lights and speakers, together

<div class="columns code-compact code-dense stage-top">
<div class="column">

#### `Speaker.java`

```java
public class Speaker extends Device implements Adjustable {
    // as before

    @Override
    public void setLevel(int level) {
        this.setVolume(level);
    }
}
```

</div>
<div class="column code-over-cards code-compact">
<div>

#### `Main.java`

<!-- Each println alone first, to ask, then its line of output; the empty spans in the note hold the indices only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1-2|4|6-8|10|10|11|11|4,6-8" data-fragment-index="0">
Light lamp = new Light("Living room lamp");
Speaker speaker = new Speaker("Kitchen speaker");

Adjustable[] adjustables = {lamp, speaker};

for (Adjustable adjustable : adjustables) {
    adjustable.setLevel(30);
}

System.out.println(lamp.getBrightness());
System.out.println(speaker.getVolume());
</code></pre>

</div>
<div class="fragment" data-fragment-index="3">
<pre><code class="nohighlight hljs console" data-noescape>30
<span class="fragment" data-fragment-index="5">30</span></code></pre>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="6"><span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="1"></span><span class="fragment" data-fragment-index="2"></span><span class="fragment" data-fragment-index="4"></span>
An interface is a <strong>type</strong>: its variables refer to objects of <strong>any class</strong> that implements it.
</blockquote>

</div>

Note:
On the left, Speaker, which implements Adjustable too: its setLevel reuses setVolume. The slider at work. On entry, a lamp and a speaker. Step 1, an array of Adjustable: an interface creates no objects, but it is a type, so it has variables and arrays, which can hold any object of a class that implements it; upcasting works with interfaces as with superclasses. Step 2, one loop sets the level of both: each runs its own setLevel, polymorphism again. Then each println alone, to ask what it prints, and its line of output on the next step: 30 and 30, the brightness of one and the volume of the other. instanceof works with interfaces too: device instanceof Adjustable adjustable picks, from an array of Device, the ones with a level. Last, the takeaway.

---

<div class="stage">

## Abstract class or interface?

### What each one can hold

<table class="data-table compare-table stage-body">
<thead>
<tr><th></th><th>Abstract class</th><th>Interface</th></tr>
</thead>
<tbody>
<tr><th scope="row">Objects with <code>new</code></th><td class="fragment custom" data-fragment-index="0">No</td><td class="fragment custom" data-fragment-index="0">No</td></tr>
<tr class="fragment" data-fragment-index="1"><th scope="row">Attributes</th><td class="fragment custom" data-fragment-index="2">Any</td><td class="fragment custom" data-fragment-index="2">Only constants</td></tr>
<tr class="fragment" data-fragment-index="3"><th scope="row">Constructors</th><td class="fragment custom" data-fragment-index="4">Yes, for <code>super(...)</code></td><td class="fragment custom" data-fragment-index="4">No</td></tr>
<tr class="fragment" data-fragment-index="5"><th scope="row">Methods with a body</th><td class="fragment custom" data-fragment-index="6">Yes</td><td class="fragment custom" data-fragment-index="6">No</td></tr>
<tr class="fragment" data-fragment-index="7"><th scope="row">Per class</th><td class="fragment custom" data-fragment-index="8">One, with <code>extends</code></td><td class="fragment custom" data-fragment-index="8">Several, with <code>implements</code></td></tr>
<tr class="fragment" data-fragment-index="9"><th scope="row">Says</th><td class="fragment custom" data-fragment-index="10">What it <strong>is</strong></td><td class="fragment custom" data-fragment-index="10">What it <strong>can do</strong></td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="11">
An abstract class shares <strong>a part</strong> with its subclasses; an interface, only <strong>a contract</strong>.
</blockquote>

</div>

Note:
The two side by side; each row shows its question first: ask for both answers before filling them in. Neither creates objects. An abstract class can hold anything a class can: attributes, constructors, methods with a body; Device shares its name, its on and their methods. An interface holds no state: any attribute written in it is a constant, public static final without saying so, and it has no constructors. Methods with a body: not in what this course writes; since Java 8 an interface can also have default and static methods with a body, which they will meet in Java's own interfaces. Per class: one superclass, any number of interfaces. Last row, how to choose: Light is a Device, and can be adjusted. Then the takeaway.

---

<div class="stage">

## Exercises

### The rest of the unit

<div class="rules rules-grid rules-three stage-top">
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Inheritance/Exercise%203">Exercise 3</a></strong>Geometric figures: an abstract class, subclasses in packages of their own, and a container for them.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Inheritance/Exercise%204">Exercise 4</a></strong>Numbers: a hierarchy under an abstract <code>Real</code>, whose operations depend on the type of each number.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Inheritance/Exercise%205">Exercise 5</a></strong>Housing: an abstract tax that each kind of home calculates its own way, and an agency that keeps them.</p>
</div>
</div>

</div>

Note:
The exercises that need the last section, abstract classes. They are in the JetBrains Academy course, in the Inheritance lesson; each title opens it on GitHub.

---

<div class="stage">

## Unit summary

### What to take away from Inheritance

<div class="rules rules-grid stage-body">
<div class="rule">
<p><strong>Inheritance</strong>A <strong>subclass</strong> <code>extends</code> a superclass, inherits all it has and does, and starts its constructor with <strong><code>super(...)</code></strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Access control</strong>Attributes <strong><code>private</code></strong>, reached through getters and setters; <code>protected</code> opens them to subclasses.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Overriding</strong>A subclass writes an inherited method again with the <strong>same signature</strong>, marked <strong><code>@Override</code></strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Polymorphism</strong>A superclass variable holds any subclass object, and each call runs <strong>its object's version</strong>; <code>instanceof</code> and <code>getClass()</code> ask for its class.</p>
</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong>Abstract classes and interfaces</strong>An <strong>abstract class</strong> is only there to be extended; an <strong>interface</strong> is a contract any class can implement.</p>
</div>
</div>

</div>

Note:
Ask the class to recall an example of each section before showing the next one: the three device screens and Device; the lamp at 250% and setBrightness; the tiles and status(); the All off button and the array of Device, and two equal lamps; watts() and the level slider. The next unit deals with what was left aside more than once in this one: what a program does when something goes wrong, exceptions, such as the ClassCastException.
