"use client";
import React, { useState } from "react";

export default function AddProductPage() {
  const [productName, setProductName] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");
  const [image, setImage] = useState("");

  const addProductHandler = (e) => {
    e.preventDefault();

    const productData = {
      productName,
      price,
      description,
      image,
    };

    console.log("Product Added:", productData);

    // Reset form
    setProductName("");
    setPrice("");
    setDescription("");
    setImage("");
  };

  const pageStyle = {
    minHeight: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#f5f5f5",
  };

  const formStyle = {
    width: "350px",
    padding: "25px",
    backgroundColor: "#fff",
    borderRadius: "10px",
    boxShadow: "0 3px 10px rgba(0,0,0,0.1)",
    display: "flex",
    flexDirection: "column",
  };

  const inputStyle = {
    marginBottom: "15px",
    padding: "10px",
    fontSize: "15px",
    borderRadius: "6px",
    border: "1px solid #ccc",
  };

  const buttonStyle = {
    padding: "12px",
    backgroundColor: "#d44a13ff",
    color: "white",
    fontSize: "16px",
    border: "none",
    borderRadius: "6px",
    cursor: "pointer",
    marginTop: "10px",
  };

  const linkStyle = {
    textAlign: "center",
    marginTop: "15px",
    color: "#007BFF",
    textDecoration: "none",
  };

  return (
    <div style={pageStyle}>
      <form style={formStyle} onSubmit={addProductHandler}>
        <h2 style={{ textAlign: "center", marginBottom: "20px" }}>
          Add Product
        </h2>

        <input
          type="text"
          placeholder="Product Name"
          style={inputStyle}
          value={productName}
          onChange={(e) => setProductName(e.target.value)}
          required
        />

        <input
          type="number"
          placeholder="Price"
          style={inputStyle}
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          required
        />

        <textarea
          placeholder="Description"
          style={{
            ...inputStyle,
            height: "80px",
            resize: "none",
          }}
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />

        <input
          type="text"
          placeholder="Image URL"
          style={inputStyle}
          value={image}
          onChange={(e) => setImage(e.target.value)}
          required
        />

        <button type="submit" style={buttonStyle}>
          Add Product
        </button>

        <a href="/" style={linkStyle}>
          Go to Home
        </a>
      </form>
    </div>
  );
}
