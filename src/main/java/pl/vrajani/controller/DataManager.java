package pl.vrajani.controller;

import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.services.OptimizerService;
import pl.vrajani.services.RequestDataService;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private RequestDataService requestDataService;

    public DataManager(IEXTradingClient iexTradingClient){
        this.requestDataService = new RequestDataService(iexTradingClient);
    }

    public void manage(List<String> symbols) {
        List<StatsOfInterest> suggestedBuys = new ArrayList<>();
        List<StatsOfInterest> suggestedSells = new ArrayList<>();
        List<StatsOfInterest> suggestedHolds = new ArrayList<>();
        List<StatsOfInterest> earningsComingUp = new ArrayList<>();

        for ( String symbol: symbols) {
            StatsOfInterest statsOfInterest = new StatsOfInterest(requestDataService.getKeyStats(symbol),
                    requestDataService.getLatestPrice(symbol));
            OptimizerService optimizerService = new OptimizerService();
            optimizerService.identifyStocks(statsOfInterest, suggestedBuys, suggestedSells, suggestedHolds);
        }

        printResults("BUYS: "+ suggestedBuys.size(), suggestedBuys);
        printResults("SELLS: "+ suggestedSells.size(), suggestedSells);
        printResults("HOLD: "+ suggestedHolds.size(), suggestedHolds);
        printResults("COMING UP EARNINGS: "+ earningsComingUp.size(), earningsComingUp);

    }

    private void printResults(String message, List<StatsOfInterest> results) {
        System.out.println("\n"+ message);
        if( results.size() == 0){
            System.out.println("None");
        } else {
            results.stream().forEach(result -> System.out.println(result.getCompanyName()));
        }
        System.out.println("\n");
    }
}
