import React, { useState } from 'react';
import { Route, Routes } from 'react-router-dom';

import Home from './pages/Home.js';
import InitialPage from './pages/InitialPage.js';
import Login from './pages/Login.js'
import Signup from './pages/Signup.js';
import CustomProducts from './pages/CustomProducts.js';
import EmployeeManagement from './pages/EmployeeManagement.js';
import MyLoans from './pages/MyLoans.js';
import SalesAnalysis from './pages/SalesAnalysis.js';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);

  const loginHandler = () => {
    setIsLoggedIn(true);
  };

  const logoutHandler = () => {
    setIsLoggedIn(false);
  };

  return (
    <div>
      {isLoggedIn ? (
        <>
          <Routes>
            <Route path="/home" element={<Home onLogout={logoutHandler} />} />
            <Route path="/" element={<InitialPage />} />
            <Route path="/custom-products" element={<CustomProducts />} />
            <Route path="/employee-management" element={<EmployeeManagement />} />
            <Route path="/my-loans" element={<MyLoans />} />
            <Route path="/sales-analysis" element={<SalesAnalysis />} />
          </Routes>
        </>
      ) : (
        <Routes>
          <Route path="/home" element={<Home loginHandler={loginHandler} />} />
          <Route path="/" element={<InitialPage />} />
          <Route
            path="/login"
            element={<Login loginHandler={loginHandler} />}
          />
          <Route path="/signup" element={<Signup />} />
        </Routes>
      )}
    </div>
  );
}

export default App;
