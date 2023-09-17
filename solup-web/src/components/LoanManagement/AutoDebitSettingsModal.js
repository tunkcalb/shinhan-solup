import React, { useState } from "react";

function AutoDebitSettingsModal({ initialPaymentAmount, closeModal, onPaymentAmountChange }) {
  const [paymentAmount, setPaymentAmount] = useState(initialPaymentAmount);

  const handleSave = () => {
    onPaymentAmountChange(paymentAmount);
    closeModal();
  };

  return (
    <div className="debitModal">
      <div className="debitModalContent">
        <p>현재 납입할 금액</p>
        <p className="debitModalText">{new Intl.NumberFormat().format(initialPaymentAmount)} 원</p>
        
        <div className="debitModalInput">
          <input
            type="number"
            value={paymentAmount}
            onChange={(e) => setPaymentAmount(e.target.value)}
          />
          <div onClick={handleSave} className="debitSave">저장</div>
        </div>
        
      </div>
    </div>
  );
}

export default AutoDebitSettingsModal;
