import React from 'react';
import AccountInfo from '../components/Home/AccountInfo.js'
import ProfitStatus from '../components/Home/ProfitStatus.js'
import NavBar from '../components/Footer.js';
import "./Home.css"

function Home() {
  return (
    <div>
      <div className='homeLogo'>
        <img src={`${process.env.PUBLIC_URL}/solup-logo-blue.png`} alt="쏠업로고"/>
      </div>
      <div className='homeContent'>
        <AccountInfo />
        <ProfitStatus />
      </div>
      <NavBar />
    </div>
  );
}

export default Home;
