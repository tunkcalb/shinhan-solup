import React from 'react';
import ProfitStatus from '../components/Home/ProfitStatus';

function ProfitStatusPage() {
  return (
    <div>
      <p>신한커피 김싸피 사장님</p>
      <p>통장 쪼갤 필요 없이,</p>
      <p>하나의 계좌에서 한 눈에 확인해보세요!</p>
      <ProfitStatus />
      <p>마진은 지정한 비율에 따른 금액만큼</p>
      <p>생활비 통장으로 바로 이체할 수 있어요!</p>
      <p>마진 정산하기 버튼</p>
    </div>
  );
}

export default ProfitStatusPage;
