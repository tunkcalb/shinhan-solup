import React from 'react';
import { Link } from 'react-router-dom';

function InitialPage() {
  return (
    <div className="initial-page">
      <h2>환영합니다</h2>
      <p>로그인하거나 회원가입하세요.</p>
      <Link to="/login">
        <button>로그인</button>
      </Link>
      <Link to="/signup">
        <button>회원가입</button>
      </Link>
    </div>
  );
}

export default InitialPage;
