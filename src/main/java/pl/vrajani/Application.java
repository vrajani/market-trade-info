package pl.vrajani;

import pl.vrajani.config.Configuration;
import pl.vrajani.controller.DataManager;
import pl.vrajani.models.Config;
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
        Config config = configuration.loadAnalyserConfig();
        if(config != null) {
            DataManager manager = new DataManager(configuration.getIexTradingClient(), config);
            Response response = manager.manage();
            System.out.println("Completed in: " + (System.currentTimeMillis() - startTime) / 1000);

            return HtmlGenerator.generateHTML(response).replaceAll("\n", "");
        }
        return "ERROR Occured while reading config";
    }
}
