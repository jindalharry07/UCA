import Link from "next/link";

export const ProductList = ({ products }) => {
  return (
    <ul className="flex">
      {products.map((p) => (
        <li key={p.id} style={{ border: "1px solid #ccc", padding: "10px", width: "300px" }}>
          <Link href={`/p/${p.id}`} style={{ textDecoration: "none", color: "inherit" }}>
            <img src={p.image} alt={p.name} width={300} />
            <h3>{p.name}</h3>
            <p>Price: ${p.price}</p>
            <p>{p.tag}</p>
          </Link>
        </li>
      ))}
    </ul>
  );
};
