var studentObject1 = {
  firstName: "fName",
  lastName: "lName",
  rollno: 1236544,
  collegeName: "Chitkara University"
};

// Convert JavaScript object to JSON string
var studentObject1String = JSON.stringify(studentObject1);
console.log(studentObject1String);
// Output: '{"firstName":"fName","lastName":"lName","rollno":1236544,"collegeName":"Chitkara University"}'

// Convert JSON string back to JavaScript object
var parsedStudentObject = JSON.parse(studentObject1String);
console.log(parsedStudentObject);
// Output: {firstName: "fName", lastName: "lName", rollno: 1236544, collegeName: "Chitkara University"}
