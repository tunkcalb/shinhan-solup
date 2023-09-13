import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import axios from 'axios';
import { setUserId, setIsAccountRegistered } from '../redux/actions'; // 액션 생성자 임포트
import { useNavigate } from 'react-router';

function AccountRegistration() {
  const userId = useSelector((state) => state.userId); // Redux 스토어에서 userId 가져오기
  const dispatch = useDispatch(); // dispatch 함수를 가져옵니다.
  const [accountNumber, setAccountNumber] = useState('');
  const [verificationCode, setVerificationCode] = useState('');
  const navigate = useNavigate();

  const handleRegisterAccount = async (e) => {
    e.preventDefault();

    try {
      if (verificationCode !== 'SOLUP') {
        alert('올바른 인증 코드를 입력하세요.');
        return;
      }
      await axios.post(`/user/account/${userId}`, {
        accountNumber: accountNumber,
      });
      console.log(verificationCode)

      dispatch(setIsAccountRegistered(true));
      alert('계좌 등록이 완료되었습니다.');
      navigate('/home');
    } catch (error) {
      console.error('API 요청 실패:', error);
      alert('계좌 등록 중 오류가 발생했습니다.');
    }
  };

  const handleTransferOneWon = async (e) => {
    e.preventDefault();
    try {
      // API 호출: 1원 송금
      await axios.post(`/account/check/${userId}`, {
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
