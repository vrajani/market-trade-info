package pl.vrajani.controller;

import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.services.Messenger;
import pl.vrajani.services.OptimizerService;
import pl.vrajani.services.RequestDataService;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private RequestDataService requestDataService;
    private Messenger messenger;

    public DataManager(IEXTradingClient iexTradingClient){
        this.requestDataService = new RequestDataService(iexTradingClient);
    }

    public void manage(List<String> symbols) {
        List<StatsOfInterest> suggestedBuys = new ArrayList<StatsOfInterest>();
        List<StatsOfInterest> suggestedSells = new ArrayList<StatsOfInterest>();
        for ( String symbol: symbols) {
            StatsOfInterest statsOfInterest = new StatsOfInterest(requestDataService.getKeyStats(symbol),
                    requestDataService.recentRequestSample(symbol));
            OptimizerService optimizerService = new OptimizerService();
            optimizerService.optimizeForBuying(statsOfInterest, suggestedBuys, suggestedSells);


        }
    }
}
