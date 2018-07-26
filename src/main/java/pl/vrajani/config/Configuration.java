package pl.vrajani.config;

import pl.zankowski.iextrading4j.client.IEXTradingClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Configuration {

    private final IEXTradingClient iexTradingClient = IEXTradingClient.create();

    public IEXTradingClient getIexTradingClient() {
        return iexTradingClient;
    }

    public Map<String, BigDecimal> getYourPrice (){
        String str = System.getenv("CURRENT_OWNINGS");
        Map<String, BigDecimal> map = Arrays.asList(str.split(","))
                .stream().map(s -> s.split(":"))
                .collect(Collectors.toMap(e -> e[0], e -> new BigDecimal(e[1])));

        System.out.println(map.toString());
        return map;
    }
}
