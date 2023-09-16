import React from "react";
import Loan_ProductList from "./Loan_ProductList";

function Loan_Products({ loanProducts, onApplyLoan }) {
  return (
    <div className="loans-3">
      <div className="debitTitle">대출상품</div>
      <Loan_ProductList />
    </div>
  );
}

export default Loan_Products;
