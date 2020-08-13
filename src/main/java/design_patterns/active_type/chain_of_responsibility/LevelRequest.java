package design_patterns.active_type.chain_of_responsibility;

public class LevelRequest {

    private String name;
    private String reason;
    private Integer day;

    public LevelRequest(String name, String reason, Integer day) {
        this.name = name;
        this.reason = reason;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
