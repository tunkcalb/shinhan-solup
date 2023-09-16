import React from "react";
import style from "./LoanProduct.module.css";

function LoanProduct({ product }) {
  const handleOpenApp = () => {
    const appScheme = 'com.shinhan.sbanking';
    const appLink = `market://details?id=${appScheme}`;
    window.location.href = appLink;
  };

  return (
    <div className={style.loanProduct} onClick={handleOpenApp}>
      <h2>{product.fin_prdt_nm}</h2>
      <p>은행 이름: {product.kor_co_nm}</p>
      <p>가입 방법: {product.join_way}</p>
      {/* <p>대출 상품 유형: {product.crdt_prdt_type_nm}</p> */}
    </div>
  );
}

export default LoanProduct;