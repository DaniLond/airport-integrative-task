import model.AvionPriorityQueue;
import model.Passenger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvionPriorityQueueTest {
    private AvionPriorityQueue avionPriorityQueueTest;

    public void setup1(){
        avionPriorityQueueTest= new AvionPriorityQueue();
        Passenger passenger= new Passenger("Isabella" , "1-A" , "1" , 1000, true , false);
        passenger.calculateProirity();

        Passenger passengerDos= new Passenger("Luisa" , "3-A" , "2" , 100, false , false);
        passengerDos.calculateProirity();

        avionPriorityQueueTest.getInputqueue().insert(passengerDos);
        avionPriorityQueueTest.getInputqueue().insert(passenger);
    }

    @Test
    public void insertQueueAbording(){
        setup1();
        Passenger passenger= new Passenger("Daniela" , "2-A" , "3" , 1500, true , false);
        passenger.calculateProirity();
        avionPriorityQueueTest.getInputqueue().insert(passenger);

        assertEquals(avionPriorityQueueTest.print() , "Name: Luisa Chair: 3-A ID: 2\nName: Daniela Chair: 2-A ID: 3\nName: Isabella Chair: 1-A ID: 1\n");

    }

    public void setup2(){
        avionPriorityQueueTest= new AvionPriorityQueue();
    }
    @Test
    public void insertElementoNull(){
        setup2();
        avionPriorityQueueTest.getInputqueue().insert(null);
        assertEquals("" , avionPriorityQueueTest.print());
    }

    public void setup3(){
        avionPriorityQueueTest= new AvionPriorityQueue();
        Passenger passenger= new Passenger("Isabella" , "1-A" , "1" , 1000, true , false);
        passenger.calculateProirityExit();

        Passenger passengerDos= new Passenger("Luisa" , "3-A" , "2" , 100, false , false);
        passengerDos.calculateProirityExit();

        avionPriorityQueueTest.getInputqueue().insert(passengerDos);
        avionPriorityQueueTest.getInputqueue().insert(passenger);
    }

    @Test
    public void insertQueueLanding(){
        setup3();
        Passenger passenger= new Passenger("Daniela" , "2-A" , "3" , 1500, true , false);
        passenger.calculateProirityExit();
        avionPriorityQueueTest.getInputqueue().insert(passenger);

        assertEquals(avionPriorityQueueTest.print() , "Name: Isabella Chair: 1-A ID: 1\nName: Daniela Chair: 2-A ID: 3\nName: Luisa Chair: 3-A ID: 2\n");
    }

    public void setup4(){
        avionPriorityQueueTest= new AvionPriorityQueue();
        Passenger passenger= new Passenger("Juan" , "2-C" , "1" , 1000, true , false);
        passenger.calculateProirity();

        Passenger passengerDos= new Passenger("Yeison" , "5-A" , "2" , 120, false , false);
        passengerDos.calculateProirity();

        Passenger passengerTres= new Passenger("Gabriel" , "3-F" , "2" , 100, true , true);
        passengerTres.calculateProirity();

        avionPriorityQueueTest.getInputqueue().insert(passengerDos);
        avionPriorityQueueTest.getInputqueue().insert(passenger);
        avionPriorityQueueTest.getInputqueue().insert(passengerTres);
    }

    @Test
    public void deletePassenger(){
        setup4();
        Passenger passengerCuatro= new Passenger("Marinet" , "3-E" , "2" , 2000, true , true);
        passengerCuatro.calculateProirity();
        avionPriorityQueueTest.getInputqueue().insert(passengerCuatro);

        assertEquals(avionPriorityQueueTest.getInputqueue().extractMax().getName(), passengerCuatro.getName());
    }

    @Test
    public void deleteMultipleElements(){
        setup4();
        avionPriorityQueueTest.getInputqueue().extractMax();
        avionPriorityQueueTest.getInputqueue().extractMax();
        avionPriorityQueueTest.getInputqueue().extractMax();
        assertTrue(avionPriorityQueueTest.getInputqueue().isEmpty());
    }

    @Test
    public void testRemoveFromEmptyQueue(){
        setup2();
        assertNull(avionPriorityQueueTest.getInputqueue().extractMax());
    }
}
