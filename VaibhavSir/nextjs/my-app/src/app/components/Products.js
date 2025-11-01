// Products.js

import ProductCard from "./ProductCard.js";

// props are the actual products we want to display on a specific page
const Products = ({products}) => {
  return (
    <>
      <div style={{display: "flex", maxWidth: "100vw", padding: "20px"}}>
        {products.map((prod, idx) => {
          return <ProductCard key={idx} name={prod.name} price={prod.price} tag={prod.tag} image={prod.image}/>
        })}
      </div>
    </>
  );
}

export default Products;

// when we click on a product, we want the route to change like 

// /products/p1
// /products/p2
// /products/p3

// we cannot go around and hardcoding these URLs, so we learn something called Dynamic Routing