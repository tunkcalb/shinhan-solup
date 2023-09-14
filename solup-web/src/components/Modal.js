import React from "react";

function Modal({isOpen, onClose, children}) {
  if (!isOpen) return null;

  return(
    <div className="modalContainer">
      <div className="modalContent">
        <span className="close" onClick={onClose}>&times;</span>
        {children}

      </div>
    </div>
  );
};

export default Modal;