package pl.vrajani.services.analyser;

import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.services.analyser.StatsAnalyser;

import java.math.RoundingMode;

public class SellAnalyser extends StatsAnalyser {

    @Override
    protected boolean getAnalysisResults(boolean isCloseTo52Weekhigh, boolean isGoodDay5ChangePercent, boolean isGoodMonth1ChangePercent, boolean isGoodMonth3ChangePercent, boolean isGoodDay50MovingAvg) {
        boolean isLowThisWeek = isGoodMonth1ChangePercent && isGoodMonth3ChangePercent && isGoodDay5ChangePercent;
        boolean isLowThisMonth = isGoodMonth3ChangePercent && !isGoodMonth1ChangePercent && isGoodDay5ChangePercent;
        boolean isLowThisMonthAndWeek = !isGoodMonth3ChangePercent && isGoodMonth1ChangePercent && isGoodDay5ChangePercent;

//        System.out.println("week: "+ isLowThisWeek);
//        System.out.println("month: "+ isLowThisMonth);
//        System.out.println("52week: "+ isCloseTo52Weekhigh);
//        System.out.println("50dayavg: "+ isGoodDay50MovingAvg);
        return isCloseTo52Weekhigh || isLowThisMonthAndWeek || isLowThisMonth || isLowThisWeek || isGoodDay50MovingAvg;

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
