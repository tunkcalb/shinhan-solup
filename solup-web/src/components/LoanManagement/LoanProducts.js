import React from "react";

function LoanProducts({ loanProducts, onApplyLoan }) {
  return (
    <div>
      <h2>대출 상품</h2>
      <ul>
        {loanProducts.map((product, index) => (
          <li key={index}>
            <p>상품명: {product.name}</p>
            <p>대출 한도: {product.limit} 원</p>
            <p>대출 이율: {product.interestRate}%</p>
            <button onClick={() => onApplyLoan(product)}>대출 신청하기</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default LoanProducts;
