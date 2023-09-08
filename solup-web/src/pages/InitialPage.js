import React from 'react';
import { Link } from 'react-router-dom';

import Header from '../components/Header';

function InitialPage() {
  return (
    <div className="initial-page">
      <Header title="헤더 테스트" />
      <h2>환영합니다</h2>
      <p>로그인하거나 회원가입하세요.</p>
      <Link to="/home">
        <button>로그인</button>
      </Link>
      <Link to="/signup">
        <button>회원가입</button>
      </Link>
    </div>
  );
}

export default InitialPage;
