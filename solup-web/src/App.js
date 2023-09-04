import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';

import Home from './pages/Home.js';
import InitialPage from './pages/InitialPage.js';
import Header from './components/Header.js';
import logoImage from './assets/solup-logo-blue.png';

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
    <Header logoImage={logoImage} />
    <Router>
      <Switch>
        <Route path="/login">
          {isLoggedIn ? <Redirect to="/" /> : <InitialPage onLogin={handleLogin} />}
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
