import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import Header from "../Header.js";
import CategorizeModal from "./CategorizeModal.js";

function TradeHistoryView() {
  const [tradeHistories, setTradeHistories] = useState([]);
  const [account, setAccount] = useState({});
  const [selectedHistory, setSelectedHistory] = useState(null);
  const [categorizedHistoryIds, setCategorizedHistoryIds] = useState([]);

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

  return (
    <div>
      <Header title="신한 주거래 사업자 통장" />

      <div>
        <p>
          {account.bank} {account.number}
        </p>
        <p>{account.balance}</p>
      </div>

      {tradeHistories.map((history, index) => (
        <div key={history.id}>
          <h3>
            {history.tradeDate}({history.tradeTime})
          </h3>
          <div style={{ cursor: "pointer" }} onClick={() => openModal(history)}>
            {history.content}({history.briefs}) -{history.withdraw}
          </div>
        </div>
      ))}

      <CategorizeModal
        isOpen={!!selectedHistory}
        onClose={closeModal}
        history={selectedHistory}
        userId={userId}
      />
    </div>
  );
}

export default TradeHistoryView;
