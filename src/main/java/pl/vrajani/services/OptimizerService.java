package pl.vrajani.services;

import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.services.analyser.StatsAnalyser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimizerService {
    public void categorizeStocks(List<StatsOfInterest> statsOfInterestList, List<StatsOfInterest> suggestedBuys, List<StatsOfInterest> suggestedSells, List<StatsOfInterest> suggestedHolds) {
        statsOfInterestList.parallelStream().forEach(statsOfInterest -> {
            boolean isBuyCandidate = isBuyCandidate(statsOfInterest);
            boolean isSellCandidate = isSellCandidate(statsOfInterest);

            if (isBuyCandidate){
                suggestedBuys.add(statsOfInterest);
            } else if (isSellCandidate){
                suggestedSells.add(statsOfInterest);
            } else if (!isBuyCandidate && !isSellCandidate){
                suggestedHolds.add(statsOfInterest);
            }
        });
    }

    private boolean isBuyCandidate(StatsOfInterest statsOfInterest) {
        StatsAnalyser analyser = StatsAnalyser.getAnalyser("buy");
        return analyser.analyse(statsOfInterest);
    }

    private boolean isSellCandidate(StatsOfInterest statsOfInterest) {
        StatsAnalyser analyser = StatsAnalyser.getAnalyser("sell");
        return analyser.analyse(statsOfInterest);
    }

    public Map<String, Float> getDividendStocks(List<StatsOfInterest> statsOfInterestList) {

        Map<String, Float> returnMap = new HashMap<>();
        statsOfInterestList.parallelStream()
                .filter(statsOfInterest -> statsOfInterest.getDividendYield().compareTo(BigDecimal.valueOf(1.75)) > 0)
                .forEach(statsOfInterest -> returnMap.put(statsOfInterest.getCompanyName(), statsOfInterest.getDividendYield().floatValue()));
        return returnMap;
    }
}
