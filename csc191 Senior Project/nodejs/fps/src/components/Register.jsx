import React, { Component } from 'react'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import TextField from 'material-ui/TextField'
import RaisedButton from 'material-ui/RaisedButton'
import { Link } from 'react-router-dom'
import {Container} from 'react-bootstrap'
import logo from './images/logo.png'
import './Header.css'


import { EditorFormatAlignCenter } from 'material-ui/svg-icons'
const styles = {
    center: {
      marginLeft: "auto",
      marginRight: "auto"
    }
}
export default class Register extends Component {
  constructor(props){
    super(props);

    this.onChangeFname = this.onChangeFname.bind(this);
    this.onChangeLname = this.onChangeLname.bind(this);
    this.onChangeCompany = this.onChangeCompany.bind(this);
    this.onChangeCountry = this.onChangeCountry.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangeConfirmemail = this.onChangeConfirmemail.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.onChangeConfirmPassword = this.onChangeConfirmPassword.bind(this);
    this.onSubmit = this.onSubmit.bind(this);

    this.state = {
      fname: "",
      lname: "",
      company: "",
      country: "",
      email: "",
      confirmemail:"",
      password: "",
      confirmpassword:""
    }
  }

  onChangeFname(e) {
    this.setState({
        fname:e.target.value
    });
  }
  onChangeLname(e) {
    this.setState({
      lname:e.target.value
    });
  }
  onChangeCompany(e){
    this.setState({
      company:e.target.value
    });
  }
  onChangeCountry(e){
    this.setState({
        country:e.target.value
    });
  }
  onChangeEmail(e){
    this.setState({
        email:e.target.value
    });
  }
  onChangeConfirmemail(e){
    this.setState({
        confirmemail:e.target.value
    });
  }
  onChangePassword(e){
    this.setState({
        password:e.target.value
    });
  }
  onChangeConfirmPassword(e){
    this.setState({
        confirmpassword:e.target.value
    });
  }
    /**
    onSubmit(e){
        e.preventDefault();
       
        const register = {
            fname: this.state.fname,
            lname: this.state.lname,
            company: this.state.company,
            country: this.state.country,
            email: this.state.email,
            confirmpassword: this.state.confirmpassword,
            confirmemail: this.state.confirmemail,
            password: this.state.password
        }
        
        window.location ='/';
        */

        // Duplicate?
       /*
       onSubmit = (event) => {
        event.preventDefault();
        fetch('/api/register', {
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
    }*/

  onSubmit = (event) => {
      event.preventDefault();
      fetch('/api/register', {
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

  render() {
    return (
      <MuiThemeProvider>
        <React.Fragment>
          <div class="container">
            {/* <div class= "rgbackground"> */}
              <form>
                <div class="container-sm">
                  <br></br>
                  <form onSubmit={this.onSubmit}>
                    <form>
                      <Container style={{
                          backgroundColor: '#717C7150',
                          borderRadius: '5px',
                          width: '600px',
                          padding: '30px',
                          position: 'center'}}>
                        <a href='/'>
                            <img src={logo} width= "40%" height="40%" alt='Fire & Risk Alliance' />
                        </a>
                        <br></br>
                        <br></br>
                        <div className="form-group row">
                          <div class="col-md-4 col-form-label text-md-right"> 
                              <label style={{color: 'black'}}> First Name: </label>
                          </div>
                          <div class="col-md-7">
                            <input 
                              required
                              autofocus className="form-control form-element form-field "
                              placeholder="First Name"
                              text="text-muted"
                              value={this.state.fname}
                              onChange={this.onChangeFname}
                            />
                          </div>
                          <div class="col-md-4 col-form-label text-md-right">
                            <label style={{color: 'black'}}>Last Name:</label>
                          </div>
                          <div class="col-md-7">
                            <input type ="text"
                              required
                              autofocus className="form-control form-element form-field"
                              placeholder="Last Name"
                              text="text-muted"
                              value={this.state.lname}
                              onChange={this.onChangeLname}
                            />
                          </div>
                          <div class="col-md-4 col-form-label text-md-right">
                            <label style={{color: 'black'}}>Company Name:</label>
                          </div>
                          <div class="col-md-7">
                            <input type ="text"
                              required
                              autofocus className="form-control form-element form-field"
                              Small select
                              placeholder="Company Name"
                              text="text-muted"
                              value={this.state.company}
                              onChange={this.onChangeCompany}
                            />
                          </div>
                          <div class="col-md-4 col-form-label text-md-right">
                            <label style={{color: 'black'}}>Country:</label>
                          </div>
                          <div class="col-md-7">
                            <input type ="text"
                                required
                                autofocus className="form-control form-element form-field"
                                placeholder="Country"
                                text="text-muted"
                                value={this.state.country}
                                onChange={this.onChangeCountry}
                            />
                          </div>
                          <div class="col-md-4 col-form-label text-md-right">
                            <label style={{color: 'black'}}>Email:</label>
                          </div>
                          <div class="col-md-7">
                            <input type ="email"
                                required
                                autofocus className="form-control form-element form-field"
                                placeholder="Email Address"
                                text="text-muted"
                                value={this.state.email}
                                onChange={this.onChangeEmail}
                            />
                          </div>
                          <div class="col-md-4 col-form-label text-md-right">
                            <label style={{color: 'black'}}>Confirm Email:</label>
                          </div>
                          <div class="col-md-7">
                            <input type ="email"
                                required
                                autofocus className="form-control form-element form-field"
                                placeholder="Confirm Email Address"
                                text="text-muted"
                                value={this.state.confirmemail}
                              onChange={this.onChangeConfirmemail}
                            />
                          </div>
                          <div class="col-md-4 col-form-label text-md-right">
                            <label style={{color: 'black'}}>Password:</label>
                          </div>
                          <div class="col-md-7">
                            <input type ="password"
                              required
                              autofocus className="form-control form-element form-field"
                              placeholder="Password"
                              text="text-muted"
                              value={this.state.password}
                              onChange={this.onChangePassword}
                            />
                            <label style={{fontSize: '10px'}}>Minimum 8 letter with 1 Capital letter and 1 number</label>                            
                          </div>
                          <div class="col-md-4 col-form-label text-md-right">
                            <label style={{color: 'black'}}>Confirm Password:</label>
                          </div>
                          <div class="col-md-7">
                          <input type ="password"
                              required
                              autofocus className="form-control form-element form-field"
                              placeholder="Confirm Password"
                              text="text-muted"
                          />
                      </div>
                        </div>
                        <div className="form-group row form-actions">
                          <div class="offset-md-4 col-md-7">
                              <input type="submit" value = "Submit" className="form-element form button btn-block btn-dark" />
                          </div>
                        </div>
                      </Container>
                    </form>
                  </form>
                </div>
              </form>
            </div>    
          {/* </div> */}
        </React.Fragment>
      </MuiThemeProvider>
    );
    return <img src={logo} alt="logo" />;
    return<div className="Header"/>;
  }
}

