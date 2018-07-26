package pl.vrajani.models;

import java.math.BigDecimal;

public class StockResponse {

    private String companyName;
    private BigDecimal lastPrice;
    private BigDecimal yourPrice = BigDecimal.ZERO;
    private String reason;
    private CLASSIFICATION classification;

    public StockResponse(){}

    public StockResponse(String companyName, BigDecimal lastPrice, BigDecimal yourPrice, Reason reason, CLASSIFICATION classification){
        this.companyName = companyName;
        this.lastPrice = lastPrice;
        this.yourPrice = yourPrice;
        this.reason = reason.getReason();
        this.classification = classification;
    }


    public CLASSIFICATION getClassification() {
        return classification;
    }

    public void setClassification(CLASSIFICATION classification) {
        this.classification = classification;
    }

    public BigDecimal getYourPrice() {
        return yourPrice;
    }

    public void setYourPrice(BigDecimal yourPrice) {
        this.yourPrice = yourPrice;
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
                ", yourPrice=" + yourPrice +
                ", reason="+ reason + " ]";
    }
}
