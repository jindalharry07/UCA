function studentArrowFunction(fName, obtainedMarks) {
  console.log(this);
  this.fNameObj = fName;
  this.obtMarksObject = obtainedMarks;

  // Normal Function // it have its this from where its called
  // this.checkResultsNormal = function () {
  //   console.log(this);
  //   function innerFunction() {
  //     console.log("\n \n Normal Inner function: ", this);
  //   }
  //   innerFunction();
  // };

  // Arrow Function // inherites every this from its parent
  // this.checkResultsArrow = () => {
  //   console.log(this);
  //   innerFunction = () => {
  //     console.log("\n \n Arrow Inner function: ", this);
  //   };
  //   innerFunction();
  // };
  this.checkResultsArrow = function (){
    console.log(this);
    innerFunction = () => {
      console.log("\n \n Arrow Inner function: ", this);
    };
    innerFunction();
  };
}
var student = new studentArrowFunction("fname5", 100);
// student.checkResultsNormal() // Window {window: Window, self: Window, document: document, name: '', location: Location, …}

student.checkResultsArrow() // studentArrowFunction {fNameObj: 'fname5', obtMarksObject: 100, checkResultsNormal: ƒ, checkResultsArrow: ƒ}