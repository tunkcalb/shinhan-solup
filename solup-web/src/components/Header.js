import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css'

function Header ({ title }) {
  return (
    <div className='header'>
      <div className='headerContent'>
        <Link to="/"><img src={`${process.env.PUBLIC_URL}/backBtn.png`} alt="뒤로가기버튼"/></Link>
        <div className='headerText'>{title}</div>
      </div>
    </div>
  );
};


export default Header;