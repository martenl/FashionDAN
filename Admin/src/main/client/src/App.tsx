import React from 'react';
import logo from './logo.svg';
import { Hello } from './features/hello/Hello';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { StartCampaign } from './features/startCampaign/StartCampaign';
import { CreateCampaign } from './features/createCampaign/CreateCampaign';
import { CampaignDetails } from './features/campaignDetails/CampaignDetails';
import { Login } from './features/login/Login';

import { BrowserRouter as Router, Switch, Route, Link, Redirect  } from 'react-router-dom';
import { LinkContainer } from 'react-router-bootstrap';
import Button from 'react-bootstrap/Button';
import ListGroup from 'react-bootstrap/ListGroup';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar'
import NavDropdown from 'react-bootstrap/NavDropdown';
import Form from 'react-bootstrap/Form';
import FormControl from 'react-bootstrap/FormControl';

function App() {
  return (
        <Router>
      <div className="App">
        <header >
          {/*<img src={logo} className="App-logo" alt="logo" />*/}
<Navbar bg="light" expand="lg">
  <Navbar.Brand href="#home">Fashion DAN</Navbar.Brand>
  <Navbar.Toggle aria-controls="basic-navbar-nav" />
  <Navbar.Collapse id="basic-navbar-nav">
    <Nav className="mr-auto">
      <LinkContainer to="/login"><Nav.Link>Home</Nav.Link></LinkContainer>
      <LinkContainer to="/hello"><Nav.Link >Hello</Nav.Link></LinkContainer>
      <NavDropdown title="Dropdown" id="basic-nav-dropdown">
        <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
        <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
        <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
        <NavDropdown.Divider />
        <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
      </NavDropdown>
    </Nav>
    <Form inline>
      <FormControl type="text" placeholder="Search" className="mr-sm-2" />
      <Button variant="outline-success">Search</Button>
    </Form>
  </Navbar.Collapse><Navbar.Collapse className="justify-content-end">
                        <Navbar.Text>
                          Signed in as: <a href="#login">Mark Otto</a>
                        </Navbar.Text>
                      </Navbar.Collapse>
</Navbar>
          <ListGroup horizontal>
            <LinkContainer to="/login"><ListGroup.Item>Login</ListGroup.Item></LinkContainer>
            <LinkContainer to="/logout"><ListGroup.Item>Logout</ListGroup.Item></LinkContainer>
            <LinkContainer to="/hello"><ListGroup.Item>Hello</ListGroup.Item></LinkContainer>
            <LinkContainer to="/createCampaign"><ListGroup.Item>Create campaign</ListGroup.Item></LinkContainer>
            <LinkContainer to="/startCampaign"><ListGroup.Item>Start campaign</ListGroup.Item></LinkContainer>
          </ListGroup>
        </header>
      </div>
      <Switch>
        <Route path='/login'>
          <Login/>
        </Route>
        <Route path='/logout'>
          <Redirect to={{pathname: '/login'}}/>
        </Route>
        <Route path='/hello'>
          <Hello/>
        </Route>
        <Route path='/startCampaign'>
          <StartCampaign/>
        </Route>
        <Route path='/createCampaign'>
          <CreateCampaign/>
        </Route>
      </Switch>
        </Router>
    );
}

export default App;
