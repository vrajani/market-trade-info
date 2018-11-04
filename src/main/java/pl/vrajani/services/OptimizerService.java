package pl.vrajani.services;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import pl.vrajani.models.Config;
import pl.vrajani.models.CurrentOwnings;
import pl.vrajani.models.Reason;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;
import pl.vrajani.services.analyser.StatsAnalyser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimizerService {

    private Config config;
    public OptimizerService(Config config) {
        this.config = config;
    }

    public StockResponse categorizeStocks(StatsOfInterest statsOfInterest, List<StockResponse> suggestedBuys, List<StockResponse>
            suggestedSells, List<StockResponse> suggestedHolds, CurrentOwnings currentOwning) {

        StockResponse stockResponse = new StockResponse(statsOfInterest.getCompanyName(), statsOfInterest.getLastPrice(),
                currentOwning, Reason.UNKNOWN, StockResponse.CLASSIFICATION.UNDECIDED);
        boolean isSellCandidate = isSellCandidate(statsOfInterest, stockResponse);
        if (isSellCandidate){
            //if (stockResponse.getCurrentOwnings().getAveragePrice().compareTo(BigDecimal.ZERO) == 1){
                suggestedSells.add(stockResponse);
            //}
        } else {
            boolean isBuyCandidate = isBuyCandidate(statsOfInterest, stockResponse);
            if (isBuyCandidate){
                suggestedBuys.add(stockResponse);
            } else {//if(stockResponse.getCurrentOwnings().getAveragePrice().compareTo(BigDecimal.ZERO) == 1){
                suggestedHolds.add(stockResponse);
            }
        }
        return stockResponse;

        //add current Holding
    }

    private boolean isBuyCandidate(StatsOfInterest statsOfInterest, StockResponse stockResponse) {
        StatsAnalyser analyser = StatsAnalyser.getAnalyser("buy", config);
        return analyser.analyse(statsOfInterest, stockResponse);
    }

    private boolean isSellCandidate(StatsOfInterest statsOfInterest, StockResponse stockResponse) {
        StatsAnalyser analyser = StatsAnalyser.getAnalyser("sell", config);
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
