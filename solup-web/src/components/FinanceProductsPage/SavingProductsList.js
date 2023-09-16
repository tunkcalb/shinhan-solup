import React, { Component } from "react";
import axios from "axios";
import SavingsProduct from "./SavingProduct";
import SavingsProductItem1 from "./SavingProductItem1";
import SavingsProductItem2 from "./SavingProductItem2";
import SavingsProductItem3 from "./SavingProductItem3";

class SavingsProductList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      savingsProducts: [],
    };
  }

  componentDidMount() {
    axios
      .get("/product/saving")
      .then((response) => {
        const savingsProducts = response.data.data.result.baseList;

        // 신한은행만 필터링
        const shinhanProducts = savingsProducts.filter((product) =>
          product.kor_co_nm.includes("신한")
        );

        this.setState({ savingsProducts: shinhanProducts });
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
        {this.state.savingsProducts.map((product, index) => {
          return (
            <div
              key={index}
              product={product}
              style={{
                display: "inline-block",
                textAlign: "left",
                margin: "0px",
              }}
            >
              <SavingsProduct product={product} />
              <SavingsProductItem1 />
              <SavingsProductItem2 />
              <SavingsProductItem3 />
              {/* <LoanProductItem4 /> */}
            </div>
          );
        })}
      </div>
    );
  }
}

export default SavingsProductList;
