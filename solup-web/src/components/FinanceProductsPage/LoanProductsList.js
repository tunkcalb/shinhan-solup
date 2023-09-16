import React, { Component } from "react";
import axios from "axios";
import LoanProduct from "./LoanProduct";
import LoanProductItem1 from "./LoanProductItem1";
import LoanProductItem2 from "./LoanProductItem2";
import LoanProductItem3 from "./LoanProductItem3";
import LoanProductItem4 from "./LoanProductItem4";

class LoanProductList extends Component {
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
      <div
        style={{ margin: "0px auto", display: "grid", placeItems: "center" }}
      >
        {this.state.loanProducts.map((product, index) => {
          return (
            <div>
              <LoanProduct product={product} />
              <LoanProductItem1 />
              <LoanProductItem2 />
              <LoanProductItem3 />
              <LoanProductItem4 />
            </div>
          );
        })}
      </div>
    );
  }
}

export default LoanProductList;