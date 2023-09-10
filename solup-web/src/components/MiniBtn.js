import React from "react";
import "./MiniBtn.css"

function MiniBtn ({onClick, text}) {
  return (
    <div>
      <button className="miniBtn"
        onClick={onClick} >{text}</button>
    </div>
  );
};

export default MiniBtn;