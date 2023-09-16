import React from "react";
import "./FinanceProducts.css";

function FinanceProducts() {
  return (
    <div className="productContainer">
      <div className="productContent">
        <p className="customTitle">금융상품 찾기</p>
          <p>소상공인 대상 대출 상품부터</p>
          <p>알뜰살뜰 모으는 적금 상품까지</p>
          <p>쏠업에서 한 눈에!👀</p>
          <img src={`${process.env.PUBLIC_URL}/customImg2.png`} alt="상품" className="custom2"/>
      </div>
    </div>
  );
}

export default FinanceProducts;
