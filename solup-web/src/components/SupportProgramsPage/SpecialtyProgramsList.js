import React, { useEffect, useState } from "react";
import axios from "axios";
import "./Programs.css"
import SpecialtyItem from "./SpecialtyItem";


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
    <div className="itemContainer">
      <SpecialtyItem title="신한 SOHO 사관학교" 
      text="성공적인 가게 영업에 필요한 맞춤형 컨설팅 
      사업 성공을 위한 브랜딩 및 홍보전략, 
      소상공인 활용 가능한 금융지원 제도 등 지원"/>

      <SpecialtyItem title="신한 SOHO 성공지원센터"
      text="자영업자의 경영 애로 해소를 위한 컨설팅
      성공한 자영업자 도와주는 멘토링 및
      8주간의 집중교육 및 맞춤형 컨설팅" />

      <SpecialtyItem title="성공 두드림 맞춤교실"
      text="영업에 필요한 지원제도 및 법률, 세무,
      상권분석, 마케팅, 장부관리 정보를 알려주는
      최신 트렌드 기반 맞춤형 컨설팅 강의" />

      <SpecialtyItem title="성공 두드림 세미나"
      text="자영업자들을 위한 찾아가는 세미나
      금융애로 현장상담반의 1대 1 컨설팅 지원,
      자영업자를 위한 은행사용설명서 강의 진행" />
    </div>
  );
}

export default SpecialtyProgramList;
