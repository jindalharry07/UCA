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

const express = require("express");
const app = express();
app.use(express.json());
const fileSystem = require("fs");

// Helper function to read db.json
// const productsData = require("./db.json");
function getProducts() {
  const data = fileSystem.readFileSync("./db.json", "utf-8", (error, data) => {
    console.log("Data from DataBase: ", data);
  }); // read file
  const jsonData = JSON.parse(data);
  return jsonData.products;
}

// Home route
app.get("/", (req, res) => {
  res.send("Server is working!");
});

// Products page
app.get("/products", (req, res) => {
  // res.json(productsData.products);

  fileSystem.readFile("./db.json", "utf8", (error, data) => {
    if (error) {
      console.error("Error reading db.json:", error);
      return res.status(500).json({ error: "Failed to read products" });
    }

    const currentDBdata = JSON.parse(data);
    const products = currentDBdata.products;

    console.log("Data from DataBase:", products);
    res.json(products);
  });

  // const products = getProducts();
  // res.json(products);
});

app.post("/products", (req, res) => {
  const newProduct = req.body;
  if (!newProduct || !newProduct.id) {
    return res.status(400).json({ error: "Product data or id is missing" });
  }

  fileSystem.readFile("./db.json", "utf8", (error, data) => {
    if (error) {
      return res.status(500).json({ message: "Internal server error" });
    }

    try {
      const currentDBData = JSON.parse(data);
      const productsDataFromDB = currentDBData.products;

      // Find product by id (ensure types match)
      const index = productsDataFromDB.findIndex(
        (p) => p.id.toString() === newProduct.id.toString()
      );

      if (index !== -1) {
        // Update existing product
        productsDataFromDB[index] = {
          ...productsDataFromDB[index],
          ...newProduct,
        };
      } else {
        // Add new product
        productsDataFromDB.push(newProduct);
      }

      currentDBData.products = productsDataFromDB;

      fileSystem.writeFile(
        "./db.json",
        JSON.stringify(currentDBData, null, 2),
        (err) => {
          if (err) {
            return res.status(500).json({ message: "Failed to save product" });
          }
          res.status(201).json({
            message:
              index !== -1
                ? "Product updated successfully"
                : "Product added successfully",
            products: productsDataFromDB,
          });
        }
      );
    } catch (parseErr) {
      res.status(500).json({ message: "Invalid database format" });
    }
  });
});

// Write put and delete

// Start server
app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}/`);
});
