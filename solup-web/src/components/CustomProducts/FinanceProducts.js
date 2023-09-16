import React from "react";
import "./FinanceProducts.css";

function FinanceProducts() {
  return (
    <div className="productContainer" style={{ textAlign: "left" }}>
      <h2>금융상품 찾기</h2>
      <>
        <p className="p" style={{ width: "100%" }}>
          소상공인 대상 대출 상품부터
        </p>
        <p className="p">알뜰살뜰 모으는 적금 상품까지</p>
        <p className="p">쏠업에서 한 눈에!</p>
      </>
    </div>
  );
}

export default FinanceProducts;
