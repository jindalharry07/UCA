import Link from 'next/link';
import { DesciptionComponent } from "./components/Description.js";
export default function Home() {
  return (
    <div>
      <h1>Home Pagre</h1>
      <DesciptionComponent name = "VALID NAME" ></DesciptionComponent>
      <Link href="/login">Go to login</Link>
    </div>
  );
}

