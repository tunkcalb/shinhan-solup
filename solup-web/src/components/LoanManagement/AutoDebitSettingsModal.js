import React, { useState } from "react";

function AutoDebitSettingsModal({ initialPaymentAmount, closeModal, onPaymentAmountChange }) {
  const [paymentAmount, setPaymentAmount] = useState(initialPaymentAmount);

  const handleSave = () => {
    onPaymentAmountChange(paymentAmount);
    closeModal();
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>자동이체 설정</h2>
        <p>현재 납입할 금액: {initialPaymentAmount} 원</p>
        
        <input
          type="number"
          value={paymentAmount}
          onChange={(e) => setPaymentAmount(e.target.value)}
        />

        <button onClick={handleSave}>저장</button>
        
        <button onClick={closeModal}>닫기</button>
      </div>
    </div>
  );
}

export default AutoDebitSettingsModal;
