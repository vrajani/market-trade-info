package pl.vrajani.services;

import pl.vrajani.models.Reason;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;
import pl.vrajani.services.analyser.StatsAnalyser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimizerService {
    public void categorizeStocks(StatsOfInterest statsOfInterest, List<StockResponse> suggestedBuys, List<StockResponse> suggestedSells, List<StockResponse> suggestedHolds, Map<String, BigDecimal> currentOwnings) {
            BigDecimal yourPrice = currentOwnings.get(statsOfInterest.getSymbol());
            if (yourPrice == null){
                yourPrice = BigDecimal.ZERO;
            }
            StockResponse stockResponse = new StockResponse(statsOfInterest.getCompanyName(), statsOfInterest.getLastPrice(),
                    yourPrice, Reason.UNKNOWN, StockResponse.CLASSIFICATION.UNDECIDED);
            boolean isBuyCandidate = isBuyCandidate(statsOfInterest, stockResponse);
            if (isBuyCandidate){
                suggestedBuys.add(stockResponse);
            } else {
                boolean isSellCandidate = isSellCandidate(statsOfInterest, stockResponse);
                if (isSellCandidate){
                    suggestedSells.add(stockResponse);
                } else {
                    suggestedHolds.add(stockResponse);
                }
            }
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
