import React, { useState } from "react";
import { useNavigate } from "react-router";

function Verification() {
const navigate = useNavigate();

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

  // Function to handle the "Next" button click
  const handleNextClick = () => {
    navigate("/signup")
  };

  return (
    <div>
      <p>본인인증을 진행해주세요</p>
      <div>
        <label htmlFor="name">이름</label>
        <input
          type="text"
          id="name"
          name="name"
          value={formData.name}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="phoneNumber">+82</label>
        <input
          type="text"
          id="phoneNumber"
          name="phoneNumber"
          value={formData.phoneNumber}
          onChange={handleInputChange}
        />
      </div>
      <button onClick={handleSendVerificationCode}>인증번호 발송</button>
      <div>
        <label htmlFor="verificationCode">인증번호</label>
        <input
          type="text"
          id="verificationCode"
          name="verificationCode"
          value={formData.verificationCode}
          onChange={handleInputChange}
        />
      </div>
      <button onClick={handleNextClick}>다음</button>
    </div>
  );
}

export default Verification;
