import React from "react";
import Subsidies from "../../components/CustomProducts/Subsidies.js";
import FinanceProducts from "../../components/CustomProducts/FinanceProducts.js";
import SupportPrograms from "../../components/CustomProducts/SupportPrograms.js";
import NavBar from "../../components/Footer.js";
import Header from "../../components/Header.js";


function CustomProducts() {
  
  return (
    <div>
      <Header title="쏠쏠한 맞춤 정보" />
      <div className="customProducts">
        <Subsidies />
        <FinanceProducts />
        <SupportPrograms />
      </div>
      <NavBar />
    </div>
  );
}

export default CustomProducts;
