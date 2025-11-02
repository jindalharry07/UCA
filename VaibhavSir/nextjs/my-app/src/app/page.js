import Link from 'next/link';
import { DescriptionComponent } from "./components/Description.js";
import { ProductList } from "./components/ProductList.js";
import Products from "./components/Products.js"
import { products } from "./components/productsData";
import { resolve } from 'styled-jsx/css';


export default async function Home() {
  function sleep(sleepTime) {
    return new Promise((resolve) => setTimeout(resolve, sleepTime));
  }

  // await sleep(5000); //wait 5 sec before loading site

  return (
    <div>
      <h1>Home Page</h1>
      <h1>Welcome to E-commerce website</h1>
      <DescriptionComponent name = "VALID NAME" ></DescriptionComponent>
      <Link href="/login">Go to login</Link>
      <div>List of Products</div>
      <ProductList products={products} />
    </div>
  );
}

