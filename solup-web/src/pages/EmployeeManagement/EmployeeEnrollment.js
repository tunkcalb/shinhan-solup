import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import { useSelector } from "react-redux";

import Header from "../../components/Header";
import NavBar from "../../components/Footer";
import InputPart from "../../components/InputPart";
import SelectPart from "../../components/SelectPart";

function EmployeeEnrollment() {
  const navigate = useNavigate();
  const userId = useSelector((state) => state.userId);
  const [formData, setFormData] = useState({
    name: "",
    bank: "신한",
    account: "",
    hourlyRate: 9860,
    workDay: 0,
    workHour: 0,
    payDay: 0,
    salary: 0,
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    let updatedFormData = { ...formData, [name]: value };

    if (name === "hourlyRate" || name === "workHour" || name === "workDay") {
      updatedFormData.salary =
        updatedFormData.hourlyRate *
        updatedFormData.workHour *
        updatedFormData.workDay;
    }

    setFormData(updatedFormData);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post(`/user/staff/${userId}`, formData)
      .then((response) => {
        navigate("/employee-management");
      })
      .catch((error) => {
        console.error("Error submitting employee data:", error);
      });
  };

  return (
    <div>
      <Header title="직원 등록" />
      <div className="enrollContainer">
        <div className="enrollImg">
          <img
            src={`${process.env.PUBLIC_URL}/employeeProfile.png`}
            alt="직원 이미지"
          />
        </div>
        <form onSubmit={handleSubmit} className="enrollForm">
          {/* 이름 */}
          <div className="partWrapper">
            <InputPart
              title="이름"
              type="text"
              name="name"
              value={formData.name}
              onChange={handleInputChange}
            />
          </div>
          {/* 근무시간 */}
          <div className="partWrapper">
            <InputPart
              title="일일 근무시간"
              type="number"
              unit="시간"
              name="workHour"
              value={formData.workHour}
              onChange={handleInputChange}
            />
          </div>
          {/* 근무일수 */}
          <div className="partWrapper">
            <InputPart
              title="한달 근무일수"
              type="number"
              unit="일"
              name="workDay"
              value={formData.workDay}
              onChange={handleInputChange}
            />
          </div>

          {/* 시급 */}
          <div className="partWrapper">
            <InputPart
              title="시급"
              type="number"
              unit="원"
              name="hourlyRate"
              value={formData.hourlyRate}
              onChange={handleInputChange}
            />
          </div>

          {/* 월급 */}
          <div className="partWrapper">
            <InputPart
              title="월급"
              type="number"
              unit="원"
              name="salary"
              value={formData.salary}
              onChange={handleInputChange}
            />
          </div>

          {/* 월급날짜 */}
          <div className="partWrapper">
            <InputPart
              title="월급날짜"
              type="number"
              unit="일"
              name="payDay"
              value={formData.payDay}
              onChange={handleInputChange}
            />
          </div>

          {/* 은행명 */}
          <div className="partWrapper">
            <SelectPart
              title="은행명"
              options={["신한", "쏠업", "땡겨"]}
              name="bank"
              value={formData.bank}
              onChange={handleInputChange}
            />
          </div>

          {/* 계좌번호 */}
          <div className="partWrapper">
            <InputPart
              title="계좌번호"
              type="text"
              name="account"
              value={formData.account}
              onChange={handleInputChange}
            />
          </div>

          <div className="enrollBtnContainer">
            <button type="submit" className="blueBtn" onClick={handleSubmit}>
              등록하기
            </button>
          </div>
        </form>
      </div>
      <NavBar />
    </div>
  );
}

export default EmployeeEnrollment;
