import React, { Component } from 'react'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
// import AppBar from 'material-ui/AppBar'
import TextField from 'material-ui/TextField'
import RaisedButton from 'material-ui/RaisedButton'
import { Link } from 'react-router-dom'
import {Container} from 'react-bootstrap'
import logo from './images/logo.png'

class CreatePassword extends Component {
    constructor(props) {
        super(props)
        this.state = {
          password1 : '',
          password2 : ''
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
            alert('That email address is not valid.');
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

                              <form autoComplete="off" onSubmit={this.handleSubmit}>
                                      <div class="form-group row">
                                          <div class="col col-lg-3 col-form-label text-lg-right">
                                            <label>Password:</label>
                                          </div>
                                          <div class= "col-lg-8">
                                            <input
                                            class="form-control form-element form-field" 
                                            type='password'
                                            name = "password1" 
                                            hintText='Password1'
                                            text="text-muted" 
                                            value = {this.state.password1} 
                                            onChange={this.handleInputChange}
                                            />
                                        </div>  
                                  </div>
                              </form>
                              <form autoComplete="off" onSubmit={this.handleSubmit}>
                                      <div class="form-group row">
                                          <div class="col col-lg-3 col-form-label text-lg-right">
                                            <label>Verify:</label>
                                          </div>
                                          <div class= "col-lg-8">
                                            <input
                                            class="form-control form-element form-field" 
                                            type='password'
                                            name = "password2" 
                                            hintText='Password2'
                                            text="text-muted" 
                                            value = {this.state.password2} 
                                            onChange={this.handleInputChange}
                                            />
                                        </div>  
                                  </div>
                              </form>
                              <div className="form-group row form-actions">
                              <div class="offset-lg-3 col-lg-8 mt-2">
                                <input type="submit" value = "Submit" className="form-element form button btn-block btn-dark" />
                            </div>
                            </div>
                          
                      </Container>
                  </div>   
              </React.Fragment>
          </MuiThemeProvider>
  
          );
      }
}
export default CreatePassword