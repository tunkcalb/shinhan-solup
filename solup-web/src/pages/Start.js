import React from "react";
import { Link } from "react-router-dom";
import BlueButton from "../components/BlueButton";

function Start () {
  return (
    <div>
      <div>반갑습니다 김싸피사장님</div>
      <div>영업점 관리를 시작해볼가요?</div>
      <div>이미지</div> 
      <BlueButton title="시작하기" destination="/signup"/>
    </div>
  );
};

export default Start;