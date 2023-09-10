import React, { useState } from 'react';
import { useDispatch } from 'react-redux'; // useDispatch 임포트
import { setIsLoggedIn, setUserId  } from '../redux/actions'; // 액션 임포트
import Header from '../components/Header';
import axios from 'axios';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();
  
  const handleLogin = async (e) => {
    e.preventDefault();
    // 로그인 API 호출
    try {
      const response = await axios.post('/user/login', {
        "username": username,
        "password": password
      });
      console.log(response.data)
      const responseData = response.data;

      if (responseData.code === '200') { // 로그인 성공
        const userId = responseData.data.id;
        // Redux를 사용하여 isLoggedIn 상태를 true로 변경
        dispatch(setIsLoggedIn(true));
        // Redux를 사용하여 userId 상태를 저장
        dispatch(setUserId(userId));

        // /home 페이지로 이동
        window.location.href = '/home'; // 현재 페이지를 새로 고침하면서 이동
      } else {
        alert('로그인 실패. 사용자 이름과 비밀번호를 확인하세요.');
      }
    } catch (error) {
      console.error('API 요청 실패:', error);
      alert('로그인 요청 중 오류가 발생했습니다.');
    }
  }

  return (
    <div className="login-container">
      <Header title="로그인" />
      <div className='container'>
        <form onSubmit={handleLogin} >
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
