import React from "react";
import "./LoanStatus.css"; // LoanStatus.css 파일 import

function LoanStatus({ remainingPrincipal, remainingInterest, nextPaymentDate, interestRate, numberOfPayments }) {
  // 대출 원금과 이자를 숫자로 변환
  const principalAmount = parseFloat(remainingPrincipal.replace(/,/g, ""));
  const interestAmount = parseFloat(remainingInterest.replace(/,/g, ""));

  // 대출 상환율 계산 (원금 상환율)
  const totalLoanAmount = principalAmount + interestAmount;
  const principalRepaymentRate = ((totalLoanAmount - principalAmount) / totalLoanAmount) * 100;

  return (
    <div>
      <h2>우리가게 대출 현황</h2>
      <p>남은 원금: {remainingPrincipal} 원</p>
      <p>남은 이자: {remainingInterest} 원</p>
      <p>납입일: {nextPaymentDate}</p>
      <p>이자율: {interestRate}%</p>
      <p>납입 횟수: {numberOfPayments} 회</p>

      {/* 프로그레스 바 표시 */}
      <div className="progress">
        <div
          className="progress-bar"
          role="progressbar"
          style={{ width: `${principalRepaymentRate}%` }}
          aria-valuenow={principalRepaymentRate}
          aria-valuemin="0"
          aria-valuemax="100"
        >
          {principalRepaymentRate.toFixed(2)}%
        <span>갚았어요</span>
        </div>
      </div>
    </div>
  );
}

export default LoanStatus;
