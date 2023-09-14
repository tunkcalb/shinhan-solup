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

  const [formData, setFormData] = useState({
    name: defaultName,
    username: "",
    password: "",
    confirmPassword: "",
    storename: "",
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
    if (formData.password !== formData.confirmPassword) {
      alert('비밀번호를 확인해주세요.');
    } else if (!formData.storename) {
      alert('상호명을 입력해주세요.');
    } else {
      try {
        const response = await axios.post('/user/signup', JSON.stringify(formData), {
          headers: {
            'Content-Type': 'application/json',
          },
        });
        if (response.data.code === '201') {
          dispatch(setIsLoggedIn(true));
          alert('회원가입이 완료되었습니다.');
          navigate('/');
        } else {
          alert('회원가입에 실패했습니다.');
        }
      } catch (error) {
        console.error('회원가입 오류:', error);
      }
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
            <label htmlFor="confirmPassword" className="inputTitle">비밀번호 확인</label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={handleInputChange}
              className="inputContent"
            />
          </div>

          <div className="inputForm">
            <label htmlFor="storename" className="inputTitle">상호명</label>
            <input
              type="text"
              id="storename"
              name="storename"
              value={formData.storename}
              onChange={handleInputChange}
              className="inputContent"
            />
          </div>
          
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
