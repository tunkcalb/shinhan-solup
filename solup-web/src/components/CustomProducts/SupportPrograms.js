import React from "react";

function SupportPrograms() {
  return (
    <div className="productContainer">
      <div className="productContent">
        <p className="customTitle">지원 프로그램 찾기</p>
        <p>가게 영업에 어려움을 겪고 계신가요?</p>
        <p>성공적인 자립을 위한 프로그램을 찾아보세요</p>
        <img src={`${process.env.PUBLIC_URL}/customImg3.png`} alt="프로그램" className="custom3"/>
      </div>
    </div>
  );
}

export default SupportPrograms;
