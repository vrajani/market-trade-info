package pl.vrajani;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.vrajani.config.Configuration;
import pl.vrajani.controller.DataManager;
import pl.vrajani.models.Config;
import pl.vrajani.models.Response;
import pl.vrajani.services.HtmlGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Application implements RequestStreamHandler {

    public static void main(String[] args) {
        Application application = new Application();
        //System.out.println(application.run());
    }

    public void run(InputStream inputStream, OutputStream outputStream, Context context) {

        LambdaLogger logger = context.getLogger();
        JSONObject responseJson = new JSONObject();

        String type = StringUtils.EMPTY;
        try {
            JSONObject event = (JSONObject) new JSONParser().parse(new InputStreamReader(inputStream));
            if (event.get("pathParameters") != null) {
                JSONObject pps = (JSONObject)event.get("pathParameters");
                if ( pps.get("type") != null) {
                    type = (String)pps.get("type");
                    logger.log("Path PArameter: "+ type);
                } else {
                    logger.log("the type parameter does not exist.");
                }
            }
        } catch (ParseException | IOException e) {
            logger.log(e.getMessage());
        }

        try {
            responseJson.put("isBase64Encoded", false);
            responseJson.put("statusCode", "200");
            JSONObject headerJson = new JSONObject();
            headerJson.put("content-type", "text/html");
            responseJson.put("headers", headerJson);



            long startTime = System.currentTimeMillis();
            Configuration configuration = new Configuration();
            Config config = configuration.loadAnalyserConfig();
            if(config != null) {
                DataManager manager = new DataManager(configuration.getIexTradingClient(), config);
                Response response = manager.manage();
                logger.log("Completed in: " + (System.currentTimeMillis() - startTime) / 1000);
                boolean ignoreOwnings = type.equalsIgnoreCase("personal")? false : true;
                responseJson.put("body",HtmlGenerator.generateHTML(response, ignoreOwnings).replaceAll("\n", "") );
                OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
                writer.write(responseJson.toJSONString());
                writer.close();
            }
        } catch (IOException e) {
            logger.log(e.getMessage());
        }

    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {
        run(inputStream,outputStream,context);
    }
}
