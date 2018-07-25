package pl.vrajani.models;

public enum REASON {
    WEEK_LOW ("Lower since a week ago"),
    WEEK_MONTH("Lower Since Month"),
    UNKNOWN("n/a");

    String reason;
    REASON(String s) {
        reason = s;
    }
    public String getReason() {
        return reason;
    }
}
