package model;

import java.util.*;

public class AvionPriorityQueue{

    private PriorityQueue<String, Passenger> queue;
    private PriorityQueue<String, Passenger> queuein;

    public  AvionPriorityQueue() {
        this.queue = new PriorityQueue<>();
    }

    public  void setPriority(Passenger p){
        String[] chair= p.getChair().split("-");
        String column= chair[1];
        String row= chair[0];
        if (row.equals("1")){
            p.setPriority(p.getPriority() + 1);
        } else if (row.equals("2")) {
            p.setPriority(p.getPriority() + 2);
        }else if (row.equals("3")) {
            p.setPriority(p.getPriority() + 3);
        }else if (row.equals("4")) {
            p.setPriority(p.getPriority() + 4);
        }else if (row.equals("5")) {
            p.setPriority(p.getPriority() + 5);
        }else if (row.equals("6")) {
            p.setPriority(p.getPriority() + 6);
        }

        if (column.equals("C") || column.equals("D")){
            p.setPriority(p.getPriority() + 7 + p.getTime());
        }else if (column.equals("B") || column.equals("E")){
            p.setPriority(p.getPriority() + 8 + p.getTime());
        } else if (column.equals("A") || column.equals("F")) {
            p.setPriority(p.getPriority() + 9 + p.getTime());
        }

    }

    public void setPriorityPassangers(HashTable<String, Passenger> passengerHashTable){

            for (int i = 0; i < passengerHashTable.getTable().length; i++) {
                setPriority(passengerHashTable.getTable()[i].getValue());
                passengerHashTable.getTable()[i].setKey(Integer.toString(passengerHashTable.getTable()[i].getValue().getPriority()));
            }

    }

    public void insertPassangers(HashTable<String, Passenger> passengerHashTable){
        for (int i= 0; i < passengerHashTable.getTable().length; i++){
            setPriorityPassangers(passengerHashTable);
            Node<String, Passenger> node= passengerHashTable.getTable()[i];
            queue.insert(node);

        }
    }

    public void print(){
        for (int i=0; i < queue.end; i++){
            System.out.println(queue.getQueue()[i].getValue().getName()+" - "+queue.getQueue()[i].getValue().getChair()+" - "+queue.getQueue()[i].getValue().getPriority()+" - "+queue.getQueue()[i].getValue().getTime());
        }

        System.out.println(" ---- ");

        queue.orderPriority();
        for (int i=0; i < queue.end; i++){
            System.out.println(queue.getQueue()[i].getValue().getName()+" - "+queue.getQueue()[i].getValue().getChair()+" - "+queue.getQueue()[i].getValue().getPriority()+" - "+queue.getQueue()[i].getValue().getTime());
        }

        System.out.println(" --2-- ");

        queue.orderPrioritymintomax();
        for (int i=0; i < queue.end; i++){
            System.out.println(queue.getQueue()[i].getValue().getName()+" - "+queue.getQueue()[i].getValue().getChair()+" - "+queue.getQueue()[i].getValue().getPriority()+" - "+queue.getQueue()[i].getValue().getTime());
        }

    }

    public void orderByChair(HashTable<String, Passenger> passengerHashTable) {
        // Crear un array auxiliar para almacenar los pasajeros ordenados por número de silla
        Passenger[] orderedPassengers = new Passenger[passengerHashTable.getTable().length];

        // Recorrer la tabla hash y agregar los pasajeros al array auxiliar
        int index = 0;
        for (int i = 0; i < passengerHashTable.getTable().length; i++) {
            if (passengerHashTable.getTable()[i] != null) {
                Passenger passenger = passengerHashTable.getTable()[i].getValue();
                orderedPassengers[index] = passenger;
                index++;
            }
        }

        // Ordenar el array auxiliar por número de silla
        for (int i = 0; i < orderedPassengers.length - 1; i++) {
            for (int j = i + 1; j < orderedPassengers.length; j++) {
                if (orderedPassengers[j] != null && orderedPassengers[i].getChair().compareTo(orderedPassengers[j].getChair()) > 0) {
                    Passenger temp = orderedPassengers[i];
                    orderedPassengers[i] = orderedPassengers[j];
                    orderedPassengers[j] = temp;
                }
            }
        }

        // Imprimir los pasajeros ordenados por número de silla
        for (int i = 0; i < orderedPassengers.length; i++) {
            if (orderedPassengers[i] != null) {
                System.out.println(orderedPassengers[i].getName() + " - " + orderedPassengers[i].getChair());
           }
        }

    }
    private int[] getRowAndColumn(String chair) {
        int row = Integer.parseInt(chair.substring(0,1));
        int col = (int)chair.charAt(2) - 65; // Restar el valor ASCII de "A"
        return new int[]{row, col};
    }
    public void orderByArrival(HashTable<String, Passenger> passengerHashTable) {
        // Crear una matriz de pasajeros
        Passenger[][] seats = new Passenger[6][6];
        for (int i = 0; i < passengerHashTable.getTable().length; i++) {
            Passenger passenger = passengerHashTable.getTable()[i].getValue();
            int[] rc = getRowAndColumn(passenger.getChair());
            seats[rc[0]-1][rc[1]] = passenger;
        }

        // Crear una matriz auxiliar para ordenar los pasajeros
        Passenger[] aux = new Passenger[36];
        int k = 0;

        // Ordenar los pasajeros por fila y columna dentro de cada fila
        for (int i = 5; i >= 0; i--) { // Recorrer filas de la 6 a la 1
            for (int j = 0; j < 6; j++) { // Recorrer columnas de la A a la D
                if (seats[i][j] != null) {
                    aux[k++] = seats[i][j];
                }
            }
        }

        // Imprimir los pasajeros en orden de llegada
        for (int i = 0; i < k; i++) {
            System.out.println((i+1) + ": " + aux[i].getName() + " - " + aux[i].getChair() + " - " + aux[i].getTime() );
        }
    }

    public void printBoardingOrder(HashTable<String, Passenger> passengerHashTable) {
        ArrayList<Passenger>[] boardingOrder = new ArrayList[6];
        for (int i = 0; i < 6; i++) {
            boardingOrder[i] = new ArrayList<Passenger>();
        }

        // Divide the passengers into sections based on their seat row
        for (int i = 0; i < passengerHashTable.getTable().length; i++) {
            Passenger passenger = passengerHashTable.getTable()[i].getValue();
            int row = Integer.parseInt(passenger.getChair().substring(0, 1));
            boardingOrder[row-1].add(passenger);
        }

        // Sort each section by boarding time
        for (int i = 0; i < 6; i++) {
            Collections.sort(boardingOrder[i], new Comparator<Passenger>() {
                public int compare(Passenger p1, Passenger p2) {
                    return Integer.compare(p1.getTime(), p2.getTime());
                }
            });
        }

        // Print the boarding order
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < boardingOrder[i].size(); j++) {
                System.out.println((j+1) + ": "+boardingOrder[i].get(j).getName() + " - " + boardingOrder[i].get(j).getChair() + " - " + boardingOrder[i].get(j).getTime() );
            }
        }
    }



}
