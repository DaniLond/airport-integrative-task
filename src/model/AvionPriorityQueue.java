package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class AvionPriorityQueue<T> {
    private PriorityQueue<T> queue;
    private Comparator<T> comparator;
    private Map<T, Integer> arrivalOrderMap;

    public AvionPriorityQueue(Comparator<T> comparator) {
        this.queue = new PriorityQueue<>(comparator);
        this.comparator = comparator;
        this.arrivalOrderMap = new HashMap<>();
    }

    public void enqueue(T element, int arrivalOrder) {
        queue.add(element);
        arrivalOrderMap.put(element, arrivalOrder);
    }

    public T dequeue() {
        T element = queue.poll();
        arrivalOrderMap.remove(element);
        return element;
    }

    public T peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }



    static class PersonaComparator implements Comparator<Passenger> {
        private Map<Passenger, Integer> arrivalOrderMap;

        public PersonaComparator(Map<Passenger, Integer> arrivalOrderMap) {
            this.arrivalOrderMap = arrivalOrderMap;
        }


        @Override
        public int compare(Passenger o1, Passenger o2) {
            return 0;
        }

        @Override
        public Comparator<Passenger> reversed() {
            return Comparator.super.reversed();
        }

        @Override
        public Comparator<Passenger> thenComparing(Comparator<? super Passenger> other) {
            return Comparator.super.thenComparing(other);
        }

        @Override
        public <U> Comparator<Passenger> thenComparing(Function<? super Passenger, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
            return Comparator.super.thenComparing(keyExtractor, keyComparator);
        }

        @Override
        public <U extends Comparable<? super U>> Comparator<Passenger> thenComparing(Function<? super Passenger, ? extends U> keyExtractor) {
            return Comparator.super.thenComparing(keyExtractor);
        }

        @Override
        public Comparator<Passenger> thenComparingInt(ToIntFunction<? super Passenger> keyExtractor) {
            return Comparator.super.thenComparingInt(keyExtractor);
        }

        @Override
        public Comparator<Passenger> thenComparingLong(ToLongFunction<? super Passenger> keyExtractor) {
            return Comparator.super.thenComparingLong(keyExtractor);
        }

        @Override
        public Comparator<Passenger> thenComparingDouble(ToDoubleFunction<? super Passenger> keyExtractor) {
            return Comparator.super.thenComparingDouble(keyExtractor);
        }
    }





}
