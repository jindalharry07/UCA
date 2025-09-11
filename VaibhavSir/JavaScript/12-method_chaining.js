// Method Chaining : WHen a function return the object the return object has atleast one property that is function, allowing you to call another function on that Object.

let s= "Hello";
// console.log(s.concat(" World").concat(" World").concat(" World"));
// console.log(s);


//Implementation
function customString(initialValue) {
  this.value = initialValue,
  this.infinteConcat = function(StringToConcat) {
    return{
      value : this.value + StringToConcat,
      infinteConcat : this.infinteConcat,
    };
  }
}

// function customString(initialValue) { // return the object
//   this.value = initialValue,
//   this.infinteConcat = function(StringToConcat) {
//     let newValue = this.value + " " + StringToConcat
//     return new customString(newValue)
//   }
// }

// function customString(initialValue) { // changes the original string
//   return {
//     value : initialValue,
//     infinteConcat : function(StringToConcat) {
//       this.value = this.value + StringToConcat;
//       return customString(this.value); 
//     },
//   };
// }

var y =new customString("hello");
console.log(y.infinteConcat(" world").infinteConcat(" king"));
console.log(y.value);