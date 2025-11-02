export const metadata = {
  title: "Product Detail | Page",
};

function PDPLayout({ children }) {
  return (
    <div>
      <main>{children}</main>
    </div>
  );
}
function PDPHeader({ children }) {
  return (
    <>
      <div>
        PDP Header
      </div>
    </>
  );
}

export default PDPLayout;
