package com.example.solup.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategorizedDto {
    // 총 수익
    private Integer income;

    // 고정비
    private Integer fixed;

    // 변동비
    private Integer variable;

    // 순수익 : 수익 - (고정비 + 변동비)
    private Integer netProfit;

}
