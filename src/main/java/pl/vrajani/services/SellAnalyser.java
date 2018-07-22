package pl.vrajani.services;

import pl.vrajani.models.StatsOfInterest;

public class SellAnalyser extends StatsAnalyser{

    @Override
    protected boolean getAnalysisResults(boolean isCloseTo52WeekLow, boolean isGoodDay5ChangePercent, boolean isGoodMonth1ChangePercent, boolean isGoodMonth3ChangePercent, boolean isGoodDay50MovingAvg) {
        return false;
    }

    @Override
    protected boolean isCloseTo52WeekLow(StatsOfInterest statsOfInterest) {
        return false;
    }

    @Override
    protected boolean isGoodDay50MovingAvg(StatsOfInterest statsOfInterest) {
        return false;
    }

    @Override
    protected boolean isGoodmonth3ChangePercent(StatsOfInterest statsOfInterest) {
        return false;
    }

    @Override
    protected boolean isGoodMonth1ChangePercent(StatsOfInterest statsOfInterest) {
        return false;
    }

    @Override
    protected boolean isGoodDay5ChangePercent(StatsOfInterest statsOfInterest) {
        return false;
    }
}
