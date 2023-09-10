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
    <button onClick={handleNextClick} className="whiteBtn">{title}</button>
  );
};

export default WhiteButton;