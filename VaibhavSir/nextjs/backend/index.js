// var http = require("http"); // <-- typo fixed: 'hhtp' → 'http'
var port = 5000;

// var server = http.createServer(function (req, res) {
//   const reqpath = req ? req.url : null;

//   if (req && reqpath == "/") {
//     // always set headers before writing
//     res.writeHead(200, { "Content-Type": "text/plain" });

//     res.write("Server is working!");
//     res.end(); // <-- finish the response
//   }
// });

// server.listen(port, () => {
//   console.log(`Server running at http://localhost:${port}/`);
// });


const express = require("express");
const app = express();

app.get("/", (req, res) => {
  res.send("Server is working!");
});

app.listen(port, () => {
  console.log("Server running at http://localhost:5000/");
});
