package model;

import java.util.ArrayList;
import java.util.Collections;

public class Passenger implements Comparable<Passenger>{
    private String name;
    private String chair;
    private String id;
    private int miles;
    private int time;
    private int priority;
    private boolean thirdAge;
    private boolean specialNeeds;
    public Passenger(String name, String chair, String id, int miles, boolean specialNeeds, boolean thirdAge ) {
        this.name = name;
        this.chair = chair;
        this.id = id;
        this.miles = miles;
        this.specialNeeds = specialNeeds;
        this.thirdAge= thirdAge;
    }



    /**
     * Descrption: the method compares two Passenger objects based on their priority and their arrival time.
     * If both objects have the same priority, the tie is broken by their arrival time.
     * If the objects have different priorities, the comparison of their priorities is returned,
     * resulting in descending order by priority
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Passenger o) {
        if (this.priority == o.priority) { // Si tienen la misma prioridad, se desempata con la hora de llegada
            return Integer.compare(this.time, o.time);
        } else {
            return Integer.compare(this.priority, o.priority); // Ordenamiento descendente por prioridad
        }
    }

    /**
     * Description: calculates a passenger's priority based on their location on the plane and certain attributes
     * that may indicate their need for special attention.
     */
    public void calculateProirity(){
        int fila = Integer.parseInt(chair.substring(0, chair.length() - 2));

        int prioridadFila = 0;
        if (fila <= 3) { // Si es de primera clase, se le da mayor prioridad
            prioridadFila = (1000 + fila) * 100;
            int necesidadesEspeciales = this.specialNeeds ? 10 : 0; // Mayor prioridad si tiene necesidades especiales
            int terceraEdad = this.thirdAge ? 5 : 0; // Mayor prioridad si es tercera edad
            priority=  prioridadFila * 1000 + necesidadesEspeciales * 1000 + terceraEdad * 100 + this.miles/1000;

        } else { // Si no es de primera clase, se calcula la prioridad normal
            prioridadFila = fila * 100;
            priority=  prioridadFila * 10 ;

        }
    }

    /**
     * Description:is in charge of calculating the priority of a passenger for the time of departure from the plane.
     * For this, the row and column of the passenger seat are first removed.
     */

    public void calculateProirityExit(){
        int fila = Integer.parseInt(chair.substring(0, chair.length() - 2));
        String columna = chair.substring(2) ;

        int prioridadFila= (7 - fila ) * 100000;
        int prioridadColumna= 0;
        if (columna.equals("C") || columna.equals("D")){
           prioridadColumna=  400;
        } else if (columna.equals("B") || columna.equals("E")) {
            prioridadColumna= 300;
        }else if (columna.equals("A") || columna.equals("F")) {
            prioridadColumna= 200;
        }

        priority= prioridadFila  + prioridadColumna;
    }

    /**
     * Descriptión: Just print the name, chair and ID of the passanger
     * @return
     */
    public String print(){
        return  "Name: " + name + " Chair: " + chair + " ID: " +id ;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
