// CountPeople.js
import React from "react";

class CountPeople extends React.Component {
  constructor() {
    super();
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
  }

  updateEntry = () => {
    this.setState((prevState) => ({
      entrycount: prevState.entrycount + 1
    }));
  };

  updateExit = () => {
    this.setState((prevState) => ({
      exitcount: prevState.exitcount + 1
    }));
  };

  render() {
    return (
      <div style={{ textAlign: "center", marginTop: "100px" }}>
        <button
          style={{
            backgroundColor: "lightgreen",
            marginRight: "20px",
            padding: "10px"
          }}
          onClick={this.updateEntry}
        >
          Login
        </button>
        <span>{this.state.entrycount} People Entered!!!</span>
        <br />
        <br />
        <button
          style={{
            backgroundColor: "lightgreen",
            marginRight: "20px",
            padding: "10px"
          }}
          onClick={this.updateExit}
        >
          Exit
        </button>
        <span>{this.state.exitcount} People Left!!!</span>
      </div>
    );
  }
}

export default CountPeople;
