import React from "react";
import LoanProductList from "../FinanceProductsPage/LoanProductsList";
import NavBar from "../Footer";
import Header from "../Header";

function LoanProducts({ loanProducts, onApplyLoan }) {
  return (
    <div>
      <Header title="금융 상품 찾기" />
      <h2>대출 상품</h2>
      <LoanProductList />
      <NavBar />
    </div>
  );
}

export default LoanProducts;
