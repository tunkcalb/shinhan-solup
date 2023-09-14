import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";

function RevenueAnalysisView() {
  const isAccountRegistered = useSelector((state) => state.isAccountRegistered);
  const [accountData, setAccountData] = useState(null);
  const [RevenueAnalysisData, setRevenueAnalysisData] = useState(null);
  const userId = useSelector((state) => state.userId);
  console.log(userId);

  useEffect(() => {
    const data = axios.get(`/user/revenue/analysis/${userId}`).then((res) => {
      setRevenueAnalysisData(res.data.data);
    });

    console.log(RevenueAnalysisData);
  }, []);
  return (
    <div>
      {RevenueAnalysisData && (
        <div>
          <p>이번달 매출 {RevenueAnalysisData.monthRevenue}원</p>
          <p>매출분석 그래프</p>
          <ul>
            {Object.entries(RevenueAnalysisData.monthlyRevenue).map(
              ([key, value]) => (
                <li key={key}>
                  {key} : {value}
                </li>
              )
            )}
          </ul>

          <p>카드 매출 분석</p>
          <ul>
            {Object.entries(RevenueAnalysisData.cardRevenue).map(
              ([key, value]) => (
                <li key={key}>
                  {key}: {value}
                </li>
              )
            )}
            <li>배달 매출 : {RevenueAnalysisData.deliverySum}</li>
            <li>현금 매출 : {RevenueAnalysisData.cash}</li>
          </ul>

          <p>카테고리별 매출 분석</p>
          <ul>
            {Object.entries(RevenueAnalysisData.analysis).map(
              ([key, value]) => (
                <li key={key}>
                  {key}: {value}
                </li>
              )
            )}
          </ul>
        </div>
      )}
    </div>
  );
}

export default RevenueAnalysisView;
