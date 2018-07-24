package pl.vrajani.controller;

import pl.vrajani.models.Response;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.services.OptimizerService;
import pl.vrajani.services.RequestDataService;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataManager {

    private RequestDataService requestDataService;
    private OptimizerService optimizerService;

    public DataManager(IEXTradingClient iexTradingClient){
        this.requestDataService = new RequestDataService(iexTradingClient);
        this.optimizerService = new OptimizerService();
    }

    public Response manage(List<String> symbols) {
        List<StatsOfInterest> suggestedBuys = new ArrayList<>();
        List<StatsOfInterest> suggestedSells = new ArrayList<>();
        List<StatsOfInterest> suggestedHolds = new ArrayList<>();
        List<StatsOfInterest> earningsComingUp = new ArrayList<>();
        Map<String, Float> bestDividendStocks;

        List<StatsOfInterest> statsOfInterestList = new ArrayList<>();
        symbols.parallelStream().forEach(symbol -> {
            statsOfInterestList.add(new StatsOfInterest(requestDataService.getKeyStats(symbol),
                    requestDataService.getLatestPrice(symbol)));
        });

        optimizerService.categorizeStocks(statsOfInterestList, suggestedBuys, suggestedSells, suggestedHolds);
        bestDividendStocks = optimizerService.getDividendStocks(statsOfInterestList);

        printListResults("BUYS: "+ suggestedBuys.size(), suggestedBuys);
        printListResults("SELLS: "+ suggestedSells.size(), suggestedSells);
        printListResults("HOLD: "+ suggestedHolds.size(), suggestedHolds);
        printListResults("COMING UP EARNINGS: "+ earningsComingUp.size(), earningsComingUp);
        printMapResults("Dividend Stocks: "+ bestDividendStocks.size(), bestDividendStocks);

        return new Response(suggestedBuys, suggestedSells, suggestedHolds, earningsComingUp, bestDividendStocks);
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
            results.stream().forEach(result -> System.out.println(result.getCompanyName()));
        }
        System.out.println("\n");
    }
}
