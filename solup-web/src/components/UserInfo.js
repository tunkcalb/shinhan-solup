import React from "react";
import { useSelector } from "react-redux";

function UserInfo() {
  const userName = useSelector((state) => state.userName);

  return (
    <div>
      <h2>안녕하세요, {userName} 사장님</h2>
    </div>
  );
}

export default UserInfo;
