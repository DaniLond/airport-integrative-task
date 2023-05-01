package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PriorityQueue<K extends Comparable<K>, V> {
    private Node<K,V>[] queue;
    int front;
    int end;

    public static final int arrSize= 36;

    public PriorityQueue() {
        this.queue = new Node[arrSize];
        this.front = -1;
        this.end = -1;
    }

    public void orderPriority(){
        for (int i=0; i < end; i++){
            int posMinor= i;

            for (int j=i; j <= end; j++){
                if (queue[j] == null){
                    queue[j]= queue[j+1];
                    queue[j+1]= null;
                    if (j+1==end){
                        end--;
                    }
                }
                if (queue[posMinor].getKey().compareTo(queue[j].getKey()) < 0){
                    posMinor= j;
                }
            }
            Node<K,V> aux2= queue[i];
            queue[i]= queue[posMinor];
            queue[posMinor]=aux2;
        }
    }

    public void orderPrioritymintomax() {
        for (int i = 0; i < queue.length - 1; i++) {
            int posMinor = i;
            for (int j = i + 1; j < queue.length; j++) {
                if (queue[j] == null) {
                    continue;
                }
                if (queue[j] != null && queue[posMinor].getKey().compareTo(queue[j].getKey()) > 0) {
                    posMinor = j;
                }
            }
            if (queue[posMinor] != null) {
                Node<K,V> aux = queue[i];
                queue[i] = queue[posMinor];
                queue[posMinor] = aux;
            }
        }
    }

    public void insert(Node<K,V> v){
        if (((end==queue.length - 1) && (front == 0) || (end + 1) == front)){
            System.out.println("\n queue overflow");
        }else {
            if (end == queue.length -1){
                end=0;
            }else {
                end= end + 1;
                queue[end]= v;
            }
            if (end == 0 && front == -1){
                front=0;
            }
        }
        //orderPriority();
    }

    public void delete(){
        Node<K, V> v;
        if (front==-1){
            System.out.println("\n underflow");
            v= null;
        }else {
            v= queue[front];
            queue[front]= null;
            if (front==end){
                front= -1;
                end=-1;
            }else {
                orderPriority();
            }
        }
    }

    public Node<K, V>[] getQueue() {
        return queue;
    }



}
