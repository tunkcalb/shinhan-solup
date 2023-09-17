import axios from "axios";
import React, { useEffect, useState } from "react";

function GrowthProgramList({ }) {
    const [data, setData] = useState([]);
    useEffect(() => {
    axios.get("/policygrow.do")
        .then((response) => {
        setData(response.data.item);
        })
        .catch((error) => {
        console.error("Error fetching data:", error);
        });
    }, []);
  return (
    <div className="itemContainer">
      <ul>
        {data.map((item) => (
          <li key={item.areaNo} className="area">
            <h3>{item.areaNm}</h3> 
            <ul>
              {item.items.map((program) => (
                <li key={program.title} className="growItem">
                  <a href={program.url} target="_blank" rel="noopener noreferrer">
                    {program.year} {program.title}
                  </a>
                </li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default GrowthProgramList;
