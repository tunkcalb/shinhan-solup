import { useEffect , useState } from "react";
import { useSelector } from "react-redux";

function TradeHistoryView () {
    const [beforeCategorized, setBeforeCategorized] = useState([]);

    useEffect(() => {
        const getBeforeCategorized = async () => {
            try {
                const response = await fetch("/account/2/not-categorized-withdraw");
                const data = await response.json();
                setBeforeCategorized(data.data)
                // tradeDate끼리 그룹화 하고 각 그룹은  tradeTime순으로 정렬
                
            } catch (error) {
                console.log(error);
            }
        };
        getBeforeCategorized();
        console.log(beforeCategorized);
    }, [])

    return (
        <div>
            {beforeCategorized.map(history => {
                return (
                    <div key={history.id}>
                    {history.tradeDate} : {history.content}-{history.withdraw}
                </div>
                );
            })}
        </div>
    )
}

export default TradeHistoryView;
