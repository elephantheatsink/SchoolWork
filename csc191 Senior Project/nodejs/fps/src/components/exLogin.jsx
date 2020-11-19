import React, { Component } from 'react'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
// import AppBar from 'material-ui/AppBar'
import TextField from 'material-ui/TextField'
import RaisedButton from 'material-ui/RaisedButton'
import { Link } from 'react-router-dom'
import {Container} from 'react-bootstrap'
import logo from './logo.png'

/*
class Login extends Component {
    state = {
        email: "",
        password: ""
    }
*/
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
                <div class="container-sm">
                        <Container style={{
                            backgroundColor: '#717C7150',
                            borderRadius: '5px',
                            width: '530px',
                             padding: '30px',
                             marginTop: '20px'
                            }}>
                            <a href='/'>
                                <img src={logo} width="50%" height="50%" alt='Fire & Risk Alliance' />
                            <br></br>
                            </a>
                            <br></br>
                            <form autoComplete="off" onSubmit={this.onSubmit}>

                                <div class="form-group row">
                                    <div class="col col-lg-3 col-form-label text-lg-right">
                                        <label> Username:</label>
                                    </div>
                                    <div class="col-lg-8">
                                        <input 
                                          text="text-muted" 
                                          autofocus class="form-control form-element form-field" 
                                          hintText='Username' 
                                          name = "email" 
                                          hintText='Email'
                                          
                                          value = {this.state.email} onChange={this.handleInputChange}
                                          required
                                          />
                                    </div>
                                </div>
                            </form>
                            <form autoComplete="off" onSubmit={this.onSubmit}>
                                      <div class="form-group row">
                                          <div class="col col-lg-3 col-form-label text-lg-right">
                                            <label >Password:</label>
                                          </div>
                                          <div class= "col-lg-8">
                                            <input
                                            class="form-control form-element form-field" 
                                            type='password'
                                            name = "password" 
                                            hintText='Password'
                                            text="text-muted" 
                                            value = {this.state.password} 
                                            onChange={this.handleInputChange}
                                            required
                                            />
                                        </div>  
                                  </div>
                              </form>
                            <div className="form-group row form-actions">
                            <div class="offset-lg-3 col-lg-8 mt-2">
                              <input type="submit" value = "Submit" className="form-element form button btn-block btn-dark" />
                          </div>
                          </div>

                        <div class="offset-lg-3 col-lg-8">
                        <Link to= '/forgotpassword' style={{color: 'black'}}> Forgot Password</Link> 
                        </div>
                        <div class="offset-lg-3 col-lg-8">
                        <Link to= '/register' style={{color: 'black'}}> Register for account</Link>  
                        </div>
                        
                    </Container>
                </div>   
            </React.Fragment>
        </MuiThemeProvider>

        );
    }
}

export default Login


