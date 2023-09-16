import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router";
import { useSelector } from "react-redux";

function Employee() {
  const { employeeId } = useParams();
  const [employee, setEmployee] = useState(null);
  const userId = useSelector((state) => state.userId);

  const handleDeleteEmployee = () => {
  // 삭제 요청을 보내고 성공 시 페이지를 리디렉션하거나 다른 작업을 수행할 수 있습니다.
    axios.delete(`/user/staff/${employeeId}`)
      .then((response) => {
      // 삭제가 성공하면 리디렉션 또는 다른 작업을 수행할 수 있습니다.
      console.log("직원 정보 삭제 완료");
      })
      .catch((error) => {
        console.error("Error deleting employee:", error);
      });
  };

  useEffect(() => {
    // /user/staff/{userId}에 GET 요청을 보내 직원의 상세 정보를 가져옵니다.
    axios.get(`/user/staff/${userId}`)
      .then((response) => {
        const selectedEmployee = response.data.data.find((e) => e.id === parseInt(employeeId, 10));
        setEmployee(selectedEmployee);
      })
      .catch((error) => {
        console.error("Error fetching employee details:", error);
      });
  }, [employeeId]);

  if (!employee) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>직원 정보</h2>
      <p>이름: {employee.name}</p>
      <p>은행: {employee.bank}</p>
      <p>계좌번호: {employee.account}</p>
      <p>월급: {employee.salary}</p>
    </div>
  );
}

export default Employee;
