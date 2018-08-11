package pl.vrajani;

import pl.vrajani.config.Configuration;
import pl.vrajani.controller.DataManager;
import pl.vrajani.models.Response;
import pl.vrajani.services.HtmlGenerator;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        System.out.println(application.run());
    }

    public String run() {
        long startTime = System.currentTimeMillis();
        Configuration configuration = new Configuration();
        DataManager manager = new DataManager(configuration.getIexTradingClient());
        Response response = manager.manage(Arrays.asList(System.getenv("COMPANY_SYMBOLS").split(",")), configuration.getYourPrice());
        System.out.println("Completed in: "+ (System.currentTimeMillis() - startTime)/1000);

        return HtmlGenerator.generateHTML(response).replaceAll("\n","");
    }
}
