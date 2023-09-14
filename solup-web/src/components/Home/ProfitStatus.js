import React, { useState } from 'react';
import { useNavigate } from 'react-router';
import "./ProfitStatus.css";
import"./AccountInfo.css";
import MiniBtn from '../MiniBtn';

function ProfitStatus() {
  const navigate = useNavigate();
  const redirectToSettlementPage = () => {
    navigate('/margin-settlement');
  };

  // 거래내역 분류 여부를 상태로 관리합니다.
  const [isClassified, setIsClassified] = useState(false);

  // 실제 거래내역 정보 (분류가 되었다고 가정)
  const classifiedData = {
    monthlyRevenue: '100,000',
    fixedExpenses: '30,000',
    variableExpenses: '20,000',
    margin: '50,000',
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
        <div className='categoryContainer'>
          {/* 매출 */}
          <div className='partContainer'>
            <img src={`${process.env.PUBLIC_URL}/cardGreen.png`} alt="매출카드" className='revCard'/>
            <div className='profitTextOverlay'>
              <div>이번달 매출</div>
              <div>
                <span className='boldSum'>{classifiedData.monthlyRevenue}</span>
                <span>원</span>
              </div>
            </div>
          </div>

          {/* 비용 */}
          <div className='costCard'>
            {/* 고정비 */}
            <div className='partContainer'>
              <img src={`${process.env.PUBLIC_URL}/cardRed.png`} alt="고정비카드" className='fc'/>
              <div className='profitTextOverlay'>
                <div>고정비</div>
                <div>
                  <span className='boldSum'>{classifiedData.fixedExpenses}</span>
                  <span>원</span>
                </div>
              </div>
            </div>
            {/* 변동비 */}
            <div className='partContainer'>
              <img src={`${process.env.PUBLIC_URL}/cardPurple.png`} alt="변동비카드" className='vc'/>
              <div className='profitTextOverlay'>
                <div>변동비</div>
                <div>
                  <span className='boldSum'>{classifiedData.variableExpenses}</span>
                  <span>원</span>
                </div>
              </div>
            </div>
          </div>

          {/* 마진 */}
          <div className='partContainer'>
            <img src={`${process.env.PUBLIC_URL}/cardOrange.png`} alt="마진카드" className='marginCard'/>
            <div className='profitTextOverlay'>
              <div>마진</div>
              <div>
                <span className='boldSum'>{classifiedData.margin}</span>
                <span>원</span>
              </div>
            </div>
            <button onClick={redirectToSettlementPage} className='marginBtnOverlay'>정산</button>
          </div>
          
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
