<!-- type: comparison -->
<!-- two versions side by side, both visible at once; [n] highlights only the lines that really differ -->
<!-- code-diff keeps every line readable; with no difference to mark, use [] (numbers, no highlight) -->

<div class="stage">

## The `if` statement

### One-line subtitle

<div class="columns code-diff stage-top">
<div class="column">

#### C

```c [1]
int passed = 1;
if (passed) {
    printf("Well done\n");
}
```

</div>
<div class="column">

#### Java

```java [1]
boolean passed = true;
if (passed) {
    System.out.println("Well done");
}
```

</div>
</div>

<blockquote class="stage-note fragment">
The one difference that matters, with the <strong>key words</strong> in bold.
</blockquote>

</div>

Note:
What the highlight marks, and why it matters; the note comes with the one click.
