import React from 'react';
import AccountInfo from '../components/Home/AccountInfo.js'
import ProfitStatus from '../components/Home/ProfitStatus.js'
import ServiceMenu from '../components/Home/ServiceMenu.js'
import NavBar from '../components/Footer.js';
import "./Home.css"
import { useSelector } from 'react-redux'
import UserInfo from '../components/UserInfo.js';
function Home() {
  const userId = useSelector((state) => state.userId);
  const userName = useSelector((state) => state.userName);
  console.log(userId)
  console.log(userName)
  const isLoggedIn = useSelector((state) => state.isLoggedIn);
  console.log(isLoggedIn)
  return (
    <div className="homeContainer">
      <div className='homeLogo'>
        <img src={`${process.env.PUBLIC_URL}/solup-logo-blue.png`} alt="쏠업로고"/>
      </div>
      <div className='homeContent'>
        <UserInfo />
        <AccountInfo />
        <ProfitStatus />
        <ServiceMenu />
        <NavBar />
      </div>
    </div>
  );
}

export default Home;
