import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';

import Home from 'solup-web/src/pages/Home.js';
import Login from 'C:/Users/SSAFY/Desktop/solup-web/src/pages/Login.js';
import Header from 'C:/Users/SSAFY/Desktop/solup-web/src/components/Header.js';
import logoImage from 'C:/Users/SSAFY/Desktop/solup-web/src/assets/solup logo-blue.png';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    <div>
    <Header logo={logoImage} />
    <Router>
      <Switch>
        <Route path="/login">
          {isLoggedIn ? <Redirect to="/" /> : <Login onLogin={handleLogin} />}
        </Route>
        <Route path="/">
          {isLoggedIn ? (
            <Home onLogout={handleLogout} />
            ) : (
              <Redirect to="/login" />
              )}
        </Route>
      </Switch>
    </Router>
    </div>
  );
}

export default App;
