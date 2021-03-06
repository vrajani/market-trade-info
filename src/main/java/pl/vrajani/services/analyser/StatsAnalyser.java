package pl.vrajani.services.analyser;

import pl.vrajani.models.Config;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;

public abstract class StatsAnalyser {
    public boolean analyse(StatsOfInterest statsOfInterest, StockResponse stockResponse) {
        boolean isGoodday5ChangePercent = isGoodDay5ChangePercent(statsOfInterest);
        boolean isGoodMonth1ChangePercent = isGoodMonth1ChangePercent(statsOfInterest);
        boolean isGoodMonth3ChangePercent = isGoodmonth3ChangePercent(statsOfInterest);
        boolean isGoodDay50MovingAvg = isGoodDay50MovingAvg(statsOfInterest);
        boolean isCloseTo52WeekExtreme = isCloseTo52WeekExtreme(statsOfInterest);
        return getAnalysisResults(stockResponse, isCloseTo52WeekExtreme, isGoodday5ChangePercent, isGoodMonth1ChangePercent,
                isGoodMonth3ChangePercent, isGoodDay50MovingAvg);
    }

    protected abstract boolean getAnalysisResults(StockResponse stockResponse, boolean isCloseTo52WeekLow, boolean isGoodday5ChangePercent, boolean isGoodMonth1ChangePercent, boolean isGoodMonth3ChangePercent, boolean isGoodDay200MovingAvg);

    public static StatsAnalyser getAnalyser(String type, Config config){
        if (type.equalsIgnoreCase("buy")){
            return new BuyAnalyser(config.getBuyConfig());
        }else if (type.equalsIgnoreCase("sell")){
            return new SellAnalyser(config.getSellConfig());
        }
        System.out.println("Invalid Analyser Requested!!!");
        return null;
    }

    protected abstract boolean isCloseTo52WeekExtreme(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodDay50MovingAvg(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodmonth3ChangePercent(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodMonth1ChangePercent(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodDay5ChangePercent(StatsOfInterest statsOfInterest);
}
