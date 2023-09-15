import axios from "axios";
import React, { useEffect, useState, useRef } from "react";
import { useSelector } from "react-redux";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

export const data11 = {
  labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
  datasets: [
    {
      label: '# of Votes',
      data: [12, 19, 3, 5, 2, 3],
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(255, 159, 64, 0.2)',
      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)',
      ],
      borderWidth: 1,
    },
  ],
};

function RevenueAnalysisView() {
  const isAccountRegistered = useSelector((state) => state.isAccountRegistered);
  const [accountData, setAccountData] = useState(null);
  const [RevenueAnalysisData, setRevenueAnalysisData] = useState(null);
  const userId = useSelector((state) => state.userId);
  const myRef = useRef(null);

  console.log(userId);

  useEffect(() => {
    const data = axios.get(`/user/revenue/analysis/${userId}`).then((res) => {
      setRevenueAnalysisData(res.data.data);
    });

    console.log(RevenueAnalysisData);
  }, []);
  return (
    <div>
      <Doughnut data={data11} />;
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
