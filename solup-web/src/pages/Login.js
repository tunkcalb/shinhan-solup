import React, { useState } from 'react';
import { useDispatch } from 'react-redux'; // useDispatch 임포트
import { setIsLoggedIn } from '../redux/actions'; // 액션 임포트

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch(); // useDispatch 훅 사용

  const handleLogin = (e) => {
    e.preventDefault(); // 폼 제출 기본 동작 방지

    // 로그인 로직을 구현하고 상태를 업데이트합니다.
    // 여기에서는 예제로 사용자 이름과 비밀번호를 확인하는 것으로 가정합니다.
    if (username === 'admin' && password === 'admin') {
      // Redux를 사용하여 isLoggedIn 상태를 true로 변경
      dispatch(setIsLoggedIn(true));
      // /home 페이지로 이동
      window.location.href = '/home'; // 현재 페이지를 새로고침하면서 이동
    } else {
      alert('로그인 실패. 사용자 이름과 비밀번호를 확인하세요.');
    }
  };

  return (
    <div className="login-container">
      <h2>로그인</h2>
      <form onSubmit={handleLogin}>
        <div className="form-group">
          <label htmlFor="username">사용자 이름</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">비밀번호</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">로그인</button>
      </form>
    </div>
  );
}

export default Login;
