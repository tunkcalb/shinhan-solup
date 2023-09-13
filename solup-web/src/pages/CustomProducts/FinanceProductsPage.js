import React, { Component } from 'react';
import SavingsProductList from '../../components/FinanceProductsPage/SavingProductsList';
import LoanProductList from '../../components/FinanceProductsPage/LoanProductsList';

class FinanceProductsPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      activeTab: 'savings', // 초기 활성 탭은 'savings' (적금)입니다.
    };
  }

  handleTabChange = (tab) => {
    this.setState({ activeTab: tab });
  };

  render() {
    const { activeTab } = this.state;

    return (
      <div>
        <h1>금융 상품 목록</h1>
        <div className="tab-buttons">
          <button
            className={activeTab === 'savings' ? 'active' : ''}
            onClick={() => this.handleTabChange('savings')}
          >
            적금상품
          </button>
          <button
            className={activeTab === 'loans' ? 'active' : ''}
            onClick={() => this.handleTabChange('loans')}
          >
            대출상품
          </button>
        </div>
        <div className="tab-content">
          {activeTab === 'savings' && <SavingsProductList />}
          {activeTab === 'loans' && <LoanProductList />}
        </div>
      </div>
    );
  }
}

export default FinanceProductsPage;
