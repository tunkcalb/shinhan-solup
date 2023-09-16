import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Header.css';

function Header({ title }) {
  const navigate = useNavigate();

  const handleGoBack = () => {
    navigate(-1);
  };

  return (
    <div className='header'>
      <div className='headerContainer'>
        <div onClick={handleGoBack}>
          <img src={`${process.env.PUBLIC_URL}/backBtn.png`} alt="뒤로가기버튼"/>
        </div>
        <span className='headerText'>{title}</span>
      </div>
    </div>
  );
}

export default Header;
