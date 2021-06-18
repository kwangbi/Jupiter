package com.yang.jupiter.api.controller;

import com.yang.jupiter.api.dto.KoreaStats;
import com.yang.jupiter.api.dto.LocationStats;
import com.yang.jupiter.api.service.CoronaVirusDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("v1")
@RestController
@RequiredArgsConstructor
public class CoronaController {
    private final CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/api/cov/global")
    public List<LocationStats> getCovGloval() throws IOException {
        List<LocationStats> allStats = coronaVirusDataService.fetchVirusData();

        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

        return allStats;
    }

    @GetMapping("/api/cov/korea")
    public List<KoreaStats> getCovKorea() throws IOException {
        List<KoreaStats> koreaStatsList = coronaVirusDataService.getKoreaCovidDatas();

        return koreaStatsList;
    }
}
