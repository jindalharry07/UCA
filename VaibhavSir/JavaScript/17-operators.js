/*
1. Arithmetic Operators

Used to perform mathematical operations:
+   // Addition
-   // Subtraction
*   // Multiplication
/   // Division
%   // Modulus (Remainder)
**  // Exponentiation (ES6)
++  // Increment
--  // Decrement

2. Logical Operators

Used with boolean values (true/false):
&&  // AND
||  // OR
!   // NOT

3. Comparison Operators

Used to compare values:
==    // Equal to (type conversion)
===   // Strict equal to (no type conversion)
!=    // Not equal to
!==   // Strict not equal to
>     // Greater than
<     // Less than
>=    // Greater than or equal to
<=    // Less than or equal to

4. Assignment Operators

Used to assign values to variables:
=     // Assign
+=    // Add and assign
-=    // Subtract and assign
*=    // Multiply and assign
/=    // Divide and assign
%=    // Modulus and assign
**=   // Exponent and assign

5. Typeof Operator

Returns the type of a variable:
typeof "hello"  // "string"

6. Delete Operator

Deletes a property from an object:
delete obj.property

7. in Operator

Checks if a property exists in an object:
'key' in obj

8. instanceof Operator

Checks if an object is an instance of a specific constructor:
obj instanceof Constructor

9. Bitwise Operators

Operate on binary representations:
&   // AND
|   // OR
^   // XOR
~   // NOT
<<  // Left shift
>>  // Right shift
>>> // Unsigned right shift

10. Spread Operator

Expands an iterable into individual elements:
const arr = [1, 2];
const newArr = [...arr, 3, 4];  // [1, 2, 3, 4]

11. Rest Operator

Collects remaining elements into an array:
function sum(...numbers) {
  return numbers.reduce((a, b) => a + b, 0);
}


 comma, binary


12. Conditional (Ternary) Operator

Shorthand for if...else:
condition ? valueIfTrue : valueIfFalse

13. Nullish Coalescing Operator (??)

Returns the right-hand operand if the left is null or undefined:

null ?? "default"  // "default"
0 ?? "default"     // 0

14. Optional Chaining Operator (?.)

Safely access nested object properties:
user?.address?.city

15. Unary Operators

Operate on a single operand:
+value   // Unary plus
-value   // Unary negation
!value   // Logical NOT
typeof   // Type check
delete   // Delete property
void     // Evaluate and return undefined

*/


// conditional (ternary) operator ? :  shorthand of if else
const isLoggedIn = false
const userName = isLoggedIn ? "Jhon Doe" : "Guest";
console.log(userName);


// optional chaining operator ?. to access nested object properties safely
var user1 = {
  "academics" : {
    "college": "collegeName",
    "profile": {
      "name": "username1"
    }
  }
}
console.log(user1.academics.profile.name);

var user2 = {
  "academics" : {
    "college": "collegeName",
  }
}
// console.log(user2.academics.profile.name); //Cannot read properties of undefined (reading 'name')
console.log(user2.academics.profile?.name); // output will be undefined because of optional chaining


// nullish coalescing operator ?? to provide default vaule for null or undefined

var firstName = null
var lastName
var userNamec
var displayName = firstName ?? lastName ?? userNamec ?? "Guest User"; // works same as optional chaining
// basically provides default values

console.log(displayName); // Guest User
