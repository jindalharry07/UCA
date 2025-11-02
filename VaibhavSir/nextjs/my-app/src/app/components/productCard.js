import Link from "next/link";

const ProductCard = ({ name, price, tag, image, id }) => {
  return (
    <Link
      href={`/products/${id}`}
      style={{ textDecoration: "none", color: "inherit" }}
    >
      <div
        style={{
          border: "1px solid #ccc",
          margin: "10px",
          padding: "10px",
          cursor: "pointer",
          borderRadius: "8px",
        }}
      >
        <img src={image} alt={name} width={300} />
        <div
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-around",
            gap: "10px",
          }}
        >
          <h3 style={{ margin: 0 }}>{name}</h3>
          <p style={{ margin: 0 }}>Price: ${price}</p>
          <p style={{ margin: 0 }}>{tag}</p>
        </div>
      </div>
    </Link>
  );
};

export default ProductCard;
