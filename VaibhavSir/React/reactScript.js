// import { useState } from "react";

// import { StrictMode } from "react";

const root = ReactDOM.createRoot(document.getElementById("reactapp"));

function DescriptionComponent(props) {
  // name = "Jacky"

  // props.name = "Jacky" //cant be change because props are immutable
  return (
    <>
      <div>
        <h2>Hello from React! to {props.name ?? "Guest User"}</h2>
        <p>This content is rendered using React and JSX.</p>
        <p>Footer</p>
        <h1 style={{ display: "none" }}>
          React Managed code {props.name ?? "Guest User"}
        </h1>
        ; {/* display none */}
      </div>
    </>
  );
}

// root.render(<DescriptionComponent name={"Valid Name"}></DescriptionComponent>)
// root.render(<DescriptionComponent name="Harry"/>);

// when comonent is used as component then the component will only accept the object as parameter
// root.render(<DescriptionComponent />);

// root.render() // esme sirf ek componemt h pass krna h

// Likes Componemt

function LikesComponent(props) {
  // let likes = 25;
  // function setLikes() {
  //   likes += 1;
  //   console.log("Likes: "+likes);
  // }

  // useState
  let [likes, setLikes] = React.useState(0);
  function updateLikes() {
    setLikes(likes + 1);
    // console.log("Likes: "+ likes) // dont give the changed value

    setTimeout(() => {
      console.log("Likes: " + likes);
    }, 1000);
  }
  function updateDislikes() {
    setLikes(likes - 1);
    console.log("Likes: " + likes); // dont give the changed value
  }

  return (
    <>
      <p>Like {likes}</p>
      <button onClick={updateLikes}>Like</button>
      <CustomButton
        label="Dislike"
        clickBhehavior={updateDislikes}
      ></CustomButton>
    </>
  );
}

function CustomButton(props) {
  return (
    <button
      style={{
        margin: "2px",
        padding: "5px",
        border: "2px solid black",
        fontWeight: 500,
      }}
      // onClick={props.updateLikes}
      onClick={props.clickBhehavior}
    >
      {props.label}
      {/* WHAT WE WILL PASS AS LABEL THAT WILL BE DISPLAY  to make the button dynamic*/}
    </button>
  );
}

function Likes2Component(props) {
  const [liked, setLiked] = React.useState("no");

  // No array
  // React.useEffect(() => {
  //   // called on every re- render
  // });

  // // empty array -- correct
  // React.useEffect(() => {
  //   // fetch the backend data
  //   // return () => {}; //cleanup fun
  // }, []);
  // /* 2 nd way */
  // function fetchData() {
  //   // code to fetch the data
  // }
  // fetchData(); // this will fetch data on every call

  // // array with dependencies
  // React.useEffect(() => {
  //   // send liked data to backend
  // }, [liked]);

  const updateLikes = () => {
    if (liked === "no") {
      setLiked("yes");
    } else {
      setLiked("no");
    }
  };

  const getLabel = () => {
    return liked === "no" ? "Like" : "Dislike";
  };

  return (
    <>
      <br />
      <br />
      <section>This is my first section</section>
      <p>Likes - {liked}</p>
      <CustomButton
        label={liked === "no" ? "Like" : "Dislike"}
        clickBhehavior={updateLikes}
      />
    </>
  );
}

function ProductComponent(propa) {
  const [productList, setProductList] = React.useState([]);

  React.useEffect(() => {
    // FETCH THE PRODUCt data
    // setTimeout
  }, []);
  const [show, setShow] = React.useState(false);
  function togle() {
    show ? setShow(false) : setShow(true);
  }
  return (
    <>
      <h3> Products List </h3>
      <ol>
        <li>Prouduct 1</li>
        <li>Prouduct 2</li>
      </ol>
      {show ? <p>{show ? "wlecome USer" : ""}</p> : ""}

      <Button label="Show Welecome text" clickBehavior={togle} />
    </>
  );
}
function Button(props) {
  return (
    <button
      style={{
        margin: "5px",
        padding: "5px",
        border: "2px solid black",
      }}
      onClick={props.clickBehavior}
    >
      {props.label}
    </button>
  );
}

function FormComponent(props) {
  const tempvariable = React.useRef(10); // doesn't re made in rerender
  const [arr1, setArray] = React.useState(["Textbox1", "Textbox2", "Textbox3"]);

  const changeOrder = () => {

    tempvariable.current++;
    const updatedArr1 = [...arr1].reverse(); // makes new arrray so react updates it otherwise n = only address change and react does not see the change

    setArray(updatedArr1);
    console.log(tempvariable.current);
  };
  return (
    <>
      <h3>Form Component</h3>
      {/* if we use key as {item}-{index} -> index will remain same only item will change on reversing resulting new unique key and just delete the text
      text1 - 0, text2 - 1, text3 - 2
      will change into text3 - 0, text2 - 1, text1 - 2 */}
      {arr1.map((item) => (
        // gives the unique key
        <div key={`${item}`}>
          <input type="text" placeholder={item} />
        </div>
      ))}
      <Button clickBehavior={changeOrder} label="Reverse Order"></Button>
      <Button clickBehavior={changeOrder} label="Reverse Order">This is Children </Button>
    </>
  );
}

function App() {
  return (
    <>
      <DescriptionComponent name={"Harry"}></DescriptionComponent>

      {/* <LikesComponent/> */}
      <Likes2Component />

      <ProductComponent />
      <FormComponent />
    </>
  );
}
root.render(<App />);
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );
