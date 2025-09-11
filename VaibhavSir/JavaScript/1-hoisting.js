// Functional vs Block

// Functional scope is created by a function
// Block scope by curly braces {}

// Hoisting - Move the declaration to the top of the scope, scope can be functional or block
// Hoisting applies to var, let, function, const, class, import

// Hoisting var and let
// var hoisting - moving the var declaration to the top of the innermost function scope
// let hoisting - moving the let declaration to the top of the innermost block scope

// --------------- Example 1 ---------------
// function f() {
//   console.log('var x before is: ', x);
//   console.log('let y before is: ', y);
  
//   if (true) {
//     var x = 2; // declaration moved to the top of this function
//     let y = 3; // let is not accessible outside this if block scope -> ReferenceError
//   } 

// }

// --------------- Example 2 ---------------
// function f() {
//   if (true) {
//     var x = 2;
//     let y = 3;
//   }
//   console.log('var x after is: ', x); // gives output 2
//   console.log('let y after is: ', y); // gives ReferenceError again
// }

// --------------- Example 3 ---------------
function f() {
  if (true) {
    console.log('var x before is: ', x); // x is accessible but undefined
    console.log('let y before is: ', y); // 'y' is visible here but cannot use 'y' before initialization
    var x = 2;
    let y = 1;
  }
  console.log('var x after is: ', x); // 
  console.log('let y after is: ', y); // still a reference error
}

// --------------- Example 4 ---------------
// function f() {
//   if (true) {
//     var x = 2;
//     let y = 1;
//     console.log('var x after in is: ', x); // x is accessible but undefined
//     console.log('let y after in is: ', y); // 'y' is visible here but cannot use 'y' before initialization
//   }
//   console.log('var x after out is: ', x); // x is accessible, gives '2'
//   console.log('let y after out is: ', y); // still a reference error
// }

f();