package com.example.solup.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategorizedDto {
    private Integer income;
    private Integer fixed;
    private Integer variable;
    private Integer netProfit;

}
