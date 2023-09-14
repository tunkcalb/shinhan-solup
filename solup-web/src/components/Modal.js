import React from "react";
import "./Modal.css";

function Modal({isOpen, onClose, children}) {

  // 모달 밖 영역을 클릭할 때 모달을 닫는 이벤트 핸들러
  const handleBackgroundClick = (e) => {
    if (e.target === e.currentTarget) {
      onClose(); // 모달 밖을 클릭하면 모달 닫기
    }
  };

  if (!isOpen) return null;

  return(
    <div className={`modalContainer ${isOpen ? 'show' : ''}`}
    onClick={handleBackgroundClick}>
      <div className="modalContent">
        {children}
      </div>
    </div>
  );
};

export default Modal;