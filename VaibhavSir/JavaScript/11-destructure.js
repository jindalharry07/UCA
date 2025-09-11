// =======================
// Array Structuring & Destructuring
// =======================

// Creating an array with different types of elements
var arr = [1, "Amit", "Garg", { name: "name1", age: 20 }];

// Destructuring array values into separate variables based on index
var [count, fName, LName, details] = arr;

console.log(count);   // Output: 1         => from arr[0]
console.log(fName);   // Output: "Amit"    => from arr[1]
console.log(LName);   // Output: "Garg"    => from arr[2]
console.log(details); // Output: { name: "name1", age: 20 } => from arr[3]

// ❌ REDECLARATION WARNING:
// Below line redeclares 'count' using var again, which is already declared above.
// This is allowed with `var` (but not good practice). Avoid doing this.
count = arr[1];
console.log(count);   // Output: "Amit"    => Overwrites previous value of `count` (was 1)


// =======================
// Object Destructuring
// =======================

let obj1 = {
  firstName: "Harry",
  LastName: "Jindal",
  details: { age: 22, address: "Delhi" }
};

// Destructuring object properties into variables.
// The keys in the object must match the variable names exactly (case-sensitive).
let { LastName, firstName } = obj1;

console.log(LastName);   // Output: "Jindal"
console.log(firstName);  // Output: "Harry"
