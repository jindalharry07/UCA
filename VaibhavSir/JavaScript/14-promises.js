//Promise
/*
promise is an object created using promise constructor function and primarily have 2 properties

1. state - pending, fulfiled, rejectet 
2. result - any value, undefined
*/

function promiseExecutor() {
  setTimeout(() => {
    console.log("Promise1 executed");
  }, 5000);
}

// simple promise
// var promise1 = new Promise(promiseExecutor);



// Executor function is called immediately when promise is created
// Executor function is called with 2 arguments -resolve and reject
// resolve and reject are functions
// Simple promise with resolve adn reject

function promiseExecutor(resolve, reject) {
  // Asynchronous operation
  setTimeout(() => {
    console.log("Promise 1 executed");

    // Either resolve or reject — you can use one of them
    // resolve("Promise 2 resolved");

    // OR you could reject it:
    reject("Promise 2 rejected");

    console.log(promise2);
  }, 2000);
}

const promise2 = new Promise(promiseExecutor); // its will be fulfilled because we have used (resolve, reject)

// Handle the result
promise2
  .then((result) => {
    console.log("Success:", result);
  })
  .catch((error) => {
    console.log("Error:", error);
  }
);

