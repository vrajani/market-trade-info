package pl.vrajani.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "count",
        "averagePrice",
        "equity"
})
public class CurrentOwnings {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("count")
    private int count = 0;

    @JsonProperty("averagePrice")
    private BigDecimal averagePrice = BigDecimal.ZERO;

    @JsonIgnore
    @JsonProperty("equity")
    private BigDecimal equity = BigDecimal.ZERO;

    public CurrentOwnings(){}
    public CurrentOwnings(String symbol){
        this.symbol = symbol;
    }


    @JsonProperty("count")
    public int getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(int count) {
        this.count = count;
    }

    @JsonProperty("averagePrice")
    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    @JsonProperty("averagePrice")
    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    @JsonProperty("equity")
    public BigDecimal getEquity() {
        return equity;
    }

    @JsonProperty("equity")
    public void setEquity(BigDecimal equity) {
        this.equity = averagePrice.multiply(BigDecimal.valueOf(count));
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString(){
        return new StringBuilder()
                .append("[").append("symbol=").append(symbol)
                .append(", count=").append(count)
                .append(", averagePrice=").append(averagePrice)
                .append(", equity=").append(equity)
                .append("]").toString();
    }
}
