package ui;

import model.Controller;
import model.Passengers;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Passengers passengers= new Passengers();
    static Scanner scanner= new Scanner(System.in);
    static Controller controller;
    public static void main(String[] args)throws IOException {
        controller= new Controller();
        menu();
    }

    public static void menu() {
        System.out.println("welcome");

        boolean exit = false;

        while (!exit) {
            System.out.println("Enter a option\n1. show aboarding list \n2. show lading list\n3. Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Aboarding passangers");
                    controller.insertPassengers();
                    controller.printPassengers();
                    break;
                case 2:
                    System.out.println("Lading passangers");
                    controller.insertExit();
                    controller.printPassengers();
                    break;
                default:
                    exit = true;
                    break;
            }
        }
    }
}

