package viralflyer.henrygarant.com.viralflyer;

import java.util.Calendar;

public class Flyer{

    private String eventName;
    private Calendar calendar;
    private String location;
    private String host;

    public Flyer() {}

    public Flyer(String host, String eventName, Calendar calendar, String location) {
        this.host = host;
        this.eventName = eventName;
        this.calendar = calendar;
        this.location = location;
    }

    public Flyer(String eventName, Calendar calendar, String location) {
        this.eventName = eventName;
        this.calendar = calendar;
        this.location = location;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
