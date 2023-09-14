import React from "react";

function InputPart({title, unit}) {
  return(
    <div>
      <div className="partTitle">{title}</div>
      <div className="partBox">
        <span> input 들어가야 할 곳  </span>
        <span>{unit}</span>
      </div>
    </div>
  );
};

export default InputPart;



