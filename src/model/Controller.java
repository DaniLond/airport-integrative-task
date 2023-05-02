package model;

import java.io.IOException;

public class Controller {
    static Passengers passengers= new Passengers();
    private AvionPriorityQueue queue;

    public Controller() throws IOException {
        passengers.load();
        queue= new AvionPriorityQueue();
    }

    public void insertPassengers(){
       queue.insertPassangers(passengers.getPassengersHashTable());
    }

    public void printPassengers(){
        System.out.println(queue.print());
    }

    public void insertExit(){
        queue.insertExit(passengers.getPassengersHashTable());
    }

}
