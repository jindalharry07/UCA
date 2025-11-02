// app/p/[dynamicProductName]/page.js
import { products } from "../../components/productsData";

export default function ProductPage({ params }) {
  const { dynamicProductName } = params;
  const product = products.find((p) => p.id === params.dynamicProductName);

  if (!product) return <h1>Product not found</h1>;

  return (
    <div style={{ padding: "20px" }}>
      <h1>{product.name}</h1>
      <img src={product.image} alt={product.name} width={400} />
      <p>Price: ${product.price}</p>
      <p>Tag: {product.tag}</p>
      <p>{product.description}</p>
    </div>
  );
}
