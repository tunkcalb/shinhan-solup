import React, { useEffect, useState } from "react";
import LoanStatus from "../components/LoanManagement/LoanStatus";
import AutoDebit from "../components/LoanManagement/AutoDebit";
import Loan_Products from "../components/LoanManagement/Loan_Products";
import { useSelector } from "react-redux";
import axios from "axios";
import Header from "../components/Header";
import NavBar from "../components/Footer";
import "../pages/styles/Loan.css"

function LoanManagement() {
  const [loanStatusData, setLoanStatusData] = useState(null);
  const userId = useSelector((state) => state.userId);

  useEffect(() => {
    axios
      .get(`/account/${userId}/loan`)
      .then((response) => {
        const data = response.data;
        setLoanStatusData(data);
      })
      .catch((error) => {
        console.error("API 요청 실패:", error);
      });
  }, [userId]);

  const autoDebitData = {
    accountNumber: loanStatusData?.data.number || "", // 대출 정보에서 가져온 계좌 번호
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
    <div >
      <Header title="대출관리"/>
      <div className="loan">
        <LoanStatus data={loanStatusData} />
        <AutoDebit {...autoDebitData} />
        <Loan_Products loanProducts={loanProductsData} onApplyLoan={handleApplyLoan} />
      </div>
      <NavBar />
    </div>
  );
}

export default LoanManagement;
