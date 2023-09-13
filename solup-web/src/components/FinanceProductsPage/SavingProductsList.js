import React, { Component } from 'react';
import axios from 'axios';
import SavingsProduct from './SavingProduct';

class SavingsProductList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      savingsProducts: [],
    };
  }

  componentDidMount() {
    axios
      .get('/product/saving')
      .then((response) => {
        const savingsProducts = response.data.data.result.baseList;

        // 신한은행만 필터링
        const shinhanProducts = savingsProducts.filter((product) => product.kor_co_nm.includes('신한'));

        this.setState({ savingsProducts: shinhanProducts });
      })
      .catch((error) => {
        console.error('API 요청 중 오류 발생:', error);
      });
  }

  render() {
    return (
      <div>
        <h1>신한은행 적금상품 목록</h1>
        {this.state.savingsProducts.map((product, index) => (
          <SavingsProduct key={index} product={product} />
        ))}
      </div>
    );
  }
}

export default SavingsProductList;
