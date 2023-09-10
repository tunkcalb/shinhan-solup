import React from "react";
import "./Start.css"
import "../Verification.css"
import BlueButton from "../../components/BlueButton";

function RegisterStore () {
  return (
    <div className="startContainer">
      <div className="startContent">
        <div className="mainText">매장 등록하기</div>
        <div className="subText">운영관리에 필요한</div>
        <div className="subText">매장 정보를 등록해주세요</div>
        {/* 여기 input 채워야 함 */}
        <BlueButton title="등록하기" destination="/ready-to-solup"/>
      </div>
    </div>    
  );
};

export default RegisterStore;