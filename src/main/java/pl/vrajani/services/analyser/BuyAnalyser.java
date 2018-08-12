package pl.vrajani.services.analyser;

import pl.vrajani.models.BuyConfig;
import pl.vrajani.models.Reason;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;

import java.math.RoundingMode;

public class BuyAnalyser extends StatsAnalyser {

    private BuyConfig buyConfig;

    public BuyAnalyser(BuyConfig buyConfig) {
        super();
        this.buyConfig = buyConfig;
    }

    @Override
    protected boolean getAnalysisResults(StockResponse stockResponse, boolean isCloseTo52WeekLow, boolean isGoodDay5ChangePercent, boolean isGoodMonth1ChangePercent, boolean isGoodMonth3ChangePercent, boolean isGoodDay50MovingAvg) {
        boolean isLowThisWeek = isGoodMonth1ChangePercent && isGoodMonth3ChangePercent && isGoodDay5ChangePercent;
        boolean isLowThisMonth = isGoodMonth3ChangePercent && !isGoodMonth1ChangePercent && isGoodDay5ChangePercent;
        Reason reason = identifyBuyReasoning(isCloseTo52WeekLow, isLowThisMonth, isLowThisWeek, isGoodDay50MovingAvg);
        if( reason != Reason.UNKNOWN){
            stockResponse.setClassification(StockResponse.CLASSIFICATION.BUY);
            stockResponse.setReason(reason.getReason());
            return true;
        }
        return false;
    }

    private Reason identifyBuyReasoning(boolean isCloseTo52WeekLow, boolean isLowThisMonth, boolean isLowThisWeek, boolean isGoodDay50MovingAvg) {
        if(isCloseTo52WeekLow){
            return Reason.CLOSE_TO_52_WEEK_LOW;
        } else if(isLowThisWeek){
            return Reason.WEEK_LOW;
        } else if(isLowThisMonth){
            return Reason.LOW_MONTH;
        } else if (isGoodDay50MovingAvg){
            return Reason.BY_50_DAY_AVG;
        }
        return Reason.UNKNOWN;
    }

    @Override
    protected boolean isCloseTo52WeekExtreme(StatsOfInterest statsOfInterest) {
        return ((statsOfInterest.getLastPrice().subtract(statsOfInterest.getWeek52low())).divide(statsOfInterest.getLastPrice(),2, RoundingMode.HALF_UP)).floatValue() <= buyConfig.getDiffTo52WeekLow();
    }

    @Override
    protected boolean isGoodDay50MovingAvg(StatsOfInterest statsOfInterest) {
        return (statsOfInterest.getDay50MovingAvg().compareTo(statsOfInterest.getLastPrice()) >= 0)
                && ((statsOfInterest.getDay50MovingAvg().subtract(statsOfInterest.getLastPrice())).divide(statsOfInterest.getLastPrice(),2, RoundingMode.HALF_UP)).floatValue() >= buyConfig.getDiff50DayMovingGreaterThan();
    }


    @Override
    protected boolean isGoodmonth3ChangePercent(StatsOfInterest statsOfInterest) {
        return statsOfInterest.getMonth3ChangePercent().floatValue() >= buyConfig.get3MonthChangeHigherThan();
    }

    @Override
    protected boolean isGoodMonth1ChangePercent(StatsOfInterest statsOfInterest) {
        return statsOfInterest.getMonth1ChangePercent().floatValue() >= buyConfig.get1MonthChangeHigherThan();
    }

    @Override
    protected boolean isGoodDay5ChangePercent(StatsOfInterest statsOfInterest) {
        return statsOfInterest.getDay5ChangePercent().floatValue() < buyConfig.get5DayChangeLowerThan();
    }
}
