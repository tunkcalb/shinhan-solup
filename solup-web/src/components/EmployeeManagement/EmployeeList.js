import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router";
import Modal from "../Modal";

function EmployeeList() {
  const [employees, setEmployees] = useState([]);
  const userId = useSelector((state) => state.userId)
  const navigate = useNavigate();

  // 모달 열기/닫기 상태를 관리
  const [isModalOpen, setIsModalOpen] = useState(false);

  // 이체 버튼 클릭 시 이벤트 핸들러
  const handleTransferButtonClick = () => {
    setIsModalOpen(true);
  };

  // 모달 닫기 이벤트 핸들러
  const handleCloseModal = () => {
    setIsModalOpen(false);
  };


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
    <div className="employeeContainer">
      <div className="userTitle">직원 리스트</div>
      <ul>
        {employees.map((employee) => (
          <li key={employee.id}  className="employeeCard">
            <img src={`${process.env.PUBLIC_URL}/shfriends4.png`} alt="직원 이미지" />
            <div className="cardLine"></div>
            <div className="cardContent">
              <ul>
                <li className="emName">{employee.name}</li>
                <li>{employee.bank} {employee.account}</li>
              </ul>
              <div onClick={handleTransferButtonClick} className="cardDetail">급여관리</div>
              <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
                {/* 모달 내용 */}
                <div className='modalPart'>
                  <div className='modalText'>
                    <div>이체 금액 원</div>
                    <div>계좌로 이체 완료!</div>
                  </div>
                  <div className='modalImg'>
                    <img src={`${process.env.PUBLIC_URL}/shfriends4.png`} alt="완료이미지" />
                  </div>
                </div>
              </Modal>
              {/* <Link to={`/employee/${employee.id}`} className="cardDetail" >급여관리</Link> */}
              </div>
          </li>
        ))}
        <div onClick={handleTransferButtonClick} className="employeeCard">
          <div className="emPlus">직원 추가하기</div> 
        </div>

      </ul>    
    </div>
  ); 
}

export default EmployeeList;
