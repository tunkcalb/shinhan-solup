import React from "react";
import LoanProductList from "../FinanceProductsPage/LoanProductsList";

function LoanProducts({ loanProducts, onApplyLoan }) {
  return (
    <div className="loans-3">
      <div className="debitTitle">대출상품</div>
      <LoanProductList />
    </div>
  );
}

export default LoanProducts;
