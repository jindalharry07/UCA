
/*
// Sets are basically arrays or collections of unique elements
var set1 = new Set([1, 2, 3]);
console.log(set1);
// Set(3) { 1, 2, 3 }
// output dont in [] because new keyword creates object
set1 = new Set([1, 4, 3, 1, 1, 2]);
console.log(set1);

console.log(set1.entries());// key: value
set1.add(10);
console.log(set1.delete(2));
console.log(set1.has(2));


console.log(set1.has(16)); //false


// set1.forEach((value) => {
//   console.log(value);
// })


set1.forEach(console.log);

// set1.forEach((value, key, set) => {
//   console.log(value, key, set);
// })

for(let value of set1){
  console.log(value);
}



for(let value of [...set1]){ // spreed operator
  console.log(value);
}

console.log(set1.values());
console.log(set1.keys()); // same as key




console.log(set1);
set1.clear();// clears the set


*/


// Map is collections of key value pair
var map1 = new Map([
  ["fname", "name1"]
])
// console.log(map1); // Map(1) { 'fname' => 'name1' }

// keys can be of any datatyoe - But in Object keys are of String

map1 = new Map([
  ["fname", "name1"],
  [{"lname": "lname"}, "actualname"]
])
console.log(map1);//Map(2) { 'fname' => 'name1', { lname: 'lname' } => 'actualname' }

// map1 = new Map([
//   [null, null]
// ])
// console.log(map1.entries());
// console.log(map1.keys());
// console.log(map1.values());

map1.forEach((value, key, map)=> {
  console.log(key, value, map);
})