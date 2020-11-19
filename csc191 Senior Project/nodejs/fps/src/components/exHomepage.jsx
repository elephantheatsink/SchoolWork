/**
 * 
 * Feel free to change any of this I was just messing around and trying to get a link to the login page set up -- AW
 * This page is set up as the default route for '/' if you look in App.js at the routes...
 */
import React, { Component } from 'react'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
// import AppBar from 'material-ui/AppBar'
import TextField from 'material-ui/TextField'
import RaisedButton from 'material-ui/RaisedButton'
import { Link } from 'react-router-dom'
import {Container} from 'react-bootstrap'
import logo from './logo.png'

import Viz from './Viz/Viz'

export class Homepage extends Component {
    render() {
        return (
            <div>
                <div class="container-xs">
                        <Container style={{
                            backgroundColor: '#717C7150',
                            
                            }}>
                                    <a href='/'>
                                    <img src={logo} alt='Fire & Risk Alliance' position="absolute" width="10%" height="10%"/>
                                    </a>
                                <ul className="menu-ul">
                        <li><Link to="/" style={{color:'black'}}>Home</Link></li>
                        <label> |</label> 
                        <li><Link to="/login"style={{color:'black'}}>Login</Link></li>   
                    </ul>
                    </Container>
                </div>
                <br/><br/> <br/> <br/> <br/> <br/>
                <Viz />
                <br/><br/> <br/> <br/> <br/> <br/>
                <li><Link to="/secret">Secret</Link></li>

                {/* 
                <form action="/register">
                    <input type="submit" value="Register" />
                </form> 
                <p>I love designing homepages becuase i have nothing better to do </p>
                <button>login to nothing...</button>    
                */}
            </div>
        )
    }
}

export default Homepage
