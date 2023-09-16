import React, { useState } from 'react';
import { useNavigate } from 'react-router';
import { Link } from 'react-router-dom';
import "./styles/MarginSettlement.css"
import InputPart from '../components/InputPart';
import SelectPart from '../components/SelectPart';
import Modal from '../components/Modal';
import BlueButton from '../components/BlueButton';

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
  // const handleTransferButtonClick = () => {
  //   alert(`이체 금액 ${transferAmount}원이 ${selectedBank} 계좌로 이체되었습니다.`);
  //   navigate('/home');
  // }; //수틀리면 되돌리기 

  // 모달 열기/닫기 상태를 관리
  const [isModalOpen, setIsModalOpen] = useState(false);

  // 이체 버튼 클릭 시 이벤트 핸들러
  const handleTransferButtonClick = () => {
    setIsModalOpen(true);
  };

  // 모달 닫기 이벤트 핸들러
  const handleCloseModal = () => {
    setIsModalOpen(false);
  };



  return (
    <>
      <div className='blueContainer'>
        <Link to="/">
          <img src={`${process.env.PUBLIC_URL}/whiteBackBtn.png`} 
          alt="뒤로가기버튼"
          className='backBtn'/>
        </Link> 
        <span className='marginTitle'>마진 정산하기</span>
        <div className='marginContent'>
          <div>이번달 마진은</div>
          <div>
            <span className='marginBold'>{margin}</span>
            <span>원이에요</span>
          </div>
          <div className='miniFont'>
            <div>쏠업에서 생활비 계좌로 바로 이체하세요</div>
            <div>마진에 따라 이체 금액도 추천해드릴게요!</div>
          </div>
        </div>
      </div>

      <div className='settlementContainer'>
        {/* 마진 금액 */}
        <div className='partWrapper'>
          <InputPart
            title="마진의 몇 %를 이체할까요?"
            unit="%"
            type="number"
            value={percentage}
            onChange={handlePercentageChange}
          />
          <div className='summary'>이체금액: {transferAmount}원</div>
        </div>

        {/* 은행명 */}
        <div className='partWrapper'>
          <SelectPart
            title="입금할 은행명"
            options={["신한은행", "쏠업은행", "땡겨은행"]}
            value={selectedBank}
            onChange={handleBankSelect}
          />
        </div>
        {/* 계좌번호 */}
        <div className='partWrapper'>
          <InputPart
            title="계좌번호"
            type="number"
            value={accountNumber}
            onChange={handleAccountNumberChange}
          />
        </div>

        {/* 이체하기 */}
        <div className="marginBtnContainer">
          <button onClick={handleTransferButtonClick} className="blueBtn">이체하기</button>
        </div>

        <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
          {/* 모달 내용 */}
          <div className='modalPart'>
            <div className='modalText'>
              <div>이체 금액 {transferAmount}원</div>
              <div>{selectedBank} 계좌로 이체 완료!</div>
            </div>
            <div className='modalImg'>
              <img src={`${process.env.PUBLIC_URL}/moneySH.png`} alt="완료이미지" />
            </div>
            <div className='modalBtn'>
              <BlueButton title="확인하기" destination="/home"/>
            </div>
          </div>
        </Modal>

      </div>

    </>
  );
}

export default ProfitStatusPage;
