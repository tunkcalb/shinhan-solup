import React from 'react';

function ServiceMenu() {
  // 매출분석 페이지로 이동하는 함수
  const goToSalesAnalysis = () => {
    
  };

  // 직원관리 페이지로 이동하는 함수
  const goToEmployeeManagement = () => {
    
  };

  // 나의 대출 페이지로 이동하는 함수
  const goToMyLoans = () => {
    
  };

  // 맞춤 상품 페이지로 이동하는 함수
  const goToCustomProducts = () => {
    
  };

  return (
    <div>
      <h2>간편 메뉴</h2>
      <button onClick={goToSalesAnalysis}>매출분석</button>
      <button onClick={goToEmployeeManagement}>직원관리</button>
      <button onClick={goToMyLoans}>나의 대출</button>
      <button onClick={goToCustomProducts}>맞춤 상품</button>
    </div>
  );
}

export default ServiceMenu;
