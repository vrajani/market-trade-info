package pl.vrajani.controller;

import pl.vrajani.models.Config;
import pl.vrajani.models.CurrentOwnings;
import pl.vrajani.models.Response;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;
import pl.vrajani.services.OptimizerService;
import pl.vrajani.services.PrintUtil;
import pl.vrajani.services.RequestDataService;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toMap;

public class DataManager {

    private RequestDataService requestDataService;
    private OptimizerService optimizerService;
    private Config config;

    BigDecimal equity = BigDecimal.ZERO;
    BigDecimal totalCost = BigDecimal.ZERO;

    public DataManager(IEXTradingClient iexTradingClient, Config config){
        this.requestDataService = new RequestDataService(iexTradingClient);
        this.optimizerService = new OptimizerService(config);
        this.config = config;
    }

    public Response manage() {
        List<StockResponse> suggestedBuys = new ArrayList<>();
        List<StockResponse> suggestedSells = new ArrayList<>();
        List<StockResponse> suggestedHolds = new ArrayList<>();
        List<StatsOfInterest> earningsComingUp = new ArrayList<>();

        Map<String, Float> bestDividendStocks = new HashMap<>();
        config.getSymbolList().parallelStream()
                .filter(Objects::nonNull)
                .forEach(symbol -> {

                    StatsOfInterest statsOfInterest = new StatsOfInterest(requestDataService.getKeyStats(symbol),
                    requestDataService.getLatestPrice(symbol));
                    CurrentOwnings currentOwning = config.getCurrentOwningBySymbol(symbol);
                    currentOwning.setEquity(statsOfInterest.getLastPrice().multiply(BigDecimal.valueOf(currentOwning.getCount())));
                    equity = equity.add(currentOwning.getEquity());

                    StockResponse stockResponse = optimizerService.categorizeStocks(statsOfInterest, suggestedBuys, suggestedSells, suggestedHolds, currentOwning);
                    totalCost = totalCost.add(currentOwning.getEquity().subtract(stockResponse.getGainOrLoss()));

                    if(statsOfInterest.getDividendYield().floatValue() > 1.75){
                        bestDividendStocks.put(statsOfInterest.getCompanyName(), statsOfInterest.getDividendYield().floatValue());
                    }
                });

        sortByGains(suggestedBuys);
        sortByGains(suggestedSells);
        sortByGains(suggestedHolds);
        Map<String, Float> bestDividendStocksSorted = sortByValues(bestDividendStocks);

        BigDecimal gainOrLoss = totalCost.subtract(equity);
        Response response = new Response(suggestedBuys, suggestedSells, suggestedHolds, earningsComingUp,
                bestDividendStocksSorted, totalCost, equity, gainOrLoss);

        PrintUtil.printResuts(response);
        return response;
    }

    private Map<String, Float> sortByValues(Map<String, Float> bestDividendStocks) {

        bestDividendStocks = bestDividendStocks
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        return bestDividendStocks;
    }

    private List<StockResponse> sortByGains(List<StockResponse> stockResponses) {
        stockResponses.sort(Comparator.<StockResponse, BigDecimal>comparing(o -> o.getCurrentOwnings().getEquity()).reversed());
        return stockResponses;
    }

}
