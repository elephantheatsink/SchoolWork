import React, { Component } from 'react';
import{NavLink} from 'react-router-dom'
import {Navbar} from 'react-bootstrap'
import logo from './images/logo.png'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
// import AppBar from 'material-ui/AppBar'
import TextField from 'material-ui/TextField'
import RaisedButton from 'material-ui/RaisedButton'

class ForgotPassword extends Component {
    render() {
        return (
            <MuiThemeProvider>
                <React.Fragment>
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
                                <a class="nav-item nav-link active"  href="/dualsprinkler">Home<span class="sr-only">current</span></a>
                                <a class="nav-item nav-link" href="/logout">Logout</a>
                                <a class="nav-item nav-link" href="/login">Login</a>
                            </div>
                        </div>
                    </nav>
                    <div class='my-legend_1'>
                    <div class='legend-title'>Legend</div>
                        <div class='legend-scale'>
                            <ul class='legend-labels'>
                                <li><span style={{background:'#000080', }}></span>550</li>
                                <li><span style={{background:'#151B8D'}}></span>650</li>
                                <li><span style={{background:'#0000A0'}}></span>750</li>
                                <li><span style={{background:'#0020C2'}}></span>850</li>
                                <li><span style={{background:'#347235'}}></span>950</li>
                                <li><span style={{background:'#6CC417'}}></span>1050</li>
                                <li><span style={{background:'#00FF00'}}></span>1150</li>
                                <li><span style={{background:'#FFFF00'}}></span>1250</li>
                                <li><span style={{background:'#FFA500'}}></span>1350</li>
                                <li><span style={{background:'#FF0000'}}></span>1450</li>
                            </ul>
                        </div>
                    {/* <div class='legend-source'>Source: <a href="#link to source">Name of source</a></div> */}
                </div>

                <div class='my-legend'>
                        <div class='legend-title'>Legend</div>
                        <div class='legend-scale'>
                            <ul class='legend-labels'>
                                <li><span style={{background:'#000080', }}></span>NAVY BLUE</li>
                                <li><span style={{background:'#151B8D'}}></span>DENIM DARK BLUE</li>
                                <li><span style={{background:'#0000A0'}}></span>EARTH BLUE</li>
                                <li><span style={{background:'#0020C2'}}></span>COLBAL BLUE</li>
                                <li><span style={{background:'#347235'}}></span>GREENISH BLUE</li>
                                <li><span style={{background:'#6CC417'}}></span>ALIEN GREEN</li>
                                <li><span style={{background:'#00FF00'}}></span>MEDIUM SPRING GREEN</li>
                                <li><span style={{background:'#FFFF00'}}></span>YELLOW</li>
                                <li><span style={{background:'#FFA500'}}></span>ORANGE</li>
                                <li><span style={{background:'#FF0000'}}></span>RED</li>
                                
                            </ul>
                        </div>

                        {/* <div class='legend-source'>Source: <a href="#link to source">Name of source</a></div> */}
                    </div>

                    </React.Fragment>
          </MuiThemeProvider>
  
          );
          return <img src={logo} alt="logo" />;
          return<div className="Header"/>;
      }
}
export default ForgotPassword