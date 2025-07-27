import React from 'react';

function Cart() {
  const items = [
    { name: 'Laptop', price: 80000 },
    { name: 'TV', price: 120000 },
    { name: 'Washing Machine', price: 50000 },
    { name: 'Mobile', price: 30000 },
    { name: 'Fridge', price: 70000 }
  ];

  return (
    <div style={{ display: 'flex', justifyContent: 'center' }}>
      <table border="1" style={{ textAlign: 'center', color: 'seagreen' }}>
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {items.map((item, index) => (
            <tr key={index}>
              <td>{item.name}</td>
              <td>{item.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Cart;
