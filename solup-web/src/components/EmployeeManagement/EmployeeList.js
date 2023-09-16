import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import { useNavigate } from "react-router";
import Modal from "../Modal";

function EmployeeList() {
  const [employees, setEmployees] = useState([]);
  const userId = useSelector((state) => state.userId);
  const navigate = useNavigate();

  // 모달 열기/닫기 상태를 관리
  const [isModalOpen, setIsModalOpen] = useState(false);

  // 선택된 직원 정보를 관리
  const [selectedEmployee, setSelectedEmployee] = useState(null);

  // 직원별 이체하기 버튼 텍스트 상태를 관리
  const [transferButtonTexts, setTransferButtonTexts] = useState({});

  // 이체 버튼 클릭 시 이벤트 핸들러
  const handleTransferButtonClick = (employee) => {
    setSelectedEmployee(employee);
    setIsModalOpen(true);
  };

  // 모달 닫기 이벤트 핸들러
  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  // '보내기' 버튼 클릭 시 이벤트 핸들러
  const handleSendButtonClick = () => {
    if (selectedEmployee) {
      // 선택 직원의 이체 버튼 텍스트를 '급여 이체 완료'로 변경
      setTransferButtonTexts((prevButtonTexts) => ({
        ...prevButtonTexts,
        [selectedEmployee.id]: "급여 이체 완료",
      }));

      // 모달을 닫음
      setIsModalOpen(false);
    }
  };

  useEffect(() => {
    axios
      .get(`/user/staff/${userId}`)
      .then((response) => {
        setEmployees(response.data.data);
      })
      .catch((error) => {
        console.error("Error fetching employee list:", error);
      });
  }, [userId]); // userId가 변경될 때만 데이터를 다시 가져오도록 수정

  return (
    <div className="employeeContainer">
      <div className="userTitle">직원 리스트</div>
      <ul>
        {employees.map((employee) => (
          <div key={employee.id}>
            <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
              {/* 모달 내용 */}
              {selectedEmployee && selectedEmployee.id === employee.id && (
                <div className="modalPart">
                  <div className="modalText">
                    <div>{employee.name} 님께</div>
                    <div>월급 {employee.salary}원</div>
                    <div>이체하시겠습니까?</div>
                  </div>
                  <div className="modalImg">
                    <img src={`${process.env.PUBLIC_URL}/moneySH.png`} alt="완료이미지" />
                  </div>
                  <div className="modalBtn">
                    <div className="blueBtn" onClick={handleSendButtonClick}>보내기</div>
                  </div>
                </div>
              )}
            </Modal>

            <li key={employee.id} className="employeeCard">
              <img src={`${process.env.PUBLIC_URL}/shfriends4.png`} alt="직원 이미지" />
              <div className="cardLine"></div>
              <div className="cardContent">
                <ul>
                  <li className="emName">{employee.name}</li>
                  <li>
                    {employee.bank} {employee.account}
                  </li>
                </ul>
                <div
                  onClick={() => handleTransferButtonClick(employee)}
                  className="cardDetail"
                >
                  {transferButtonTexts[employee.id] || "급여 이체하기"}
                </div>
              </div>
            </li>
          </div>
        ))}
        <button onClick={() => navigate('/employee-enrollment')} className="employeeCard">
          <div className="emPlus">직원 추가하기</div>
        </button>
      </ul>
    </div>
  );
}

export default EmployeeList;
