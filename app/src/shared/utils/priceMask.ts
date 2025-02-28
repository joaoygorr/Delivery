import { ChangeEvent } from "react";

const handlePriceChange = (e: ChangeEvent<HTMLInputElement>) => {
    const onlyNumbers = e.target.value.replace(/\D/g, "");

    if (!onlyNumbers) e.target.value = "R$ ";;

    const numericValue = ((parseInt(onlyNumbers, 10)) / 100).toFixed(2);

    e.target.value = `R$ ${numericValue.replace(".", ",").replace(/\B(?=(\d{3})+(?!\d))/g, ".")}`;
}

export default handlePriceChange;