package pl.vrajani.models;

import java.math.BigDecimal;

public class CurrentHolding {
    private String companyName;
    private BigDecimal lastPrice;
    private BigDecimal yourPrice = BigDecimal.ZERO;

    public CurrentHolding(){}
    public CurrentHolding(StockResponse stockResponse){
        this.companyName = stockResponse.getCompanyName();
        this.lastPrice = stockResponse.getLastPrice();
        this.yourPrice = stockResponse.getYourPrice();
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

    public BigDecimal getYourPrice() {
        return yourPrice;
    }

    public void setYourPrice(BigDecimal yourPrice) {
        this.yourPrice = yourPrice;
    }
}
