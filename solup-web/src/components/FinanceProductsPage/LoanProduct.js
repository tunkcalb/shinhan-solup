import React from 'react';

function LoanProduct({ product }) {
  const handleApplyLoan = () => {
    const solAppPackage = 'com.shinhan.sbanking';

    // Android
    if (navigator.userAgent.match(/Android/i)) {
      window.location.href = `market://details?id=${solAppPackage}`;
    }
    
    // 모바일 브라우저
    else {
      const playStoreUrl = `https://play.google.com/store/apps/details?id=${solAppPackage}`;
      window.open(playStoreUrl, '_blank');
      console.log('다른 플랫폼 또는 데스크톱 브라우저에서 열림');
    }
  };

  return (
    <div>
      <h2>{product.fin_prdt_nm}</h2>
      <p>은행 이름: {product.kor_co_nm}</p>
      <p>가입 방법: {product.join_way}</p>
      <p>대출 상품 유형: {product.crdt_prdt_type_nm}</p>
      <hr />
      <button onClick={handleApplyLoan}>대출 신청하기</button>
    </div>
  );
}

export default LoanProduct;
