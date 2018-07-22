package pl.vrajani.services;

import pl.zankowski.iextrading4j.api.marketdata.LastTrade;
import pl.zankowski.iextrading4j.api.stocks.KeyStats;
import pl.zankowski.iextrading4j.api.stocks.TimeSeries;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.marketdata.LastTradeRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.KeyStatsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.PriceRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.TimeSeriesRequestBuilder;

import java.math.BigDecimal;
import java.util.List;

public class RequestDataService {

    private IEXTradingClient client;
    public RequestDataService(IEXTradingClient iexTradingClient){
        this.client = iexTradingClient;
    }


    public KeyStats getKeyStats(String symbol) {
        final KeyStats keyStats = client.executeRequest(new KeyStatsRequestBuilder()
                .withSymbol(symbol)
                .build());
        System.out.println(keyStats);

        return keyStats;
    }

    public List<TimeSeries> getTimeSeriesData (String symbol){
        final List<TimeSeries> timeSeriesList = client.executeRequest(new TimeSeriesRequestBuilder()
                .withSymbol("aapl")
                .build());
        System.out.println(timeSeriesList);
        return timeSeriesList;
    }

    public List<LastTrade> recentRequestSample(String symbol) {

        final List<LastTrade> lastTradeList = client.executeRequest(new LastTradeRequestBuilder()
                .withSymbol(symbol)
                .build());
        System.out.println(lastTradeList);
        return lastTradeList;
    }

    public BigDecimal getLatestPrice (String symbol){
        BigDecimal price = client.executeRequest(new PriceRequestBuilder()
                .withSymbol(symbol)
                .build());
        System.out.println(symbol + ": Latest Price - "+price.toString());
        return price;
    }
}
