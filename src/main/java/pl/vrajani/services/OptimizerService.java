package pl.vrajani.services;

import pl.vrajani.models.REASON;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;
import pl.vrajani.services.analyser.StatsAnalyser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimizerService {
    public void categorizeStocks(List<StatsOfInterest> statsOfInterestList, List<StockResponse> suggestedBuys, List<StockResponse> suggestedSells, List<StatsOfInterest> suggestedHolds) {
        statsOfInterestList.parallelStream().forEach(statsOfInterest -> {
            StockResponse stockResponse = new StockResponse(statsOfInterest.getCompanyName(), statsOfInterest.getLastPrice(),
                    REASON.UNKNOWN, StockResponse.CLASSIFICATION.UNDECIDED);
            boolean isBuyCandidate = isBuyCandidate(statsOfInterest, stockResponse);
            boolean isSellCandidate = isSellCandidate(statsOfInterest, stockResponse);

            if (isBuyCandidate){
                suggestedBuys.add(stockResponse);
            } else if (isSellCandidate){
                suggestedSells.add(stockResponse);
            } else {
                suggestedHolds.add(statsOfInterest);
            }
        });
    }

    private boolean isBuyCandidate(StatsOfInterest statsOfInterest, StockResponse stockResponse) {
        StatsAnalyser analyser = StatsAnalyser.getAnalyser("buy");
        return analyser.analyse(statsOfInterest, stockResponse);
    }

    private boolean isSellCandidate(StatsOfInterest statsOfInterest, StockResponse stockResponse) {
        StatsAnalyser analyser = StatsAnalyser.getAnalyser("sell");
        return analyser.analyse(statsOfInterest, stockResponse);
    }

    public Map<String, Float> getDividendStocks(List<StatsOfInterest> statsOfInterestList) {

        Map<String, Float> returnMap = new HashMap<>();
        statsOfInterestList.parallelStream()
                .filter(statsOfInterest -> statsOfInterest.getDividendYield().compareTo(BigDecimal.valueOf(1.75)) > 0)
                .forEach(statsOfInterest -> returnMap.put(statsOfInterest.getCompanyName(), statsOfInterest.getDividendYield().floatValue()));
        return returnMap;
    }
}
