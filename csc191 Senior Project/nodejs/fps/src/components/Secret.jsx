import React, { Component } from 'react';
import { NavLink } from 'react-router-dom'


export default class Secret extends Component {
  constructor() {
    super();
    this.state = {
      message: 'Loading...'
    }
  }

  componentDidMount() {
    fetch('/api/secret')
      .then(res => res.text())
      .then(res => this.setState({message: res}));
  }

  render() {
    return (
      <div>
        <h1>Secret</h1>
        <p>{this.state.message}</p>
        <NavLink to="/logout"className="main-nav" activeClassName="main-nav-active">Log Out</NavLink>    

        
      </div>
    );
  }
}