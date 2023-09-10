import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { setIsLoggedIn } from '../redux/actions';
import axios from 'axios';
import Header from '../components/Header';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('/user/login', {
        "username": username,
        "password": password
      });

      const responseData = response.data;

      if (responseData.message === '로그인 성공') {
        dispatch(setIsLoggedIn(true));
        window.location.href = '/home';
      } else {
        alert('로그인 실패. 사용자 이름과 비밀번호를 확인하세요.');
      }
    } catch (error) {
      console.error('API 요청 실패:', error);
      alert('로그인 요청 중 오류가 발생했습니다.');
    }
  };

  return (
    <div className="login-container">
      <Header title="로그인" />
      <div className='container'>
        <form onSubmit={handleLogin}>
          <div className="inputForm">
            <label htmlFor="username" className="inputTitle">아이디</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              className="inputContent"
            />
          </div>
          <div className="inputForm">
            <label htmlFor="password" className="inputTitle">비밀번호</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="inputContent"
            />
          </div>
          <button type="submit" className='blueBtn'>로그인</button>
        </form>
      </div>
    </div>
  );
}

export default Login;
