import React, { useState } from "react";
// import { useHistory } from "react-router-dom";
import AutoDebitSettingsModal from "./AutoDebitSettingsModal"; 


// function formatDate(dateString){
//   const options = {day:'numeric'};
//   return new Date(dateString).toLocaleDateString(undefined,options);
// }

function AutoDebit({ accountNumber }) {
  const [paymentAmount, setPaymentAmount] = useState(1200000); 

  const [isModalOpen, setIsModalOpen] = useState(false); 

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const handlePaymentAmountChange = (newPaymentAmount) => {
    setPaymentAmount(newPaymentAmount);
  };

  const parsedAccountNumber = `${accountNumber.substring(0, 3)}-${accountNumber.substring(3, 6)}-${accountNumber.substring(6, 12)}`;
  return (
    <div className="loans">
      <div className="debitTitle">대출금 자동 이체</div>
      <p className="debitText">사업자 계좌에서 가게 대출 상환도 빠르게!</p>
      <div className="debitBox">
        <div className="debitContent">
          <p className="status-mini">대출계좌</p>
          <p>{parsedAccountNumber}</p>
        </div>
        <div className="debitContent">
          <p className="status-mini">납입금액</p>
          <p>{new Intl.NumberFormat().format(paymentAmount)} 원</p>
        </div>
        <button onClick={isModalOpen ? closeModal : openModal} className="blueBtn">자동이체 설정</button>  
        {isModalOpen && (
          <AutoDebitSettingsModal
            initialPaymentAmount={paymentAmount}
            closeModal={closeModal}
            onPaymentAmountChange={handlePaymentAmountChange}
          />
        )}
      </div>
      
    </div>
  );
}

export default AutoDebit;
