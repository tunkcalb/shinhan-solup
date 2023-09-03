import React from 'react';

function Home({ onLogout }) {
  const handleLogout = () => {
    // 로그아웃 로직을 구현하고 필요한 상태를 업데이트하세요.
    onLogout();
  };

  return (
    <div className="home-container">
      <h2>홈 페이지</h2>
      <p>환영합니다! 이 페이지에서는 다양한 내용이나 기능을 제공할 수 있습니다.</p>
      <button onClick={handleLogout}>로그아웃</button>
    </div>
  );
}

export default Home;
