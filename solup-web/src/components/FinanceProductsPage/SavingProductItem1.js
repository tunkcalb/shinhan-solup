import React from "react";
import style from "./SavingProduct.module.css";

function SavingsProductItem1() {
  return (
    <div className={style.savingProduct}>
      <h2>신한 가맹점</h2>
      <p>사업기간 1년 이상 개인 사업자</p>
      <p>한도 : 일 5만원/월 100만원</p>
    </div>
  );
}

export default SavingsProductItem1;
