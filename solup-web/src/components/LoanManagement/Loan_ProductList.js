import React, { Component } from "react";
import axios from "axios";
import Loan_Product from "./Loan_Product";

class Loan_ProductList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loanProducts: [],
    };
  }

  componentDidMount() {
    axios
      .get("/product/loan")
      .then((response) => {
        const allLoanProducts = response.data.data.result.baseList;
        const shinanLoanProducts = allLoanProducts.filter((product) =>
          product.kor_co_nm.includes("신한")
        );
        this.setState({ loanProducts: shinanLoanProducts });
      })
      .catch((error) => {
        console.error("API 요청 중 오류 발생:", error);
      });
  }

  render() {
    return (
      <div>
        <div className="debitText">신한은행 대출상품 목록을 확인하세요</div>
        {this.state.loanProducts.map((product, index) => (
          <div key={index} className="debitBox">
            <Loan_Product product={product} />
          </div>
        ))}
      </div>
    );
  }
}

export default Loan_ProductList;