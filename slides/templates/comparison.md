<!-- type: comparison -->
<!-- the known version alone, the other one next to it, then only the lines that really differ -->
<!-- code-diff keeps every line readable; with no difference to mark, use fenced blocks with [] -->
<!-- inside raw <pre>, write < as &amp;lt; (the entity is decoded once before the HTML is read) -->

<div class="stage">

## The `if` statement

### One-line subtitle

<div class="columns code-diff stage-top">
<div class="column">

#### C

<!-- C alone on entry, Java at step 0; from step 1 both blocks step together -->
<pre><code class="c" data-trim data-line-numbers="|1" data-fragment-index="1">
int passed = 1;
if (passed) {
    printf("Well done\n");
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### Java

<pre><code class="java" data-trim data-line-numbers="|1" data-fragment-index="1">
boolean passed = true;
if (passed) {
    System.out.println("Well done");
}
</code></pre>

</div>
</div>

<!-- Same index as the highlight: reveal renumbers indices without gaps before the code steps exist -->
<blockquote class="stage-note fragment" data-fragment-index="1">
The one difference that matters, with the <strong>key words</strong> in bold.
</blockquote>

</div>

Note:
On entry, the version they know; ask them how it would look in the other one. Step 1, the other version. Step 2, what the highlight marks, and why it matters, with the note.
