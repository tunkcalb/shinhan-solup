import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import { useSelector } from "react-redux";

function EmployeeEnrollment() {
    const navigate = useNavigate();
    const userId = useSelector((state) => state.userId);
    const [formData, setFormData] = useState({
      name: "",
      bank: "신한",
      account: "",
      hourlyRate: 0,
      workDay: 0,
      workHour: 0,
      payDay: 0,
      salary: 0,
    });
  
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        let updatedFormData = { ...formData, [name]: value };
    
        if (name === "hourlyRate" || name === "workHour" || name === "workDay") {
          updatedFormData.salary = updatedFormData.hourlyRate * updatedFormData.workHour * updatedFormData.workDay;
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
      <h2>직원 등록 페이지</h2>
      <form onSubmit={handleSubmit}>
        <label>
          이름:
          <input type="text" name="name" value={formData.name} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          하루 근무 시간:
          <input type="number" name="workHour" value={formData.workHour} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          한달 근무 일수:
          <input type="number" name="workDay" value={formData.workDay} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          시급:
          <input type="number" name="hourlyRate" value={formData.hourlyRate} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          월급:
          <input type="number" name="salary" value={formData.salary} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          월급일:
          <input type="number" name="payDay" value={formData.payDay} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          은행:
          <select name="bank" value={formData.bank} onChange={handleInputChange}>
            <option value="신한">신한</option>
            <option value="싸피">싸피</option>
            <option value="쏠쏠">쏠쏠</option>
          </select>
        </label>
        <br />
        <label>
          계좌번호:
          <input type="text" name="account" value={formData.account} onChange={handleInputChange} />
        </label>
        <br />
        <button type="submit">등록하기</button>
      </form>
    </div>
  );
}

export default EmployeeEnrollment;
