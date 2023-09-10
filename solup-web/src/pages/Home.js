import React from 'react';
import AccountInfo from '../components/Home/AccountInfo.js'
import ProfitStatus from '../components/Home/ProfitStatus.js'
import ServiceMenu from '../components/Home/ServiceMenu.js'
import NavBar from '../components/Footer.js';
import "./Home.css"

function Home() {
  return (
    <div className="homeContainer">
      <div className='homeLogo'>
        <img src={`${process.env.PUBLIC_URL}/solup-logo-blue.png`} alt="쏠업로고"/>
      </div>
      <div className='homeContent'>
        <h3>신한커피 김싸피 사장님</h3>
          <AccountInfo />
          <ProfitStatus />
          <ServiceMenu />
          <NavBar />
      </div>
    </div>
  );
}

export default Home;
