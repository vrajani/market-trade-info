
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
    "DiffTo52WeekLow",
    "Diff50DayMovingGreaterThan",
    "3MonthChangeHigherThan",
    "1MonthChangeHigherThan",
    "5DayChangeLowerThan"
})
public class BuyConfig {

    @JsonProperty("DiffTo52WeekLow")
    private Double diffTo52WeekLow;
    @JsonProperty("Diff50DayMovingGreaterThan")
    private Double diff50DayMovingGreaterThan;
    @JsonProperty("3MonthChangeHigherThan")
    private Double _3MonthChangeHigherThan;
    @JsonProperty("1MonthChangeHigherThan")
    private Double _1MonthChangeHigherThan;
    @JsonProperty("5DayChangeLowerThan")
    private Double _5DayChangeLowerThan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("DiffTo52WeekLow")
    public Double getDiffTo52WeekLow() {
        return diffTo52WeekLow;
    }

    @JsonProperty("DiffTo52WeekLow")
    public void setDiffTo52WeekLow(Double diffTo52WeekLow) {
        this.diffTo52WeekLow = diffTo52WeekLow;
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

    @JsonProperty("5DayChangeLowerThan")
    public Double get5DayChangeLowerThan() {
        return _5DayChangeLowerThan;
    }

    @JsonProperty("5DayChangeLowerThan")
    public void set5DayChangeLowerThan(Double _5DayChangeLowerThan) {
        this._5DayChangeLowerThan = _5DayChangeLowerThan;
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
