<!-- .slide: class="cover" -->

## Unit 2: Classes, objects, and methods

### The building blocks of object-oriented programming

Note:
First step into object-oriented programming. They already write Java with primitive types, arrays and control structures (unit 1), and from C they know how to group data in a struct and work on it with separate functions: a class brings that data and the functions that act on it together.

---

<!-- .slide: class="contents" data-section="1" -->

1. Classes and objects
2. Creating objects
3. static and final
4. Overloading
5. Strings

Note:
Walk through the index quickly; the details come in each section.

---

<div class="stage">

## Three bank accounts

### What do they have in common?

<div class="phones stage-body">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>AG</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Ana García López</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 1234 5678 9012</p>
<p class="phone-balance">€1,250.00</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>LP</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Luis Pérez Martín</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 5590 2173 4468</p>
<p class="phone-balance">€80.50</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>MR</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Marta Ruiz Sánchez</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 8812 0457 3391</p>
<p class="phone-balance">€0.00</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
</div>

</div>

Note:
Don't say "class" or "object" yet. Let the class answer first: the three screens are laid out the same, every account has a holder, an account number and a balance, only the values change. Then point at the buttons: deposit and withdraw are there on all three, so the same operations work on any account. The answer comes below.

--

<div class="stage">

## The template they share

### Remove the values and the three accounts look the same

<div class="phone-beside stage-body">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name phone-slot">Holder</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-label phone-slot">IBAN</p>
<p class="phone-balance phone-slot">Balance</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone-beside-cards">
<div class="rule rule-state fragment">
<p><strong>Data</strong>Holder, IBAN and balance: every account has them, each with its own values.</p>
</div>
<div class="rule rule-behavior fragment">
<p><strong>Operations</strong>Deposit and withdraw: every account can do them.</p>
</div>
<blockquote class="fragment">
Same <strong class="text-state">data</strong> and same <strong class="text-behavior">operations</strong>, different <strong>values</strong>.
</blockquote>
</div>
</div>

</div>

Note:
This is the answer to the previous slide. Take the values away and one screen is left, the same for the three accounts. First the data: every account has a holder, an IBAN and a balance, but Ana's balance is not Luis's. Then the operations: any account can take a deposit or a withdrawal. The fields are empty because each account fills them in its own way; the buttons are not, because depositing works the same for everyone. Then ask them, out loud: how would they write it in C, with what they already know? Give them a few minutes, on paper or in pairs; the solution is below: the struct, the functions, and what ties them together. Still no "class" or "object": the names come after the C version.

--

<div class="stage">

## The C version

### The data and its operations live apart

<div class="columns code-compact stage-top">
<div class="column">

<!-- Each piece is named first, so the class can say how they would write it; its code comes on the next step -->
#### The <span class="text-state">data</span><span class="fragment" data-fragment-index="0">: a struct</span>

<pre class="fragment" data-fragment-index="0"><code class="c" data-trim data-line-numbers>
struct Account {
    char holder[50];
    char iban[35];
    double balance;
};
</code></pre>

</div>
<div class="column fragment" data-fragment-index="1">

#### The <span class="text-behavior">operations</span><span class="fragment" data-fragment-index="2">: loose functions</span>

<!-- At step 3, what ties each function to the struct, with the note -->
<pre class="fragment" data-fragment-index="2"><code class="c" data-trim data-line-numbers="|1-2,5-7" data-fragment-index="3">
void deposit(struct Account *account, double amount) {
    account->balance += amount;
}

void withdraw(struct Account *account, double amount) {
    if (account->balance >= amount) {
        account->balance -= amount;
    }
}
</code></pre>

</div>
</div>

<!-- Same index as the highlight: reveal renumbers indices without gaps before the code steps exist -->
<blockquote class="stage-note fragment" data-fragment-index="3">
Nothing ties them together: every <strong class="text-behavior">operation</strong> has to be <strong>handed</strong> the <strong class="text-state">data</strong>, and by pointer.
</blockquote>

</div>

Note:
Their answer, in two pieces, each one named before its code so they can say how they would write it. On entry, only the data: ask them how they would keep it in C. Step 1, the struct, what they did in Programming Fundamentals I: it groups the data of one account under one type; text is a char array with room for the final '\0', 35 because the longest IBAN has 34 characters. Step 2, the operations: ask them again. Step 3, functions outside the struct, on their own; ask them how each function gets to the account it has to change. Step 4, the only link is that parameter: each function has to receive the account, and by pointer, because passed by value it would get a copy and the real balance would not move; every field is reached through account->. Withdraw checks the balance first; if there is not enough money it does nothing (reporting it comes with exceptions, in unit 4). The takeaway is the reason for what comes next: nothing ties the data to its operations, every call has to remember the & and the right account, and nothing in the struct says what can be done with it. Classes put the operations inside, next to the data. (The balance can still be changed by hand, ana.balance = -500.00, in C and, for now, in Java too; forbidding it comes later in the course.)

---

<div class="stage">

## Classes and objects

### Another way: the data and its operations together

<div class="phone-family stage-top">
<div class="phone-group">
<p class="phone-caption"><strong>Class</strong>The template: what every account <strong class="text-state">has</strong> and <strong class="text-behavior">does</strong></p>
<div class="phones">
<div class="phone phone-marked">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name phone-slot">Holder</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-label phone-slot">IBAN</p>
<p class="phone-balance phone-slot">Balance</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
</div>
</div>
<svg class="phone-arrow fragment" data-fragment-index="1" viewBox="0 0 24 24" aria-hidden="true"><path d="M2 11h15.2l-5.6-5.6L13 4l8 8-8 8-1.4-1.4 5.6-5.6H2z"/></svg>
<div class="phone-group fragment" data-fragment-index="1">
<p class="phone-caption"><strong>Objects</strong>Made from the template: each one with its own values</p>
<div class="phones">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>AG</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Ana García López</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 1234 5678 9012</p>
<p class="phone-balance">€1,250.00</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>LP</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Luis Pérez Martín</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 5590 2173 4468</p>
<p class="phone-balance">€80.50</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>MR</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Marta Ruiz Sánchez</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 8812 0457 3391</p>
<p class="phone-balance">€0.00</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong>class</strong> describes what its objects <strong class="text-state">have</strong> and <strong class="text-behavior">do</strong>; each <strong>object</strong> has its own <strong>values</strong>.
</blockquote>

</div>

Note:
The answer to the problems of the C version: instead of a struct here and functions there, one template that holds both. Now the names. The empty screen is the class: it says what every account has (holder, IBAN, balance) and what it can do (deposit, withdraw), but it holds no money, and nobody can deposit into a template. Each filled screen is an object: a real account made from the class, with its own values. One class, and as many objects as we need: three here, millions in a real bank.

--

<div class="stage">

## In Java: the data

### The struct becomes a class

<div class="code-over-cards stage-top">
<div class="columns code-diff">
<div class="column">

#### C: `bank.c`

<!-- C alone on entry, Java at step 0; from step 1 both blocks and the cards go together -->
<pre><code class="c" data-trim data-line-numbers="|1,5|2-3|2-4" data-fragment-index="1">
struct Account {
    char holder[50];
    char iban[35];
    double balance;
};
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### Java: `Account.java`

<pre><code class="java" data-trim data-line-numbers="|1,5|2-3|2-4" data-fragment-index="1">
class Account {
    String holder;
    String iban;
    double balance;
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-pair">
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>class</code></strong>Takes the place of <code>struct</code>, with no <code>;</code> after the closing brace.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong><code>String</code></strong>Java's type for text, with no size to choose. More about it at the end of the unit.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
A class declares its <strong class="text-state">data</strong> as a struct did: a <strong>type</strong> and a <strong>name</strong> for each one.
</blockquote>

</div>

Note:
First piece: the data. On entry, the struct they just wrote; ask them how they think it looks in Java. Step 1, the Java version: almost the same. Step 2, the keyword: class instead of struct, and no semicolon after the brace. As with Hello.java, the class goes in a file with its name, Account.java. Step 3, the text: no char arrays and no sizes, just String, Java's type for text; for now that is all they need, it gets its own section at the end of the unit. The balance stays a double. Last, the takeaway: so far a class is a struct with another keyword; the difference comes with the operations, on the slide below.

--

<div class="stage">

## In Java: the operations

### The functions move inside the class

<div class="columns code-diff code-compact stage-top">
<div class="column">

#### C: `bank.c`

<!-- C alone on entry, Java at step 0; at step 1 both blocks mark what changes -->
<pre><code class="c" data-trim data-line-numbers="|3-4,7-9" data-fragment-index="1">
/* struct, as before */

void deposit(struct Account *account, double amount) {
    account->balance += amount;
}

void withdraw(struct Account *account, double amount) {
    if (account->balance >= amount) {
        account->balance -= amount;
    }
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### Java: `Account.java`

<pre><code class="java" data-trim data-line-numbers="|4-5,8-10" data-fragment-index="1">
class Account {
    // holder, iban and balance, as before

    void deposit(double amount) {
        balance += amount;
    }

    void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }
}
</code></pre>

</div>
</div>

<!-- Same index as the highlight: reveal renumbers indices without gaps before the code steps exist -->
<blockquote class="stage-note fragment" data-fragment-index="1">
Inside a class, an <strong class="text-behavior">operation</strong> needs no parameter to reach the <strong class="text-state">data</strong>: it works on that of <strong>its own object</strong>.
</blockquote>

</div>

Note:
The piece where C and Java really part ways. On entry, the two C functions; ask them where they would put them in Java. Step 1, the Java version: the same two functions, but written inside the class, under the data from the previous slide (the comment stands for those three lines, so the snippet alone would not compile; the same goes for the struct at the top of bank.c). Step 2, what changes: the struct Account pointer is gone from both, and so is account->; balance on its own means the balance of the account the operation is working on. In C the function had to be told which account; in Java the operation belongs to the account, so it already knows. How to say which account then? That comes with Creating objects.

---

<div class="stage">

## Attributes and methods

### The names for what a class has and does

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0: attributes on entry, methods at step 0, the names at step 1, none with the note -->
<pre><code class="java" data-trim data-line-numbers="2-4|6-14|1-4,6,10|" data-fragment-index="0">
class Account {
    String holder;
    String iban;
    double balance;

    void deposit(double amount) {
        balance += amount;
    }

    void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule rule-state">
<p><strong>Attributes</strong>The data every object of the class holds, each object with its own values: <code>holder</code>, <code>iban</code>, <code>balance</code>.</p>
</div>
<div class="rule rule-behavior fragment" data-fragment-index="0">
<p><strong>Methods</strong>The operations every object can do, on its own attributes: <code>deposit</code>, <code>withdraw</code>.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Naming conventions</strong>The class, a noun in <strong><code>PascalCase</code></strong>. Attributes in <code>camelCase</code>, as variables. Methods in <code>camelCase</code> too, and verbs.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong>class</strong> groups <strong class="text-state">attributes</strong>, what its objects have, and <strong class="text-behavior">methods</strong>, what they do.
</blockquote>

</div>

Note:
Now the formal names for what they have been calling data and operations, on the whole class at last. On entry, the attributes: the variables declared in the class, outside any method; every object gets its own copy, which is why Ana's balance is not Luis's. They are also called fields, and that is the word many books and IntelliJ use. Step 1, the methods: the functions declared in the class; they work on the attributes of the object they are called on. Step 2, how to name them, conventions like the ones for variables in the first unit: it compiles either way, but every Java programmer follows them. A class is a thing, so a noun, in PascalCase, every word capitalized, the first one too, as Account; that capital is also how Account and account can be told apart at a glance. Attributes are camelCase, like any variable. Methods are camelCase too, and they do something, so they are verbs: deposit, withdraw. Last, the takeaway: a class is attributes plus methods, in the same colors as the phones.

---

<!-- .slide: class="contents" data-section="2" -->

Note:
The first section is done: they know what a class and an object are, and the names for what a class has and does. Now how objects are actually created in Java, and how to give them their values from the start.

---

<div class="stage">

## Creating objects

### `new` builds one, and the dot reaches what it has and does

<div class="code-over-cards code-compact stage-top">
<div class="columns code-diff">
<div class="column">

#### C: `bank.c`

<!-- C alone on entry, Java at step 0; from step 1 both blocks and the cards go together -->
<pre><code class="c" data-trim data-line-numbers="|4,8|5-7|9|4-9" data-fragment-index="1">
/* struct and its functions, as before */

int main(void) {
    struct Account ana = {
        .holder = "Ana García López",
        .iban = "ES00 4321 0001 1234 5678 9012",
        .balance = 1250.00
    };
    deposit(&amp;ana, 100.00);
    return 0;
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### Java: `Account.java`

<pre><code class="java" data-trim data-line-numbers="|5|6-8|9|5-9" data-fragment-index="1">
class Account {
    // attributes and methods, as before

    public static void main(String[] args) {
        Account ana = new Account();
        ana.holder = "Ana García López";
        ana.iban = "ES00 4321 0001 1234 5678 9012";
        ana.balance = 1250.00;
        ana.deposit(100.00);
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-three">
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>new</code></strong>Creates the object, as it created arrays. Its attributes start at <code>null</code> and <code>0.0</code>, not garbage.</p>
</div>
<div class="rule rule-state fragment" data-fragment-index="2">
<p><strong>Attributes</strong>The dot reaches them by name, as in a struct, to give them a value or read it.</p>
</div>
<div class="rule rule-behavior fragment" data-fragment-index="3">
<p><strong>Methods</strong>The dot also calls them, on that object, with no <code>&amp;</code>.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="4">
<strong><code>new</code></strong> creates an <strong>object</strong>, and the <strong>dot</strong> reaches its <strong class="text-state">attributes</strong> and <strong class="text-behavior">methods</strong>.
</blockquote>

</div>

Note:
One account, created and used in both languages. Both comments stand for what came before: in C the struct and the functions, in Java the attributes and methods; for now main goes inside the class Account itself. On entry, the C version: ask them how they think it looks in Java. Step 1, the Java version. Step 2, creating it: in C the variable is the struct itself, filled in its initializer, and a field left out would hold garbage if it were not initialized; in Java the type is just Account, with no struct in front, and new builds the object, the same new that built arrays; text starts as null, which means no text yet, and numbers at 0.0. Step 3, the attributes: C names each field in the initializer (.holder = ...), and Java gives each one its value afterwards with the same dot as a struct; the same dot reads them too. Unlike a char array, a String can take = at any time, not only when it is declared. Step 4, the methods: the dot also calls them, and this answers the question left open before: ana.deposit(100.00) deposits into ana's account, with no & and no pointer, because the object goes before the dot. What this way of creating objects costs comes back after the two memory diagrams below.

--

<div class="stage">

## What `new` does in memory

### Variables in the stack, objects in the heap

<div class="memory-layout code-compact stage-top">

<!-- Code and diagram step together from 0: each line, then its variable (and the object for new) -->
<pre><code class="java" data-trim data-line-numbers="1|2|3|" data-fragment-index="0">
int count = 3;
Account luis;
Account ana = new Account();
</code></pre>

<div class="memory">
<div class="memory-frame memory-stack"></div>
<div class="memory-frame memory-heap"></div>
<p class="memory-title memory-stack">Stack</p>
<p class="memory-title memory-heap">Heap</p>
<div class="memory-var memory-row-1"><span class="memory-cell">count</span><span class="memory-link"></span><span class="memory-cell memory-value">3</span></div>
<div class="memory-var memory-row-2 fragment" data-fragment-index="0"><span class="memory-cell">luis</span><span class="memory-link"></span><span class="memory-cell memory-null">null</span></div>
<div class="memory-none memory-row-2 fragment" data-fragment-index="0"></div>
<div class="memory-var memory-row-3 fragment" data-fragment-index="1"><span class="memory-cell">ana</span><span class="memory-link"></span><span class="memory-cell memory-value">0x7ad4</span></div>
<div class="memory-arrow memory-row-3 fragment" data-fragment-index="1"></div>
<div class="memory-object fragment" data-fragment-index="1">
<p>Account</p>
<span class="memory-cell">holder</span><span class="memory-link"></span><span class="memory-cell memory-null">null</span>
<span class="memory-cell">iban</span><span class="memory-link"></span><span class="memory-cell memory-null">null</span>
<span class="memory-cell">balance</span><span class="memory-link"></span><span class="memory-cell memory-value">0.0</span>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong>primitive</strong> variable holds its <strong>value</strong>; an object variable holds a <strong>reference</strong> to its object, or <strong><code>null</code></strong>.
</blockquote>

</div>

Note:
What happens in memory when a program creates objects. The stack keeps the variables of the method that is running, main here; the heap keeps the objects created with new. Each variable points to what it holds, the dashed arrow. On entry, a primitive: count holds the value itself, 3, right in the stack. Step 1, luis, declared without new: a variable of a class type does not hold an object, it holds a reference to one, and here it refers to none, drawn as null and as an arrow that leads nowhere; nothing exists in the heap for it. (Strictly, a local variable with no value cannot even be used, the compiler stops it with variable luis might not have been initialized; an attribute left like this is null. Either way, there is no object.) Step 2, ana with new: new builds the object in the heap, with its attributes at their starting values, null for the texts and 0.0 for the balance, and gives back where it is; ana keeps that address, drawn as 0x7ad4 and as the arrow that leads to the object itself, which Java never shows us. The dot follows that arrow: ana.balance goes to the object and reads its balance. With luis there is no arrow to follow: if the program runs the dot on a variable that is null, it stops with a NullPointerException. Last, the takeaway.

--

<div class="stage">

## Two variables, one object

### Assigning a reference does not copy the object

<div class="memory-layout code-compact stage-top">

<!-- Code and diagram step together from 0; the empty span in the note holds index 2, which only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1|2|3|4|2" data-fragment-index="0">
Account ana = new Account();
Account other = ana;
other.deposit(100.00);
System.out.println(ana.balance);
</code></pre>

<div class="memory">
<div class="memory-frame memory-stack"></div>
<div class="memory-frame memory-heap"></div>
<p class="memory-title memory-stack">Stack</p>
<p class="memory-title memory-heap">Heap</p>
<div class="memory-var memory-row-1"><span class="memory-cell">ana</span><span class="memory-link"></span><span class="memory-cell memory-value">0x7ad4</span></div>
<div class="memory-arrow memory-row-1"></div>
<div class="memory-var memory-row-2 fragment" data-fragment-index="0"><span class="memory-cell">other</span><span class="memory-link"></span><span class="memory-cell memory-value">0x7ad4</span></div>
<div class="memory-arrow memory-row-2 fragment" data-fragment-index="0"></div>
<div class="memory-object">
<p>Account</p>
<span class="memory-cell">holder</span><span class="memory-link"></span><span class="memory-cell memory-null">null</span>
<span class="memory-cell">iban</span><span class="memory-link"></span><span class="memory-cell memory-null">null</span>
<span class="memory-cell">balance</span><span class="memory-link"></span><span class="memory-cell memory-value"><span class="phone-swap"><span class="fragment fade-out" data-fragment-index="1">0.0</span><span class="phone-changed fragment" data-fragment-index="1">100.0</span></span></span>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3"><span class="fragment" data-fragment-index="2"></span>
Assigning one object variable to another copies the <strong>reference</strong>, not the object: both reach <strong>the same object</strong>.
</blockquote>

</div>

Note:
A difference with C that causes real bugs. On entry, one account, as on the previous slide. Step 1, other = ana: ask them what they expect, thinking of C, where assigning a struct copies all its fields. In Java the variable only holds a reference, so the assignment copies the address, 0x7ad4: two arrows, one object. Step 2, a deposit through other: the balance of that one object becomes 100.0. Step 3, printing ana.balance: ask them what it prints; 100.0, because ana and other are two names for the same account. The same happens when an object is passed to a method: the method gets the reference and can change the object, with no & and no pointers in sight. Last, the takeaway, with the assignment marked.

---

<div class="stage">

## Two accounts, by hand

### What does it print?

<div class="code-run code-compact stage-top">

<!-- the last step repeats the println: the output under the code shows with it -->
```java [1-4|6-8|10|10]
Account ana = new Account();
ana.holder = "Ana García López";
ana.iban = "ES00 4321 0001 1234 5678 9012";
ana.balance = 1250.00;

Account luis = new Account();
luis.holder = "Luis Pérez Martín";
luis.balance = 80.50;

System.out.println(luis.iban);
```

<div class="code-run-output">

```console
null
```

</div>
</div>

<blockquote class="stage-note code-run-output">
Giving each attribute its value by hand takes <strong>one line per attribute</strong>, and <strong>nothing warns</strong> when one is forgotten.
</blockquote>

</div>

Note:
Accounts created the way they know, as in Creating objects, now two of them; the lines go inside main, as there. On entry, Ana's account: new, then one line per attribute. Step 1, Luis's account: ask them to check it before going on; it is easy to miss that it has no IBAN. Step 2, printing luis.iban: ask what it prints. Step 3, null: it compiles and runs without a word, the account simply exists half-made, and the mistake shows up later and somewhere else, as a null where an IBAN was expected or a NullPointerException as soon as a method is called on it. Then ask them what the problem is with creating objects this way: one line per attribute for every object (with three accounts, twelve lines), nothing that forces every attribute to get a value, and an object that is incomplete until its last assignment. Ask how they would make forgetting one impossible. A hint for later: new Account(), with nothing in the parentheses, calls a constructor that Java adds on its own and that gives the attributes nothing; they will see it two slides below. Last, the takeaway; the fix, a constructor that asks for every value, comes below.

--

<div class="stage">

## Constructors

### Create an object and give it its values at once

<div class="columns code-compact code-dense stage-top">
<div class="column">

<!-- Code and cards step together from 0: the constructor on entry, this at step 0, deposit and withdraw at step 1,
     the constructor again with the note -->
<pre><code class="java" data-trim data-line-numbers="6|7-9|13,17-18|6-10" data-fragment-index="0">
class Account {
    String holder;
    String iban;
    double balance;

    Account(String holder, String iban, double balance) {
        this.holder = holder;
        this.iban = iban;
        this.balance = balance;
    }

    void deposit(double amount) {
        this.balance += amount;
    }

    void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
        }
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>The constructor</strong>Named as the class and with <strong>no return type</strong>. It runs every time <code>new</code> creates an object.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong><code>this</code></strong>Tells the object's attribute from a variable with <strong>the same name</strong>: <code>this.holder</code> is the attribute, <code>holder</code> the parameter.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Always, for clarity</strong>Even with no clash, <code>this.balance</code> says at a glance that it is the object's own. From now on, we always write it.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong>constructor</strong> gives each object its values <strong>the moment it is created</strong>.
</blockquote>

</div>

Note:
The fix for the problem on the slide above: a way to give an object all its values the moment it is created. On entry, the constructor: it looks like a method, but it is named exactly as the class and has no return type, not even void. It runs on its own every time new creates an object. Step 1, this: it stands for the object itself, and its use is telling apart two things with the same name. The parameters are named as the attributes, which is the usual style, so inside the constructor holder alone means the parameter, the closest one; this.holder is the attribute. Without this, holder = holder would assign the parameter to itself and the attribute would stay null. Step 2, deposit and withdraw: there is no clash there, so balance alone, as they wrote it until now, already means the attribute, and it compiles. Still, this.balance says at a glance that it belongs to the object and is not a parameter or a local variable, so from here on the course always writes it, for attributes and for calls to the object's own methods (this.deposit(amount)); their code should do the same. Last, the takeaway; how new uses it comes on the slide below.

--

<div class="stage">

## Creating with the constructor

### The same account, before and after

<div class="code-over-cards code-compact stage-top">
<div class="columns code-diff">
<div class="column">

#### Without a constructor

<!-- Before alone on entry; at step 0 after appears and both mark how the account is created;
     then new Account() on the left with the first card, and with the second also the constructor on the right;
     the note brings back the comparison -->
<pre><code class="java" data-trim data-line-numbers="|5-8|5|5|5-8" data-fragment-index="0">
class Account {
    // attributes and methods, as before

    public static void main(String[] args) {
        Account ana = new Account();
        ana.holder = "Ana García López";
        ana.iban = "ES00 4321 0001 1234 5678 9012";
        ana.balance = 1250.00;
    }
}
</code></pre>

</div>
<div class="column fragment" data-fragment-index="0">

#### With the constructor

<pre><code class="java" data-trim data-line-numbers="|5-8||2|5-8" data-fragment-index="0">
class Account {
    // attributes, constructor and methods, as before

    public static void main(String[] args) {
        Account ana = new Account(
                "Ana García López",
                "ES00 4321 0001 1234 5678 9012",
                1250.00);
    }
}
</code></pre>

</div>
</div>
<div class="rules rules-grid rules-pair">
<div class="rule fragment" data-fragment-index="1">
<p><strong>The default constructor</strong>With no constructor written, Java adds an empty one: it is what <code>new Account()</code> calls.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="2">
<p><strong>Gone once you write one</strong>Writing a constructor removes the default one: <code>new Account()</code> no longer compiles.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
<strong>One</strong> <code>new</code> per object instead of one line per attribute, and <strong>no attribute can be forgotten</strong>.
</blockquote>

</div>

Note:
Ana's account from Creating objects, created both ways, with main inside the class as before. On entry, how it was done until now: new, and then each attribute by hand with the dot. Step 1, the version with the constructor, with what changes marked in both: the same three values, in the order of the constructor's parameters, go straight to new, as they went between the braces of the C initializer; one value per line only because the call is long. Step 2, the first card, with only new Account() marked, explains the left-hand side: they never wrote a constructor before, yet new Account() worked, because a class with no constructor gets an empty one from Java, the default constructor. Step 3, the catch, in red, with cause and effect marked: the line that stands for the constructor on the right, and the new Account() on the left that it breaks: as soon as the class has a constructor of its own, Java no longer adds the default one, so the left-hand version stops compiling with this class; new Account() with no values does not exist any more. Step 4, the takeaway, with the comparison marked again, the gain: with three accounts, three lines instead of twelve, and since the constructor asks for every value, none can be left out by mistake.

---

<div class="stage">

## Exercises

### What you can solve so far

<div class="rules stage-top">
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Classes%2C%20objects%2C%20and%20methods/Exercise%201">Exercise 1</a></strong>Monomials: a class with its attributes, a constructor and methods that evaluate it.</p>
</div>
</div>

</div>

Note:
The first exercise of the course that can be solved with what has been seen up to here: a class, its objects and a constructor. They are in the JetBrains Academy course, in the Classes, objects, and methods lesson; each title opens it on GitHub.

---

<!-- .slide: class="contents" data-section="3" -->

Note:
Objects created and given their values: each account has its own holder, IBAN and balance. Now two words that change that picture: static, for what belongs to the class and is shared by every object, and final, for what never changes once set.

---

<div class="stage">

## One rate for every account

### What changes in one account, and what in all of them?

<div class="phones stage-body">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>AG</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Ana García López</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 1234 5678 9012</p>
<p class="phone-balance"><span class="phone-swap"><span class="fragment fade-out" data-fragment-index="1">€1,250.00</span><span class="phone-changed fragment" data-fragment-index="1">€1,350.00</span></span></p>
<p class="phone-label">Available balance</p>
<p class="phone-label phone-arrived fragment" data-fragment-index="2">Interest rate <span class="phone-swap"><span class="fragment fade-out" data-fragment-index="4"><span class="phone-changed fragment" data-fragment-index="3">2.00%</span></span><span class="phone-changed fragment" data-fragment-index="4">2.50%</span></span></p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main phone-tap fragment custom" data-fragment-index="0"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>LP</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Luis Pérez Martín</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 5590 2173 4468</p>
<p class="phone-balance">€80.50</p>
<p class="phone-label">Available balance</p>
<p class="phone-label phone-arrived fragment" data-fragment-index="2">Interest rate <span class="phone-swap"><span class="fragment fade-out" data-fragment-index="4"><span class="phone-changed fragment" data-fragment-index="3">2.00%</span></span><span class="phone-changed fragment" data-fragment-index="4">2.50%</span></span></p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>MR</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Marta Ruiz Sánchez</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 8812 0457 3391</p>
<p class="phone-balance">€0.00</p>
<p class="phone-label">Available balance</p>
<p class="phone-label phone-arrived fragment" data-fragment-index="2">Interest rate <span class="phone-swap"><span class="fragment fade-out" data-fragment-index="4"><span class="phone-changed fragment" data-fragment-index="3">2.00%</span></span><span class="phone-changed fragment" data-fragment-index="4">2.50%</span></span></p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
</div>

</div>

Note:
The three accounts again. Step 1, Ana taps Deposit to put in 100 euros. Ask them where those 100 go: in which account, and on which of the three phones something should change. Step 2, the answer: only her balance, now 1,350, in yellow; the other two stay as they were, because each object has its own balance. Step 3, a new line appears on the three phones, in yellow, the interest rate: what the bank pays each account for the money kept in it. Its value is not shown yet. Ask them: will it be different for each account, like the balance, or the same for all? Step 4, the value: 2.00% on the three phones at once, the same for everyone. Ask them where that 2.00% is kept: if every account had its own copy, changing the rate would mean changing them one by one, and they could end up different. It is one value for the whole bank, kept once, and every account reads it. Step 5, the bank raises its rate to 2.50%: it changes, in yellow, on the three phones at once, which only works because there is a single value. That is what static is about, below.

--

<div class="stage">

## Static attributes

### One value for the whole class

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="2|4-6|2" data-fragment-index="0">
class Account {
    static double interestRate = 2.00;

    String holder;
    String iban;
    double balance;

    // constructor and methods, as before
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>static</code>: one per class</strong>Kept once in the class and shared by all its objects: if it changes, all of them see the new value.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Without <code>static</code>: one per object</strong>Every object gets its own copy, with its own value.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Through the class</strong>No object is needed to reach it, only the class name: <code>Account.interestRate = 2.50;</code></p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong><code>static</code></strong> attribute belongs to the <strong>class</strong>: one value, <strong>shared by all its objects</strong>.
</blockquote>

</div>

Note:
The rate from the phones, in code. On entry, the line with static: interestRate is declared once in the class and kept once, not inside every account, so when it changes, every account sees the new value. Step 1, the attributes they already know, without static: each object gets its own copy, which is why Ana's deposit only changed her balance. Step 2, how to reach it: since it belongs to the class, it is used through the class name, Account.interestRate, and no account is needed; this is the line the bank runs to raise the rate. Inside the methods of Account it is used by its name alone, like any other attribute. If anyone brings up static from C: there it means something else, a local variable that keeps its value between calls or a global visible only in its file; in Java it only means that something belongs to the class. Last, the takeaway.

--

<div class="stage">

## Static methods

### Called through the class, with no object

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="7-9|3||7-9" data-fragment-index="0">
class Account {
    static double interestRate = 2.00;
    double balance;

    // the rest of the class, as before

    static void setInterestRate(double rate) {
        interestRate = rate;
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>A <code>static</code> method</strong>Belongs to the class too, and is called through it: <code>Account.setInterestRate(2.50);</code></p>
</div>
<div class="rule rule-error fragment" data-fragment-index="0">
<p><strong>No object, no attributes</strong>It runs for no object in particular, so it has no <code>this</code>, and an attribute without <code>static</code> does not compile inside it: whose would it be?</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>main</code> is one</strong>That is why <code>main</code> is <code>static</code>: Java runs it before any object exists.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong><code>static</code></strong> method works with the class, not with an object: it can only use what is <strong><code>static</code></strong>, and its parameters.
</blockquote>

</div>

Note:
Below the attributes, the same idea for methods: they can be static too. On entry, setInterestRate: static, so it is called through the class, Account.setInterestRate(2.50), with no account at all, and it changes the rate for every account. Step 1, the catch, in red: a static method does not run on any account, so it has no balance to use; writing balance inside it is an error, non-static variable balance cannot be referenced from a static context, because there is no object to take it from; for the same reason there is no this, which is why interestRate is written without it. It can use interestRate, which is static, and its parameters. Step 2, something they have been writing since the first day: public static void main. It is static because Java calls it when the program starts, before any object has been created. Last, the takeaway.

---

<div class="stage">

## What never changes

### Which of these could never change?

<div class="phones stage-body">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>AG</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Ana García López</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 1234 5678 9012<svg class="phone-lock fragment" data-fragment-index="0" viewBox="0 0 24 24" aria-hidden="true"><path fill-rule="evenodd" d="M7 10V7a5 5 0 0 1 10 0v3h1a1 1 0 0 1 1 1v9a1 1 0 0 1-1 1H6a1 1 0 0 1-1-1v-9a1 1 0 0 1 1-1zm2 0h6V7a3 3 0 0 0-6 0z"/></svg></p>
<p class="phone-balance">€1,250.00</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>LP</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Luis Pérez Martín</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 5590 2173 4468<svg class="phone-lock fragment" data-fragment-index="0" viewBox="0 0 24 24" aria-hidden="true"><path fill-rule="evenodd" d="M7 10V7a5 5 0 0 1 10 0v3h1a1 1 0 0 1 1 1v9a1 1 0 0 1-1 1H6a1 1 0 0 1-1-1v-9a1 1 0 0 1 1-1zm2 0h6V7a3 3 0 0 0-6 0z"/></svg></p>
<p class="phone-balance">€80.50</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>MR</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Marta Ruiz Sánchez</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 8812 0457 3391<svg class="phone-lock fragment" data-fragment-index="0" viewBox="0 0 24 24" aria-hidden="true"><path fill-rule="evenodd" d="M7 10V7a5 5 0 0 1 10 0v3h1a1 1 0 0 1 1 1v9a1 1 0 0 1-1 1H6a1 1 0 0 1-1-1v-9a1 1 0 0 1 1-1zm2 0h6V7a3 3 0 0 0-6 0z"/></svg></p>
<p class="phone-balance">€0.00</p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
</div>

</div>

Note:
Back to the three accounts. Ask them which values could change during the life of an account: the balance, all the time; the holder, if it is corrected or the account changes hands; the rate, when the bank decides. Step 1, the padlocks: the IBAN identifies the account from the day it is opened, and changing it would make it a different account. That is a value set once and never touched again, what final is for, below.

--

<div class="stage">

## Final attributes

### Given a value once, and never again

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="3|8|3|3,8" data-fragment-index="0">
class Account {
    String holder;
    final String iban;
    double balance;

    Account(String holder, String iban, double balance) {
        this.holder = holder;
        this.iban = iban;
        this.balance = balance;
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>final</code>: set once</strong>The attribute gets its value once and keeps it for the life of the object.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>In the constructor</strong>That one time is usually the constructor: each object gets its own value there.</p>
</div>
<div class="rule rule-error fragment" data-fragment-index="1">
<p><strong>Never again</strong>Any later change does not compile: <code>ana.iban = "ES00 9999 0001 0000 0000 0000";</code></p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A <strong><code>final</code></strong> attribute is set <strong>once</strong>, usually in the constructor, and <strong>never changes</strong>.
</blockquote>

</div>

Note:
The padlock, in code. On entry, final before the type: iban will get a value once and keep it. Step 1, where that happens: in the constructor, so every account gets its own IBAN when it is created; a final attribute is still one per object, Ana's IBAN is not Luis's. If no constructor gave it a value, the class would not compile. Step 2, in red: after that, any assignment is an error, cannot assign a value to final variable iban, wherever it is written. final also works on local variables, with the same meaning. Last, the takeaway.

---

<div class="stage">

## Constants: `static final`

### Shared by the whole class, and fixed

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="2|3|2" data-fragment-index="0">
class Account {
    static final String BANK_CODE = "4321";
    static double interestRate = 2.00;

    // attributes, constructor and methods, as before
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong><code>static final</code>: a constant</strong>One for the class, and it never changes. Named in <strong><code>UPPER_SNAKE_CASE</code></strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Only <code>static</code></strong>Shared too, but it can change.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="1">
<strong><code>static final</code></strong> makes a <strong>constant</strong>: one for the class, and it never changes.
</blockquote>

</div>

Note:
The two words together. On entry, BANK_CODE: static, so there is one for the whole class, and final, so it never changes; that is a constant, the 4321 every IBAN of this bank carries. Constants are named in UPPER_SNAKE_CASE, the convention they saw for constants in the first unit, so they stand out in the code; it is used as Account.BANK_CODE. Step 1, the difference with interestRate: static as well, one for the class, but not final, because the bank changes it from time to time. Last, the takeaway.

---

<div class="stage">

## Four kinds of attribute

### What each keyword changes

<!-- The attributes stay; each row's answers fill in at once, after asking the class -->
<table class="data-table stage-body">
<thead>
<tr><th>Attribute</th><th>Keywords</th><th>How many</th><th>Can it change?</th></tr>
</thead>
<tbody>
<tr><th scope="row"><code>balance</code></th><td class="fragment custom" data-fragment-index="0">none</td><td class="fragment custom" data-fragment-index="0">One per object</td><td class="fragment custom" data-fragment-index="0">Yes</td></tr>
<tr><th scope="row"><code>iban</code></th><td class="fragment custom" data-fragment-index="1"><code>final</code></td><td class="fragment custom" data-fragment-index="1">One per object</td><td class="fragment custom" data-fragment-index="1">No</td></tr>
<tr><th scope="row"><code>interestRate</code></th><td class="fragment custom" data-fragment-index="2"><code>static</code></td><td class="fragment custom" data-fragment-index="2">One for the class</td><td class="fragment custom" data-fragment-index="2">Yes</td></tr>
<tr><th scope="row"><code>BANK_CODE</code></th><td class="fragment custom" data-fragment-index="3"><code>static final</code></td><td class="fragment custom" data-fragment-index="3">One for the class</td><td class="fragment custom" data-fragment-index="3">No</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="4">
<strong><code>static</code></strong> decides <strong>how many</strong> there are; <strong><code>final</code></strong>, whether it can <strong>change</strong>.
</blockquote>

</div>

Note:
The section in one table, with the account's own attributes. The four attributes are there from the start; for each, ask the class which keywords it has, how many copies there are and whether it can change, and only then fill in its row. On entry, balance: an ordinary attribute, one per object, and it changes. Then iban: final keeps one per object but fixes it. Then interestRate: static makes it one for the class, and it can still change. Last, BANK_CODE: both together make a constant. The takeaway sums it up: the two words answer two different questions, so they combine freely.

---

<!-- .slide: class="contents" data-section="4" -->

Note:
Accounts now have attributes and methods of the object and of the class, some of them fixed. Now how one class can offer the same operation, or the same constructor, in several versions with the same name.

---

<div class="stage">

## One name, several versions

### In C, one function per type; in Java, one name

<div class="columns code-diff stage-top">
<div class="column code-build">

#### C

<!-- Written line by line (the lines after the current one stay hidden), so the class can be asked for the next one;
     then Java whole, and both marked at once with the note. The empty spans hold indices 0 and 1, which only
     the C steps use: reveal renumbers indices without gaps before the code steps exist -->
<span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="1"></span>
<pre><code class="c" data-trim data-line-numbers="1|2|3||1-3" data-fragment-index="0">
int a = abs(-3);
long b = labs(-3L);
double c = fabs(-2.5);
</code></pre>

</div>
<div class="column fragment" data-fragment-index="2">

#### Java

<pre><code class="java" data-trim data-line-numbers="|1-3" data-fragment-index="3">
int a = Math.abs(-3);
long b = Math.abs(-3L);
double c = Math.abs(-2.5);
</code></pre>

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
<strong>Overloading</strong>: several methods with the <strong>same name</strong> and different parameters. Java picks one from the <strong>arguments</strong>.
</blockquote>

</div>

Note:
The absolute value in C, written line by line. On entry, an int: abs. Ask them how they would do it for a long; step 1, labs, another function. Ask again for a double; step 2, fabs, a third one. In C two functions cannot share a name, so each type needs its own. Step 3, Java: one name, Math.abs, for the three types; there are really three methods called abs inside Math, one with an int parameter, one with a long and one with a double. Step 4, both marked side by side, and the name for it, overloading: Java looks at the arguments of each call, their number and their types, and runs the version that matches.

--

<div class="stage">

## You have used it already

### `println` is overloaded too

<div class="runs stage-body">
<div class="rule">
<p><strong>A <code>String</code></strong>The version that takes text.</p>
</div>
<div>

```java
System.out.println("Hello");
```

</div>
<div>

```console
Hello
```

</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>An <code>int</code></strong>Another method with the same name, for integers.</p>
</div>
<div class="fragment" data-fragment-index="0">

```java
System.out.println(42);
```

</div>
<div class="fragment" data-fragment-index="0">

```console
42
```

</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>A <code>double</code></strong>And another one, for decimals.</p>
</div>
<div class="fragment" data-fragment-index="1">

```java
System.out.println(3.5);
```

</div>
<div class="fragment" data-fragment-index="1">

```console
3.5
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Each call runs the version whose <strong>parameters match</strong> its arguments.
</blockquote>

</div>

Note:
Something they have done since the first program without knowing it. println is not one method but several, all named println, one per type of argument: String, int, double, boolean, char and more. On entry, the String version; then the int one; then the double one. They never chose: Java did, from the type of what they passed. Last, the takeaway.

---

<div class="stage">

## Two ways to open an account

### With a first deposit, or empty

<div class="phones stage-body">
<div class="phone">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>LG</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Lucía Gómez Ruiz</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 2468 1357 0864</p>
<p class="phone-balance"><span class="phone-mark fragment custom" data-fragment-index="1">€200.00</span></p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
<div class="phone fragment" data-fragment-index="0">
<div class="phone-screen">
<div class="phone-status"><span>9:41</span><span class="phone-island"></span><span class="phone-icons"><svg viewBox="0 0 18 12" aria-hidden="true"><path d="M0 8.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H0zM4.8 6a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1v6H4.8zm4.8-2.5a1 1 0 0 1 1-1h1.5a1 1 0 0 1 1 1V12H9.6zm4.8-2.5a1 1 0 0 1 1-1H17a1 1 0 0 1 1 1v11h-3.6z"/></svg><svg viewBox="0 0 16 12" aria-hidden="true"><path d="M8 12 5.6 9.4a3.4 3.4 0 0 1 4.8 0zM3.4 7.1a6.5 6.5 0 0 1 9.2 0l-1.5 1.6a4.3 4.3 0 0 0-6.2 0zM1.1 4.8a9.8 9.8 0 0 1 13.8 0l-1.5 1.6a7.6 7.6 0 0 0-10.8 0z"/></svg><svg viewBox="0 0 26 12" aria-hidden="true"><path fill-rule="evenodd" d="M3 0h17a3 3 0 0 1 3 3v6a3 3 0 0 1-3 3H3a3 3 0 0 1-3-3V3a3 3 0 0 1 3-3zm0 1.2A1.8 1.8 0 0 0 1.2 3v6A1.8 1.8 0 0 0 3 10.8h17A1.8 1.8 0 0 0 21.8 9V3A1.8 1.8 0 0 0 20 1.2zM3.5 2.5h13v7h-13zM24 4a2 2 0 0 1 0 4z"/></svg></span></div>
<div class="phone-header">
<span class="phone-avatar"><span>PS</span></span>
</div>
<div>
<p class="phone-greeting">Hello,</p>
<p class="phone-name">Pedro Sanz Gil</p>
</div>
<div class="phone-account">
<p class="phone-label">Current account</p>
<p class="phone-iban">ES00 4321 0001 9753 8642 1100</p>
<p class="phone-balance"><span class="phone-mark fragment custom" data-fragment-index="1">€0.00</span></p>
<p class="phone-label">Available balance</p>
</div>
<div class="phone-actions">
<span class="phone-button phone-button-main"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6z"/></svg>Deposit</span>
<span class="phone-button"><svg viewBox="0 0 24 24" aria-hidden="true"><path d="M5 11h14v2H5z"/></svg>Withdraw</span>
</div>
<span class="phone-home"></span>
</div>
</div>
</div>

</div>

Note:
Two new customers. On entry, Lucía opens her account with a first deposit of 200 euros. Step 1, Pedro opens his empty. Step 2, the difference, marked: one starts with the amount of the first deposit, the other at 0. Ask them: with the constructor we have, the one that asks for the balance, how would we create Pedro's account? Passing 0.00 works, but every empty account would have to say it. It would be clearer to have two ways of creating an account: that is, two constructors, below.

--

<div class="stage">

## Overloaded constructors

### One per way of creating the object

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="6|12|13|6,12" data-fragment-index="0">
class Account {
    String holder;
    String iban;
    double balance;

    Account(String holder, String iban, double balance) {
        this.holder = holder;
        this.iban = iban;
        this.balance = balance;
    }

    Account(String holder, String iban) {
        this(holder, iban, 0.00);
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>All the parameters</strong>One constructor takes a value for every attribute.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Only some of them</strong>Another, with the same name, takes fewer and gives the rest a default value.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>this(...)</code></strong>Calls the other constructor of the class, so the assignments are written once.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
A class can have <strong>several constructors</strong>, as long as their <strong>parameters differ</strong>.
</blockquote>

</div>

Note:
The two ways of opening an account, in code. On entry, the constructor they know, with a value for every attribute: Lucía's account, new Account("Lucía Gómez Ruiz", "ES00 ...", 200.00). Step 1, a second one: same name, as every constructor, but only holder and IBAN; new picks it when it gets two arguments, as for Pedro's account. Step 2, its body: this(...) with parentheses calls the other constructor of the same class with the balance at 0.00, so the three assignments are written once; it has to be the first line of the constructor. It is another use of this: before, this.holder was an attribute of the object; now this(...) is a constructor of the class. Last, the takeaway.

---

<div class="stage">

## Overloaded methods

### A deposit, with or without a concept

<div class="columns code-compact stage-top">
<div class="column">

<!-- Code and cards step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="4-6|8-11|9|4,8" data-fragment-index="0">
class Account {
    // attributes and constructors, as before

    void deposit(double amount) {
        this.balance += amount;
    }

    void deposit(double amount, String concept) {
        this.deposit(amount);
        System.out.printf("%s: +%.2f\n", concept, amount);
    }
}
</code></pre>

</div>
<div class="column rules">
<div class="rule">
<p><strong>The basic version</strong>Does the operation with the least it needs.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>A fuller version</strong>Same name, with more parameters for more details.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>One calls the other</strong>The fuller version reuses the basic one, so the shared work is written once.</p>
</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Methods are overloaded the same way: <strong>same name</strong>, <strong>different parameters</strong>.
</blockquote>

</div>

Note:
The same with a method. On entry, deposit as they know it, ana.deposit(100.00). Step 1, a second deposit with a concept, ana.deposit(350.00, "Rent"), like the message of a transfer that the banking app shows next to the amount; here it is just printed. Step 2, its first line calls the other deposit, so the rule for updating the balance is written once; if it ever changes, it changes in one place. Last, the takeaway.

---

<div class="stage">

## What tells them apart

### The signature: the name and the parameters

<div class="rules stage-top">
<div class="rule">
<p><strong>The signature</strong>The name of a method and the <strong>number</strong>, <strong>type</strong> and <strong>order</strong> of its parameters. Each version needs its own: <code>deposit(double)</code> and <code>deposit(double, String)</code>.</p>
</div>
<div class="rule rule-error fragment">
<p><strong>Not the return type</strong>It is not part of the signature: two methods that differ only in what they return do not compile, such as <code>void deposit(double amount)</code> and <code>boolean deposit(double amount)</code>.</p>
</div>
<div class="rule rule-error fragment">
<p><strong>Not the parameter names</strong>They are not part of it either: renaming a parameter gives the same method, defined twice, such as <code>deposit(double amount)</code> and <code>deposit(double money)</code>.</p>
</div>
</div>

<blockquote class="stage-note fragment">
Overloaded methods share the <strong>name</strong> and differ in their <strong>signature</strong>: Java picks one from the <strong>call</strong>.
</blockquote>

</div>

Note:
The rule behind it, and its name. On entry, the signature: what identifies a method in a class, its name plus how many parameters, of which types, in which order; deposit(double) and deposit(double, String) have the same name but different signatures. Constructors have one too, with the class name. Step 1, in red, what is not part of it: void deposit(double) and boolean deposit(double) do not compile together, method deposit(double) is already defined; looking at ana.deposit(100.00), nobody could tell which one is meant. Step 2, the same with names: deposit(double amount) and deposit(double money) have the same signature. Last, the takeaway: Java only sees the call, and a call only shows the name and the arguments.

--

<div class="stage">

## Which one runs?

### With both constructors and both deposits

<div class="columns code-compact code-dense stage-top">
<div class="column">

<pre><code class="java" data-trim data-line-numbers>
class Account {
    String holder;
    String iban;
    double balance;

    Account(String holder, String iban, double balance) {
        this.holder = holder;
        this.iban = iban;
        this.balance = balance;
    }

    Account(String holder, String iban) {
        this(holder, iban, 0.00);
    }

    void deposit(double amount) {
        this.balance += amount;
    }

    void deposit(double amount, String concept) {
        this.deposit(amount);
        System.out.printf("%s: +%.2f\n", concept, amount);
    }
}
</code></pre>

</div>
<div class="column code-run">

<!-- Each call is marked first, then its row is filled in: every step is repeated, so the row comes with the copy.
     The empty spans in the table's first header hold the indices only the code uses: reveal renumbers indices without gaps before the code steps exist -->
<pre><code class="java" data-trim data-line-numbers="|3-4|3-4|5|5|6|6|7|7|8|8|9|9" data-fragment-index="0">
class Main {
    public static void main(String[] args) {
        Account ana = new Account("Ana García López",
                "ES00 4321 0001 1234 5678 9012");
        Account luis = new Account();
        ana.deposit(350.00, "Rent");
        ana.deposit(100);
        ana.deposit("Rent");
        ana.deposit("Rent", 350.00);
    }
}
</code></pre>

<table class="data-table verdict-table">
<thead>
<tr><th>Line<span class="fragment" data-fragment-index="0"></span><span class="fragment" data-fragment-index="2"></span><span class="fragment" data-fragment-index="4"></span><span class="fragment" data-fragment-index="6"></span><span class="fragment" data-fragment-index="8"></span><span class="fragment" data-fragment-index="10"></span></th><th class="mark-head">Compiles?</th><th>Runs</th></tr>
</thead>
<tbody>
<tr><th scope="row">3–4</th><td class="mark mark-ok fragment custom" data-fragment-index="1">✓</td><td class="fragment custom" data-fragment-index="1">Two-parameter constructor</td></tr>
<tr><th scope="row">5</th><td class="mark mark-error fragment custom" data-fragment-index="3">✗</td><td class="fragment custom" data-fragment-index="3">No constructor without parameters</td></tr>
<tr><th scope="row">6</th><td class="mark mark-ok fragment custom" data-fragment-index="5">✓</td><td class="fragment custom" data-fragment-index="5">Deposit with a concept</td></tr>
<tr><th scope="row">7</th><td class="mark mark-ok fragment custom" data-fragment-index="7">✓</td><td class="fragment custom" data-fragment-index="7">Basic deposit: the <code>int</code> widens</td></tr>
<tr><th scope="row">8</th><td class="mark mark-error fragment custom" data-fragment-index="9">✗</td><td class="fragment custom" data-fragment-index="9">No deposit takes a <code>String</code> alone</td></tr>
<tr><th scope="row">9</th><td class="mark mark-error fragment custom" data-fragment-index="11">✗</td><td class="fragment custom" data-fragment-index="11">The order of the parameters counts</td></tr>
</tbody>
</table>

</div>
</div>

</div>

Note:
The whole class Account on the left, and on the right a main that calls it in every way, now in a class of its own, Main.java, in the same folder as Account.java; like Account, it has no public in front, which only matters once there are packages (Hello.java had it in unit 1); three of its lines do not compile on purpose, which is what the table finds out. Each call is marked first, with its row still empty: ask the class whether it compiles and which version runs, and the next step fills in the row. Lines 3 and 4, one call split in two, two arguments, so the two-parameter constructor. Line 5, new Account() with nothing: the class has constructors, so the default one is gone, as they saw in Creating with the constructor. Line 6, a double and a String: the deposit with a concept. Line 7, deposit(100) with an int: there is no deposit(int), but an int widens to double automatically, as they saw in the first unit, so it runs deposit(double). Line 8, a String alone: no version matches. Line 9, the same two values in the wrong order do not match either: Java matches each call by the number, type and order of its arguments.

---

<div class="stage">

## Exercises

### What you can solve now

<div class="rules rules-grid rules-pair stage-top">
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Classes%2C%20objects%2C%20and%20methods/Exercise%204">Exercise 4</a></strong>Points and vectors: two classes that work together, with a constant and overloaded constructors.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Classes%2C%20objects%2C%20and%20methods/Exercise%207">Exercise 7</a></strong>Matrices: an array of arrays as an attribute, several constructors and the usual operations.</p>
</div>
</div>

</div>

Note:
The exercises that need overloaded constructors, from the section just seen, and the static final constants of the one before. They are in the JetBrains Academy course, in the Classes, objects, and methods lesson; each title opens it on GitHub.

---

<!-- .slide: class="contents" data-section="5" -->

Note:
The accounts have been full of text from the start: holders and IBANs, all of them String. In unit 1 String was just the type of args in main, and in this unit the type for text, with no more said. Now what it really is, and how it differs from text in C.

---

<div class="stage">

## Text in C and in Java

### A `char` array there, an object here

<table class="data-table compare-table stage-body">
<thead>
<tr><th>Task</th><th>C</th><th>Java</th><th>Result</th></tr>
</thead>
<tbody>
<tr><th scope="row">Declare</th><td class="fragment custom" data-fragment-index="0"><code>char name[50] = "Ana";</code></td><td class="fragment custom" data-fragment-index="1"><code>String name = "Ana";</code></td><td class="fragment custom" data-fragment-index="2"><code>"Ana"</code></td></tr>
<tr class="fragment" data-fragment-index="3"><th scope="row">Length</th><td class="fragment custom" data-fragment-index="4"><code>strlen(name)</code></td><td class="fragment custom" data-fragment-index="5"><code>name.length()</code></td><td class="fragment custom" data-fragment-index="6"><code>3</code></td></tr>
<tr class="fragment" data-fragment-index="7"><th scope="row">Join</th><td class="fragment custom" data-fragment-index="8"><code>strcat(name, " López")</code></td><td class="fragment custom" data-fragment-index="9"><code>name + " López"</code></td><td class="fragment custom" data-fragment-index="10"><code>"Ana López"</code></td></tr>
<tr class="fragment" data-fragment-index="11"><th scope="row">Change it</th><td class="fragment custom" data-fragment-index="12"><code>strcpy(name, "Luis")</code></td><td class="fragment custom" data-fragment-index="13"><code>name = "Luis"</code></td><td class="fragment custom" data-fragment-index="14"><code>"Luis"</code></td></tr>
<tr class="fragment" data-fragment-index="15"><th scope="row">Compare</th><td class="fragment custom" data-fragment-index="16"><code>strcmp(a, b) == 0</code></td><td class="fragment custom" data-fragment-index="17"><code>a.equals(b)</code></td><td class="fragment custom" data-fragment-index="18"><code>0</code> in C, <code>true</code> in Java, if equal</td></tr>
</tbody>
</table>

<blockquote class="stage-note fragment" data-fragment-index="19">
A <strong><code>String</code></strong> is an <strong>object</strong>: no size to choose, no <code>'\0'</code>, and its operations are <strong>methods</strong>.
</blockquote>

</div>

Note:
What they did with text in C, task by task; each row comes in four steps: the task, how they did it in C, then Java, and the result, the same in both, with name holding "Ana". Ask them for the C version before showing it. Declaring: in C a char array with a size chosen in advance and room for the final '\0'; in Java a String, with no size at all. Length: strlen, from string.h, gives 3 too: it counts the characters up to the final '\0', which it does not count, and has nothing to do with the 50 of the array, which is what sizeof(name) would give; in Java a method of the object, length(), with parentheses, unlike the length of an array. Joining: strcat changes name itself, and writes past the end if the array is too small; + leaves name as it was and builds a new text of the right size, which has to be kept: name = name + " López". Changing it: strcpy against a plain =. Comparing: the one row where the result differs: strcmp gives an int, 0 when both texts are equal, a negative or a positive number when the first goes before or after the second; equals gives a boolean, true or false. It gets its own slide soon. Last, the takeaway: String is a class, the first one they use that they did not write.

--

<div class="stage">

## A `String` is an object

### Its methods are called with the dot

<!-- The text every call below works on -->
<div class="code-over-cards code-compact stage-top">

```java
String holder = "Ana García López";
```

<div class="runs">
<div class="rule">
<p><strong>Length</strong>How many characters it has.</p>
</div>
<div>

```java
holder.length()
```

</div>
<div class="fragment" data-fragment-index="0">

```console
16
```

</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>One character</strong>The one at a position, counting from 0, as in an array.</p>
</div>
<div class="fragment" data-fragment-index="1">

```java
holder.charAt(0)
```

</div>
<div class="fragment" data-fragment-index="2">

```console
A
```

</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong>Search</strong>Whether a text is inside it, or where it starts.</p>
</div>
<div class="fragment" data-fragment-index="3">

```java
holder.contains("García")
```

</div>
<div class="fragment" data-fragment-index="4">

```console
true
```

</div>
<div class="rule fragment" data-fragment-index="5">
<p><strong>Transform</strong>A new text made from it.</p>
</div>
<div class="fragment" data-fragment-index="5">

```java
holder.toUpperCase()
```

</div>
<div class="fragment" data-fragment-index="6">

```console
ANA GARCÍA LÓPEZ
```

</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="7">
<strong><code>String</code></strong> is a class that comes with Java: its objects hold text, and its <strong>methods</strong> work on it.
</blockquote>

</div>

Note:
All on the String at the top, holder = "Ana García López", Ana's holder from the accounts; it stays on screen so every result can be checked against it. Each method comes with its call first: ask what it gives before showing it. length(): 16 characters, spaces and accented letters included. charAt(0): the first character, a char, positions from 0 as in arrays; the last one is at length() - 1, and past it the program stops with an exception, as an array index out of range. contains: true; indexOf would give where it starts, or -1 if it is not there. toUpperCase: a new text in capitals; what happens to holder itself comes in a later slide. There are many more (substring, replace, trim, split...): the Java documentation lists them all. Last, the takeaway.

---

<div class="stage">

## Two ways to create a `String`

### A literal, or `new`

<div class="memory-layout code-compact stage-top">

<!-- Code and diagram step together from 0 -->
<pre><code class="java" data-trim data-line-numbers="1|2|3|" data-fragment-index="0">
String a = "Ana";
String b = "Ana";
String c = new String("Ana");
</code></pre>

<div class="memory memory-short memory-rows-3">
<div class="memory-frame memory-stack"></div>
<div class="memory-frame memory-heap"></div>
<p class="memory-title memory-stack">Stack</p>
<p class="memory-title memory-heap">Heap</p>
<div class="memory-var memory-row-1"><span class="memory-cell">a</span><span class="memory-link"></span><span class="memory-cell memory-value">0x51c2</span></div>
<div class="memory-arrow memory-row-1"></div>
<div class="memory-text memory-rows-1-2"><span class="memory-cell">"Ana"</span></div>
<div class="memory-var memory-row-2 fragment" data-fragment-index="0"><span class="memory-cell">b</span><span class="memory-link"></span><span class="memory-cell memory-value">0x51c2</span></div>
<div class="memory-arrow memory-row-2 fragment" data-fragment-index="0"></div>
<div class="memory-var memory-row-3 fragment" data-fragment-index="1"><span class="memory-cell">c</span><span class="memory-link"></span><span class="memory-cell memory-value">0x7e09</span></div>
<div class="memory-arrow memory-row-3 fragment" data-fragment-index="1"></div>
<div class="memory-text memory-row-3 fragment" data-fragment-index="1"><span class="memory-cell">"Ana"</span></div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2">
Equal <strong>literals</strong> share one object; <strong><code>new String</code></strong> always creates <strong>a new one</strong>.
</blockquote>

</div>

Note:
Two ways of getting a String, drawn as with new. On entry, a literal, text written between quotes: Java creates the String object and a keeps its address. Step 1, b with the same literal: Java does not create a second object, it reuses the one it already has for "Ana": each different literal is kept once, in a part of the heap called the string pool, so b gets the same address, 0x51c2; it can do this safely because strings never change. Step 2, c with new String: new always builds a new object, even with the same text, at another address. In practice strings are written as literals; new String is shown here only to see the difference, which matters when strings are compared, in Comparing strings. Last, the takeaway.

--

<div class="stage">

## Changing one of them

### Assigning a variable changes where it points, not the object

<div class="memory-layout code-compact stage-top">

<!-- Code and diagram step together from 0: at step 0 the shared object gives way to the two a and b refer to.
     The empty span in the note holds index 1, which only the code uses -->
<pre><code class="java" data-trim data-line-numbers="1-2|3|4|3" data-fragment-index="0">
String a = "Ana";
String b = "Ana";
a = "Luis";
System.out.println(b);
</code></pre>

<div class="memory memory-short">
<div class="memory-frame memory-stack"></div>
<div class="memory-frame memory-heap"></div>
<p class="memory-title memory-stack">Stack</p>
<p class="memory-title memory-heap">Heap</p>
<div class="memory-var memory-row-1"><span class="memory-cell">a</span><span class="memory-link"></span><span class="memory-cell memory-value"><span class="phone-swap"><span class="fragment fade-out" data-fragment-index="0">0x51c2</span><span class="phone-changed fragment" data-fragment-index="0">0x3f80</span></span></span></div>
<div class="memory-arrow memory-row-1"></div>
<div class="memory-var memory-row-2"><span class="memory-cell">b</span><span class="memory-link"></span><span class="memory-cell memory-value">0x51c2</span></div>
<div class="memory-arrow memory-row-2"></div>
<div class="memory-text memory-rows-1-2 fragment fade-out" data-fragment-index="0"><span class="memory-cell">"Ana"</span></div>
<div class="memory-text memory-row-1 fragment" data-fragment-index="0"><span class="memory-cell">"Luis"</span></div>
<div class="memory-text memory-row-2 fragment" data-fragment-index="0"><span class="memory-cell">"Ana"</span></div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="2"><span class="fragment" data-fragment-index="1"></span>
Assigning a variable makes it refer to <strong>another object</strong>: the object stays as it was, and so do the <strong>other variables</strong>.
</blockquote>

</div>

Note:
The question the slide above leaves open: if a and b share one object, does changing a change b? It helps to know how literals work: Java keeps each different literal only once, in a part of the heap called the string pool, so every "Ana" written anywhere in the program is the same object, with one address, and every "Luis" is another one, with its own. On entry, a and b sharing the pool's "Ana". Step 1, a = "Luis": the "Ana" object is not touched; a gets the address of the "Luis" object, 0x3f80, and that is all an assignment ever does to a variable of a class type, change where it points. The drawing moves "Ana" next to b only to keep the arrows straight: it is the same object, at the same address, and b still refers to it. Step 2, printing b: ask them what it prints; Ana. Sharing is safe because a String never changes: no method can alter the pool's "Ana" behind b's back. With an Account it was different: other.deposit changed the object both variables shared, because an Account can change. Last, the takeaway, with the assignment marked, which holds for any object.

--

<div class="stage">

## Comparing strings

### `==` or `equals`?

<div class="code-over-cards code-compact stage-top">

```java
String a = "Ana";
String b = "Ana";
String c = new String("Ana");
```

<div class="runs">
<div class="rule">
<p><strong><code>==</code> with two literals</strong>The same object, so it happens to work.</p>
</div>
<div>

```java
a == b
```

</div>
<div class="fragment" data-fragment-index="0">

```console
true
```

</div>
<div class="rule rule-error fragment" data-fragment-index="1">
<p><strong><code>==</code> with <code>new</code></strong>Compares the references: two objects.</p>
</div>
<div class="fragment" data-fragment-index="1">

```java
a == c
```

</div>
<div class="fragment" data-fragment-index="2">

```console
false
```

</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong><code>equals</code></strong>Compares the text: the same characters.</p>
</div>
<div class="fragment" data-fragment-index="3">

```java
a.equals(c)
```

</div>
<div class="fragment" data-fragment-index="4">

```console
true
```

</div>
</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="5">
To compare two texts, always <strong><code>equals</code></strong>: <strong><code>==</code></strong> only says whether they are the same object.
</blockquote>

</div>

Note:
The trap, with the three strings from Two ways to create a String. Each row, the comparison first: ask what it gives before showing it. On entry, a == b; step 1, true, but only because both literals share one object: == compares references, and here they happen to be the same. Step 2, a == c; step 3, false, the same text in two different objects. Step 4, a.equals(c); step 5, true: equals compares the characters, as strcmp did in C. The first row is why the bug goes unnoticed: with literals == seems to work, and it stops working when the text comes from new, from the keyboard or from another method. Last, the takeaway.

---

<div class="stage">

## Strings never change

### Every method gives back a new one

<div class="runs stage-body">
<div class="rule">
<p><strong>Calling it is not enough</strong>The method makes a new text, and nobody keeps it.</p>
</div>
<div>

```java
String name = "ana";
name.toUpperCase();
System.out.println(name);
```

</div>
<div class="fragment" data-fragment-index="0">

```console
ana
```

</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong>Keep the result</strong>The variable now refers to the new text.</p>
</div>
<div class="fragment" data-fragment-index="1">

```java
String name = "ana";
name = name.toUpperCase();
System.out.println(name);
```

</div>
<div class="fragment" data-fragment-index="2">

```console
ANA
```

</div>
</div>

<blockquote class="stage-note fragment" data-fragment-index="3">
A <strong><code>String</code></strong> is <strong>immutable</strong>: its methods never change it, they return a <strong>new</strong> one.
</blockquote>

</div>

Note:
Another common mistake. On entry, toUpperCase called on its own: ask what it prints. Step 1, still ana: the method did not touch name, it made a new String in capitals and returned it, and nobody kept it. It compiles, which is why it goes unnoticed. Step 2, the fix: assign the result back. Step 3, ANA: name now refers to the new object, and the old one is left for the garbage collector. Last, the takeaway: no String method ever changes the String it is called on; the same goes for +, which builds a new one each time.

--

<div class="stage">

## Building text: `StringBuilder`

### One object that does change

<div class="code-run stage-top">

<!-- the last step repeats the println: the output under the code shows with it -->
```java [1|3|5|5]
StringBuilder numbers = new StringBuilder();
for (int i = 1; i <= 3; i++) {
    numbers.append(i).append(" ");
}
System.out.println(numbers.toString());
```

<div class="code-run-output">

```console
1 2 3
```

</div>
</div>

<blockquote class="stage-note code-run-output">
To build text step by step, a <strong><code>StringBuilder</code></strong>: it <strong>grows</strong> instead of being copied each time.
</blockquote>

</div>

Note:
The other side of immutability. Joining strings with + in a loop builds a new String on every pass and throws the previous one away; for a few pieces it does not matter, for thousands it does. On entry, a StringBuilder, created with new like any object: a text that can change. Step 1, append adds to the end of the same object, and returns it, so calls can be chained. Step 2, toString turns it into a String when it is finished; ask what it prints. Step 3, 1 2 3, with a space at the end.

---

<div class="stage">

## Exercises

### The rest of the unit

<div class="rules rules-grid rules-pair stage-top">
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Classes%2C%20objects%2C%20and%20methods/Exercise%202">Exercise 2</a></strong>People: an array of objects as an attribute, two constructors and some error cases.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Classes%2C%20objects%2C%20and%20methods/Exercise%203">Exercise 3</a></strong>Books: their data and their pages, deciding the parameters and the return type of each method.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Classes%2C%20objects%2C%20and%20methods/Exercise%205">Exercise 5</a></strong>Polynomials: a class built on the monomials of Exercise 1.</p>
</div>
<div class="rule">
<p><strong><a href="https://github.com/alfaro96/programming-fundamentals-ii/tree/main/code/Classes%2C%20objects%2C%20and%20methods/Exercise%206">Exercise 6</a></strong>A library: a class that holds several books, those of Exercise 3.</p>
</div>
</div>

</div>

Note:
The exercises that also work with text: comparing strings with equals, or building them with StringBuilder. They are in the JetBrains Academy course, in the Classes, objects, and methods lesson; each title opens it on GitHub.

---

<div class="stage">

## Unit summary

### What to take away from Classes, objects, and methods

<div class="rules rules-grid stage-body">
<div class="rule">
<p><strong>Classes and objects</strong>A <strong>class</strong> describes what its objects have, <strong class="text-state">attributes</strong>, and do, <strong class="text-behavior">methods</strong>; each <strong>object</strong> has its own values.</p>
</div>
<div class="rule fragment" data-fragment-index="0">
<p><strong>Creating objects</strong><strong><code>new</code></strong> builds an object, the variable keeps a <strong>reference</strong> to it, and a <strong>constructor</strong> gives it its values.</p>
</div>
<div class="rule fragment" data-fragment-index="1">
<p><strong><code>static</code> and <code>final</code></strong><strong><code>static</code></strong>: one for the class. <strong><code>final</code></strong>: set once. Both: a <strong>constant</strong>.</p>
</div>
<div class="rule fragment" data-fragment-index="2">
<p><strong>Overloading</strong>The <strong>same name</strong> with a different <strong>signature</strong>: Java picks the version from the call.</p>
</div>
<div class="rule fragment" data-fragment-index="3">
<p><strong>Strings</strong>A <strong><code>String</code></strong> is an immutable object: compare it with <strong><code>equals</code></strong>, never with <code>==</code>.</p>
</div>
</div>

</div>

Note:
Ask the class to recall an example of each section before showing the next one: the three phones and the template; new, the memory diagram, Luis's forgotten IBAN and the constructor; the interest rate and the IBAN's padlock; the two deposits; the two "Ana" and new String. The next unit builds on this one: classes that reuse other classes, inheritance.
