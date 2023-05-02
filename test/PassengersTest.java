import excepctions.DuplicateItemException;
import model.Passenger;
import model.Passengers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PassengersTest {
    private Passengers passengersList;

    // Test for insert method
    public void setup1(){
        passengersList= new Passengers();
        Passenger passengerUno= new Passenger("Daniel" , "1A" , "2" , 0 , true, false);
        passengersList.getPassengersHashTable().insert("2" , passengerUno);
    }

    //testOne: Inserts an element into the hash table and verifies that it was inserted correctly.
    @Test
    public void addNewPassenger(){
        setup1();
        Passenger passenger= new Passenger("Johan" , "2B" , "6" , 100 , false, false);
        passengersList.getPassengersHashTable().insert("6" , passenger);
        assertEquals(passengersList.getPassengersHashTable().search("6").getId(), "6");
    }

    public void setUp2(){
        passengersList= new Passengers();
        Passenger passenger= new Passenger("Luisa" , "5B" , "11" , 200, false, false);
        passengersList.getPassengersHashTable().insert("11" , passenger);
    }

    //TestTwo: nserting an element into an already occupied position (collision handling)
    @Test
    public void insertarWithCollision(){
        setUp2();
        Passenger passengerDos= new Passenger("Andrea" , "2B" , "20" , 1200 , true, false);
        passengersList.getPassengersHashTable().insert("20" , passengerDos);
        assertEquals("11" , passengersList.getPassengersHashTable().search("11").getId());
        assertEquals("20", passengersList.getPassengersHashTable().search("20").getId());
    }

    public void setUp3(){
        passengersList= new Passengers();
        Passenger passenger= new Passenger("Isabella" , "3A" , "4", 1200 , true, false);
        passengersList.getPassengersHashTable().insert("4" , passenger);
    }

    //TestThree: inserting an item with a duplicate key
    @Test
    public void insertDuplicateItem(){
        setUp3();

        assertThrows(DuplicateItemException.class, ()->{
            passengersList.getPassengersHashTable().insert("4" ,  new Passenger("Isabella" , "3A" , "4", 1200 , false, false));
        });
    }

    // Test for the search method

    public void setUp4(){
        passengersList= new Passengers();
        Passenger passenger= new Passenger("Isabella" , "3A" , "4", 1200, false, false);
        Passenger passenger1= new Passenger("Paula" , "4A", "11" , 0, false, false);
        Passenger passenger2= new Passenger("Camilo" , "2C" , "12" , 10000, false, false);

        passengersList.getPassengersHashTable().insert("4" , passenger);
        passengersList.getPassengersHashTable().insert("11" , passenger1);
        passengersList.getPassengersHashTable().insert("12" , passenger2);
    }

    //TestOne: successful search for a passenger
    @Test
    public void successfulSearch(){
        setUp4();
        Passenger passenger3= new Passenger("Daniela" , "3C" , "20" , 10000 , false , false);
        passengersList.getPassengersHashTable().insert("20" , passenger3);

        Passenger passengerSeeker= passengersList.getPassengersHashTable().search("20");
        assertEquals("Daniela", passengerSeeker.getName());
    }

    //TestTwo: search for a passenger without success
    @Test
    public void UnsuccessfulSearch(){
        setUp4();
        Passenger passengerSeeker= passengersList.getPassengersHashTable().search("20");
        assertNull(passengerSeeker);
    }

    //TestThree: search with collisions
    @Test
    public void searchWithCollisions(){
        setUp4();
        // In the first iteration, the result of the hash function of the passenger Juan is the same as the passenger Paula who is in scenario 4, in this case for both it would be 26
        Passenger passenger= new Passenger("Juan" , "5E" , "20" , 1000 , false, false);
        passengersList.getPassengersHashTable().insert("20" , passenger);
        Passenger passengerSeeker= passengersList.getPassengersHashTable().search("20");
        assertEquals("Juan" , passengerSeeker.getName());
    }
}
