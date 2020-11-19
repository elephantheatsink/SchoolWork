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
import { NavLink } from 'react-router-dom'
import {Container} from 'react-bootstrap'
import logo from './images/logo.png'
import './Homepage.css'
import './Header.css'

import Viz from './Viz/Viz'

class Homepage extends Component {
    render() {
        return (
                <div>
                <nav class="navbar navbar-expand-sm navbar-default">
                        <div class="logo1">
                        <a class="navbar-brand\\\" href="#">
                            <img src={logo} alt='Fire & Risk Alliance' position="absolute" width="12%" height="12%"/>
                        </a>
                        </div>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                                <a class="nav-item nav-link active"  href="/">Home<span class="sr-only">current</span></a>
                                <a class="nav-item nav-link" href="/login">|Login</a>
                                <a class="nav-item nav-link" href="/register">|Register</a>
                                <a class="nav-item nav-link" href="/logout">|Logout</a>

                            </div>
                        </div>
                    </nav>
                
                <br/><br/> <br/> 
                <br/> <br/> <br/>
                <Viz />
                <br/><br/> <br/> <br/> <br/> <br/>
                <NavLink to="/secret"className="main-nav" activeClassName="main-nav-active">Secret</NavLink>    

                
                {/* 
                <form action="/register">
                    <input type="submit" value="Register" />
                </form> 
                <p>I love designing homepages becuase i have nothing better to do </p>
                <button>login to nothing...</button>    
                */}
            </div>
        )
        return <div className= "Header"/>;
        return <div className= "Homepage"/>;
    }
}

export default Homepage
