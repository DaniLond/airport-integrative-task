package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Passengers {
    private HashTable<String, Passenger> passengersHashTable;

    static String path= "airport-integrative-task/data.txt";

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
        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String content = "";
        String line = "";
        while ((line = reader.readLine()) != null) {
            content += line + "\n";
        }
        Gson gson = new Gson();
        Node<String, Passenger>[] array = gson.fromJson(content, new TypeToken<Node<String, Passenger>[]>(){}.getType());

        int n=0;
        for (Node<String, Passenger> d : array) {
            n++;
            Passenger valor = d.getValue();
            String clave = d.getKey();
            valor.setTime(n);
            passengersHashTable.insert(clave, valor);
        }
        fis.close();

    }



}
