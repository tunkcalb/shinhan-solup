import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import { useNavigate } from "react-router";
import EmployeeList from "../../components/EmployeeManagement/EmployeeList";

function EmployeeManagement() {
  const userId = useSelector((state) => state.userId);
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  
  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await axios.get(`/user/staff/${userId}`);
        const data = response.data.data;
        setEmployees(data);
        setLoading(false);
      } catch (error) {
        console.error("직원 데이터를 불러오는 중 오류 발생: ", error);
        setLoading(false);
      }
    };

    fetchEmployees();
  }, [userId]);

  return (
    <div>
      <h2>직원 관리 페이지.</h2>
      {loading ? (
        <p>데이터를 불러오는 중...</p>
      ) : employees.length === 0 ? (
        <button onClick={() => navigate('/employee-enrollment')}>직원 등록</button>
      ) : (
        <div>
          <EmployeeList />
          <button onClick={() => navigate('/employee-enrollment')}>직원 추가하기</button>
        </div>
      )}
    </div>
  );
}

export default EmployeeManagement;
