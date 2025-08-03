import React, { Component } from 'react';

class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
      loading: true,
    };
  }

  componentDidMount() {
    fetch('https://api.randomuser.me/')
      .then((res) => res.json())
      .then((data) => {
        this.setState({
          user: data.results[0],
          loading: false,
        });
      })
      .catch((error) => {
        console.error("Error fetching user:", error);
      });
  }

  render() {
    const { user, loading } = this.state;

    if (loading) {
      return <h2 style={{ textAlign: 'center' }}>Loading...</h2>;
    }

    return (
      <div style={{ textAlign: 'center', marginTop: '80px' }}>
        <h1 style={{ fontWeight: 'bold' }}>
          {`${user.name.title} ${user.name.first} ${user.name.last}`}
        </h1>
        <img
          src={user.picture.large}
          alt="User"
          style={{
            borderRadius: '8px',
            marginTop: '20px',
            width: '100px',
            height: '100px',
            objectFit: 'cover',
          }}
        />
      </div>
    );
  }
}

export default Getuser;
