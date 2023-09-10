import React, { useState } from 'react';
import './AccountInfo.css';
import MiniBtn from '../MiniBtn';

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
    setIsAccountRegistered(true);
  };

  return (
    <div className='infoContainer'>
      <div className='infoTitle'>사업자 계좌 정보</div>
      {isAccountRegistered ? (
        <div>
          <p>은행명: {accountData.bankName}</p>
          <p>계좌번호: {accountData.accountNumber}</p>
          <p>계좌 잔액: {accountData.accountBalance}</p>
        </div>
      ) : (
        <div className='noAccount'>
          <div>아직 거래내역이 없네요!</div>
          <div>영업 계좌 정보를 등록해주세요</div>
          <MiniBtn text="등록하기" onClick={redirectToAccountRegistration} />
        </div>
      )}
    </div>
  );
}

export default AccountInfo;
