package model;

import java.util.ArrayList;

public class Passenger {
    private String name;
    private String chair;
    private String id;
    private int miles;

    private ArrayList<SpecialNeeds> specialNeeds;

    public Passenger(String name, String chair, String id, int miles) {
        this.name = name;
        this.chair = chair;
        this.id = id;
        this.miles = miles;
        this.specialNeeds = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }
}
