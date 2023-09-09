package com.example.solup.dto.revenue;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueDto {
    private String month;
    private String revenue;
}
