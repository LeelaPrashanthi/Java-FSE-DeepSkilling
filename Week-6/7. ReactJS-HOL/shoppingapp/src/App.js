import React from 'react';
import Cart from './Cart';

export class OnlineShopping extends React.Component {
  render() {
    return (
      <div>
        <h1 style={{ color: 'green', textAlign: 'center' }}>
          Items Ordered :
        </h1>
        <Cart />
      </div>
    );
  }
}

export default OnlineShopping;
