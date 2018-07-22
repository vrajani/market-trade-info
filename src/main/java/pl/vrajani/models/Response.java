package pl.vrajani.models;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<String> suggestedBuys = new ArrayList<>();
    private List<String> suggestedSells = new ArrayList<>();
    private List<String> suggestedHolds = new ArrayList<>();
    private List<String> earningsComingUp = new ArrayList<>();

    public Response(List<StatsOfInterest> suggestedBuys, List<StatsOfInterest> suggestedSells, List<StatsOfInterest> suggestedHolds, List<StatsOfInterest> earningsComingUp){
        extractName(suggestedBuys, this.suggestedBuys);
        extractName(suggestedSells, this.suggestedSells);

        extractName(suggestedHolds, this.suggestedHolds);
        extractName(earningsComingUp, this.earningsComingUp);
    }

    private void extractName(List<StatsOfInterest> suggestedStats, List<String> suggestedNames) {
        suggestedStats.parallelStream().forEach(statsOfInterest -> suggestedNames.add(statsOfInterest.getCompanyName()));
    }

    public Response(){}

    public List<String> getSuggestedBuys() {
        return suggestedBuys;
    }

    public void setSuggestedBuys(List<String> suggestedBuys) {
        this.suggestedBuys = suggestedBuys;
    }

    public List<String> getSuggestedSells() {
        return suggestedSells;
    }

    public void setSuggestedSells(List<String> suggestedSells) {
        this.suggestedSells = suggestedSells;
    }

    public List<String> getSuggestedHolds() {
        return suggestedHolds;
    }

    public void setSuggestedHolds(List<String> suggestedHolds) {
        this.suggestedHolds = suggestedHolds;
    }

    public List<String> getEarningsComingUp() {
        return earningsComingUp;
    }

    public void setEarningsComingUp(List<String> earningsComingUp) {
        this.earningsComingUp = earningsComingUp;
    }
}
