"use client";
import React from 'react';

export default function Login() {
  const formStyle = {
    display: 'flex',
    flexDirection: 'column',
    maxWidth: '300px',
    margin: '0 auto',
    padding: '20px',
    border: '1px solid #ccc',
    borderRadius: '8px',
    backgroundColor: '#f9f9f9',
  };

  const inputStyle = {
    marginBottom: '10px',
    padding: '8px',
    fontSize: '16px',
    borderRadius: '4px',
    border: '1px solid #ccc',
  };

  const buttonStyle = {
    padding: '10px',
    backgroundColor: '#007BFF',
    color: 'white',
    fontSize: '16px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
  };

  const linkStyle = {
    display: 'block',
    textAlign: 'center',
    marginTop: '20px',
    textDecoration: 'none',
    color: '#007BFF',
  };

  const loginHandler = (e) => {
    e.preventDefault(); // Prevent page reload
    // Do login logic here
    console.log("Login form submitted");
  };

  return (
    <>
      <h1 style={{ textAlign: 'center' }}>Login</h1>
      <form style={formStyle} onSubmit={loginHandler}>
        <input type="email" placeholder="Email" style={inputStyle} required />
        <input type="password" placeholder="Password" style={inputStyle} required />
        {/* <button type="submit" style={buttonStyle} onClick={loginHandler}>Login</button> */}
        <button type="submit" style={buttonStyle}>Login</button>
      </form>
      <a href="/" style={linkStyle}>Go to Home</a>
    </>
  );
}
