import React, { Component } from 'react'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
// import AppBar from 'material-ui/AppBar'
import TextField from 'material-ui/TextField'
import RaisedButton from 'material-ui/RaisedButton'
import { Link } from 'react-router-dom'
import {Container} from 'react-bootstrap'
import logo from './images/logo.png'
import './Header.css'
class Login extends Component {
  constructor(props) {
    super(props)
    this.state = {
      email : '',
      password: ''
    };
  }
    
  handleInputChange = (event) => {
    const { value, name } = event.target;
    this.setState({
      [name]: value
    });
  }
    
  onSubmit = (event) => {
    event.preventDefault();
    fetch('/api/authenticate', {
      method: 'POST',
      body: JSON.stringify(this.state),
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(res => {
      if (res.status === 200) {
        this.props.history.push('/');
      } else {
        const error = new Error(res.error);
        throw error;
      }
    })
    .catch(err => {
      console.error(err);
      alert('Error logging in please try again');
    });
  }
    
    /*
    handleSubmit = event => {
        event.preventDefault(); //For debugging purposes, remove when we actually have a DB to dump data to.
        console.log(this.state);
        //Console.WriteLine(this.state);
    }
    */
    handleChange = (event) => {
        this.setState({[event.target.name]: event.target.value})
    }

    render() {
      return (
        <MuiThemeProvider>
          <React.Fragment>
            <div class="container">
              {/* <div class="hpbackground"> */}
                <div class="container-sm" style={{ display:'inline'}}>
                  <Container style={{       
                    backgroundColor: '#717C7150',
                    borderRadius: '5px',
                    width: '530px',
                    padding: '50px',
                    position:'center'
                    
                  }}>
                          
                    <a href='/'>
                      <img src={logo} width="50%" height="50%" alt='Fire & Risk Alliance' />
                      <br></br>
                    </a>
                    <br></br>
                    {/*  This may be a duplicate form that may be unneccesary.
                      <form onSubmit={this.onSubmit}>
                        <div class="form-group row">
                          <div class="col col-lg-3 col-form-label text-lg-right">
                            <label style={{ color:'white', fontWeight:'bolder'}}> Username:</label>
                          </div>
                          <div class="col-lg-8">
                            <input
                              type="text" 
                              text="text-muted" 
                              autofocus class="form-control form-element form-field" 
                              hintText='Username' 
                              name = "email" 
                              hintText='Email'
                              placeholder="Example@"
                              
                              value = {this.state.email} onChange={this.handleChange}
                              required
                            />

                          </div>
                        </div>
                      </form>
                    */}
                    <form autoComplete="off" onSubmit={this.onSubmit}>
                      <div class="form-group row">
                        <div class="col col-lg-3 col-form-label text-lg-right">
                            <label style={{ color:'black', fontWeight:'normal'}}> Username:</label>
                        </div>
                        <div class="col-lg-8">
                          <input
                            type="text" 
                            text="text-muted" 
                            autofocus class="form-control form-element form-field" 
                            hintText='Username' 
                            name = "email" 
                            hintText='Email'
                            placeholder="Example@"
                            
                            value = {this.state.email} onChange={this.handleChange}
                            required
                          />

                        </div>
                      </div>
                      <div class="form-group row">
                        <div class="col col-lg-3 col-form-label text-lg-right">
                          <label style={{ color:'black', fontWeight:'normal'}}>Password:</label>
                        </div>
                        <div class= "col-lg-8">
                          <input
                            autofocus class="form-control form-element form-field" 
                            type='password'
                            name = "password" 
                            hintText='Password'
                            text="text-muted" 
                            placeholder ="Password"
                            value = {this.state.password} 
                            onChange={this.handleInputChange}
                            required
                          />
                        </div>  
                      </div>
                      <div className="form-group row form-actions">
                        <div class="offset-lg-3 col-lg-8 mt-2">
                          <input type="submit" value = "Submit" className="form-element form button btn-block btn-dark" />
                        </div>
                      </div>
                    </form>

                    <div class="offset-lg-3 col-lg-8">
                      <Link to= '/forgotpassword' style={{color: 'black' , fontWeight:'normal'}}> Forgot Password</Link> 
                    </div>
                    <div class="offset-lg-3 col-lg-8">
                          <Link to= '/register' style={{color: 'black' , fontWeight:'normal'}}> Register for account</Link>  
                    </div>
                  </Container>
                </div>
              </div>
            {/* </div> */}
          </React.Fragment>
        </MuiThemeProvider>

      );
      return <img src={logo} alt="logo" />;
      return<div className="Header"/>;
    }
}

export default Login


