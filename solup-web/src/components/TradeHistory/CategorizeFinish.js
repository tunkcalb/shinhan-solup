import React from "react";
import BlueButton from "../BlueButton";
import { useDispatch } from "react-redux";

import { setIsCategorized } from "../../redux/actions";

function CategorizeFinish() {
  const dispatch = useDispatch();

  const completeCategorize = () => {
    dispatch(setIsCategorized(true));
  };

  return (
    <div style={{ height: "50%" }}>
      <div style={{ textAlign: "center", marginTop: "3rem" }}>
        <h2 style={{ textAlign: "center", fontWeight: "bold", margin: "0rem" }}>
          분류가 완료되었어요!
        </h2>
        <p
          style={{
            textAlign: "center",
            color: "gray",
            margin: "0.1rem",
            fontSize: "0.8rem",
            fontWeight: "bold",
          }}>
          이제 SOLUP에서 자동으로 분류되어요
        </p>
        <img
          src={`${process.env.PUBLIC_URL}/readyToSolupImg.png`}
          alt="신한프렌즈"
          style={{ width: "60%" }}
        />
        <div style={{ margin: "2rem" }} onClick={completeCategorize}>
          <BlueButton title="손익현황을 볼래요" destination="/home" />
        </div>
      </div>
    </div>
  );
}

export default CategorizeFinish;
