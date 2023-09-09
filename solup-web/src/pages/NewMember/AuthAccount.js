import React from "react";
import "./Start.css"
import "../Verification.css"
import BlueButton from "../../components/BlueButton";

function AuthAccount () {
  return(
    <div className="startContainer">
      <div className="startContent">
        <div className="mainText">계좌 인증하기</div>
        <div className="subText">신한은행 계좌로 1원을 입금하여</div>
        <div className="subText">숫자 4자리로 계좌를 인증해주세요</div>
        {/* 여기 input 채워야 함 */}
        <BlueButton title="인증완료" destination="/register-store"/>
      </div>
    </div>
  );
};

export default AuthAccount;