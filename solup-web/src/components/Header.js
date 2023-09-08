import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css'

function Header ({ title }) {
  return (
    <div className='header'>
      <div className='headerContainer'>
        <Link to="/"><img src={`${process.env.PUBLIC_URL}/backBtn.png`} alt="뒤로가기버튼"/></Link> 
        <span className='headerText'>{title}</span>
      </div>
    </div>
  );
};


export default Header;