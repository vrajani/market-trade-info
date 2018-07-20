import pl.vrajani.config.Configuration;
import pl.vrajani.controller.DataManager;

import java.util.Arrays;
import java.util.List;

public class Application {

    private static final List<String> COMPANY_SYMBOLS = Arrays.asList(new String[] {"AAPL"});
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        DataManager manager = new DataManager(configuration.getIexTradingClient());
        manager.manage(COMPANY_SYMBOLS);

    }
}
