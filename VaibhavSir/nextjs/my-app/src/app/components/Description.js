"use client";
import { useEffect } from "react";

export function DescriptionComponent(props) {
  useEffect(() => {
    console.log("Component Mountes");
  }, []);
  return (
    // props are immutable
    <>
      <h1>React maneged code {props.name ?? "Guest name"}</h1>
    </>
  );
}
