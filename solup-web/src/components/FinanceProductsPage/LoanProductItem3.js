import React from "react";
import style from "./LoanProduct.module.css";

function LoanProductItem3() {
  const handleOpenApp = () => {
    const appScheme = "com.shinhan.sbanking";
    const appLink = `intent://${appScheme}/#Intent;scheme=${appScheme};action=android.intent.action.VIEW;package=${appScheme};end`;
    window.location.href = appLink;
  };
  return (
    <div className={style.loanProduct} onClick={handleOpenApp}>
      <h2>소상공인 저금리 대환대출</h2>
      <p>
        대환대상 대출(연 7% 이상 고금리 대출) 보유 및 소상공인 저금리 대환보증
        발급 가능 고객
      </p>
      <p>한도 : 보증서 이내</p>
    </div>
  );
}

export default LoanProductItem3;
