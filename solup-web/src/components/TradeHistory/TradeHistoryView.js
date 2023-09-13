import { useEffect , useState } from "react";
import { useSelector } from "react-redux";

function TradeHistoryView () {
    const [beforeCategorized, setBeforeCategorized] = useState([]);
    const [account, setAccount] = useState();

    useEffect(() => {
        const fetchData = async () => {
            const [accountData, categorizedData] = await Promise.all([
                getAccountData(), getBeforeCategorized()
            ]);
            setAccount(accountData);
            setBeforeCategorized(categorizedData);
        }
        console.log(account);
    }, []);

    const getBeforeCategorized = async () => {
        try {
            const response = await fetch("/account/2/not-categorized-withdraw");
            const data = await response.json();
            const sortedData = groupAndSortData(data.data);
            return sortedData;
        } catch (error) {
            console.log(error);
        }
    };
    
    const getAccountData = async () => {
        try {
            const response = await fetch("/account/2");
            const data = await response.json();
            return data;
        } catch (error) {
            console.log(error);
        }
    }

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
                const timeA =
                    a.tradeTime.hour * 3600 +
                    a.tradeTime.minute * 60 +
                    a.tradeTime.second;
                const timeB =
                    b.tradeTime.hour * 3600 +
                    b.tradeTime.minute * 60 +
                    b.tradeTime.second;
                return timeA - timeB;
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

    return (
        <div>
            {/* <div>
                <div>{account.bank} {account.number}</div>
                <h3>{account.balance}</h3>
            </div> */}

            {beforeCategorized.map((dateBlock) => (
                <div key={dateBlock.tradeDate}>
                    <h2>{dateBlock.tradeDate}</h2>
                    {dateBlock.histories.map((history) => (
                        <div key={history.id}>
                            {history.tradeTime.hour}:{history.tradeTime.minute}:{history.tradeTime.second} - {history.content}-{history.withdraw}
                        </div>
                    ))}
                </div>
            ))}
        </div>
    )
}

export default TradeHistoryView;
