package com.yang.jupiter.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class LocationStats {
    private String state;
    private String country;
    private int latestTotalCases; // 오늘
    private int diffFromPrevDay; // 증가량
}
