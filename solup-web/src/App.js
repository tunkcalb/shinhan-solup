import React, { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import { store, persistor } from './redux/store';
import { useSelector } from 'react-redux';
import { Navigate, useNavigate } from 'react-router-dom';

import Home from './pages/Home.js';
import InitialPage from './pages/InitialPage.js';
import Login from './pages/Login.js';
import Signup from './pages/Signup.js';
import CustomProducts from './pages/CustomProducts/CustomProducts.js';
import EmployeeManagement from './pages/EmployeeManagement/EmployeeManagement';
import MyLoans from './pages/MyLoans.js';
import SalesAnalysis from './pages/SalesAnalysis.js';
import Verification from './pages/Verification.js';
import Start from './pages/NewCustomer/Start';
import ProfitStatusPage from './pages/MarginSettlement';
import TradeHistory from './pages/TradeHistory';
import FinanceProductsPage from './pages/CustomProducts/FinanceProductsPage';
import AccountQuestion from './pages/NewCustomer/AccountQuestion';
import AuthAccount from './pages/NewCustomer/AuthAccount';
import RegisterStore from './pages/NewCustomer/RegisterStore';
import ReadyToSolup from './pages/NewCustomer/ReadyToSolup';
import AccountRegistration from './pages/NewCustomer/AccountRegistration';
import Guide from './components/Home/Guide';
import SupportProgramsPage from './pages/CustomProducts/SupportProgramsPage';
import EmployeeEnrollment from './pages/EmployeeManagement/EmployeeEnrollment';
import EmployeeInfo from './pages/EmployeeManagement/EmployeeInfo';
import LoanManagement from './pages/LoanManagement';
import RevenueAnalysisView from './components/RevenueAnalysis/RevenueAnalysisView';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const loginHandler = () => {
    setIsLoggedIn(true);
  };

  const logoutHandler = () => {
    setIsLoggedIn(false);
  };

  return (
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <div className="App">
          <Routes>
            {isLoggedIn ? (
              <Route path="/" element={<Home onLogout={logoutHandler} />} />
            ) : (
              <Route path="/" element={<InitialPage />} />
            )}
            <Route path="/home" element={<Home onLogout={logoutHandler} />} />
            <Route path="/login" element={<Login loginHandler={loginHandler} />} />
            <Route path="/verification" element={<Verification />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/custom-products" element={<CustomProducts />} />
            <Route path="/employee-management" element={<EmployeeManagement />} />
            <Route path="/employee-enrollment" element={<EmployeeEnrollment />} />
            <Route path="/employee/:employeeId" element={<EmployeeInfo />} />
            <Route path="/my-loans" element={<MyLoans />} />
            <Route path="/sales-analysis" element={<SalesAnalysis />} />
            <Route path="/start" element={<Start />} />
            <Route path="/margin-settlement" element={<ProfitStatusPage />} />
            <Route path="/trade-history" element={<TradeHistory />} />
            <Route path="/finance-products" element={<FinanceProductsPage />} />
            <Route path='/account-question' element={<AccountQuestion />} />
            <Route path='/auth-account' element={<AuthAccount />} />
            <Route path='/register-store' element={<RegisterStore />} />
            <Route path='/ready-to-solup' element={<ReadyToSolup />} />
            <Route path='/account-register' element={<AccountRegistration />} />
            <Route path='/revenue-analysis-view' element={<RevenueAnalysisView />} />
            <Route path='/support-programs' element={<SupportProgramsPage />} />
            <Route path='/guide' element={<Guide />} />
            <Route path='/loan-management' element={<LoanManagement />} />
          </Routes>
        </div>
      </PersistGate>
    </Provider>
  );
}

export default App;
