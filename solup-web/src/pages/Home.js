import React from 'react';
import AccountInfo from '../components/Home/AccountInfo.js'
import ProfitStatus from '../components/Home/ProfitStatus.js'
import ServiceMenu from '../components/Home/ServiceMenu.js'
import UserInfo from '../components/UserInfo.js'
import AccountNumber from '../components/AccountNumber.js'
function Home() {
  return (
    <div className="home-container">
      <UserInfo />
      <AccountNumber />
      <AccountInfo />
      <ProfitStatus />
      <ServiceMenu />
    </div>
  );
}

export default Home;
