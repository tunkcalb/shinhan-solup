import React from "react";

function AutoDebit({ accountNumber, paymentAmount }) {
  return (
    <div>
      <h2>대출금 자동 이체</h2>
      <p>대출 계좌번호: {accountNumber}</p>
      <p>납입할 금액: {paymentAmount} 원</p>
      {/* 여기에 납입 버튼 또는 폼을 추가하세요 */}
    </div>
  );
}

export default AutoDebit;
