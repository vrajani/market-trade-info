package pl.vrajani.models;

import java.math.BigDecimal;

public class StockResponse {

    private String companyName;
    private BigDecimal lastPrice;
    private CurrentOwnings currentOwnings;
    private String reason;
    private CLASSIFICATION classification;

    public StockResponse(){}

    public StockResponse(String companyName, BigDecimal lastPrice, CurrentOwnings currentOwnings, Reason reason, CLASSIFICATION classification){
        this.companyName = companyName;
        this.lastPrice = lastPrice;
        this.currentOwnings = currentOwnings;
        this.reason = reason.getReason();
        this.classification = classification;
    }


    public CLASSIFICATION getClassification() {
        return classification;
    }

    public void setClassification(CLASSIFICATION classification) {
        this.classification = classification;
    }

    public CurrentOwnings getCurrentOwnings() {
        return currentOwnings;
    }

    public void setCurrentOwnings(CurrentOwnings currentOwnings) {
        this.currentOwnings = currentOwnings;
    }

    public enum CLASSIFICATION {
        BUY, SELL, UNDECIDED
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString(){
        return "["+ companyName +
                ", lastPrice=" + lastPrice +
                ", currentOwnings=" + currentOwnings.toString() +
                ", reason="+ reason + " ]";
    }
}
