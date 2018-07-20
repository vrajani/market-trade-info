package pl.vrajani.controller;

import pl.vrajani.models.StatsOfInterest;
import pl.vrajani.notifications.Messenger;
import pl.vrajani.request.RequestData;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

import java.util.List;

public class DataManager {

    private RequestData requestData;
    private Messenger messenger;

    public DataManager(IEXTradingClient iexTradingClient){
        this.requestData = new RequestData(iexTradingClient);
    }

    public void manage(List<String> symbols) {
        for ( String symbol: symbols) {
            StatsOfInterest statsOfInterest = new StatsOfInterest(requestData.getKeyStats(symbol),
                    requestData.recentRequestSample(symbol));



        }
    }
}
