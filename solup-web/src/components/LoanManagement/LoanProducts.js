import React from "react";
import LoanProductList from "../FinanceProductsPage/LoanProductsList";

function LoanProducts({ loanProducts, onApplyLoan }) {
  return (
    <div>
      <h2>대출 상품</h2>
      <LoanProductList />
    </div>
  );
}

export default LoanProducts;
