import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import "../../pages/styles/Home.css";
import "./Guide.css";
import BlueButton from "../BlueButton";


function Guide () {

  const settings = {
    dots: true,
    infinite: false,
    speed: 500,
    slidesToShow : 1,
    slidesToScroll: 1,
    arrows: false,
  };

  return (
    <div>
      <div className='homeLogo'>
        <img src={`${process.env.PUBLIC_URL}/solup-logo-blue.png`} alt="쏠업로고"/>
      </div>
      <div className="guideContainer">
        <Slider {...settings} className="guideContent">
          <div>
            <div className="guideItem">
              <img src={`${process.env.PUBLIC_URL}/guide11.png`} alt="가이드1"/>
              <div className="guideText">뒤섞여 있는 가게 관련 거래 내역,</div>
              <div className="guideText">장사도 바쁜데 통장 쪼개기는 언제🤔?</div>              
            </div>
          </div>

          <div>
            <div className="guideItem">
              <img src={`${process.env.PUBLIC_URL}/guide22.png`} alt="가이드2"/>
              <div className="guideText">첫 지출 내역만 분류하면 준비 완료!</div>
              <div className="guideText">이제 동일한 거래는 한 번에 분류돼요</div>            
            </div>
          </div>

          <div>
            <div className="guideItem">
              <img src={`${process.env.PUBLIC_URL}/guide33.png`} alt="가이드3"/>
              <div className="guideText">장사에 꼭 필요한 손익구조 관리,</div>
              <div className="guideText">쏠업에서 지금 바로 시작해보세요</div>            
            </div>
          </div>
        </Slider>
      </div>
      <div className="btnMargin">
        <BlueButton title="카테고리 분류 시작하기" destination=""/> 
      </div>
    </div>
  );
};

export default Guide;