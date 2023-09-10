import React from 'react';
import AccountInfo from '../components/Home/AccountInfo.js'
import ProfitStatus from '../components/Home/ProfitStatus.js'
import NavBar from '../components/Footer.js';
import "./Home.css"

function Home() {
  return (
    <div className="homeContainer">
      <div className='homeLogo'>
        <img src={`${process.env.PUBLIC_URL}/solup-logo-blue.png`} alt="쏠업로고"/>
      </div>
      <div className='homeContent'>
        {/* 가게 정보, 사용자 이름 받아와서 적용되어야 함 */}
        <div className='homeText'>
          <span>신한커피 </span>
          <span className='boldText'>김싸피 </span>
          <span>사장님</span>
        </div>
        <AccountInfo />
        <ProfitStatus />
      </div>
        <NavBar />
    </div>
  );
}

export default Home;
