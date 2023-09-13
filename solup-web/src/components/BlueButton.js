import React from "react";
import { useNavigate } from "react-router";
import "./BlueButton.css";

function BlueButton ({title, destination}) {
  const navigate = useNavigate();
  const handleNextClick = () => {
    if (destination) {
      navigate(destination);
    }
  };
  return (
    <div className="btnContainer">
      <button onClick={handleNextClick} className="blueBtn">{title}</button>
    </div>
  );
};

export default BlueButton;