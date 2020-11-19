import React, { Component } from 'react';
import logo from './images/logo.png'
import{NavLink} from 'react-router-dom'

export class Header extends Component {
    render() {
        return (
                <div className ="logo1">
                    <a href='/'>
                        <img src={logo} width="25%" height="25%" alt='Fire & Risk Alliance' />
                    </a>
                <ul className="menu-ul">
                    <li><NavLink to="/">Home</NavLink></li>  
                </ul>
                </div>
        )
    }
}

export default Header
