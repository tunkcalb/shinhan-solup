import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import Header from "../Header.js";
import Navbar from "../Footer.js";
import CategorizeModal from "./CategorizeModal.js";
import CategorizeFinish from "./CategorizeFinish.js";
import Modal from "../../components/Modal.js";

import style from "./TradeHistoryView.module.css";

function TradeHistoryView() {
  const [tradeHistories, setTradeHistories] = useState([]);
  const [account, setAccount] = useState({});
  const [selectedHistory, setSelectedHistory] = useState(null);
  const [expenseType, setExpenseType] = useState("");
  const [expenseCategory, setExpenseCategory] = useState("");

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
      const response = await fetch(
        `/account/${userId}/not-categorized-withdraw`
      );
      const jsonResponse = await response.json();
      const data = jsonResponse.data;

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

  const openModal = (history) => {
    setSelectedHistory(history);
  };

  const closeModal = () => {
    setSelectedHistory(null);
  };

  const handleModalClick = (expenseType, expenseCategory) => {
    console.log(expenseType, expenseCategory);
    setExpenseType(expenseType);
    setExpenseCategory(expenseCategory);
    updateTradeHistories();
    console.log(tradeHistories);
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
    return formattedDate;
  };

  return (
    <div>
      <Header title="신한 주거래 사업자 통장" />

      {!!tradeHistories ? (
        <div className={style.container}>
          <div>
            {account.bank} {account.number}
          </div>
          <div style={{ marginBottom: "1rem" }}>
            <span className={style.balance}>{account.balance} </span>
            <span className={style.won}>원</span>
          </div>
        </div>
      ) : null}

      {tradeHistories.map((history, index) => (
        <div key={history.id} className={style.container}>
          {index + 1 < tradeHistories.length &&
          tradeHistories[index].tradeDate ===
            tradeHistories[index + 1].tradeDate ? (
            <div
              style={{ cursor: "pointer" }}
              onClick={() => openModal(history)}>
              <div className={style.history}>
                <img src={`${process.env.PUBLIC_URL}/shinhanLogo.png`} />
                <span style={{ display: "inline-block", width: "100%" }}>
                  <span
                    style={{
                      display: "block",
                      display: "flex",
                      justifyContent: "space-between",
                    }}>
                    <span
                      style={{
                        display: "inline-block",
                        marginLeft: "1rem",
                        fontWeight: "bold",
                        fontSize: "0.8rem",
                        lineHeight: "2",
                        verticalAlign: "middle",
                      }}>
                      {history.content}({history.briefs})
                    </span>
                    <span style={{ color: "red", fontWeight: "500" }}>
                      -{history.withdraw} 원
                    </span>
                  </span>
                  <span
                    style={{
                      display: "inline-block",
                      marginLeft: "1rem",
                      color: "#8989c7",
                      fontSize: "0.7rem",
                      verticalAlign: "top",
                    }}>
                    {history.tradeTime}
                  </span>
                </span>
              </div>
            </div>
          ) : (
            <>
              <div className={style.date}>{monthDay(history.tradeDate)}</div>
              <div
                style={{ cursor: "pointer" }}
                onClick={() => openModal(history)}>
                <div className={style.history}>
                  <img src={`${process.env.PUBLIC_URL}/shinhanLogo.png`} />
                  <span style={{ display: "inline-block", width: "100%" }}>
                    <span
                      style={{
                        display: "block",
                        display: "flex",
                        justifyContent: "space-between",
                      }}>
                      <span
                        style={{
                          display: "inline-block",
                          marginLeft: "1rem",
                          fontWeight: "bold",
                          fontSize: "0.8rem",
                          lineHeight: "2",
                          verticalAlign: "middle",
                        }}>
                        {history.content}({history.briefs})
                      </span>
                      <span
                        style={{
                          color: "red",
                          fontWeight: "500",
                        }}>
                        -{history.withdraw} 원
                      </span>
                    </span>
                    <span
                      style={{
                        display: "inline-block",
                        marginLeft: "1rem",
                        color: "#8989c7",
                        fontSize: "0.7rem",
                        verticalAlign: "top",
                      }}>
                      {history.tradeTime}
                    </span>
                  </span>
                </div>
              </div>
            </>
          )}
        </div>
      ))}

      <CategorizeModal
        isOpen={!!selectedHistory}
        onClose={closeModal}
        history={selectedHistory}
        userId={userId}
        onClick={handleModalClick}
      />
      {!!tradeHistories ? null : <CategorizeFinish />}
      <Navbar />
    </div>
  );
}

export default TradeHistoryView;
