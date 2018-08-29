package pl.vrajani.controller;

import pl.vrajani.models.Config;
import pl.vrajani.models.CurrentHolding;
import pl.vrajani.models.Response;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;
import pl.vrajani.services.OptimizerService;
import pl.vrajani.services.RequestDataService;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

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
        List<CurrentHolding> currentHoldings = new ArrayList<>();

        Map<String, Float> bestDividendStocks = new HashMap<>();
        config.getSymbolList().parallelStream()
                .filter(Objects::nonNull)
                .forEach(symbol -> {

                    StatsOfInterest statsOfInterest = new StatsOfInterest(requestDataService.getKeyStats(symbol),
                    requestDataService.getLatestPrice(symbol));
                    optimizerService.categorizeStocks(statsOfInterest, suggestedBuys, suggestedSells, suggestedHolds, config.getCurrentOwnings());

                    if(statsOfInterest.getDividendYield().floatValue() > 1.75){
                        bestDividendStocks.put(statsOfInterest.getCompanyName(), statsOfInterest.getDividendYield().floatValue());
                    }
                });

        sortByGains(suggestedBuys);
        sortByGains(suggestedSells);
        sortByGains(suggestedHolds);
        Map<String, Float> bestDividendStocksSorted = sortByValues(bestDividendStocks);

        printStockResults("BUYS: "+ suggestedBuys.size(), suggestedBuys);
        printStockResults("SELLS: "+ suggestedSells.size(), suggestedSells);
        printStockResults("HOLD: "+ suggestedHolds.size(), suggestedHolds);
        printListResults("COMING UP EARNINGS: "+ earningsComingUp.size(), earningsComingUp);
        printMapResults("Dividend Stocks: "+ bestDividendStocksSorted.size(), bestDividendStocksSorted);

        return new Response(suggestedBuys, suggestedSells, suggestedHolds, earningsComingUp, bestDividendStocksSorted, currentHoldings);
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
        stockResponses.sort((Comparator.comparing(StockResponse::getYourPrice).reversed()));
        return stockResponses;
    }

    private static void printMapResults(String message, Map<String, Float> results) {
        System.out.println("\n"+ message);
        if( results.size() == 0){
            System.out.println("None");
        } else {
            for(Map.Entry entry : results.entrySet()){
                System.out.println(entry.toString());
            }
        }
        System.out.println("\n");
    }

    private static void printListResults(String message, List<StatsOfInterest> results) {
        System.out.println("\n"+ message);
        if( results.size() == 0){
            System.out.println("None");
        } else {
            results.parallelStream().filter(Objects::nonNull).forEach(result -> System.out.println(result.getCompanyName()));
        }
        System.out.println("\n");
    }

    private static void printStockResults(String message, List<StockResponse> results) {
        System.out.println("\n"+ message);
        if( results.size() == 0){
            System.out.println("None");
        } else {
            results.stream().filter(Objects::nonNull).forEach(result -> System.out.println(result.toString()));
        }
        System.out.println("\n");
    }
}
