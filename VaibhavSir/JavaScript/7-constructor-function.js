var arr = new Array(1, 2, 3, 4, 5);
// var arr = new Array(...[1, 2, 3, 4, 5]);
// ... is called the spread operator (or sometimes the rest operator, depending on context).
// It "spreads out" elements from an iterable (like an array) into individual elements.

// var arr1 = arr.map((item) => {
//   return 2 * item;
// });
var arr1 = arr.map(callbackMultiplyBy2);

function callbackMultiplyBy2 (item) {
  return item * 2;
}

// function callbackMultiplyBy2 (item) {
//   item * 2;
// }

// if we dont return any thing it will return {undefined, undefined, undefined}
// same as length of array


// console.log(arr1);


/* Make custom map same as .map function in Array */
Array.prototype.myMap = function(callback) {
  var res = [];

  for (var i = 0; i < this.length; i++) {
    if (this.hasOwnProperty(i)) {
      // var ele = callback(this[i], i, this);
      var ele = callback(this[i]);
      res.push(ele);
    }
  }

  return res;
}

var arr2 = arr.myMap(callbackMultiplyBy2);
console.log(arr2);