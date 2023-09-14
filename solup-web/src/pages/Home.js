import React from 'react';
import { useSelector } from 'react-redux'
import AccountInfo from '../components/Home/AccountInfo.js'
import ProfitStatus from '../components/Home/ProfitStatus.js'
import NavBar from '../components/Footer.js';
import UserInfo from '../components/UserInfo.js';
import "./styles/Home.css"


function Home() {
  const userId = useSelector((state) => state.userId);
  const userName = useSelector((state) => state.userName);
  const isLoggedIn = useSelector((state) => state.isLoggedIn);
  const isAccountRegistered = useSelector((state) => state.isAccountRegistered);
  console.log("userId: " + userId)
  console.log("name: " + userName)
  console.log("Login: " + isLoggedIn)
  console.log("account register: " + isAccountRegistered)
  return (
    <div>
      <div className='homeLogo'>
        <img src={`${process.env.PUBLIC_URL}/solup-logo-blue.png`} alt="쏠업로고"/>
      </div>
      <div className='homeContent'>
        <UserInfo />
        <AccountInfo />
        <ProfitStatus />
      </div>
      <NavBar />
    </div>
  );
}

export default Home;
