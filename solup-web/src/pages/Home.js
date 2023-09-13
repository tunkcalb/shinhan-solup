import React from 'react';
import AccountInfo from '../components/Home/AccountInfo.js'
import ProfitStatus from '../components/Home/ProfitStatus.js'
import NavBar from '../components/Footer.js';
import "./styles/Home.css"
import { useSelector } from 'react-redux'
import UserInfo from '../components/UserInfo.js';
function Home() {
  const userId = useSelector((state) => state.userId);
  const userName = useSelector((state) => state.userName);
  const isLoggedIn = useSelector((state) => state.isLoggedIn);
  const isAccountRegistered = useSelector((state) => state.isAccountRegistered);
  console.log(userId)
  console.log(userName)
  console.log(isLoggedIn)
  console.log(isAccountRegistered)
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
