/*
 * 
 * The homepage component will load for '/'
 * Login page will load for '/login' need to connect the two -- Austin
 * 
 * some colors from the FRS logo tan: '#E2D5B7', red: '#B53033', dark red: '#551515'
 * 
 * 
 * 
 */


import React from 'react';

// import { Route, BrowserRouter as Router, Switch} from 'react-router-dom'
import { Link, Route, Switch } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css"
import Login from './components/Login'
import Homepage from './components/Homepage'
import Developers from './components/Developers/Developers'
import Test from './components/Test'
import Header from './components/Header'
import Register from './components/Register'
import Logout from './components/Logout'
import ForgotPassword from './components/ForgotPassword'
import CreatePassword from './components/CreatePassword'
import Secret from './components/Secret';
import withAuth from './components/withAuth';
import DualSprinkler from './components/DualSprinkler'
import './App.css';



function App() {
  return (
    <div className="App">
      <div className="container">

          
            {/* <Header/> */}
            <Switch>

              <Route path='/' exact component={Homepage}/>
              <Route path='/login' component={Login}/>
              <Route path='/developers' component={Developers}/>
              <Route path='/test' component={Test}/>
              <Route path='/register' component={Register}/>
              <Route path='/forgotpassword' component={ForgotPassword}/>
              <Route path='/createpassword' component={CreatePassword}/>
              <Route path='/logout' component={withAuth(Logout)}/>
              <Route path='/header' component={Header}/>
              <Route path="/secret" component={withAuth(Secret)} />  
              <Route path='/dualsprinkler' component={DualSprinkler}/>            

            </Switch>
           
      </div>
    </div>
  );
}

export default App;
