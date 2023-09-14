import React from 'react';

function LoanProduct({ product }) {
  const handleApplyLoan = () => {
    const solAppPackage = 'com.shinhan.sbanking';
    const playStoreUrl = `https://play.google.com/store/apps/details?id=${solAppPackage}`;
    window.open(playStoreUrl, '_blank');
  };

  return (
    <div>
      <h2>{product.fin_prdt_nm}</h2>
      <p>은행 이름: {product.kor_co_nm}</p>
      <p>가입 방법: {product.join_way}</p>
      <p>대출 상품 유형: {product.crdt_prdt_type_nm}</p>
      <hr />
      <a href="https://play.google.com/store/apps/details?id=com.shinhan.sbanking">대출 신청하기</a>
    </div>
  );
}

export default LoanProduct;
