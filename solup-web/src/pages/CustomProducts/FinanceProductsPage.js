import React, { Component } from "react";
import SavingsProductList from "../../components/FinanceProductsPage/SavingProductsList";
import LoanProductList from "../../components/FinanceProductsPage/LoanProductsList";
import Header from "../../components/Header";
import NavBar from "../../components/Footer";
import "../../pages/styles/FinanceProductsPage.css";

class FinanceProductsPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      activeTab: "savings", // 초기 활성 탭은 'savings' (적금)입니다.
    };
  }

  handleTabChange = (tab) => {
    this.setState({ activeTab: tab });
  };

  render() {
    const { activeTab } = this.state;

    return (
      <div>
        <Header title="금융 상품 찾기" />
        <div className="tab-buttons">
          <span className="span-container">
            <span
              className={`tab ${activeTab === "savings" ? "active" : ""}`}
              onClick={() => this.handleTabChange("savings")}
            >
              적금상품
            </span>
          </span>
          <span className="span-container">
            <span
              className={`tab ${activeTab === "loans" ? "active" : ""}`}
              onClick={() => this.handleTabChange("loans")}
            >
              대출상품
            </span>
          </span>
        </div>
        <div className="tab-content">
          {activeTab === "savings" && <SavingsProductList />}
          {activeTab === "loans" && <LoanProductList />}
        </div>
        <NavBar />
      </div>
    );
  }
}

export default FinanceProductsPage;
