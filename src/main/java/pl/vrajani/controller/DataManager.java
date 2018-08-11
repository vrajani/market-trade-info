package pl.vrajani.controller;

import pl.vrajani.models.CurrentHolding;
import pl.vrajani.models.Response;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;
import pl.vrajani.services.OptimizerService;
import pl.vrajani.services.RequestDataService;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DataManager {

    private RequestDataService requestDataService;
    private OptimizerService optimizerService;

    public DataManager(IEXTradingClient iexTradingClient){
        this.requestDataService = new RequestDataService(iexTradingClient);
        this.optimizerService = new OptimizerService();
    }

    public Response manage(List<String> symbols, Map<String, BigDecimal> currentOwnings) {
        List<StockResponse> suggestedBuys = new ArrayList<>();
        List<StockResponse> suggestedSells = new ArrayList<>();
        List<StockResponse> suggestedHolds = new ArrayList<>();
        List<StatsOfInterest> earningsComingUp = new ArrayList<>();
        List<CurrentHolding> currentHoldings = new ArrayList<>();

        Map<String, Float> bestDividendStocks = new HashMap<>();
        symbols.parallelStream()
                .filter(Objects::nonNull)
                .forEach(symbol -> {

                    StatsOfInterest statsOfInterest = new StatsOfInterest(requestDataService.getKeyStats(symbol),
                    requestDataService.getLatestPrice(symbol));
                    optimizerService.categorizeStocks(statsOfInterest, suggestedBuys, suggestedSells, suggestedHolds, currentOwnings);

                    if(statsOfInterest.getDividendYield().floatValue() > 1.75){
                        bestDividendStocks.put(statsOfInterest.getCompanyName(), statsOfInterest.getDividendYield().floatValue());
                    }
                });

        printStockResults("BUYS: "+ suggestedBuys.size(), suggestedBuys);
        printStockResults("SELLS: "+ suggestedSells.size(), suggestedSells);
        printStockResults("HOLD: "+ suggestedHolds.size(), suggestedHolds);
        printListResults("COMING UP EARNINGS: "+ earningsComingUp.size(), earningsComingUp);
        printMapResults("Dividend Stocks: "+ bestDividendStocks.size(), bestDividendStocks);

        return new Response(suggestedBuys, suggestedSells, suggestedHolds, earningsComingUp, bestDividendStocks, currentHoldings);
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
            results.parallelStream().filter(Objects::nonNull).forEach(result -> System.out.println(result.toString()));
        }
        System.out.println("\n");
    }
}
