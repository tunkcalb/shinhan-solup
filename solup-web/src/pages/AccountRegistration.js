import React, { useState } from 'react';
import axios from 'axios';

function AccountRegistration() {
  const [accountNumber, setAccountNumber] = useState('');
  const [verificationCode, setVerificationCode] = useState('');
  const [isAccountRegistered, setIsAccountRegistered] = useState(false);

  const handleRegisterAccount = async (e) => {
    e.preventDefault();

    try {
      // 인증 코드 확인 (인증 코드가 'SOLUP'인지 확인)
      if (verificationCode !== 'SOLUP') {
        alert('올바른 인증 코드를 입력하세요.');
        return;
      }

      // API 호출: 계좌 등록
      await axios.post('/user/account/{userId}', {
        accountNumber: accountNumber,
      });

      // 계좌 등록 완료
      setIsAccountRegistered(true);
      alert('계좌 등록이 완료되었습니다.');
    } catch (error) {
      console.error('API 요청 실패:', error);
      alert('계좌 등록 중 오류가 발생했습니다.');
    }
  };

  const handleTransferOneWon = async () => {
    try {
      // API 호출: 1원 송금
      await axios.post('/account/check/{userId}', {
        accountNumber: accountNumber,
      });

      // 1원 송금 완료
      alert('1원 송금이 완료되었습니다.');
    } catch (error) {
      console.error('API 요청 실패:', error);
      alert('1원 송금 중 오류가 발생했습니다.');
    }
  };

  return (
    <div className="account-registration-container">
      <h2>계좌 등록</h2>
      <form onSubmit={handleRegisterAccount}>
        <div className="inputForm">
          <label htmlFor="accountNumber">계좌번호</label>
          <input
            type="text"
            id="accountNumber"
            value={accountNumber}
            onChange={(e) => setAccountNumber(e.target.value)}
            required
          />
        </div>
        <div>
            <button onClick={handleTransferOneWon}>1원 송금하기</button>
        </div>
        <div className="inputForm">
          <label htmlFor="verificationCode">인증코드</label>
          <input
            type="text"
            id="verificationCode"
            value={verificationCode}
            onChange={(e) => setVerificationCode(e.target.value)}
            required
          />
        </div>
        <button type="submit">계좌 등록</button>
      </form>

    </div>
  );
}

export default AccountRegistration;
