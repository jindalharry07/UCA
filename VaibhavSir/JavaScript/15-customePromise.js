function customePromiseExecuter(resolve, reject) {
  // Asynchronous Operation
  setTimeout(() => {
    console.log("Promise 1 executed");
  }, 2000);
}

function PromiseCustom(executerFunArg) {
  this.state = "pending";
  executerFunArg();
}
var promise1 = new PromiseCustom(customePromiseExecuter);

// simple promise with resolve and reject
function customePromiseExecuter(resolve, reject) {
  // Asynchronous Operation
  setTimeout(() => {
    console.log("Promise 1 executed");
    resolve("Promise1 executed");
    // reject("Promise1 rejected");
  }, 2000);
}

function PromiseCustom(executorFuncArg) {
  this.state = "pending";
  let successCallBack;
  let failureCallBack;

  this.then = function (callBack) {
    successCallBack = callBack;
  };

  this.catch = function () {
    failureCallBack = callBack;
  };


  executorFuncArg(
    (responseArg) => { // resolve function
      this.state = "fulfilled";
      successCallBack(responseArg); //.then method
    },
    (errorArg) => {
      this.state = "rejected";
      failureCallBack(errorArg);
    }
  );
}

var customePromise1 = new PromiseCustom(customePromiseExecuter);
customePromise1.then((removeEventListener)=>{
  console.log("Fulfiled");
})

customePromise1.catch(())