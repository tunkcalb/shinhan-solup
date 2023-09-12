import React from "react";
import { Link } from "react-router-dom";
import './Footer.css';

function NavBar () {
  return (
    <div>
      <ul className="navBarList">
        <Link to="/" className="navItem">
          <img src={`${process.env.PUBLIC_URL}/nav1.png`} alt="대출"/>
          {/* <img src={`${process.env.PUBLIC_URL}/대출.png`} alt="대출"/> */}
          <div>대출</div>
        </Link>
        <Link to="/" className="navItem">
          <img src={`${process.env.PUBLIC_URL}/nav2.png`} alt="매출"/>
          <div>매출</div>
        </Link>
        <Link to="/" className="navItem">
          <img src={`${process.env.PUBLIC_URL}/nav3.png`} alt="홈"/>
          <div>홈</div>
        </Link>
        <Link to="/" className="navItem">
          <img src={`${process.env.PUBLIC_URL}/nav4.png`} alt="직원"/>
          <div>직원</div>
        </Link>
        <Link to="/" className="navItem">
          <img src={`${process.env.PUBLIC_URL}/nav5.png`} alt="상품"/>
          <div>상품</div>
        </Link>
        {/* <img src={`${process.env.PUBLIC_URL}/대출.png`} alt="대출"/>
        <img src={`${process.env.PUBLIC_URL}/매출.png`} alt="매출"/>
        <img src={`${process.env.PUBLIC_URL}/홈.png`} alt="홈"/>
        <img src={`${process.env.PUBLIC_URL}/직원.png`} alt="직원"/>
        <img src={`${process.env.PUBLIC_URL}/상품.png`} alt="상품"/> */}
      </ul>
    </div>
  );
};

export default NavBar;