package pl.vrajani.services;

import pl.vrajani.models.Response;
import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.models.StockResponse;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PrintUtil {

    public static void printResuts(Response response) {

        System.out.println("total Cost = " + response.getTotalCost());
        System.out.println("Equity = " + response.getEquity());
        System.out.println("Gain or Loss = " + response.getTotalCost().subtract(response.getEquity()));
        printStockResults("BUYS: "+ response.getSuggestedBuys().size(), response.getSuggestedBuys());
        printStockResults("SELLS: "+ response.getSuggestedSells().size(), response.getSuggestedSells());
        printStockResults("HOLD: "+ response.getSuggestedHolds().size(), response.getSuggestedHolds());
        //printListResults("COMING UP EARNINGS: "+ response.getEarningsComingUp().size(), earningsComingUp);
        printMapResults("Dividend Stocks: "+ response.getBestDividendStocks().size(), response.getBestDividendStocks());

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
