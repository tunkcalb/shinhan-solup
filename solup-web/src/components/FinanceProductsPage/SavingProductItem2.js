import React from "react";
import style from "./SavingProduct.module.css";

function SavingsProductItem2() {
  return (
    <div className={style.savingProduct}>
      <h2>사업자성공기원적금(자유적립식)</h2>
      <p>금액과 저축일을 자유롭게 저축하고 싶은 사업자</p>
      <p>한도 : 월 1천만원 이내</p>
    </div>
  );
}

export default SavingsProductItem2;
