package model;

import java.io.*;

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

}
