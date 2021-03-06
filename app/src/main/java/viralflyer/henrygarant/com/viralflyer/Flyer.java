package viralflyer.henrygarant.com.viralflyer;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Flyer {

    private String eventName;
    private Calendar calendar;
    private String location;
    private String host;

    public Flyer() {
        eventName = "";
        calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.HOUR_OF_DAY);
        location = "";
        host = "";
    }

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
        host = "";
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

    @Override
    public String toString() {
        return "Flyer{" +
                "eventName='" + eventName + '\'' +
                ", calendar=" + calendar.toString() +
                ", location='" + location + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

    public String toReadableText() {
        return "Event Name='" + eventName + '\'' +
                ", Date='" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.YEAR)) + '\'' +
                ", Time='" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + '\'' +
                ", Location='" + location + '\'' +
                ", Host='" + host + '\'';
    }
}
