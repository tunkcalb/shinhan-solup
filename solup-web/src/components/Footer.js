import React from "react";
import './Footer.css';

function NavBar () {
  return (
    <div className="navBar">
      <ul className="navBarContainer">
        안녕하세호 노답 내비바
        <img src={`${process.env.PUBLIC_URL}/대출.png`} alt="대출"/>
        <img src={`${process.env.PUBLIC_URL}/매출.png`} alt="매출"/>
        <img src={`${process.env.PUBLIC_URL}/홈.png`} alt="홈"/>
        <img src={`${process.env.PUBLIC_URL}/직원.png`} alt="직원"/>
        <img src={`${process.env.PUBLIC_URL}/상품.png`} alt="상품"/>

      </ul>
    </div>
  );
};

export default NavBar;