import React from "react";

function GrowthProgram({ program }) {
  return (
    <div>
      <h2>성장 프로그램 상세 정보</h2>
      <p>년도: {program.year}</p>
      <p>제목: {program.title}</p>
      <p>
        <a href={program.url} target="_blank" rel="noopener noreferrer">
          상세 정보 보기
        </a>
      </p>
    </div>
  );
}

export default GrowthProgram;
