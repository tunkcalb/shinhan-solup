import React from "react";
import Subsidies from '../../components/CustomProducts/Subsidies.js'
import FinanceProducts from '../../components/CustomProducts/FinanceProducts.js'
import SupportPrograms from '../../components/CustomProducts/SupportPrograms.js'

function CustomProducts () {
    return (
        <div>
            <Subsidies />
            <FinanceProducts />
            <SupportPrograms />
        </div>
    )
}

export default CustomProducts;