import React from "react";
import { useNavigate } from "react-router";
import "./WhiteButton.css";

function WhiteButton ({title, destination}) {
  const navigate = useNavigate();
  const handleNextClick = () => {
    if (destination) {
      navigate(destination);
    }
  };
  return (
    <div className="btnContainer">
        <button onClick={handleNextClick} className="whiteBtn">{title}</button>
    </div>
  );
};

export default WhiteButton;