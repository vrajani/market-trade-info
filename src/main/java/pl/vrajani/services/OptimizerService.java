package pl.vrajani.services;

import pl.vrajani.models.StatsOfInterest;

import java.util.List;

public class OptimizerService {
    public void optimizeForBuying(StatsOfInterest statsOfInterest, List<StatsOfInterest> suggestedBuys, List<StatsOfInterest> suggestedSells) {
        boolean isBuyCandidate = isBuyCandidate(statsOfInterest);
        boolean isSellCandidate = isSellCandidate(statsOfInterest);

        if (isBuyCandidate){
            suggestedBuys.add(statsOfInterest);
        } else if (isSellCandidate){
            suggestedSells.add(statsOfInterest);
        } else if (!isBuyCandidate && !isSellCandidate){
            System.out.println("HOLD ON!!!");
        }
    }

    private boolean isBuyCandidate(StatsOfInterest statsOfInterest) {
        StatsAnalyser analyser = StatsAnalyser.getAnalyser("buy");
        return analyser.analyse(statsOfInterest);
    }

    private boolean isSellCandidate(StatsOfInterest statsOfInterest) {
        StatsAnalyser analyser = StatsAnalyser.getAnalyser("sell");
        return analyser.analyse(statsOfInterest);
    }
}
