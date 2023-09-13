import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import "../../pages/Home.css";
import "./Guide.css";

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
            </div>

          </div>

          <div>
            <div className="guideItem">
              <img src={`${process.env.PUBLIC_URL}/guide22.png`} alt="가이드2"/>
            </div>

          </div>

          <div>
            <div className="guideItem">
              <img src={`${process.env.PUBLIC_URL}/guide33.png`} alt="가이드3"/>
            </div>

          </div>

        </Slider>
      </div>
    </div>
  );
};

export default Guide;