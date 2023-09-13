import React from "react";
import "./Start.css"
import "../styles/Verification.css"
import BlueButton from "../../components/BlueButton";

function ReadyToSolup () {
  return (
    <div className="startContainer">
      <div className="startContent">
        <div className="mainText">순익분석에 필요한</div>
        <div className="mainText">준비가 완료되었습니다!</div>
        <div className="subText">지금 바로 쏠업과 함께</div>
        <div className="subText">똑똑한 매장관리를 시작해보세요!</div>
        <div className="imgContainer"><img src={`${process.env.PUBLIC_URL}/readyToSolupImg.png`} alt="신한프렌즈3"/></div> 
        <BlueButton title="좋아요" destination="/home"/>
      </div>
    </div>    
  );
};

export default ReadyToSolup;