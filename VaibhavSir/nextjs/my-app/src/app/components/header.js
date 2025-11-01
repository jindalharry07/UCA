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
      <Link href="/">
        <h1>E-Com</h1>
      </Link>

      <Link href="/login">
        <a>
          <button style={{ height: "30px" }}>Login</button>
        </a>
      </Link>
    </header>
  );
}

export default Header;
