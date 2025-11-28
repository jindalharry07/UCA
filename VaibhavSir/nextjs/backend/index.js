// var http = require("http");
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

// const express = require("express");
import express from "express";
// const fileSystem = require("fs");
import bodyParser from "body-parser";
import fileSystem from "fs";

// import the routes
import productRoutes from "./routes/products.js"

const app = express();
app.use(bodyParser.json());

app.use("/*splat", function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  // res.header("Access-Control-Allow-Origin", "http://localhost:3000");
  res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
  res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
  next(); // ye next route ko continue karne ke liye important hai
});

// // Helper function to read db.json
// // const productsData = require("./db.json");
// function getProducts() {
//   const data = fileSystem.readFileSync("./db.json", "utf-8", (error, data) => {
//     console.log("Data from DataBase: ", data);
//   }); // read file
//   const jsonData = JSON.parse(data);
//   return jsonData.products;
// }

// Home route
app.get("/", (req, res) => {
  res.send("Server is working!");
});

// Products page

// app.get("/products", (req, res) => {
//   fileSystem.readFile("./db.json", "utf8", (error, data) => {
//     if (error) {
//       console.error("Error reading database file: ", error);
//       res.status(500).json({ message: "Internal server error" });
//     }
//     // console.log("Data from database: ", data);
//     const currentDBData = JSON.parse(data);
//     // console.log("Formatted data from database: ", currentDBData);
//     const productsDataFromDB = currentDBData.products;

//     console.log("Products data from database: ", productsDataFromDB);
//     res.json(productsDataFromDB);
//   });
// });

app.use("/products", productRoutes);

// app.post("/products", (req, res) => {
//   const newProduct = req.body;
//   if (!newProduct || !newProduct.id) {
//     return res.status(400).json({ error: "Product data or id is missing" });
//   }

//   fileSystem.readFile("./db.json", "utf8", (error, data) => {
//     if (error) {
//       return res.status(500).json({ message: "Internal server error" });
//     }

//     try {
//       const currentDBData = JSON.parse(data);
//       const productsDataFromDB = currentDBData.products;

//       // Find product by id (ensure types match)
//       const index = productsDataFromDB.findIndex(
//         (p) => p.id.toString() === newProduct.id.toString()
//       );

//       if (index !== -1) {
//         // Update existing product
//         productsDataFromDB[index] = {
//           ...productsDataFromDB[index],
//           ...newProduct,
//         };
//       } else {
//         // Add new product
//         productsDataFromDB.push(newProduct);
//       }

//       currentDBData.products = productsDataFromDB;

//       fileSystem.writeFile(
//         "./db.json",
//         JSON.stringify(currentDBData, null, 2),
//         (err) => {
//           if (err) {
//             return res.status(500).json({ message: "Failed to save product" });
//           }
//           res.status(201).json({
//             message:
//               index !== -1
//                 ? "Product updated successfully"
//                 : "Product added successfully",
//             products: productsDataFromDB,
//           });
//         }
//       );
//     } catch (parseErr) {
//       res.status(500).json({ message: "Invalid database format" });
//     }
//   });
// });

// // Write put and delete
// function readDB() {
//   const data = fileSystem.readFileSync("./db.json", "utf-8");
//   return JSON.parse(data);
// }

// function writeDB(data, res, successMessage) {
//   fileSystem.writeFile("./db.json", JSON.stringify(data, null, 2), (err) => {
//     if (err) {
//       return res.status(500).json({ message: "Failed to write database" });
//     }
//     res.json({ message: successMessage, products: data.products });
//   });
// }


// app.put("/products/:id", (res, req) => {
//   const id = req.params.id;
//   const updatedData = res.body;

//   try {
//     const db = readDB();
//     const products = db.products;

//     const index = products.findIndex((p) => p.id.toString() === id.toString());

//     if (index === -1) {
//       return res.status(404).json({ message: "Product don't found!" });
//     }

//     products[index] = { ...products[index], ...updatedData };
//     writeDB(db, res, "Product updated successfully");
//   } catch (err) {
//     res.status(500).json({ message: "Failed to update product" });
//   }
// });


// app.delete("/products/:id", (req, res) => {
//   const id = req.params.id;

//   try {
//     const db = readDB();
//     const products = db.products;

//     const index = products.findIndex((p) => p.id.toString() === id.toString());
//     if (index === -1) {
//       return res.status(404).json({ message: "Product not found" });
//     }

//     products.splice(index, 1);

//     writeDB(db, res, "Product deleted successfully");
//   } catch (err) {
//     res.status(500).json({ message: "Failed to delete product" });
//   }
// });

// // Sample input : localhost:5000/products? id=3& name=NewName
// app.put("/products", (req, res) => {
//   let params = req.query; // used ofor multiple queries
//   let body = req.body;
// });

// Start server
app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}/`);
});
