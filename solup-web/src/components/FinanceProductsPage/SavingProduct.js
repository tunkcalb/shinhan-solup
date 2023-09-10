import React from 'react';

function SavingsProduct({ product }) {
  return (
    <div>
      <h2>{product.fin_prdt_nm}</h2>
      <p>은행 이름: {product.kor_co_nm}</p>
      <p>가입 방법: {product.join_way}</p>
      <p>이율 정보: {product.mtrt_int}</p>
      <p>우대 조건: {product.spcl_cnd}</p>
      <p>가입 제한: {product.join_deny === '1' ? '제한 없음' : '제한 있음'}</p>
      <p>가입 대상: {product.join_member}</p>
      <p>기타 정보: {product.etc_note}</p>
      <p>최대 한도: {product.max_limit}</p>
      <p>공시 시작일: {product.dcls_strt_day}</p>
      <p>공시 종료일: {product.dcls_end_day || '무제한'}</p>
      <p>제출일: {product.fin_co_subm_day}</p>
      <hr />
    </div>
  );
}

export default SavingsProduct;
