package pl.vrajani.models;

public enum REASON {
    WEEK_LOW ("Lower since a week ago"),
    UNKNOWN("n/a");

    String reason;
    REASON(String s) {
        reason = s;
    }
    public String getReason() {
        return reason;
    }
}
