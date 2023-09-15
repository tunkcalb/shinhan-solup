import { useEffect , useState } from "react";
import { useSelector } from "react-redux";
import modalStyle from "./TradeHistoryView.module.css";

function TradeHistoryView () {
    const [beforeCategorized, setBeforeCategorized] = useState([]);
    const [account, setAccount] = useState({});
    const [selectedHistory, setSelectedHistory] = useState(null);
    
    // const userId = useSelector(state => state.userId);
    const userId = 1;

    useEffect(() => {
        updateAccount();
        updateBeforeCategorized();
    }, []);

    const updateBeforeCategorized = async () => {
        try {
            const response = await fetch(`/account/${userId}/not-categorized-withdraw`);
            const data = await response.json();
            const sortedData = groupAndSortData(data.data);
            setBeforeCategorized(sortedData);
            console.log(beforeCategorized);
        } catch (error) {
            console.log(error);
        }
    };

    const updateAccount = async () => {
        try {
            const response = await fetch(`/account/${userId}`);
            const data = await response.json();
            setAccount(data);
            console.log(account);
        } catch (error) {
            console.log(error);
        }
    };
    
    // data를 그룹화 하는 메서드
    const groupAndSortData = (data) => {
        const groupedData = {};
        data.forEach((history) => {
            const dateKey = history.tradeDate;
            if (!groupedData[dateKey]) groupedData[dateKey] = [];
            groupedData[dateKey].push(history);
        });
    
        // tradeDate를 기준으로 역순 정렬
        const sortedKeys = Object.keys(groupedData).sort((a, b) => {
            return new Date(b) - new Date(a);
        });
    
        const result = [];
        sortedKeys.forEach((dateKey) => {
          // tradeTime을 기준으로 정렬
            const sortedHistories = groupedData[dateKey].sort((a, b) => {
                const timeA = convertTimeToSeconds(a.tradeTime);
                const timeB = convertTimeToSeconds(b.tradeTime);
                return timeB - timeA;
            });
    
          // tradeDate와 묶인 블록을 생성
            const dateBlock = {
                tradeDate: dateKey,
                histories: sortedHistories,
            };
            result.push(dateBlock);
        });
        return result;
    };

    // tradeTime을 초로 변환하는 함수
    const convertTimeToSeconds = (timeStr) => {
        const [hour, minute, second] = timeStr.split(":").map(Number);
        return hour * 3600 + minute * 60 + second;
    };

    const handleOpenModal = (history) => {
        setSelectedHistory(history);
    };

    const handleCloseModal = () => {
        setSelectedHistory(null);
    };

    return (
        <div>
            <div>
                <p>{account.bank} {account.number}</p>
                <p>{account.balance}</p>
            </div>

            {beforeCategorized.map((dateBlock, index) => (
                <div key={dateBlock.tradeDate}>
                    <h2>{dateBlock.tradeDate}</h2>
                    {dateBlock.histories.map((history, historyIndex) => (
                        <div
                            key={history.id}
                            style={{ cursor : "pointer" }}
                            onClick={() => handleOpenModal(history)} //클릭하면 모달 open
                        >
                            {history.tradeTime} {history.content}({history.briefs}) -{history.withdraw}
                        </div>
                    ))}

                    {/* 모달 창 */}
                    {selectedHistory && (
                        <div className={`${modalStyle.modal}`}>
                            <h3>거래내역 분류 모달</h3>
                            <p>{selectedHistory.id} : {selectedHistory.briefs}</p>
                        
                        {/* 분류 버튼 */}
                        <button onClick={handleCloseModal}>닫기</button>
                        </div>
                    )}

                </div>
            ))}
        </div>
    )
}

export default TradeHistoryView;
