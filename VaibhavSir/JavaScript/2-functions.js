// Multiple ways to disclare functions
var name = "some name";

// Function declaration - traditional apporach 1
function foo1(param1) {
  console.log("Inside foo1 functionwith params as: ", param1);
}

// Function declaration - traditional apporach 2
var foo2 = function (param1) {
  console.log("Inside foo3 functionwith params as: ", param1);
};

// Function declaration - traditional apporach 3
var foo3 = (param1) => {
  console.log("Inside foo3 functionwith params as: ", param1);
};

// Function declaration - traditional apporach 4
(param1) => {
  console.log("Inside foo4 functionwith params as: ", param1);
};

// Self invoking functions
((param1) => {
  console.log("Inside foo4 functionwith params as: ", param1);
})();


// Generator function
function* scoreGenerator(initialScore = 0) {
  let currentScore = initialScore;
  while (currentScore < 2) {
    // Yielding the current score and incrementing it
    yield currentScore;
    currentScore++;
  }
  // return currentScore; // This will be returned when the generator is done
}
// Using the generator function
const scoreGen = scoreGenerator();
// Getting the first score
console.log(scoreGen.next()); // {value: 0, done: false}
console.log(scoreGen.next()); // {value: 1, done: false}
console.log(scoreGen.next()); // {value: 2, done: true}
console.log(scoreGen.next()); // {value: undefined, done: true}

// Function declaration - When declaring - define function paramters (not arguments)
function foo1(param1) {
  console.log("Inside foo1 functionwith params as: ", param1);
}
// When calling any function - Arguments are passed (and not parameters)
foo1("Hello World!");