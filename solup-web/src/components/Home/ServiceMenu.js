import React from 'react';
import { useHistory } from 'react-router-dom';
import { useNavigate } from "react-router-dom";

function ServiceMenu() {

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
