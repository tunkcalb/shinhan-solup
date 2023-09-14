import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router';
import axios from 'axios';
import { setUserId, setIsAccountRegistered } from '../../redux/actions'; // 액션 생성자 임포트

import "../styles/Start.css";
import "../styles/Verification.css";
import BlueButton from '../../components/BlueButton';

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
    <div className="startContainer">
      <div className="startContent">
        <div className='registerText'>
          <div className="mainText">계좌 인증하기</div>
          <div className="subText">신한은행 계좌로 1원을 입금하여</div>
          <div className="subText">숫자 4자리로 계좌를 인증해주세요</div>

        </div>
        {/* 구분 */}
        <form onSubmit={handleRegisterAccount}>
          <div className="inputForm">
            <label htmlFor="accountNumber" className="inputTitle">계좌번호</label>
            <input
              type="text"
              id="accountNumber"
              value={accountNumber}
              onChange={(e) => setAccountNumber(e.target.value)}
              required
              className="inputContent"
            />
            <button onClick={handleTransferOneWon} className='whiteBtn'>1원 송금하기</button>
          </div>


          <div className="inputForm">
            <label htmlFor="verificationCode" className="inputTitle">인증코드</label>
            <input
              type="text"
              id="verificationCode"
              value={verificationCode}
              onChange={(e) => setVerificationCode(e.target.value)}
              required
              className="inputContent"
            />
          </div>

          <div className='registerBtn'>
            {/* 인증 버튼 클릭 시 인증 완료 페이지로*/}
            <button type="submit" className='blueBtn'>인증하기</button>
          </div>
        </form>
      </div>

    </div>
  );
}

export default AccountRegistration;
