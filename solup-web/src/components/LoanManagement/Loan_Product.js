import React from "react";

function Loan_Product({ product }) {
  const handleOpenApp = () => {
    const appScheme = "com.shinhan.sbanking";
    const appLink = `intent://${appScheme}/#Intent;scheme=${appScheme};action=android.intent.action.VIEW;package=${appScheme};end`;
    window.location.href = appLink;
  };

  return (
    <div className='products'>
      <p className='pd-name'>{product.fin_prdt_nm}</p>
      <div>
        <div className='pd-content'>
          <b>은행명</b><p>{product.kor_co_nm}</p>
        </div>
        <div className='pd-content'>
          <b>가입</b><p>{product.join_way}</p>
        </div>
        <div className='pd-content'>
          <b>상품유형</b><p>{product.crdt_prdt_type_nm}</p>
        </div>
      </div>
      <button onClick={handleOpenApp} className='blueBtn'>쏠에서 확인하기</button>
    </div>
  );
}

export default Loan_Product;
