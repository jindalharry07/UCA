import express from "express";
import fileSystem from "fs";

const router = express.Router();

// Helper function to read db.json
// const productsData = require("./db.json");
function getProducts() {
  const data = fileSystem.readFileSync("./db.json", "utf-8", (error, data) => {
    console.log("Data from DataBase: ", data);
  }); // read file
  const jsonData = JSON.parse(data);
  return jsonData.products;
}


// Products page
router.get("/", (req, res) => {
  fileSystem.readFile("./db.json", "utf8", (error, data) => {
    if (error) {
      console.error("Error reading database file: ", error);
      res.status(500).json({ message: "Internal server error" });
    }
    // console.log("Data from database: ", data);
    const currentDBData = JSON.parse(data);
    // console.log("Formatted data from database: ", currentDBData);
    const productsDataFromDB = currentDBData.products;

    console.log("Products data from database: ", productsDataFromDB);
    res.json(productsDataFromDB);
  });
});


router.post("/", (req, res) => {
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
function readDB() {
  const data = fileSystem.readFileSync("./db.json", "utf-8");
  return JSON.parse(data);
}

function writeDB(data, res, successMessage) {
  fileSystem.writeFile("./db.json", JSON.stringify(data, null, 2), (err) => {
    if (err) {
      return res.status(500).json({ message: "Failed to write database" });
    }
    res.json({ message: successMessage, products: data.products });
  });
}

router.put("/:id", (req, res) => {
  const id = req.params.id;
  const updatedData = req.body;

  try {
    const db = readDB();
    const products = db.products;

    const index = products.findIndex((p) => p.id.toString() === id.toString());

    if (index === -1) {
      return res.status(404).json({ message: "Product don't found!" });
    }

    products[index] = { ...products[index], ...updatedData };
    writeDB(db, res, "Product updated successfully");
  } catch (err) {
    res.status(500).json({ message: "Failed to update product" });
  }
});

router.delete("/:id", (req, res) => {
  const id = req.params.id;

  try {
    const db = readDB();
    const products = db.products;

    const index = products.findIndex((p) => p.id.toString() === id.toString());
    if (index === -1) {
      return res.status(404).json({ message: "Product not found" });
    }

    products.splice(index, 1);

    writeDB(db, res, "Product deleted successfully");
  } catch (err) {
    res.status(500).json({ message: "Failed to delete product" });
  }
});

// Sample input : localhost:5000/products? id=3& name=NewName
// router.put("/", (req, res) => {
//   let params = req.query; // used ofor multiple queries
//   let body = req.body;
// });


export default router;