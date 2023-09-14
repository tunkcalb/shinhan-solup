import { useEffect , useState } from "react";
import { useSelector } from "react-redux";
import ClassificationModal from "./ClassificationModal";

function TradeHistoryView () {
    const [beforeCategorized, setBeforeCategorized] = useState([]);
    const [account, setAccount] = useState({});
    const [selectedHistoryIndex, setSelectedHistoryIndex] = useState(null);
    const [currentHistoryIndex, setCurrentHistoryIndex] = useState(0); // 현재 선택된 거래내역 인덱스
    const [classifiedCount, setClassifiedCount] = useState(0); // 분류된 거래내역 개수
    
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

    const loadNextHistory = async () => {
        try {
            const response = await fetch(`/account/${userId}/not-categorized-withdraw`);
            const data = await response.json()
            const sortedData = groupAndSortData(data.data);

            const nextIndex = selectedHistoryIndex + 1;
            if (nextIndex < sortedData.length) {
                setSelectedHistoryIndex(nextIndex);
            } else {
                setSelectedHistoryIndex(null);
            }
            setBeforeCategorized(sortedData);
        } catch (error) {
            console.log(error);
        }
    }

    const classifyTradeHistory = async (data) => {
        try {
            const response = await fetch(`/account/{userId}/trade-history`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });
            if (response.ok) return true;
            else return false;
        } catch (error) {
            console.log(error);
            return false;
        }
    };

    const handleOpenModal = (historyIndex) => {
        setCurrentHistoryIndex(historyIndex);
    }

    const handleCloseModal = () => {
        setCurrentHistoryIndex(0); // 모달 닫기 후 초기화
    };

    const handleClassify = async (classifiedHistory) => {
        const isSuccess = await classifyTradeHistory(classifiedHistory);

        if (isSuccess) setClassifiedCount(classifiedCount + 1);
        else console.log("API 호출 실패");
    };

    const handleNextClick = () => {
        if (currentHistoryIndex < beforeCategorized.length - 1) handleOpenModal(currentHistoryIndex + 1);
        else setClassifiedCount(beforeCategorized.length);
    };

    return (
        <div>
            {currentHistoryIndex !== null && currentHistoryIndex < beforeCategorized.length && (
                <ClassificationModal
                    history={beforeCategorized[currentHistoryIndex]}
                    onClose={handleCloseModal}    
                    onClassify={handleClassify}
                    userId={userId}
                />
            )}

            <div>
                {classifiedCount > 0 && classifiedCount < beforeCategorized.length && (
                    <p>{classifiedCount}개의 동일한 거래내역을 동시에 분류해요</p>
                )}
                {classifiedCount === beforeCategorized.length && (
                    <p>분류가 완료됐어요</p>
                )}
                
                {beforeCategorized.map((dateBlock, index) => (
                    <div key={dateBlock.tradeDate}>
                        <h2>{dateBlock.tradeDate}</h2>
                        {dateBlock.histories.map((history, historyIndex) => (
                            <div
                                key={history.id}
                                onClick={() => handleOpenModal(index)}
                                style={{ cursor : "pointer" }}
                            >
                                {history.tradeTime} {history.content}({history.briefs}) -{history.withdraw}
                            </div>
                        ))}
                    </div>
                ))}

                {currentHistoryIndex < beforeCategorized.length && (
                    <button onClick={handleNextClick}>다음으로 이동</button>
                )}

                {classifiedCount === beforeCategorized.length && (
                    <button onClick={() => setCurrentHistoryIndex(null)}>닫기</button>
                )}
            </div>
        </div>
    )
}

export default TradeHistoryView;
