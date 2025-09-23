const root = ReactDOM.createRoot(document.getElementById("reactapp"));
function DescriptionComponent({name}) {
  name = "Jacky"
  return (
    <>
      <div>
        <h2>Hello from React! to {name ?? "Guest User"}</h2>
        <p>This content is rendered using React and JSX.</p>
        <p>Footer</p>
      </div>
      
    </>
  );
}



root.render(<DescriptionComponent name="Harry"/>); // when comonent is used as component then the component will only accept the object as parameter
// root.render(<DescriptionComponent />);

