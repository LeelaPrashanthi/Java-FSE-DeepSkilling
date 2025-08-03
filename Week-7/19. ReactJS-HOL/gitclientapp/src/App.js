import './App.css';
import GitClient from './GitClient';
import { useEffect, useState } from 'react';

function App() {
  const [username, setUsername] = useState('techiesyed');
  const [repositories, setRepositories] = useState([]);

  useEffect(() => {
    if (username.trim() === '') return;

    GitClient.getRepositories(username)
      .then(response => {
        setRepositories(response.data);
      })
      .catch(error => {
        console.error("Error fetching repos:", error);
        setRepositories([]);
      });
  }, [username]);

  return (
    <div className="App">
      <h1>GitHub Repositories Viewer</h1>

      <input
        type="text"
        placeholder="Enter GitHub username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />

      {repositories.length > 0 ? (
        repositories.map(repo => (
          <p key={repo.name}>{repo.name}</p>
        ))
      ) : (
        <p>No repositories found.</p>
      )}
    </div>
  );
}

export default App;
