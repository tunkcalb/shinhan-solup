import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import AutoDebitSettingsModal from "./AutoDebitSettingsModal"; 

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

  return (
    <div>
      <h2>대출금 자동 이체</h2>
      <p>대출 계좌번호: {accountNumber}</p>
      <p>납입할 금액: {paymentAmount} 원</p>
      
      <button onClick={openModal}>자동이체 설정</button>

      {isModalOpen && (
        <AutoDebitSettingsModal
          initialPaymentAmount={paymentAmount}
          closeModal={closeModal}
          onPaymentAmountChange={handlePaymentAmountChange}
        />
      )}
    </div>
  );
}

export default AutoDebit;
