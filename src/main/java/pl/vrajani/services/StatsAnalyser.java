package pl.vrajani.services;

import pl.vrajani.models.StatsOfInterest;

public abstract class StatsAnalyser {
    public boolean analyse(StatsOfInterest statsOfInterest) {
        boolean isGoodday5ChangePercent = isGoodDay5ChangePercent(statsOfInterest);
        boolean isGoodMonth1ChangePercent = isGoodMonth1ChangePercent(statsOfInterest);
        boolean isGoodMonth3ChangePercent = isGoodmonth3ChangePercent(statsOfInterest);
        boolean isGoodDay50MovingAvg = isGoodDay50MovingAvg(statsOfInterest);
        boolean isCloseTo52WeekExtreme = isCloseTo52WeekExtreme(statsOfInterest);
        return getAnalysisResults(isCloseTo52WeekExtreme, isGoodday5ChangePercent, isGoodMonth1ChangePercent,
                isGoodMonth3ChangePercent, isGoodDay50MovingAvg);
    }

    protected abstract boolean getAnalysisResults(boolean isCloseTo52WeekLow, boolean isGoodday5ChangePercent, boolean isGoodMonth1ChangePercent, boolean isGoodMonth3ChangePercent, boolean isGoodDay200MovingAvg);

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

    protected abstract boolean isCloseTo52WeekExtreme(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodDay50MovingAvg(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodmonth3ChangePercent(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodMonth1ChangePercent(StatsOfInterest statsOfInterest);
    protected abstract boolean isGoodDay5ChangePercent(StatsOfInterest statsOfInterest);
}
