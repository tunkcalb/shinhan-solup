import React from 'react';
import AccountInfo from '../components/Home/AccountInfo.js'
import ProfitStatus from '../components/Home/ProfitStatus.js'
import ServiceMenu from '../components/Home/ServiceMenu.js'
import NavBar from '../components/Footer.js';

function Home() {
  return (
    <div className="home-container">
      <h3>신한커피 김싸피 사장님</h3>
        <AccountInfo />
        <ProfitStatus />
        <ServiceMenu />
        <NavBar />
    </div>
  );
}

export default Home;
