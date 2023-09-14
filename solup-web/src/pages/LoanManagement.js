import React from "react";
import LoanStatus from "../components/LoanManagement/LoanStatus";
import AutoDebit from "../components/LoanManagement/AutoDebit";
import LoanProducts from "../components/LoanManagement/LoanProducts";
import LoanProductList from "../components/FinanceProductsPage/LoanProductsList";

function LoanManagement() {
  const loanStatusData = {
    remainingPrincipal: "30,000,000",
    remainingInterest: "5,000,000",
    nextPaymentDate: "2023-09-01",
    interestRate: "5.83",
    numberOfPayments: "6",
  };

  const autoDebitData = {
    accountNumber: "11048299999",
    paymentAmount: "1,000,000",
  };

  const loanProductsData = [
    {
      name: "대출 상품 1",
      limit: "50,000,000",
      interestRate: "4.5",
    },
    {
      name: "대출 상품 2",
      limit: "40,000,000",
      interestRate: "4.0",
    },
    {
      name: "대출 상품 3",
      limit: "60,000,000",
      interestRate: "4.8",
    },
  ];

  const handleApplyLoan = (selectedProduct) => {
    console.log("대출 신청:", selectedProduct);
  };

  return (
    <div>
      <LoanStatus {...loanStatusData} />
      <AutoDebit {...autoDebitData} />
      <LoanProducts loanProducts={loanProductsData} onApplyLoan={handleApplyLoan} />
    </div>
  );
}

export default LoanManagement;
