import React from 'react';
import { useHistory } from 'react-router-dom';

function Header({ title }) {
  const history = useHistory();

  const goBack = () => {
    history.goBack();
  };

  return (
    <div className="header">
      <button className="back-button" onClick={goBack}>
        뒤로 가기
      </button>
      <h1 className="page-title">{title}</h1>
    </div>
  );
}

export default Header;