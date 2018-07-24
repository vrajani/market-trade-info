package pl.vrajani.models;

import java.math.BigDecimal;

public class StockResponse {

    private String companyName;
    private BigDecimal lastPrice;
    private REASON reason;
    private CLASSIFICATION classification;

    public StockResponse(){}

    public StockResponse(String companyName, BigDecimal lastPrice, REASON reason, CLASSIFICATION classification){
        this.companyName = companyName;
        this.lastPrice = lastPrice;
        this.reason = reason;
        this.classification = classification;
    }


    public CLASSIFICATION getClassification() {
        return classification;
    }

    public void setClassification(CLASSIFICATION classification) {
        this.classification = classification;
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

    public REASON getReason() {
        return reason;
    }

    public void setReason(REASON reason) {
        this.reason = reason;
    }

    @Override
    public String toString(){
        return "["+ companyName +
                ", lastPrice=" + lastPrice +
                ", reason="+ reason.getReason() + " ]";
    }
}
