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

        <h1 style={{display: "none"}}>React Managed code {props.name??"Guest User"}</h1>;  {/* display none */}
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

    setTimeout(()=>{
      console.log("Likes: "+likes);
    },1000)

  }
  function updateDislikes() {
    setLikes(likes - 1);
    console.log("Likes: "+ likes) // dont give the changed value
  }

  return (
    <>
      <p>Like {likes}</p>
      <button onClick={updateLikes}>Like</button>
      <CustomButton label="Dislike" updateLikes={updateDislikes}></CustomButton>
    </>
  )
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
      onClick={props.updateLikes}
    >
      {props.label} 
      {/* WHAT WE WILL PASS AS LABEL THAT WILL BE DISPLAY  to make the button dynamic*/}
    </button>
  );
}

function App() {
  return (
    <>
      <DescriptionComponent name={"Harry"}></DescriptionComponent>

      <LikesComponent/>
    </>
  )
}
root.render(<App/>);
// root.render(<React.StrictMode>
//   <App/>
// </React.StrictMode>)