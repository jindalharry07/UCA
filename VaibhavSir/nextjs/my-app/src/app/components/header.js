import Link from "next/link";

function Header() {
  return (
    <header
      style={{
        backgroundColor: "grey",
        padding: "2px 20px",
        margin: "0 -20px",
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
      }}
    >
      {/* Logo / Home Link */}
      <Link href="/" style={{ textDecoration: "none", color: "inherit" }}>
        <h1>E-Com</h1>
      </Link>

      {/* Login Button Link */}
      <Link href="/login">
        <button style={{ height: "30px" }}>Login</button>
      </Link>
    </header>
  );
}

export default Header;
