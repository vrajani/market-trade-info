package pl.vrajani.services.analyser;

import pl.vrajani.models.Reason;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;

import java.math.RoundingMode;

public class SellAnalyser extends StatsAnalyser {

    @Override
    protected boolean getAnalysisResults(StockResponse stockResponse, boolean isCloseTo52Weekhigh, boolean isGoodDay5ChangePercent, boolean isGoodMonth1ChangePercent, boolean isGoodMonth3ChangePercent, boolean isGoodDay50MovingAvg) {
        boolean isLowThisWeek = isGoodMonth1ChangePercent && isGoodMonth3ChangePercent && isGoodDay5ChangePercent;
        boolean isLowThisMonth = isGoodMonth3ChangePercent && !isGoodMonth1ChangePercent && isGoodDay5ChangePercent;
        boolean isLowThisMonthAndWeek = !isGoodMonth3ChangePercent && isGoodMonth1ChangePercent && isGoodDay5ChangePercent;

        Reason reason = identifySellReason(isCloseTo52Weekhigh, isLowThisMonthAndWeek, isLowThisMonth, isLowThisWeek, isGoodDay50MovingAvg);
        if( reason != Reason.UNKNOWN){
            stockResponse.setClassification(StockResponse.CLASSIFICATION.SELL);
            stockResponse.setReason(reason.getReason());
            return true;
        }
        return false;
    }

    private Reason identifySellReason(boolean isCloseTo52Weekhigh, boolean isLowThisMonthAndWeek, boolean isLowThisMonth, boolean isLowThisWeek, boolean isGoodDay50MovingAvg) {
        if(isCloseTo52Weekhigh){
            return Reason.CLOSE_TO_52_WEEK_HIGH;
        } else if(isLowThisMonthAndWeek){
            return Reason.WEEK_AND_MONTH_HIGH;
        } else if(isLowThisWeek){
            return Reason.WEEK_HIGH;
        } else if(isLowThisMonth){
            return Reason.HIGH_MONTH;
        } else if (isGoodDay50MovingAvg){
            return Reason.BY_50_DAY_AVG;
        }
        return Reason.UNKNOWN;
    }

    @Override
    protected boolean isCloseTo52WeekExtreme(StatsOfInterest statsOfInterest) {
        return ((statsOfInterest.getWeek52high().subtract(statsOfInterest.getLastPrice())).divide(statsOfInterest.getLastPrice(),2, RoundingMode.HALF_UP)).floatValue() <= 0.01;
    }

    @Override
    protected boolean isGoodDay50MovingAvg(StatsOfInterest statsOfInterest) {
        return (statsOfInterest.getDay50MovingAvg().compareTo(statsOfInterest.getLastPrice()) < 0)
                && ((statsOfInterest.getLastPrice().subtract(statsOfInterest.getDay50MovingAvg())).divide(statsOfInterest.getLastPrice(),2, RoundingMode.HALF_UP)).floatValue() >= 0.045;
    }


    @Override
    protected boolean isGoodmonth3ChangePercent(StatsOfInterest statsOfInterest) {
        return statsOfInterest.getMonth3ChangePercent().floatValue() >= 0.155;
    }

    @Override
    protected boolean isGoodMonth1ChangePercent(StatsOfInterest statsOfInterest) {
        return statsOfInterest.getMonth1ChangePercent().floatValue() >= 0.085;
    }

    @Override
    protected boolean isGoodDay5ChangePercent(StatsOfInterest statsOfInterest) {
        return statsOfInterest.getDay5ChangePercent().floatValue() >= 0.05;
    }

}
