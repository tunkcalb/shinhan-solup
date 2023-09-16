import React from "react";
import "./InputPart.css";

function InputPart({ title, unit, name, type, value, onChange, placeholder}) {
  return (
    <div>
      <div className="partTitle">{title}</div>
      <div className="partBox">
        <input
          type={type}
          value={value}
          name={name}
          onChange={onChange}
          placeholder={placeholder}
        />
        <span>{unit}</span>
      </div>
    </div>
  );
}

export default InputPart;


