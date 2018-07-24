package pl.vrajani.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

    private List<StockResponse> suggestedBuys = new ArrayList<>();
    private List<StockResponse> suggestedSells = new ArrayList<>();
    private List<String> suggestedHolds = new ArrayList<>();
    private List<String> earningsComingUp = new ArrayList<>();
    private Map<String, Float> bestDividendStocks = new HashMap<>();

    public Response(){}

    public Response(List<StockResponse> suggestedBuys, List<StockResponse> suggestedSells, List<StatsOfInterest>
            suggestedHolds, List<StatsOfInterest> earningsComingUp, Map<String, Float> bestDividendStocks){
        this.suggestedBuys = suggestedBuys;
        this.suggestedSells = suggestedSells;

        extractNameFromList(suggestedHolds, this.suggestedHolds);
        extractNameFromList(earningsComingUp, this.earningsComingUp);
        this.bestDividendStocks = bestDividendStocks;
    }

    private void extractNameFromList(List<StatsOfInterest> suggestedStats, List<String> suggestedNames) {
        suggestedStats.parallelStream().forEach(statsOfInterest -> suggestedNames.add(statsOfInterest.getCompanyName()));
    }

    public List<StockResponse> getSuggestedBuys() {
        return suggestedBuys;
    }

    public void setSuggestedBuys(List<StockResponse> suggestedBuys) {
        this.suggestedBuys = suggestedBuys;
    }

    public List<StockResponse> getSuggestedSells() {
        return suggestedSells;
    }

    public void setSuggestedSells(List<StockResponse> suggestedSells) {
        this.suggestedSells = suggestedSells;
    }

    public List<String> getSuggestedHolds() {
        return suggestedHolds;
    }

    public void setSuggestedHolds(List<String> suggestedHolds) {
        this.suggestedHolds = suggestedHolds;
    }

    public List<String> getEarningsComingUp() {
        return earningsComingUp;
    }

    public void setEarningsComingUp(List<String> earningsComingUp) {
        this.earningsComingUp = earningsComingUp;
    }

    public Map<String, Float> getBestDividendStocks() {
        return bestDividendStocks;
    }

    public void setBestDividendStocks(Map<String, Float> bestDividendStocks) {
        this.bestDividendStocks = bestDividendStocks;
    }
}
