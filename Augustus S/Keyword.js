<script>

// Input

var input = "This is a test sentence bitch please ! Give me my money back or I         will kill you , d.,,sa.dkkasjd bn   jkdghuyhsdgf   ";

// Remove articles

input = input.replace(/a/g, "");
input = input.replace(/an/g, "");
input = input.replace(/the/g, "");

console.log(input);

// Remove punctuation

var punctuation = "[\.,-\/#!$%\^&\*;:{}=\-_`~()]\'\"<>@+?|";
input = input.replace(/punctuation/g, "");

// Remove useless spaces

input = input.replace(/\s+/g,' ').trim();

// Split the sentence

var output = input.split(" ");

// Output

console.log(output);

</script>