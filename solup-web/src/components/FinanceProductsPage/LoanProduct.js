import React from 'react';

function LoanProduct({ product }) {
  const handleOpenApp = () => {
    const appScheme = 'com.shinhan.sbanking';
    const appLink = `intent://${appScheme}/#Intent;scheme=${appScheme};action=android.intent.action.VIEW;package=${appScheme};end`;
    window.location.href = appLink;
  };

  return (
    <div>
      <h2>{product.fin_prdt_nm}</h2>
      <p>은행 이름: {product.kor_co_nm}</p>
      <p>가입 방법: {product.join_way}</p>
      <p>대출 상품 유형: {product.crdt_prdt_type_nm}</p>
      <hr />
      <button onClick={handleOpenApp}>앱 열기</button>
    </div>
  );
}

export default LoanProduct;
