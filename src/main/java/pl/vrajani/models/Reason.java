package pl.vrajani.models;

public enum Reason {
    UNKNOWN("n/a"),
    CLOSE_TO_52_WEEK_LOW("Close to 52 week low"),
    CLOSE_TO_52_WEEK_HIGH("Close to 52 week high"),
    WEEK_LOW ("Lower since a week ago"),
    WEEK_HIGH ("High since a week ago"),
    LOW_MONTH("Low compared to last month"),
    HIGH_MONTH("High compared to last month"),
    BY_50_DAY_AVG("By 50 Day Moving average");

    String reason;
    Reason(String s) {
        reason = s;
    }
    public String getReason() {
        return reason;
    }
}
