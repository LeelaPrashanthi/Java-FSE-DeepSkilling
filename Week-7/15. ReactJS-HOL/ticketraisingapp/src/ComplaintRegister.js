import React, { useState } from 'react';

const ComplaintRegister = () => {
  const [name, setName] = useState('');
  const [complaint, setComplaint] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!name || !complaint) {
      alert('Please enter both name and complaint.');
      return;
    }

    const transactionId = Math.floor(1 + Math.random() * 99); // Between 1 and 99

    alert(
      `Thanks ${name}\nYour Complaint was Submitted.\nTransaction ID is: ${transactionId}`
    );

    // Clear fields
    setName('');
    setComplaint('');
  };

  return (
    <div style={{ textAlign: 'center', marginTop: '50px' }}>
      <h2 style={{ color: 'red', fontWeight: 'bold' }}>
        Register your complaints here!!!
      </h2>
      <form onSubmit={handleSubmit} style={{ display: 'inline-block', marginTop: '20px' }}>
        <div style={{ marginBottom: '10px', textAlign: 'left' }}>
          <label>Name:</label><br />
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            style={{ width: '300px', padding: '5px' }}
          />
        </div>
        <div style={{ marginBottom: '10px', textAlign: 'left' }}>
          <label>Complaint:</label><br />
          <textarea
            rows="3"
            value={complaint}
            onChange={(e) => setComplaint(e.target.value)}
            style={{ width: '300px', padding: '5px' }}
          />
        </div>
        <button type="submit" style={{ padding: '8px 20px' }}>
          Submit
        </button>
      </form>
    </div>
  );
};

export default ComplaintRegister;
