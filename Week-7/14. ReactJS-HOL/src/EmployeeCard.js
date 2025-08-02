import React, { useContext } from 'react';
import ThemeContext from './ThemeContext';

function EmployeeCard({ employee }) {
    const theme = useContext(ThemeContext);

    return (
        <div className="card">
            <h3>{employee.name}</h3>
            <p>{employee.designation}</p>
            <button className={theme === 'dark' ? 'btn-dark' : 'btn-light'}>
                View Profile
            </button>
        </div>
    );
}

export default EmployeeCard;
