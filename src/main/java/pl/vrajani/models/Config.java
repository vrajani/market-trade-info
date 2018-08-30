package pl.vrajani.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "buyConfig",
    "sellConfig"
})
public class Config {

    @JsonProperty("buyConfig")
    private BuyConfig buyConfig;

    @JsonProperty("sellConfig")
    private SellConfig sellConfig;

    @JsonProperty("symbolList")
    private List<String> symbolList;

    @JsonProperty("currentOwnings")
    private List<CurrentOwnings> currentOwnings;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("buyConfig")
    public BuyConfig getBuyConfig() {
        return buyConfig;
    }

    @JsonProperty("buyConfig")
    public void setBuyConfig(BuyConfig buyConfig) {
        this.buyConfig = buyConfig;
    }

    @JsonProperty("sellConfig")
    public SellConfig getSellConfig() {
        return sellConfig;
    }

    @JsonProperty("sellConfig")
    public void setSellConfig(SellConfig sellConfig) {
        this.sellConfig = sellConfig;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<String> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(List<String> symbolList) {
        this.symbolList = symbolList;
    }


    public List<CurrentOwnings> getCurrentOwnings() {
        return currentOwnings;
    }

    public void setCurrentOwnings(List<CurrentOwnings> currentOwnings) {
        this.currentOwnings = currentOwnings;
    }

    public CurrentOwnings getCurrentOwningBySymbol(String symbol) {
        List<CurrentOwnings> filteredList = currentOwnings.parallelStream().filter(currentOwning -> currentOwning.getSymbol().equalsIgnoreCase(symbol)).collect(Collectors.toList());
        if(filteredList == null || filteredList.size() == 0){
            return new CurrentOwnings(symbol);
        }
        return filteredList.get(0);
    }
}
