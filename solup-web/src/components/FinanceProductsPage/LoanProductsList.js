import React, { Component } from 'react';
import axios from 'axios';
import LoanProduct from './LoanProduct';

class LoanProductList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loanProducts: [],
    };
  }

  componentDidMount() {
    axios
      .get('/product/loan')
      .then((response) => {
        const allLoanProducts = response.data.data.result.baseList;
        const shinanLoanProducts = allLoanProducts.filter(
          (product) => product.kor_co_nm.includes('신한')
        );
        this.setState({ loanProducts: shinanLoanProducts });
      })
      .catch((error) => {
        console.error('API 요청 중 오류 발생:', error);
      });
  }

  render() {
    return (
      <div>
        <h1>신한은행 대출상품 목록</h1>
        {this.state.loanProducts.map((product, index) => (
          <LoanProduct key={index} product={product} />
        ))}
      </div>
    );
  }
}

export default LoanProductList;
