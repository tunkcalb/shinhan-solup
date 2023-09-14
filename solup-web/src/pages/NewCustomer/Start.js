import React from "react";
import { useSelector } from "react-redux";
// import { Link } from "react-router-dom";
import "../styles/Start.css"
import "../styles/Verification.css"
import BlueButton from "../../components/BlueButton";


function Start () {
  const userName = useSelector((state) => state.userName);

  return (
    <div className="startContainer">
      <div className="startContent">
        <div className="mainText">반갑습니다</div>
        <div className="mainText">{userName} 사장님</div>
        <div className="subText">영업점 관리를 시작해볼까요?</div>
        <div className="imgContainer"><img src={`${process.env.PUBLIC_URL}/startImg.png`} alt="신한프렌즈1"/></div> 
        <div className="startBtn">
          <BlueButton title="시작하기" destination="/account-question"/>
        </div>
      </div>
    </div>
  );
};

export default Start;