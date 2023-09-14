import React, { useEffect, useState } from "react";
import axios from "axios";

function SpecialtyProgramList() {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get("/policygrnty.do")
      .then((response) => {
        setData(response.data.item);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  return (
    <div>
      <h2>소상공인 특화 프로그램 목록</h2>
      <ul>
        {data.map((item) => (
          <li key={item.areaNo}>
            <h3>{item.areaNm}</h3>
            <ul>
              {item.items.map((program) => (
                <li key={program.title}>
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

export default SpecialtyProgramList;
