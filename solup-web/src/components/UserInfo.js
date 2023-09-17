import React from "react";
import { useSelector } from "react-redux";
import "./UserInfo.css";

function UserInfo() {
  const userName = useSelector((state) => state.userName);

  return (
    <div>
      <div className="userTitle">안녕하세요, {userName} 사장님</div>
    </div>
  );
}

export default UserInfo;
