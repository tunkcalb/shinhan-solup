import React from "react";

function LoanProduct({ product }) {
  const handleOpenApp = () => {
    const appScheme = 'com.shinhan.sbanking';
    const appLink = `market://details?id=${appScheme}`;
    window.location.href = appLink;
  };

  return (
    <div className="savingProduct" onClick={handleOpenApp}>
      <p className="itemTitle">{product.fin_prdt_nm}</p>
      <div className="itemContent">
        <p>은행 이름: {product.kor_co_nm}</p>
        <p>가입 방법: {product.join_way}</p>
      </div>
    </div>
  );
}

export default LoanProduct;