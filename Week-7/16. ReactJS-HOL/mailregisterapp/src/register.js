import React, { useState } from 'react';

const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: ''
  });

  const handleChange = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const { name, email, password } = formData;

    // Validations
    if (name.length < 5) {
      alert("Full Name must be 5 characters long!");
      return;
    }

    if (!email.includes('@') || !email.includes('.')) {
      alert("Email must include '@' and '.'");
      return;
    }

    if (password.length < 8) {
      alert("Password must be at least 8 characters long!");
      return;
    }

    alert("Registration Successful!");
    setFormData({ name: '', email: '', password: '' });
  };

  return (
    <div style={{ marginTop: '50px', textAlign: 'center' }}>
      <h2 style={{ color: 'red', fontWeight: 'bold' }}>Register Here!!!</h2>
      <form onSubmit={handleSubmit} style={{ display: 'inline-block', textAlign: 'left' }}>
        <div style={{ marginBottom: '10px' }}>
          <label>Name:</label><br />
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            style={{ width: '250px', padding: '5px' }}
          />
        </div>
        <div style={{ marginBottom: '10px' }}>
          <label>Email:</label><br />
          <input
            type="text"
            name="email"
            value={formData.email}
            onChange={handleChange}
            style={{ width: '250px', padding: '5px' }}
          />
        </div>
        <div style={{ marginBottom: '10px' }}>
          <label>Password:</label><br />
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            style={{ width: '250px', padding: '5px' }}
          />
        </div>
        <button type="submit" style={{ width: '100%', padding: '8px' }}>
          Submit
        </button>
      </form>
    </div>
  );
};

export default Register;
