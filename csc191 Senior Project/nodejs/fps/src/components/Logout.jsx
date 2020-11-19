import React, { Component } from 'react'
import { NavLink } from 'react-router-dom'


class Logout extends Component {
    constructor() {
        super();
        this.state = {
          
        }
      }
    componentDidMount() {
        fetch('/api/logout')
          .then(res => res.text())
          .then(res => this.setState({message: res}));
      }

      render() {
        return (
          <div>
            <h1>Logout</h1>
            <p>{this.state.message}</p>
            <NavLink to="/"className="main-nav" activeClassName="main-nav-active">Return to Home</NavLink>    

          </div>
        );
      }
      
    
}
export default Logout