import React from "react";
import "./Programs.css";

function SpecialtyItem({title, text}){
  return(
    <div className="itemCard">
      <div className="itemContent">
        <div className="itemTitle">{title}</div>
        <div className="itemLine"></div>
        <div className="itemTexts">{text}</div>
      </div>
    </div>
  );
};

export default SpecialtyItem;