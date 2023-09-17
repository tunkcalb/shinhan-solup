import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Header from "../components/Header";
import BlueButton from "../components/BlueButton";
import "./styles/Verification.css";

function Verification() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: "",
    phoneNumber: "",
    verificationCode: "",
  });
  const [destination, setDestination] = useState("");

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSendVerificationCode = async () => {
    try {
      const response = await axios.post("/user/sms", {
        recipientPhoneNumber: formData.phoneNumber,
      });

      if (response.data.code === "200") {
        alert("인증번호를 전송했습니다.");
      } else {
        alert("인증번호 전송에 실패했습니다.");
      }
    } catch (error) {
      console.error("인증번호 전송 오류:", error);
    }
  };

  // const handleNextButtonClick = () => {
  //   navigate(
  //     `/signup?name=${formData.name}&phoneNumber=${formData.phoneNumber}`
  //   );
  // };

  const updateDestination = () => {
    if (
      formData.name === "" ||
      formData.phoneNumber === "" ||
      formData.verificationCode === ""
    ) {
      alert("모든 항목을 채워주세요");
      setDestination("/verification");
    } else {
      setDestination(
        `/signup?name=${formData.name}&phoneNumber=${formData.phoneNumber}`
      );
      navigate(
        `/signup?name=${formData.name}&phoneNumber=${formData.phoneNumber}`
      );
    }
  };

  return (
    <div>
      <Header title="회원가입" />
      <div className="container">
        <div className="contents">
          <div className="subTitle">본인인증을 진행해주세요</div>
          <div className="inputForm">
            <label htmlFor="name" className="inputTitle">
              이름
            </label>
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
            <label htmlFor="phoneNumber" className="inputTitle">
              전화번호
            </label>
            <input
              type="text"
              id="phoneNumber"
              name="phoneNumber"
              value={formData.phoneNumber}
              onChange={handleInputChange}
              className="inputContent"
            />
            <button onClick={handleSendVerificationCode} className="whiteBtn">
              인증번호 발송
            </button>
          </div>

          <div className="inputForm">
            <label htmlFor="verificationCode" className="inputTitle">
              인증번호
            </label>
            <input
              type="text"
              id="verificationCode"
              name="verificationCode"
              value={formData.verificationCode}
              onChange={handleInputChange}
              className="inputContent"
            />
          </div>

          <div className="verificationBtnContainer" onClick={updateDestination}>
            <BlueButton title="다음" destination={destination} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Verification;
