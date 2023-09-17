import React from "react";
import "./SelectPart.css";

function SelectPart({title, options, name, value, onChange,}) {
  return(
    <div>
      <div className="partTitle">{title}</div>
      <div className="selectContent">
        <select name={name} value={value} onChange={onChange}>
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