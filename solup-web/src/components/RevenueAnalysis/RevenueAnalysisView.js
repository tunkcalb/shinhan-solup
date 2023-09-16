import React, { useState, useEffect } from "react";
import axios from "axios";
import ReactApexChart from "react-apexcharts";
import { useSelector } from "react-redux";
import Loading from "../../pages/Loading";
function RevenueAnalysisView() {
  const [isLoading, setIsLoading] = useState(true);
  const [RevenueAnalysisData, setRevenueAnalysisData] = useState([]);
  const [SalesAnalysisData, setSalesAnalysisData] = useState([]);
  const userId = useSelector((state) => state.userId);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get(`/account/${userId}/categorized-history`);
        const data = res.data.data;
        const saleres = await axios.get(`/account/${userId}/trade-history`);
        const saledata = saleres.data.data;

        if (data) {
          setRevenueAnalysisData(data);
        } else {
          console.error("데이터가 없습니다.");
        }

        if (saledata) {
          setSalesAnalysisData(saledata);
          setIsLoading(false);
        } else {
          console.error("데이터가 없습니다.");
        }
      } catch (error) {
        console.error("데이터를 가져오는 동안 오류가 발생했습니다:", error);
        setIsLoading(false);
      }
    };

    fetchData();
  }, [userId]);

  // 매출 현황
  const radialBarData = SalesAnalysisData?.filter(
    (item) => item.category === 1
  );
  const radialBarTotal = radialBarData
    ? radialBarData.reduce((acc, item) => acc + item.deposit, 0)
    : 0;

  // 전월 매출 데이터
  const previousData = radialBarData?.filter((item) =>
    item.tradeDate.startsWith("2023-08")
  );
  const previousTotal = previousData?.reduce(
    (acc, item) => acc + item.deposit,
    0
  );

  // 현재 매출 데이터
  const currentData = radialBarData?.filter((item) =>
    item.tradeDate.startsWith("2023-09")
  );
  const currentTotal = currentData?.reduce(
    (acc, item) => acc + item.deposit,
    0
  );

  // 전월 매출 대비 현재 매출 비율 계산
  const ratio = (currentTotal / previousTotal) * 100;

  // 원형 프로그레스 바 설정
  const radialBarOptions = {
    chart: {
      type: "radialBar",
      height: 300,
      width: 300,
    },
    plotOptions: {
      radialBar: {
        hollow: {
          size: "50%", // 중앙 부분이 비어있는 크기 설정
        },
        dataLabels: {
          name: {
            fontSize: "22px",
            color: "002D8A",
          },
          value: {
            bold: "true",
            fontSize: "16px",
          },
        },
        track: {
          background: "#ccc", // 원형 바깥쪽 트랙 색상 설정
          strokeWidth: "100%", // 원형 바깥쪽 트랙 너비 설정
        },
      },
    },
    fill: {
      type: "solid",
      colors: ["#002D82"], // 바의 색상 설정
    },
    series: [ratio.toFixed(0)], // 원형 프로그레스 바의 값 설정
    labels: ["전월 대비 매출"],
  };

  // 매출 시계열 그래프
  const lineData = SalesAnalysisData?.filter(
    (item) => item.category === 1
  ).sort((a, b) => {
    // tradeDate와 tradeTime을 합쳐서 비교
    const dateA = new Date(`${a.tradeDate}T${a.tradeTime}`).getTime();
    const dateB = new Date(`${b.tradeDate}T${b.tradeTime}`).getTime();
    return dateA - dateB;
  });

  const lineOptions = {
    chart: {
      type: "line",
      height: 300,
      width: 500,
    },
    xaxis: {
      type: "datetime",
    },
    yaxis: {
      min: 0,
      max: 200000,
    },
    stroke: {
      curve: "smooth",
    },
    colors: ["#002D82"],
  };
  const lineSeries = [
    {
      data: lineData?.map((item) => ({
        x: new Date(`${item.tradeDate}T${item.tradeTime}`).getTime(),
        y: item.deposit,
      })),
    },
  ];

  // 파이차트
  const pieChartData = SalesAnalysisData?.filter((item) => item.category === 1);
  console.log(SalesAnalysisData);
  const groupedPieChartData = pieChartData?.reduce((acc, item) => {
    if (!acc[item.briefs]) {
      acc[item.briefs] = 0;
    }
    acc[item.briefs] += item.deposit;
    return acc;
  }, {});

  const pieChartLabels = Object.keys(groupedPieChartData).map((label) => {
    return label.replace("결제 입금", "");
  });

  const pieChartOptions = {
    chart: {
      type: "pie",
      height: 300,
      width: 300,
    },
    labels: pieChartLabels,
    colors: ["#001A5F", "#002D82", "#A7AED3"], // 파이차트(8월매출) 색깔 바꿔주세요
  };

  const pieChartSeries = Object.values(groupedPieChartData);

  // 카드 분류 도넛차트
  const donutChartData = SalesAnalysisData?.filter(
    (item) => item.category === 1 && item.briefs === "카드결제 입금"
  );

  // briefs가 "카드결제 입금"이면서 category가 1인 데이터 그룹화
  const groupedDonutChartData = donutChartData?.reduce((acc, item) => {
    if (!acc[item.name]) {
      acc[item.name] = 0;
    }
    acc[item.name] += item.deposit;
    return acc;
  }, {});

  const donutChartOptions = {
    chart: {
      type: "donut",
      height: 300,
      width: 300,
    },
    labels: Object.keys(groupedDonutChartData), // name을 레이블로 설정
    colors: ["#001A5F", "#002D82", "#A7AED3"], // 도넛차트(카드매출) 색깔 바꿔주세요
  };

  const donutChartSeries = Object.values(groupedDonutChartData); // 각 name의 총 입금액을 시리즈로 설정

  // 비용 분류 바차트
  const calculateCurrentExpenses = () => {
    const currentData = RevenueAnalysisData?.filter(
      (item) => item.tradeDate.startsWith("2023-08") && item.category === 2
    );

    const groupedExpenses = currentData?.reduce((acc, item) => {
      if (!acc[item.expenseCategory]) {
        acc[item.expenseCategory] = 0;
      }
      acc[item.expenseCategory] += item.withdraw;
      return acc;
    }, {});

    // 비용을 합하여 내림차순으로 정렬
    const sortedExpenses = Object.keys(groupedExpenses)
      .map((category) => ({
        category,
        value: groupedExpenses[category],
      }))
      .sort((a, b) => b.value - a.value);

    const categories = sortedExpenses.map((expense) => expense.category);
    const values = sortedExpenses.map((expense) => expense.value);

    return {
      categories,
      values,
    };
  };

  const barChartData = calculateCurrentExpenses();

  const barChartOptions = {
    chart: {
      type: "bar",
      height: 300,
      width: 500,
    },
    plotOptions: {
      bar: {
        horizontal: true,
      },
    },
    xaxis: {
      categories: barChartData.categories,
    },
    colors: "#002D82", // 바차트(지출분석) 색깔 설정
  };

  const barChartSeries = [
    {
      data: barChartData.values,
      name: barChartData.categories,
    },
  ];

  return (
    <div>
      {isLoading ? (
        <Loading />
      ) : (
        <div>
          <h3>Radial Bar Chart (8월 매출 비율)</h3>
          <ReactApexChart
            options={radialBarOptions}
            series={[ratio.toFixed(0)]}
            type="radialBar"
          />

          <h3>Stacked Area Chart (매출 변동)</h3>
          <ReactApexChart
            options={lineOptions}
            series={lineSeries}
            type="line"
          />

          <h3>Pie Chart (8월 매출)</h3>
          <ReactApexChart
            options={pieChartOptions}
            series={pieChartSeries}
            type="pie"
          />

          <h3>Donut Chart (8월 카드결제 입금)</h3>
          <ReactApexChart
            options={donutChartOptions}
            series={donutChartSeries}
            type="donut"
          />

          <h3>Bar Chart (8월 비용)</h3>
          <ReactApexChart
            options={barChartOptions}
            series={barChartSeries}
            type="bar"
          />
        </div>
      )}
    </div>
  );
}

export default RevenueAnalysisView;
