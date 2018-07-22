package pl.vrajani.services;

import pl.vrajani.models.StatsOfInterest;

public class BuyAnalyser extends StatsAnalyser {


    protected boolean isCloseTo52WeekLow(StatsOfInterest statsOfInterest) {
        return false;
    }

    protected boolean isGoodDay200MovingAvg(StatsOfInterest statsOfInterest) {
        return true;
    }

    protected boolean isGoodmonth3ChangePercent(StatsOfInterest statsOfInterest) {
        return false;
    }

    protected boolean isGoodMonth1ChangePercent(StatsOfInterest statsOfInterest) {
        return false;
    }

    protected boolean isGoodDay5ChangePercent(StatsOfInterest statsOfInterest) {
        return false;
    }
}
