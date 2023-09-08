import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../assets/solup_logo_blue.png'; // Import the image

function InitialPage() {
  return (
    <div className="initial-page">
      <p>SOL로 하는 손 쉬운 영업, 쏠업</p>
      <img src={logo} alt="SOLUP Logo" />
      <div>
        <Link to="/login">
          <button>로그인</button>
        </Link>
        <Link to="/verification">
          <button>회원가입</button>
        </Link>
      </div>
    </div>
  );
}

export default InitialPage;
