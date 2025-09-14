// fetch is mordern way to use ajax but uses promises
fetch("www.google.com"); // it return promise
// It is Browser api
var x = fetch("https://localhost:8080/data");
x.then((response) => {
  // if(response.ok){

  // }
  console.log("Response is : ", response);
});

// xmlHTTPRequest -> used as ajax // dont return any promise
// xmlHTTPRequest is function
/*
- we want to see promise use case 
- the primary use case of promise is to handle asynchronous operations
- one of the most common async operation in web development is making network requests
- the network request are basically made to fetch some data from Servers 
- we will see how XMLHttpRequest constructor function is used to make network requests and "fetch" custom implementation using XMLHttpRequest
*/

var xmlHTTPRequest = new XMLHttpRequest();
xmlHTTPRequest.open("GET", "https://localhost:8080/data");
xmlHTTPRequest.send();

xmlHTTPRequest.onreadystatechange = function () {
  if (xmlHTTPRequest.readyState === 4) {
    console.log("Response received from server is : ", xmlHTTPRequest.response); // will print what is on localhost address
  }
};


// Implementing custom fetch using XMLHttpRequest

// Accpts a url
// return apromise
// the promise is resolved when the data is fetched from server

function customFetch(url) {
  // return Promise;

  return new Promise(excutorFunc);
  function excutorFunc(resolve, reject) {
    var xmlHTTPRequest = new XMLHttpRequest();
    xmlHTTPRequest.open("GET", url);
    xmlHTTPRequest.send();

    xmlHTTPRequest.onreadystatechange = function () {
      if (xmlHTTPRequest.readyState === 4) { //response has been fully received 
        // Value	  State	    Meaning
        // 0	      UNSENT	  Request not initialized
        // 1	      OPENED	  open() has been called
        // 2	      HEADERS_  RECEIVED	send() has been called, and headers have been received
        // 3	      LOADING	  Response is being received (data is being downloaded)
        // 4	      DONE	    Entire operation is complete
        console.log(
          "Response received from server is : ",
          xmlHTTPRequest.response
        ); // will print what is on localhost address

        resolve(xmlHTTPRequest.response); // called resolve
      }
    };
  }
}

var data = customFetch("https://localhost:8080/data");
data.then((response) => {
  console.log("Response from custom fetch is : ", response);
})


// Implement sleep method in promise
