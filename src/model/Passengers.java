package model;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.HashTable;
import model.Node;
import model.Passenger;

public class Passengers {
    private HashTable<String, Passenger> passengersHashTable;

    static String path= "data.txt";

    public Passengers() {
        this.passengersHashTable = new HashTable<>();
    }


    public HashTable<String, Passenger> getPassengersHashTable() {
        return passengersHashTable;
    }

    /**
     * Description: Load the elements of the txt to the hash table
     * @throws IOException IOException
     */
    public void load() throws IOException {
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String content = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            Gson gson = new Gson();
            Node<String, Passenger>[] array = gson.fromJson(content, new TypeToken<Node<String, Passenger>[]>(){}.getType());

            for (Node<String, Passenger> d : array) {
                Passenger valor = d.getValue();
                String clave = d.getKey();
                passengersHashTable.insert(clave, valor);
            }
            fis.close();
        } else {
            file.createNewFile();
        }
    }

    /**
     * Description:calculate the priority of passengers when boarding the plane for first class
     */

    public int calculatePriority(Passenger passenger) {
        int priority = 0;
        if (passenger.getSpecialNeeds() != null) {
            for (SpecialNeeds need : passenger.getSpecialNeeds()) {
                switch (need) {
                    case PREGNANT:
                        priority += 3;
                        break;
                    case DISABLED:
                        priority += 5;
                        break;
                    case THIRD_AGE:
                        priority += 4;
                        break;
                    case WITH_CHILDREN:
                        priority += 2;
                        break;
                    default:
                        break;
                }
            }
        }
        priority += passenger.getMiles();

        // Checking for seat class
        if (passenger.getChair().equalsIgnoreCase("First Class")) {
            priority += 1;
        }

        return priority;
    }
    public ArrayList<SpecialNeeds> getSpecialNeeds() {

        ArrayList<SpecialNeeds> specialNeeds = getSpecialNeeds();
        return specialNeeds;
    }



}
