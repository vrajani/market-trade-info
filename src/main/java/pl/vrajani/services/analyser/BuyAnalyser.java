package pl.vrajani.services.analyser;

import pl.vrajani.models.StatsOfInterest;
import java.math.RoundingMode;

public class BuyAnalyser extends StatsAnalyser {

    @Override
    protected boolean getAnalysisResults(boolean isCloseTo52WeekLow, boolean isGoodDay5ChangePercent, boolean isGoodMonth1ChangePercent, boolean isGoodMonth3ChangePercent, boolean isGoodDay50MovingAvg) {
        boolean isLowThisWeek = isGoodMonth1ChangePercent && isGoodMonth3ChangePercent && isGoodDay5ChangePercent;
        boolean isLowThisMonth = isGoodMonth3ChangePercent && !isGoodMonth1ChangePercent && isGoodDay5ChangePercent;

//        System.out.println("week: "+ isLowThisWeek);
//        System.out.println("month: "+ isLowThisMonth);
//        System.out.println("52week: "+ isCloseTo52WeekLow);
//        System.out.println("50dayavg: "+ isGoodDay50MovingAvg);
        return isCloseTo52WeekLow || isLowThisMonth || isLowThisWeek || isGoodDay50MovingAvg;
    }

    @Override
    protected boolean isCloseTo52WeekExtreme(StatsOfInterest statsOfInterest) {
        return ((statsOfInterest.getLastPrice().subtract(statsOfInterest.getWeek52low())).divide(statsOfInterest.getLastPrice(),2, RoundingMode.HALF_UP)).floatValue() <= 0.05;
    }

    @Override
    protected boolean isGoodDay50MovingAvg(StatsOfInterest statsOfInterest) {
        return (statsOfInterest.getDay50MovingAvg().compareTo(statsOfInterest.getLastPrice()) >= 0)
                && ((statsOfInterest.getDay50MovingAvg().subtract(statsOfInterest.getLastPrice())).divide(statsOfInterest.getLastPrice(),2, RoundingMode.HALF_UP)).floatValue() >= 0.045;
    }


    @Override
    protected boolean isGoodmonth3ChangePercent(StatsOfInterest statsOfInterest) {
        return statsOfInterest.getMonth3ChangePercent().floatValue() >= 0.075;
    }

    @Override
    protected boolean isGoodMonth1ChangePercent(StatsOfInterest statsOfInterest) {
        return statsOfInterest.getMonth1ChangePercent().floatValue() >= 0.035;
    }

    @Override
    protected boolean isGoodDay5ChangePercent(StatsOfInterest statsOfInterest) {
        return statsOfInterest.getDay5ChangePercent().floatValue() < 0.005;
    }
}
