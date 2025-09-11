// class Student {
//   maxMarks = 500 // instance property
//   #fname // define private variable   // Private - '#' -> private access, use '#' everywhere inside the class to refer to these variables

//   constructor (fname, obtainedMarks) {
//     // this.fname = fname; // Public - by default the fields are public, can be accessed outside the class 
//     this.#fname = fname; // private property
//     this.obtainedMarks = obtainedMarks;
//   }

//   checResult() {
//     let percentage = (this.obtainedMarks / this.maxMarks) * 100;
//     if(percentage>40) {
//       console.log("pass")
//       return percentage;
//     } else {
//       console.log("fail")
//       return percentage;
//     }
//   }

//   getName() {
//     return this.#fname;
//   }
// }


// Static Variable
class Student {
  // static maxMarks = 500 // public static instance property
  static #maxMarks = 500 // private static instance property
  #fname // define private variable

  constructor (fname, obtainedMarks) {
    // this.fname = fname;
    this.#fname = fname; // private property
    this.obtainedMarks = obtainedMarks;
  }

  checResult() {
    let percentage = (this.obtainedMarks / Student.#maxMarks) * 100;
    if(percentage>40) {
      console.log("pass")
      return percentage;
    } else {
      console.log("fail")
      return percentage;
    }
  }

  getName() {
    return this.#fname;
  }

  static getPrivateStatic() {
    return Student.#maxMarks;
  }
}

var Student1 = new Student ("name1", 450)

console.log(Student1)
console.log(Student1.checResult()) 
console.log(Student.getPrivateStatic()) 