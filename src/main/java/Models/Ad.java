package Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ad {
    private int id;
    private String title;
    private String lastBounced;

    public Ad(int id, String title, String lastBounced) {
        this.id = id;
        this.title = title;
        this.lastBounced = lastBounced;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastBounced() {
        return lastBounced;
    }

    public void setLastBounced(String lastBounced) {
        this.lastBounced = lastBounced;
    }

    @Override
    public String toString() {
        this.lastBounced = this.lastBounced.replace("T"," ").replace(".000Z","");
        return String.format("מודעה מס' %d - %s הוקפצה לאחרונה ב: %s",this.id, this.title.replaceAll("(\\r|\\n)", ""), this.lastBounced);
    }
}
