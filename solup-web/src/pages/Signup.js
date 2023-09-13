import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from 'axios';
import { useDispatch } from "react-redux";
import { setIsLoggedIn } from "../redux/actions";
import Header from "../components/Header";
import "./styles/Signup.css"
import "./styles/Verification.css"

function Signup() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const defaultName = queryParams.get('name') || "";
  const defaultPhoneNumber = queryParams.get('phoneNumber') || "";

  // 상호명 업데이트 必
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    name: defaultName,
    phoneNumber: defaultPhoneNumber,
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleNextClick = async () => {
    try {
      // 회원가입 요청 보내기
      const response = await axios.post('/user/signup', JSON.stringify(formData), {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      if (response.data.code === '201') { // 회원가입 성공 여부를 적절히 확인해주세요.
        // 회원가입 성공 시 Redux를 사용하여 isLoggedIn 값을 true로 변경
        dispatch(setIsLoggedIn(true));
        alert('회원가입이 완료되었습니다.');
        navigate('/');
      } else {
        alert('회원가입에 실패했습니다.');
      }
    } catch (error) {
      console.error('회원가입 오류:', error);
    }
  };
  
  return (
    <div>
      <Header title="회원가입" />
      <div className="container">
        <div className="contents">
          <div className="inputForm">
            <label htmlFor="username" className="inputTitle">아이디</label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleInputChange}
              className="inputContent"
            />
            <button onClick={handleNextClick} className="whiteBtn">아이디 중복확인</button>
          </div>
          
          <div className="inputForm">
            <label htmlFor="password" className="inputTitle">비밀번호</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleInputChange}
              className="inputContent"
            />
          </div>

          <div className="inputForm">
            <label htmlFor="password" className="inputTitle">비밀번호 확인</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleInputChange}
              className="inputContent"
            />
          </div>

          {/* 상호명 인풋 수정 必 */}
          <div className="inputForm">
            <label htmlFor="storename" className="inputTitle">상호명</label>
            <input
              type="text"
              id="storename"
              name="storename"
              value={formData.password}
              onChange={handleInputChange}
              className="inputContent"
            />
          </div>
          
          {/* 여기 업체명 적는 inputForm */}

          <div className="signupBtnContainer">
            <div className="btnContainer">
              <button onClick={handleNextClick} className="blueBtn">회원가입 완료</button>
            </div>
          </div>

        </div>

      </div>
    </div>
  );
}

export default Signup;
