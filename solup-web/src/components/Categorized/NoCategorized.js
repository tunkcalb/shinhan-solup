import React from "react";

const NoCategorized = () => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "65vh",
      }}
    >
      <p
        style={{
          textAlign: "center",
          color: "gray",
          margin: "0.1rem",
          fontSize: "0.8rem",
          fontWeight: "bold",
        }}
      >
        분류된 항목이 없어요
      </p>
      <img
        src={`${process.env.PUBLIC_URL}/shfriends5.gif`}
        alt="신한프렌즈"
        style={{ width: "60%" }}
      />
    </div>
  );
};

export default NoCategorized;
