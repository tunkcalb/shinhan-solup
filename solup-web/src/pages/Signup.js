import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // react-router-dom의 useNavigate 사용
import { useDispatch, useSelector } from "react-redux"; // useDispatch 사용
import { setIsLoggedIn } from "../redux/actions"; // 액션 임포트
import "./Verification.css";
import "./Signup.css"
import Header from "../components/Header";

function Signup() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const isLoggedIn = useSelector((state) => state.isLoggedIn); // Redux 스토어의 isLoggedIn 상태를 읽음

  // State to store form values
  const [formData, setFormData] = useState({
    name: "",
    phoneNumber: "",
    verificationCode: "",
    username: "", // 아이디 입력란
    password: "", // 비밀번호 입력란
    confirmPassword: "", // 비밀번호 확인 입력란
  });

  // Function to handle form input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  // Function to handle the "Next" button click
  const handleNextClick = () => {
    // 여기에서 아이디 중복 확인 로직을 수행할 수 있습니다.
    // 이 예제에서는 그냥 다음 페이지로 이동합니다.
    // Redux를 사용하여 isLoggedIn 값을 true로 변경
    dispatch(setIsLoggedIn(true));

    // 초기 페이지로 이동
    navigate('/');
  };

  return (
    <div>
      <Header title="회원가입" />
      <div className="container">
        {/* 아이디 입력란 */}
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
        <button onClick={handleNextClick} className="blueBtn">회원가입 완료</button>
      </div>
    </div>
  );
}

export default Signup;
