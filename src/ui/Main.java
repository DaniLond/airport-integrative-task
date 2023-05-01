package ui;

import model.Controller;
import model.Passengers;

import java.io.IOException;

public class Main {

    static Passengers passengers= new Passengers();
    static Controller controller;
    public static void main(String[] args)throws IOException {
        controller= new Controller();
        controller.insertPassengers();
        controller.printPassengers();
    }
}

