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
    <button onClick={handleNextClick} className="blueBtn">{title}</button>
  );
};

export default BlueButton;