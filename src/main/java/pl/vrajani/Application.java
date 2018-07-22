package pl.vrajani;

import pl.vrajani.config.Configuration;
import pl.vrajani.controller.DataManager;

import java.util.Arrays;
import java.util.List;

public class Application {

    private static final List<String> COMPANY_SYMBOLS = Arrays.asList("AAPL", "MSFT", "FB", "NFLX", "JNJ",
            "AMAT", "TWTR", "MILN", "OKTA", "JD", "IQ", "AMD", "BAC", "INTC", "SBUX", "GRPN", "HMNY", "CRON", "DBX",
            "F", "GAMR", "KMI", "PFE", "MU", "SQ", "VMW", "ATVI", "UA", "BABA", "AMZN", "GOOG", "GOOGL", "TSLA",
            "AMC", "T", "IFN", "TTM");
    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        Configuration configuration = new Configuration();
        DataManager manager = new DataManager(configuration.getIexTradingClient());
        manager.manage(COMPANY_SYMBOLS);
        System.out.println("Completed in: "+ (System.currentTimeMillis() - startTime)/1000);
    }
}
