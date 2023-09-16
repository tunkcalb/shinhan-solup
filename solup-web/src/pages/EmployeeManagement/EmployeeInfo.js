import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { useSelector } from "react-redux";
import Loading from "../Loading";

function EmployeeInfo() {
  const { employeeId } = useParams();
  const [employee, setEmployee] = useState(null);
  const userId = useSelector((state) => state.userId);
  const navigate = useNavigate();

  useEffect(() => {
    // /user/staff/{userId}에 GET 요청을 보내 직원의 상세 정보를 가져옵니다.
    axios
      .get(`/user/staff/${userId}`)
      .then((response) => {
        const selectedEmployee = response.data.data.find(
          (e) => e.id === parseInt(employeeId, 10)
        );
        setEmployee(selectedEmployee);
      })
      .catch((error) => {
        console.error("Error fetching employee details:", error);
      });
  }, [employeeId]);

  if (!employee) {
    return <Loading />;
  }

  return (
    <div>
      <h2>직원 정보 페이지</h2>
      <p>이름: {employee.name}</p>
      <p>은행: {employee.bank}</p>
      <p>계좌번호: {employee.account}</p>
      <p>월급: {employee.salary}</p>
      <button onClick={() => navigate("/employee-management")}>
        직원 목록으로
      </button>
    </div>
  );
}

export default EmployeeInfo;
