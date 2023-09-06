import React from 'react';
import { useHistory } from 'react-router-dom';
import { useNavigate } from "react-router-dom";

function ServiceMenu() {
  // const history = useHistory();

  // // 매출분석 페이지로 이동하는 함수
  // const goToSalesAnalysis = () => {
  //   history.push('/sales-analysis');
  // };

  // // 직원관리 페이지로 이동하는 함수
  // const goToEmployeeManagement = () => {
  //   history.push('/employee-management');
  // };

  // // 나의 대출 페이지로 이동하는 함수
  // const goToMyLoans = () => {
  //   history.push('/my-loans');
  // };

  // // 맞춤 상품 페이지로 이동하는 함수
  // const goToCustomProducts = () => {
  //   history.push('/custom-products');
  // };
  const navigate = useNavigate();

  return (
    <div>
      <h2>간편 메뉴</h2>
      <button onClick={() => {
        navigate("/sales-analysis")
      }}>매출분석</button>
      <button onClick={() => {
        navigate("/employee-management")
      }}>직원관리</button>
      <button onClick={() => {
        navigate("/my-loans")
      }}>나의 대출</button>
      <button onClick={() => {
        navigate("/custom-products")
      }}>맞춤 상품</button>
    </div>
  );
}

export default ServiceMenu;
