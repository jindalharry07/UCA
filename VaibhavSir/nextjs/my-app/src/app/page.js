import Link from 'next/link';
import { DescriptionComponent } from "./components/Description.js";
import { ProductList } from "./components/ProductList.js";
import Products from "./components/Products.js"


export default function Home() {
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
    <div>
      <h1>Home Page</h1>
      <h1>Welcome to E-commerce website</h1>
      <DescriptionComponent name = "VALID NAME" ></DescriptionComponent>
      <Link href="/login">Go to login</Link>
      <div>List of Products</div>
      <ProductList products={['p1', 'p2', 'p3', 'p4']} />

      <div>
        <h1>Products</h1>
        <Products products={products} />
      </div>
    </div>
  );
}

