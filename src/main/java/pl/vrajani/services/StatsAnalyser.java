package pl.vrajani.services;

import pl.vrajani.models.StatsOfInterest;

public abstract class StatsAnalyser {
    public abstract boolean analyse(StatsOfInterest statsOfInterest);

    private final static BuyAnalyser buyAnalyser = new BuyAnalyser();
    private final static SellAnalyser sellAnalyser = new SellAnalyser();

    public static StatsAnalyser getAnalyser(String type){
        if (type.equalsIgnoreCase("buy")){
            return buyAnalyser;
        }else if (type.equalsIgnoreCase("sell")){
            return sellAnalyser;
        }
        System.out.println("Invalid Analyser Requested!!!");
        return null;
    }

}
