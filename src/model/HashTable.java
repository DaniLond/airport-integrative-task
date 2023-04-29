package model;

import excepctions.DuplicateItemException;

import java.util.Arrays;

public class HashTable<K,V> implements IHashTable<K,V> {
    public static final int arrSize= 36;
    private Node<K,V>[] table;

    public HashTable() {
        this.table = new Node[arrSize];
    }

    /**
     * Description: Coverts a string to an int value
     * @param string String
     * @return resultado Int
     */
    public int stringToInt(String string) {
        int resultado = 0;
        for (int i = 0; i < string.length(); i++) {
            resultado += (int) string.charAt(i);
        }
        return resultado;
    }

    /**
     * Description:Calculates the position in which an element must go in the hash table
     * @param key K
     * @param i int
     * @return int
     */
    public int h(K key , int i){
        int keyInt= stringToInt((String) key);
        return ((keyInt % arrSize + i) % arrSize);

    }

    /**
     * Description: Insert an element to the hash table taking into account the hash function,
                    handling collions and in case the element is already there it throws an exception
     * @param key K
     * @param value V
     * @throws DuplicateItemException DuplicateItemException
     */
    @Override
    public void insert(K key, V value) throws DuplicateItemException{
        int i = 0;
        do {
            int j = h(key, i);
            if (table[j] == null) { // si la posición está libre
                table[j] = new Node<>(key, value);
                return;
            } else {
                if (!(table[j].getKey() == key)){
                    i++;
                }else {
                    //Elemento duplicado
                    throw new DuplicateItemException();
                }
            }
        } while (i != arrSize); // si se ha recorrido toda la tabla, hay desbordamiento
        throw new RuntimeException("Desbordamiento de tabla hash");
    }

    /**
     * Description: Looks up an element in the hash table given its key to use in the hash function and returns that element
     * @param key K
     * @return V
     */
    @Override
    public V search(K key) {
        int i = 0;
        int j;
        do {
            j = h(key, i);
            if (table[j] != null && table[j].getKey().equals(key)) { // si se ha encontrado la clave
                return table[j].getValue();
            }
            i++;
        } while (table[j] != null && i != arrSize); // si se ha recorrido toda la tabla o se encuentra una posición libre
        return null; // si no se ha encontrado la clave
    }

    public Node<K, V>[] getTable() {
        return table;
    }


    public void setTable(Node<K, V>[] table) {
        this.table = table;
    }


}
