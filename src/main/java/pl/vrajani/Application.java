package pl.vrajani;

import pl.vrajani.config.Configuration;
import pl.vrajani.controller.DataManager;
import pl.vrajani.models.Response;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    public Response run() {
        long startTime = System.currentTimeMillis();
        Configuration configuration = new Configuration();
        DataManager manager = new DataManager(configuration.getIexTradingClient());
        Response response = manager.manage(Arrays.asList(System.getenv("COMPANY_SYMBOLS").split(",")));
        System.out.println("Completed in: "+ (System.currentTimeMillis() - startTime)/1000);
        return response;
    }
}
