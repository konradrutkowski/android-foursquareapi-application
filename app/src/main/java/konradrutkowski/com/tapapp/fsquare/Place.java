package konradrutkowski.com.tapapp.fsquare;

import java.io.Serializable;

public class Place implements Serializable {

    private String url;
    private String ID;
    private String name;
    private String city;
    private String category;
    private String checkins;
    private String distance;
    private String address;
    private long dbID;

    public long getDbID() {
        return dbID;
    }

    public void setDbID(long dbID) {
        this.dbID = dbID;
    }

    public String getUrl() {
        return url;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }


    public String getCity() {
        return city;
    }

    public String getCategory() {
        return category;
    }

    public String getCheckins() {
        return checkins;
    }

    public String getDistance() {
        return distance;
    }

    public String getAddress() {
        return address;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCheckins(String checkins) {
        this.checkins = checkins;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


