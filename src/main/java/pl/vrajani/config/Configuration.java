package pl.vrajani.config;

import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.vrajani.models.Config;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Configuration {

    private final IEXTradingClient iexTradingClient = IEXTradingClient.create();

    public IEXTradingClient getIexTradingClient() {
        return iexTradingClient;
    }

    public Map<String, BigDecimal> readEnvPropertyToMap(String propertyKey){
        String str = System.getenv(propertyKey);
        Map<String, BigDecimal> map = Arrays.asList(str.split(","))
                .stream().map(s -> s.split(":"))
                .collect(Collectors.toMap(e -> e[0], e -> new BigDecimal(e[1])));

        System.out.println(map.toString());
        return map;
    }

    public Config loadAnalyserConfig() {
        InputStream is = null;
        try {
            if(System.getenv("env") != null && System.getenv("env").equalsIgnoreCase("local")) {


                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                is = classloader.getResourceAsStream("config.json");
                //create ObjectMapper instance

            } else {
                final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
                S3Object object = s3.getObject("market-trade-info", "config.json");
                is = object.getObjectContent();
            }

            //convert json string to object
            ObjectMapper objectMapper = new ObjectMapper();
            Config config = objectMapper.readValue(is, Config.class);

            if(System.getenv("processOwnings") != null && System.getenv("processOwnings").equalsIgnoreCase("false")){
                config.setCurrentOwnings(new ArrayList<>());
            }
            return config;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
