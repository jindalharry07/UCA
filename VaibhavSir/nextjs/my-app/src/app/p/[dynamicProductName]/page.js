// app/products/[id]/page.js (or similar)

export default function ProductDetailsPage({ params }) {
  console.log("--------- Page params --------", params);

  return <>PDP Page for Product ID: {params.id}</>;
}
