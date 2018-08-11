package pl.vrajani.services;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import pl.vrajani.models.Response;

import java.io.StringWriter;

public class HtmlGenerator {

    public static String generateHTML(Response response){


        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("file.resource.loader.class", ClasspathResourceLoader.class.getName());

        velocityEngine.init();

        Template t = velocityEngine.getTemplate("index.vm");

        VelocityContext context = new VelocityContext();
        context.put("suggestedBuys", response.getSuggestedBuys());
        context.put("suggestedSells", response.getSuggestedSells());
        context.put("suggestedHolds", response.getSuggestedHolds());
        context.put("bestDividendStocks", response.getBestDividendStocks());

        StringWriter writer = new StringWriter();
        t.merge( context, writer );

        return writer.toString();
    }
}
