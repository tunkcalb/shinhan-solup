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
      <Subsidies />
      <FinanceProducts />
      <SupportPrograms />
      <NavBar className="navbar" />
    </div>
  );
}

export default CustomProducts;
