package model;

public class AvionPriorityQueue{

    private PQueue<Passenger> Inputqueue;
    private PQueue<Passenger> outqueue;

    public  AvionPriorityQueue() {
        this.Inputqueue = new PQueue();
    }


    /**
     * Description: This is a method that takes a hash table containing Passenger objects and inserts them
     * into an Inputqueue after calculating their priority.
     * @param passengerHashTable
     * @return void
     */

    public void insertPassangers(HashTable<String, Passenger> passengerHashTable){

        for (int i= 0; i < passengerHashTable.getTable().length; i++){
            passengerHashTable.getTable()[i].getValue().calculateProirity();
            Passenger passenger= passengerHashTable.getTable()[i].getValue();
            Inputqueue.insert(passenger);
        }
    }

    /**
     * Description: Inserts passengers into the departure queue.
     * @param passengerHashTable
     */

    public void insertExit(HashTable<String, Passenger> passengerHashTable){
        for (int i= 0; i < passengerHashTable.getTable().length; i++){
            //setPriorityPassangers(passengerHashTable);
            passengerHashTable.getTable()[i].getValue().setPriority(0);
            passengerHashTable.getTable()[i].getValue().calculateProirityExit();
            Passenger passenger= passengerHashTable.getTable()[i].getValue();
            Inputqueue.insert(passenger);
        }
    }

    /**
     * Description:The print() method returns a text string containing the
     * information of the passengers in the Inputqueue.
     * @return s String
     */
    public String print() {
        String s= "";
        while (!Inputqueue.isEmpty()) {
            s += Inputqueue.extractMax().print() + "\n";
        }
        return s;
    }

    public PQueue<Passenger> getInputqueue() {
        return Inputqueue;
    }

    public PQueue<Passenger> getOutqueue() {
        return outqueue;
    }
}
