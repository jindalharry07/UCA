import Link from "next/link";

const ProductCard = ({ name, price, tag, image, id }) => {
  return (
    <Link href={`/products/${id}`}>
      <div style={{ border: "1px solid #ccc", margin: "10px", padding: "10px", cursor: "pointer" }}>
        <img src={image} alt={name} width={150} />
        <h3>{name}</h3>
        <p>Price: ${price}</p>
        <p>{tag}</p>
      </div>
    </Link>
  );
};

export default ProductCard;
