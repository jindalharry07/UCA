import Link from "next/link";
import Button from "./button";
function Header() {
  return (
    <header style={{
      backgroundColor: "grey",
      padding: "2px 20px",
      margin: "0 -20px",
      display: "flex",
      justifyContent: "space-between",
      alignItems: "center"
    }}>
      <Link href="/">
        <h1>E-Com</h1>
      </Link>
      <div className="display-flex">
          <Link href="/login">
          <Button variant="primary">Login</Button>      
          </Link>
          <Link href="/addproduct">
          <Button variant="primary">Add Product</Button>
          </Link>
      </div>
      
    </header>
  );
}

export default Header;