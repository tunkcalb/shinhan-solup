import React from 'react';
import { useHistory } from 'react-router-dom';
import './Header.css'

function Header({ logoImage, title }) {
  const history = useHistory();

  const goBack = () => {
    history.goBack();
  };

  return (
    <div className="header">
      <button className="back-button" onClick={goBack}>
        뒤
      </button>
      <h1 className="page-title">{title}</h1>
      <img src={logoImage} alt="LogoImage" />
    </div>
  );
}

export default Header;