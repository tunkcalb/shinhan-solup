import React from "react";
// import { Link } from "react-router-dom";
import "./Start.css"
import "../Verification.css"
import BlueButton from "../../components/BlueButton";


function Start () {
  return (
    <div className="startContainer">
      <div className="startContent">
        <div className="mainText">반갑습니다</div>
        <div className="mainText">김싸피 사장님</div>
        <div className="subText">영업점 관리를 시작해볼까요?</div>
        <div className="imgContainer"><img src={`${process.env.PUBLIC_URL}/startImg.png`} alt="신한프렌즈1"/></div> 
        <BlueButton title="시작하기" destination="/account-question"/>
      </div>
    </div>
  );
};

export default Start;