package ui;

import model.Passengers;

import java.io.IOException;

public class Main {
    static Passengers passengers= new Passengers();
    public static void main(String[] args)throws IOException {
        passengers.load();
    }
}