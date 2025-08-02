import './App.css';
import { EmployeesData } from './Employee';
import EmployeesList from './EmployeesList';
import { useState } from 'react';
import ThemeContext from './ThemeContext';

function App() {
  const [theme, setTheme] = useState('light');

  return (
    <ThemeContext.Provider value={theme}>
      <div>
        <label>SELECT A THEME </label>
        <select onChange={(e) => setTheme(e.target.value)} value={theme}>
          <option value='light'>Light</option>
          <option value='dark'>Dark</option>
        </select>
      </div>

      <EmployeesList employees={EmployeesData} />
    </ThemeContext.Provider>
  );
}

export default App;
