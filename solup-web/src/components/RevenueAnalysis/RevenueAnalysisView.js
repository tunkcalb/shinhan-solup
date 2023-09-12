import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";

function RevenueAnalysisView () {
    const isAccountRegistered = useSelector((state) => state.isAccountRegistered);
    const [accountData, setAccountData] = useState(null);
    const [RevenueAnalysisData, setRevenueAnalysisData] = useState(null);
    const userId = useSelector((state) => state.userId);
    console.log(userId);
    // const data = axios.get(`http://54.206.147.12/user/revenue/analysis/${userId}`).then(res => {console.log(res.data)})
    
    useEffect(() => {
        const data = axios.get(`http://localhost:8080/user/revenue/analysis/1`).then(res => {setRevenueAnalysisData(res.data.data)})
    }, [])
    return (
        <div>
            <h2>이번달 매출</h2>
            {RevenueAnalysisData && (<p>{RevenueAnalysisData.monthRevenue}</p>)}
        </div>
    )
}

export default RevenueAnalysisView;


