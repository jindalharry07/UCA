function Button({ children, variant, type = "button", onClick }) {
  return (
    <>
      {variant == "light" && (
        <button
          type="button"
          className="text-white bg-green-700 hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
          onClick={onClick}
        >
          {children}
        </button>
      )}
      {variant == "primary" && (
        <button
          type="button"
          className="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-button-800"
          onClick={onClick}
        >
          {children}
        </button>
      )}
    </>
  );
}
export default Button;
