package pl.vrajani.config;

import pl.zankowski.iextrading4j.client.IEXTradingClient;

public class Configuration {

    private final IEXTradingClient iexTradingClient = IEXTradingClient.create();

    public IEXTradingClient getIexTradingClient() {
        return iexTradingClient;
    }
}
