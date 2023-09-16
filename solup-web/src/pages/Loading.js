import React from "react";

const Loading = () => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
      }}
    >
      <p
        style={{
          textAlign: "center",
          color: "gray",
          margin: "0.1rem",
          fontSize: "2rem",
          fontWeight: "bold",
        }}
      >
        로딩중
      </p>
      <img
        src={`${process.env.PUBLIC_URL}/shfriends6.gif`}
        alt="신한프렌즈"
        style={{ width: "60%" }}
      />
    </div>
  );
};

export default Loading;
