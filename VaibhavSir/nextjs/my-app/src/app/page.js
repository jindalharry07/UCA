import Link from "next/link";
import ProductsListAlias from "@/app/components/ProductList";

// Cache revalidation / Disable caching for this page with 0 value
// export const revalidate = 10;

// Scenario 1- Do not provide revalidate // static page //recommended
// Scenario 2- add revalidate with value as 0 // dyanamic server side - always revalidate
// Scenario 3- add revalidate with value as 60 // catche after 60 sec

export default async function Home() {
  // -------Simulate API call behavior with a delay-------
  function sleep(sleepTime) {
    return new Promise((resolve) => setTimeout(resolve, sleepTime));
  }
  await sleep(2000);
  console.log("Slept for 10 seconds");
  // -----------------------------------------------------

  const products = [
    {
      id: 1,
      name: "Product 1",
      price: "50$",
      tag: "Top seller",
      image: "https://placehold.co/300x200",
    },
    {
      id: 2,
      name: "Product 2",
      price: "150$",
      tag: "Recommended",
      image: "https://placehold.co/300x200",
    },
    {
      id: 3,
      name: "Product 3",
      price: "20$",
      tag: "On Sale",
      image: "https://placehold.co/300x200",
    },
    {
      id: 4,
      name: "Product 4",
      price: "800$",
      tag: "Best price",
      image: "https://placehold.co/300x200",
    },
  ];

  return (
    <>
      <div style={{ textAlign: "center" }}>
        <h1>Welcome to the books ecommerce application</h1>
      </div>

      {/* <div></div> */}
      <ProductsListAlias products={products} />
    </>
  );
}

// /p/poduct1

// /p/poduct2

// /p/product1000