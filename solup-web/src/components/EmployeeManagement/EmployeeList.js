import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

function EmployeeList() {
  const [employees, setEmployees] = useState([]);
  const userId = useSelector((state) => state.userId)

  useEffect(() => {
    axios.get(`/user/staff/${userId}`)
      .then((response) => {
        setEmployees(response.data.data);
      })
      .catch((error) => {
        console.error("Error fetching employee list:", error);
      });
  }, []);

  return (
    <div>
      <h2>직원 리스트</h2>
      <ul>
        {employees.map((employee) => (
          <li key={employee.id}>
            {employee.name}
            <Link to={`/employee/${employee.id}`}>상세보기</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default EmployeeList;
