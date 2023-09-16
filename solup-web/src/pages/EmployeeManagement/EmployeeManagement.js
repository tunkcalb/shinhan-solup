import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import { useNavigate } from "react-router";
import EmployeeList from "../../components/EmployeeManagement/EmployeeList";
import Header from "../../components/Header";
import NavBar from "../../components/Footer";
import "../styles/Employee.css";

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
    <div className="employees">
      <Header title="직원관리" />
      {loading ? (
        <p>데이터를 불러오는 중...</p>
      ) : employees.length === 0 ? (
        <div className="employeeContainer">
          <div className="userTitle">직원 관리</div>
          <div className="employeeContent">
            <p>등록한 직원이 없어요😯</p>
            <p>직원 등록 후 이용해주세요</p>
            <button 
              onClick={() => navigate('/employee-enrollment')}
              className="employeeBtn">직원 등록</button>
          </div>
        </div>
      ) : (
        <div>
          <EmployeeList />
        </div>
      )}
      <NavBar />
    </div>
  );
}

export default EmployeeManagement;
