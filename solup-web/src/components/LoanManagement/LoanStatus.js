import React from "react";
import "./LoanStatus.css";

function LoanStatus({ data }) {
  // 데이터가 없거나 유효하지 않을 경우 에러 메시지를 표시합니다.
  if (!data || !data.data) {
    return <div>대출 계좌를 불러오지 못했어요</div>;
  }

  // 주어진 데이터에서 필요한 정보 추출
  const {
    loanableAmount,
    withdrawalAmount,
    interestRate,
    repeatNumber,
    loanHistories,
  } = data.data;

  // 이자 계산 및 납입 정보 추출
  const remainingPrincipal = loanableAmount - withdrawalAmount;
  const totalInterest = remainingPrincipal * (interestRate / 100);
  const numberOfPayments = loanHistories.length;

  // 다음 납입 정보 추출
  const nextPaymentDate =
    numberOfPayments > 0
      ? loanHistories[numberOfPayments - 1].tradeDate
      : "없음";

  // 원금 상환 비율 계산
  const principalRepaymentRate =
    (remainingPrincipal / withdrawalAmount) * 100;

  // deposit 값의 합 구하기
  const totalDeposit = loanHistories.reduce(
    (total, history) => total + history.deposit,
    0
  );
  console.log(totalDeposit)

  return (
    <div>
      <h2>우리가게 대출 현황</h2>
      <p>남은 원금: {new Intl.NumberFormat().format(remainingPrincipal)} 원</p>
      <p>남은 이자: {new Intl.NumberFormat().format(totalInterest)} 원</p>
      <p>납입일: {nextPaymentDate}</p>
      <p>이자율: {interestRate}%</p>
      <p>납입 횟수: {numberOfPayments} 회</p>

      {/* 프로그레스 바 표시 */}
      <div className="progress">
        <div
          className="progress-bar"
          role="progressbar"
          style={{ width: `${(totalDeposit / loanableAmount) * 100}%` }}
          aria-valuenow={totalDeposit}
          aria-valuemin="0"
          aria-valuemax={withdrawalAmount}
        >
          {(totalDeposit / loanableAmount*100).toFixed(2)}%
          <span>갚았어요</span>
        </div>
      </div>
    </div>
  );
}

export default LoanStatus;
