package pl.vrajani.services;

import pl.vrajani.models.StatsOfInterest;

public abstract class StatsAnalyser {
    public boolean analyse(StatsOfInterest statsOfInterest) {
        boolean isGoodday5ChangePercent = isGoodDay5ChangePercent(statsOfInterest);
        boolean isGoodMonth1ChangePercent = isGoodMonth1ChangePercent(statsOfInterest);
        boolean isGoodMonth3ChangePercent = isGoodmonth3ChangePercent(statsOfInterest);
        boolean isGoodDay200MovingAvg = isGoodDay200MovingAvg(statsOfInterest);
        boolean isCloseTo52WeekLow = isCloseTo52WeekLow(statsOfInterest);
        return isCloseTo52WeekLow || isGoodday5ChangePercent || isGoodMonth1ChangePercent ||
                isGoodMonth3ChangePercent || isGoodDay200MovingAvg;
    }
    private final static BuyAnalyser buyAnalyser = new BuyAnalyser();
    private final static SellAnalyser sellAnalyser = new SellAnalyser();

    public static StatsAnalyser getAnalyser(String type){
        if (type.equalsIgnoreCase("buy")){
            return buyAnalyser;
        }else if (type.equalsIgnoreCase("sell")){
            return sellAnalyser;
        }
        System.out.println("Invalid Analyser Requested!!!");
        return null;
    }

    protected abstract boolean isCloseTo52WeekLow(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodDay200MovingAvg(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodmonth3ChangePercent(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodMonth1ChangePercent(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodDay5ChangePercent(StatsOfInterest statsOfInterest);
}
