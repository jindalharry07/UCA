import Link from 'next/link';
import { DescriptionComponent } from "./components/Description.js";
import { ProductList } from "./components/ProductList.js";
export default function Home() {
  return (
    <div>
      <h1>Home Pagre</h1>
      <DescriptionComponent name = "VALID NAME" ></DescriptionComponent>
      <Link href="/login">Go to login</Link>
      <div>List of Products</div>
      <ProductList products={['p1', 'p2', 'p3', 'p4']} />

    </div>
  );
}

