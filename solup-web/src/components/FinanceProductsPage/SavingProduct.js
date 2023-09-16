import React from "react";
import "./SavingProduct.css";

function SavingsProduct({ product }) {
  return (
    <div className="savingProduct">
      <p className="itemTitle">{product.fin_prdt_nm}</p>
      <div className="itemContent">
        <p>은행 이름: {product.kor_co_nm}</p>
        <p>가입 방법: {product.join_way}</p>
      </div>
    </div>
  );
}

export default SavingsProduct;
