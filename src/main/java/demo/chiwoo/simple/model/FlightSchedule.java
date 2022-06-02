package demo.chiwoo.simple.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightSchedule {
    private Long timestamp;

    private String source;

    @JsonProperty("flight_no")
    private String flightNo;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FlightSchedule{" +
                "timestamp=" + timestamp +
                ", source='" + source + '\'' +
                ", flightNo='" + flightNo + '\'' +
                '}';
    }
}
