package viralflyer.henrygarant.com.viralflyer;

import java.util.Date;

public class TestFlyer {

    private String message;
    private Date whenCreated;

    public TestFlyer(String message) {
        this.message = message;
        this.whenCreated = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated() {
        this.whenCreated = new Date();
    }
}
