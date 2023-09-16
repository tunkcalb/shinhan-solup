import React from "react";

function Subsidies() {
  return (
    <div className="productContainer">
      <div className="productContent">
        <p className="customTitle">정부 보조금 찾기</p>
        <p>사장님께 꼭 필요한 정부 보조금,</p>
        <p>쏠업에서 다 모아 봤어요😉</p>
        <img src={`${process.env.PUBLIC_URL}/customImg1.png`} alt="보조금" className="custom1"/>
      </div>
    </div>
  );
}

export default Subsidies;
