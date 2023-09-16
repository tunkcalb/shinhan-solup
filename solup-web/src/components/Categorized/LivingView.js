import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import Header from "../Header.js";
import Navbar from "../Footer.js";
import NoCategorized from "./NoCategorized.js";

import style from "./Categorized.module.css";

function LivingView() {
  const [tradeHistories, setTradeHistories] = useState([]);
  const [account, setAccount] = useState({});
  const [totalWithdraw, setTotalWithdraw] = useState(0);

  // const userId = useSelector(state => state.userId);
  const userId = 1;

  useEffect(() => {
    updateAccount();
    updateTradeHistories();
    console.log(tradeHistories);
    console.log(account);
  }, []);

  const updateTradeHistories = async () => {
    try {
      const response = await fetch(`/account/${userId}/livings`);
      const jsonResponse = await response.json();
      const data = jsonResponse.data.tradeHistories;
      const totalLiving = jsonResponse.data.totalLiving;
      const withdraw = jsonResponse.data;
      setTotalWithdraw(totalLiving);
      console.log(withdraw);

      const sortedData = data.slice().sort((a, b) => {
        const dateComparison = b.tradeDate.localeCompare(a.tradeDate);
        if (dateComparison === 0) {
          return (
            convertTimeToSeconds(b.tradeTime) -
            convertTimeToSeconds(a.tradeTime)
          );
        }
        return dateComparison;
      });

      setTradeHistories(sortedData);
    } catch (error) {
      console.log(error);
    }
  };

  const updateAccount = async () => {
    try {
      const response = await fetch(`/account/${userId}`);
      const data = await response.json();
      setAccount(data);
    } catch (error) {
      console.log(error);
    }
  };

  const convertTimeToSeconds = (timeStr) => {
    const [hour, minute, second] = timeStr.split(":").map(Number);
    return hour * 3600 + minute * 60 + second;
  };

  const monthDay = (date) => {
    const localDate = new Date(date);
    // 월을 한글로 변환
    const months = [
      "1월",
      "2월",
      "3월",
      "4월",
      "5월",
      "6월",
      "7월",
      "8월",
      "9월",
      "10월",
      "11월",
      "12월",
    ];
    const monthInKorean = months[localDate.getMonth()];

    // 날짜를 "8월 15일" 형식으로 가공
    const formattedDate = monthInKorean + " " + localDate.getDate() + "일";

    // 결과 출력
    console.log(formattedDate); // "8월 15일"
    return formattedDate;
  };

  return (
    <div>
      <Header title="생활비 인출 내역" />

      <div className={style.container}>
        <div>
          {!!tradeHistories ? null : account.bank}
          {!!tradeHistories ? null : account.number}
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <span className={style.balance}>
            {" "}
            {!!tradeHistories
              ? null
              : new Intl.NumberFormat().format(totalWithdraw)}{" "}
          </span>
          <span className={style.won}>{!!tradeHistories ? null : "원"}</span>
        </div>
      </div>

      {tradeHistories.map((history, index) => (
        <div key={history.id} className={style.container}>
          {index + 1 < tradeHistories.length &&
          tradeHistories[index].tradeDate ===
            tradeHistories[index + 1].tradeDate ? (
            <div>
              <div className={style.history}>
                <img src={`${process.env.PUBLIC_URL}/shinhanLogo.png`} />
                <span style={{ display: "inline-block", width: "100%" }}>
                  <span
                    style={{
                      display: "block",
                      display: "flex",
                      justifyContent: "space-between",
                    }}
                  >
                    <span
                      style={{
                        display: "inline-block",
                        marginLeft: "1rem",
                        fontWeight: "bold",
                        fontSize: "0.8rem",
                        lineHeight: "2",
                        verticalAlign: "middle",
                      }}
                    >
                      {history.content}({history.briefs})
                    </span>
                    <span style={{ color: "red", fontWeight: "500" }}>
                      -{new Intl.NumberFormat().format(history.withdraw)} 원
                    </span>
                  </span>
                  <span
                    style={{
                      display: "inline-block",
                      marginLeft: "1rem",
                      color: "#8989c7",
                      fontSize: "0.7rem",
                      verticalAlign: "top",
                    }}
                  >
                    {history.tradeTime}
                  </span>
                </span>
              </div>
            </div>
          ) : (
            <>
              <div className={style.date}>{monthDay(history.tradeDate)}</div>
              <div>
                <div className={style.history}>
                  <img src={`${process.env.PUBLIC_URL}/shinhanLogo.png`} />
                  <span style={{ display: "inline-block", width: "100%" }}>
                    <span
                      style={{
                        display: "block",
                        display: "flex",
                        justifyContent: "space-between",
                      }}
                    >
                      <span
                        style={{
                          display: "inline-block",
                          marginLeft: "1rem",
                          fontWeight: "bold",
                          fontSize: "0.8rem",
                          lineHeight: "2",
                          verticalAlign: "middle",
                        }}
                      >
                        {history.content}({history.briefs})
                      </span>
                      <span
                        style={{
                          color: "red",
                          fontWeight: "500",
                        }}
                      >
                        -{new Intl.NumberFormat().format(history.withdraw)} 원
                      </span>
                    </span>
                    <span
                      style={{
                        display: "inline-block",
                        marginLeft: "1rem",
                        color: "#8989c7",
                        fontSize: "0.7rem",
                        verticalAlign: "top",
                      }}
                    >
                      {history.tradeTime}
                    </span>
                  </span>
                </div>
              </div>
            </>
          )}
        </div>
      ))}
      {!!tradeHistories ? <NoCategorized /> : null}
      <Navbar />
    </div>
  );
}

export default LivingView;
