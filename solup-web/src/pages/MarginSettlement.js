import React, { useState } from 'react';
import { useNavigate } from 'react-router';

function ProfitStatusPage() {
  const navigate = useNavigate();
  // 이체금액 변수를 상태로 관리
  const [transferAmount, setTransferAmount] = useState(0);

  // 마진 상태 변수
  const [margin, setMargin] = useState(100000);

  // 계좌번호와 선택한 은행을 상태로 관리
  const [accountNumber, setAccountNumber] = useState('');
  const [selectedBank, setSelectedBank] = useState('');

  // 퍼센트 입력 상태를 관리
  const [percentage, setPercentage] = useState(0);

  // 퍼센트 입력 시 이벤트 핸들러
  const handlePercentageChange = (event) => {
    const newPercentage = parseFloat(event.target.value);
    setPercentage(newPercentage);
    calculateTransferAmount(newPercentage);
  };

  // 이체 금액을 계산하는 함수
  const calculateTransferAmount = (percentage) => {
    const calculatedAmount = (margin * percentage) / 100;
    setTransferAmount(calculatedAmount);
  };

  // 계좌번호 입력 시 이벤트 핸들러
  const handleAccountNumberChange = (event) => {
    const newAccountNumber = event.target.value;
    setAccountNumber(newAccountNumber);
  };

  // 은행 선택 시 이벤트 핸들러
  const handleBankSelect = (event) => {
    const newBank = event.target.value;
    setSelectedBank(newBank);
  };

  // 이체 버튼 클릭 시 이벤트 핸들러
  const handleTransferButtonClick = () => {
    alert(`이체 금액 ${transferAmount}원이 ${selectedBank} 계좌로 이체되었습니다.`);
    navigate('/home');
  };

  return (
    <div>
      <p>이번달 마진은 {margin} 원이에요</p>

      <p>쏠업에서 생활비 계좌로 바로 이체하세요</p>
      <p>마진에 따라 이체 금액도 추천해드릴게요!</p>
      <input type="number" value={percentage} onChange={handlePercentageChange} />
      <span>%</span>
      <p>이체금액: {transferAmount}원</p>
      <p>입금할 계좌 정보를 입력해주세요</p>
      <p>계좌번호</p>
      <input type="text" value={accountNumber} onChange={handleAccountNumberChange} />
      <select value={selectedBank} onChange={handleBankSelect}>
        <option value="신한은행">신한은행</option>
        <option value="쏠업은행">쏠업은행</option>
        <option value="땡겨은행">땡겨은행</option>
      </select>
      <div>
          <button onClick={handleTransferButtonClick}>이체하기</button>
      </div>
    </div>
  );
}

export default ProfitStatusPage;
