import React from 'react';
import "./styles/InitialPage.css"
import logo from '../assets/solup_logo_blue.png'; // Import the image
import BlueButton from '../components/BlueButton';
import WhiteButton from '../components/WhiteButton';

function InitialPage() {
  return (
    <div className="initialPage">
      <div>
        <div className='mainLogo'>
          <img src={logo} alt="SOLUP Logo" />
        </div>
        <div className='initText'>
          <span>SOL</span>
          <span className='initLight'>로 하는 손 쉬운 영업,</span>
          <span> 쏠업</span>
        </div>
        <div className='buttons'>
          <BlueButton title="로그인" destination="/login" />
          <WhiteButton title="회원가입" destination="/verification" />
        </div>
      </div>
    </div>
  );
}

export default InitialPage;
