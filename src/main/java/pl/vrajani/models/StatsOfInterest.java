package pl.vrajani.models;

import pl.zankowski.iextrading4j.api.marketdata.LastTrade;
import pl.zankowski.iextrading4j.api.stocks.KeyStats;
import java.math.BigDecimal;
import java.util.List;

public class StatsOfInterest {

    private final String companyName;
    private final BigDecimal week52high;
    private final BigDecimal week52low;
    private final BigDecimal dividendRate;
    private final BigDecimal dividendYield;
    private final String symbol;
    private final BigDecimal day50MovingAvg;
    private final BigDecimal year1ChangePercent;
    private final BigDecimal ytdChangePercent;
    private final BigDecimal month6ChangePercent;
    private final BigDecimal month3ChangePercent;
    private final BigDecimal day5ChangePercent;
    private final BigDecimal day30ChangePercent;
    private final List<LastTrade> lastTrades;

    public StatsOfInterest(KeyStats keyStats, List<LastTrade> lastTrades) {
        this.companyName = keyStats.getCompanyName();
        this.week52high = keyStats.getWeek52high();
        this.week52low = keyStats.getWeek52low();
        this.dividendRate = keyStats.getDividendRate();
        this.dividendYield = keyStats.getDividendYield();
        this.symbol = keyStats.getSymbol();
        this.day50MovingAvg = keyStats.getDay50MovingAvg();
        this.year1ChangePercent = keyStats.getYear1ChangePercent();
        this.ytdChangePercent = keyStats.getYtdChangePercent();
        this.month6ChangePercent = keyStats.getMonth6ChangePercent();
        this.month3ChangePercent = keyStats.getMonth3ChangePercent();
        this.day5ChangePercent = keyStats.getDay5ChangePercent();
        this.day30ChangePercent = keyStats.getDay30ChangePercent();
        this.lastTrades = lastTrades;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public BigDecimal getWeek52high() {
        return this.week52high;
    }

    public BigDecimal getWeek52low() {
        return this.week52low;
    }

    public BigDecimal getDividendRate() {
        return this.dividendRate;
    }

    public BigDecimal getDividendYield() {
        return this.dividendYield;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public BigDecimal getDay50MovingAvg() {
        return this.day50MovingAvg;
    }

    public BigDecimal getYear1ChangePercent() {
        return this.year1ChangePercent;
    }

    public BigDecimal getYtdChangePercent() {
        return this.ytdChangePercent;
    }

    public BigDecimal getMonth6ChangePercent() {
        return this.month6ChangePercent;
    }

    public BigDecimal getMonth3ChangePercent() {
        return this.month3ChangePercent;
    }

    public BigDecimal getDay5ChangePercent() {
        return this.day5ChangePercent;
    }

    public BigDecimal getDay30ChangePercent() {
        return this.day30ChangePercent;
    }

    public List<LastTrade> getLastTrades() {
        return lastTrades;
    }
}
