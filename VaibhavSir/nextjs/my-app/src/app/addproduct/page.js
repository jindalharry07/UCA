"use client";
import React, { useRef } from "react";
import Button from "../components/button";

export default function AddProductPage() {
  const nameRef = useRef();
  const priceRef = useRef();
  const descRef = useRef();
  const imgRef = useRef();

  const handleSubmit = (e) => {
    e.preventDefault();
    const newProduct = {
        id: Date.now(), // temporary ID
        name: nameRef.current.value,
        price: priceRef.current.value,
        tag: descRef.current.value,
        image: imgRef.current.value,
    };
    
    alert("Product added");
    console.log("New Product:", newProduct);

    fetch("http://localhost:5000/products", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newProduct),
      })
        .then((response) => {
          console.log("Response from server after adding product: ", response);
          window.alert("Product added successfully!");
        })
        .catch((error) => {
          console.error("Error while adding product: ", error);
          window.alert("Failed to add product.");
        });

      console.log("Product Data: ", productData);
      console.log("Name Ref using useRef: ", nameRef.current);
      console.log("Name Ref using DOM: ", document.getElementById("name"));
    };

  return (
    <>
        <h1 className="text-center text-2xl font-bold mt-10">Add a Prouct</h1>
      <form  className="max-w-sm mx-auto mt-20 border-1 p-8 "  >
        
        {/* Product Name */}
        <div className="mb-5">
          <label htmlFor="name" className="block mb-2.5 text-sm font-medium text-heading">
            Product Name
          </label>
          <input
            ref={nameRef}
            type="text"
            id="name"
            className="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow-xs placeholder:text-body"
            placeholder="Enter product name"
            required
          />
        </div>

        {/* Price */}
        <div className="mb-5">
          <label htmlFor="price" className="block mb-2.5 text-sm font-medium text-heading">
            Price
          </label>
          <input
            ref={priceRef}
            type="number"
            id="price"
            step="0.01"
            className="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow-xs placeholder:text-body"
            placeholder="Enter price"
            required
          />
        </div>

        {/* Description */}
        <div className="mb-5">
          <label htmlFor="tag" className="block mb-2.5 text-sm font-medium text-heading">
           tag
          </label>
          <textarea
            ref={descRef}
            id="tag"
            className="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow-xs placeholder:text-body"
            rows="3"
            placeholder="Product description"
            required
          ></textarea>
        </div>

        {/* Image URL */}
        <div className="mb-5">
          <label htmlFor="image" className="block mb-2.5 text-sm font-medium text-heading">
            Image URL
          </label>
          <input
            ref={imgRef}
            type="url"
            id="image"
            className="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow-xs placeholder:text-body"
            placeholder="https://example.com/image.jpg"
            required
          />
        </div>

        {/* Submit Button */}
        <Button variant="light" onClick={handleSubmit}>Add Product</Button>
      </form>
       <a href="/" >Go to Home</a>
    </>
  );
}