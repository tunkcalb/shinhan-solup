import React, { useState } from "react";
// import { useNavigate } from "react-router"; 큰일나면 수정한다
import "./Verification.css"
import Header from "../components/Header";
import BlueButton from "../components/BlueButton";
import WhiteButton from "../components/WhiteButton";

function Verification() {
// const navigate = useNavigate(); 큰일나면 수정한다

  // State to store form values
  const [formData, setFormData] = useState({
    name: "",
    phoneNumber: "",
    verificationCode: "",
  });

  // Function to handle form input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  // Function to handle sending the verification code
  const handleSendVerificationCode = () => {
    // Implement logic to send verification code here
  };

  // 큰일나면 수정한다
  // const handleNextClick = () => {
  //   navigate("/signup")
  // };

  return (
    <div>
      <Header title="회원가입" />
      <div className="container">
        <div className="subTitle">본인인증을 진행해주세요</div>
        <div className="inputForm">
          <label htmlFor="name" className="inputTitle">이름</label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleInputChange}
            className="inputContent"
          />
        </div>

        <div className="inputForm">
          <label htmlFor="phoneNumber" className="inputTitle">전화번호</label>
          <input
            type="text"
            id="phoneNumber"
            name="phoneNumber"
            value={formData.phoneNumber}
            onChange={handleInputChange}
            className="inputContent"
          />
          <WhiteButton title="인증번호 발송" destination="" />    
        </div>
        {/* <button onClick={handleSendVerificationCode}>인증번호 발송</button> */}

        <div className="inputForm">
          <label htmlFor="verificationCode" className="inputTitle">인증번호</label>
          <input
            type="text"
            id="verificationCode"
            name="verificationCode"
            value={formData.verificationCode}
            onChange={handleInputChange}
            className="inputContent"
          />
        </div>
        <BlueButton title="다음" destination="/signup" />
      </div>
    </div>
  );
}

export default Verification;
