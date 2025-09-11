// Spread Operator: The spread operator is a syntax in JavaScript represented by three dots (...) that allows an iterable (like an array or string) or an object to be expanded (spread) into individual elements or key-value pairs.
var num1 = [1, 2, 3, 4];
var num2 = [5, 6, 7, 8];

var combinedArray = [...num1, ...num2];
// console.log(combinedArray);


var student1 = {
  name: "name1",
  age: 20
};
var student2 = {
  rollNo: 123,
  marks: 900
};

var studentDetail = {...student1, ...student2};
// console.log(studentDetail);


//Rest Operator: collects all arguments into an array called 'item'
function sumAll(...item) {
  var length = item.length;
  let sum = 0;
  for (let i = 0; i < length; i++) {
    sum += item[i];
  }
  return sum;
}
sumAll(1, 2);
// console.log(sumAll(1, 2, 3, 4, 5, 6));


const student = {
  name: "Alice",
  age: 22,
  rollNo: 101,
  marks: 95
};

const { name, ...details } = student;
console.log("Name:", name);

for (let key in student) {
  console.log(key + ": " + student[key]);
  // const { ele, ...details } = student;
  // console.log(key + ":"+  ele);
}

