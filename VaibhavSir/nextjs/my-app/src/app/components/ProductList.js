// components/productList.js

import React from 'react';

export const ProductList = ({ products }) => {
  return (
    <ul>
      {products.map((p, index) => (
        <li key={index}>{p}</li>
      ))}
    </ul>
  );
};
