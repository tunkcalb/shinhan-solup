import React, { useState, useEffect } from "react";
import "./AccountInfo.css";
import MiniBtn from "../MiniBtn";
import { useNavigate } from "react-router";
import { useSelector } from "react-redux";
import axios from "axios"; // axios를 사용하여 API 호출

function AccountInfo() {
  const navigate = useNavigate();
  const isAccountRegistered = useSelector((state) => state.isAccountRegistered);
  const [accountData, setAccountData] = useState(null);
  const userId = useSelector((state) => state.userId);

  useEffect(() => {
    if (isAccountRegistered) {
      console.log(userId);
      axios
        .get(`/account/${userId}`)
        .then((response) => {
          // API 응답에서 필요한 정보 추출
          const data = response.data;
          const accountInfo = {
            bankName: data.bank,
            accountNumber: data.number,
            accountBalance: data.balance,
          };
          console.log(accountInfo);
          // 계좌 정보 상태 업데이트
          setAccountData(accountInfo);
        })
        .catch((error) => {
          console.error("API 요청 실패:", error);
        });
    }
  }, [isAccountRegistered]);

  // 계좌 등록 페이지로 이동하는 함수
  const redirectToAccountRegistration = () => {
    navigate("/account-register");
  };
  // 계좌 쪼개는 거
  const parsedAccountNumber = (accountNumber) => {
    return `${accountNumber.substring(0, 3)}-${accountNumber.substring(
      3,
      6
    )}-${accountNumber.substring(6, 12)}`;
  };
  return (
    <div className="infoContainer">
      {isAccountRegistered && accountData ? (
        <div className="accountContainer">
          <img
            src={`${process.env.PUBLIC_URL}/cardProfit.png`}
            alt="계좌카드"
            className="cardImg"
          />
          <div className="textOverlay">
            <div className="bankName">
              <img
                src={`${process.env.PUBLIC_URL}/shinhanLogo.png`}
                alt="신한로고"
                className="shLogo"
              />
              <span> {accountData.bankName}</span>
              <span> {parsedAccountNumber(accountData.accountNumber)}</span>
            </div>

            <div className="balance">
              <span className="boldBalance">
                {new Intl.NumberFormat().format(accountData.accountBalance)}
              </span>
              <span className="normalBalance"> 원</span>
            </div>
          </div>
        </div>
      ) : (
        <div className="noAccount">
          <div>아직 거래내역이 없네요!</div>
          <div>사업자 계좌를 등록해주세요</div>
          <MiniBtn text="등록하기" onClick={redirectToAccountRegistration} />
        </div>
      )}
    </div>
  );
}

export default AccountInfo;
