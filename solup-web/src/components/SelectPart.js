import React from "react";
import "./SelectPart.css";

function SelectPart({title, options, value, onChange,}) {
  return(
    <div>
      <div className="partTitle">{title}</div>
      <div className="selectContent">
        <select value={value} onChange={onChange}>
          {options.map((option) => (
            <option key={option} value={option}>
              {option}
            </option>
          ))}
        </select>

      </div>
    </div>
  );
};

export default SelectPart;