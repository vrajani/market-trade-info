
package pl.vrajani.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "DiffTo52WeekHigh",
    "Diff50DayMovingGreaterThan",
    "3MonthChangeHigherThan",
    "1MonthChangeHigherThan",
    "5DayChangeHigherThan"
})
public class SellConfig {

    @JsonProperty("DiffTo52WeekHigh")
    private Double diffTo52WeekHigh;
    @JsonProperty("Diff50DayMovingGreaterThan")
    private Double diff50DayMovingGreaterThan;
    @JsonProperty("3MonthChangeHigherThan")
    private Double _3MonthChangeHigherThan;
    @JsonProperty("1MonthChangeHigherThan")
    private Double _1MonthChangeHigherThan;
    @JsonProperty("5DayChangeHigherThan")
    private Double _5DayChangeHigherThan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("DiffTo52WeekHigh")
    public Double getDiffTo52WeekHigh() {
        return diffTo52WeekHigh;
    }

    @JsonProperty("DiffTo52WeekHigh")
    public void setDiffTo52WeekHigh(Double diffTo52WeekHigh) {
        this.diffTo52WeekHigh = diffTo52WeekHigh;
    }

    @JsonProperty("Diff50DayMovingGreaterThan")
    public Double getDiff50DayMovingGreaterThan() {
        return diff50DayMovingGreaterThan;
    }

    @JsonProperty("Diff50DayMovingGreaterThan")
    public void setDiff50DayMovingGreaterThan(Double diff50DayMovingGreaterThan) {
        this.diff50DayMovingGreaterThan = diff50DayMovingGreaterThan;
    }

    @JsonProperty("3MonthChangeHigherThan")
    public Double get3MonthChangeHigherThan() {
        return _3MonthChangeHigherThan;
    }

    @JsonProperty("3MonthChangeHigherThan")
    public void set3MonthChangeHigherThan(Double _3MonthChangeHigherThan) {
        this._3MonthChangeHigherThan = _3MonthChangeHigherThan;
    }

    @JsonProperty("1MonthChangeHigherThan")
    public Double get1MonthChangeHigherThan() {
        return _1MonthChangeHigherThan;
    }

    @JsonProperty("1MonthChangeHigherThan")
    public void set1MonthChangeHigherThan(Double _1MonthChangeHigherThan) {
        this._1MonthChangeHigherThan = _1MonthChangeHigherThan;
    }

    @JsonProperty("5DayChangeHigherThan")
    public Double get5DayChangeHigherThan() {
        return _5DayChangeHigherThan;
    }

    @JsonProperty("5DayChangeHigherThan")
    public void set5DayChangeHigherThan(Double _5DayChangeHigherThan) {
        this._5DayChangeHigherThan = _5DayChangeHigherThan;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
