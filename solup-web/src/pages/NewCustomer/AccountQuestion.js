import React from "react";
import "../styles/Start.css"
import "../styles/Verification.css";
import BlueButton from "../../components/BlueButton";
import WhiteButton from "../../components/WhiteButton";


function AccountQuestion () {
  return (
    <div className="startContainer">
      <div className="startContent">
        <div className="mainText">신한은행 계좌가</div>
        <div className="mainText">있으신가요?</div>
        <div className="subText">편리한 영업 관리를 위해</div>
        <div className="subText">신한은행 사업자 계좌가 필요해요</div>
        <div className="imgContainer"><img src={`${process.env.PUBLIC_URL}/questionImg.png`} alt="신한프렌즈2" className="sh2"/></div> 
        <div className="questionBtn">
          <BlueButton title="예(계좌 인증하기)" destination="/account-register"/>
          <WhiteButton title="아니오(계좌 개설하기)" destination=""/>
        </div>
      </div>
    </div>
  );
};

export default AccountQuestion;