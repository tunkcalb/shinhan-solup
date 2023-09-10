import React, { useState } from 'react';
import "./ProfitStatus.css";
import MiniBtn from '../MiniBtn';

function ProfitStatus() {
  // 거래내역 분류 여부를 상태로 관리합니다.
  const [isClassified, setIsClassified] = useState(false);

  // 실제 거래내역 정보 (분류가 되었다고 가정)
  const classifiedData = {
    monthlyRevenue: '100,000원',
    fixedExpenses: '30,000원',
    variableExpenses: '20,000원',
    margin: '50,000원',
  };

  // 거래내역 분류 페이지로 이동하는 함수
  const redirectToClassificationPage = () => {
    // 분류 페이지로 이동하는 코드
    setIsClassified(true)
  };

  return (
    <div className='statusContainer'>
      <div className='profitTitle'>우리가게 손익 현황</div>
      {isClassified ? (
        <div>
          <p>이번달 매출: {classifiedData.monthlyRevenue}</p>
          <p>고정비: {classifiedData.fixedExpenses}</p>
          <p>변동비: {classifiedData.variableExpenses}</p>
          <p>마진: {classifiedData.margin}</p>
        </div>
      ) : (
        <div className='noCategory'>
          <div className='beforeImg'>
            <img src={`${process.env.PUBLIC_URL}/profitImg.svg`} alt="분류전이미지"/>
          </div>
          <div>쉽고 빠른 손익 분석을 위해</div>
          <div>최초 카테고리 분류 1회가 필요해요</div>
          <MiniBtn text="분류하기" onClick={redirectToClassificationPage} />
        </div>
      )}
    </div>
  );
}

export default ProfitStatus;
