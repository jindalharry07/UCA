// app/products/[id]/page.js (or similar)

// /app/page.js - Home

import Link from "next/link";
import Products from "./components/Products";

const Home = () => {
  const products = [
    {
      id: 1,
      name: "Product 1",
      price: "50",
      tag: "Top seller",
      image: "https://placehold.co/300x200",
    },
    {
      id: 2,
      name: "Product 2",
      price: "30",
      tag: "Top seller",
      image: "https://placehold.co/300x200",
    },
    {
      id: 3,
      name: "Product 3",
      price: "20",
      tag: "Top seller",
      image: "https://placehold.co/300x200",
    },
    {
      id: 4,
      name: "Product 4",
      price: "10",
      tag: "Top seller",
      image: "https://placehold.co/300x200",
    },
  ];

  return (
    <>
      <h1>Hello from next!</h1>
      <Link href="/login">Login</Link>
      <div>
        <h1>Products</h1>
        <Products products={products} />
      </div>
    </>
  );
};

export default Home;