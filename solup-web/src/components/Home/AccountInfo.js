import React, { useState } from 'react';

function AccountInfo() {
  // 계좌 정보가 등록 여부 확인
  const [isAccountRegistered, setIsAccountRegistered] = useState(false);

  // 계좌 정보 (등록된 경우에만 표시)
  const accountData = {
    bankName: '신한은행',
    accountNumber: '123-456-7890',
    accountBalance: '1,000,000원',
  };

  // 계좌 등록 페이지로 이동하는 함수
  const redirectToAccountRegistration = () => {
    // 계좌 등록 페이지로 이동하는 코드
  };

  return (
    <div>
      <h2>사업자 계좌 정보</h2>
      {isAccountRegistered ? (
        <div>
          <p>은행명: {accountData.bankName}</p>
          <p>계좌번호: {accountData.accountNumber}</p>
          <p>계좌 잔액: {accountData.accountBalance}</p>
        </div>
      ) : (
        <div>
          <p>아직 거래내역이 없네요!</p>
          <p>영업 계좌 정보를 등록해주세요</p>
          <button onClick={redirectToAccountRegistration}>계좌 등록하기</button>
        </div>
      )}
    </div>
  );
}

export default AccountInfo;
