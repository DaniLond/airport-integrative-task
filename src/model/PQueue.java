package model;

import java.util.ArrayList;

public class PQueue<T extends Comparable<T>>{
    private ArrayList<T> heap;

    public PQueue() {
        this.heap = new ArrayList<>();
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int leftChild(int i) {
        return 2 * i + 1;
    }

    public int rightChild(int i) {
        return 2 * i + 2;
    }

    /**
     * Description: Swap the elements at two different indices of the heap
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Description:The insert method is used to insert an element on a heap,
     * specifically on a Max Heap, where the element to be inserted must be greater than
     * or equal to the existing elements on the heap.
     * @param item
     */

    public void insert(T item) {
        if (item == null){
            return;
        }
        heap.add(item);
        int i = heap.size() - 1;
        while (i > 0 && heap.get(i).compareTo(heap.get(parent(i))) > 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }


    /**
     * Description:The extractMax() method is used to remove the highest priority element (the maximum)
     * from a heap, and then reorder the structure to maintain the heap maximum property.
     * @return
     */
    public T extractMax() {
        if (heap.isEmpty()){
            return null;
        }
        T maxItem = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        maxHeapify(0);
        return maxItem;
    }

    /**
     *Description: is used to maintain the property of a max-heap after
     * the root node has been removed or its value has been changed.
     * @param i
     */

    public void maxHeapify(int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        int largest = i;
        if (l < heap.size() && heap.get(l).compareTo(heap.get(largest)) > 0) {
            largest = l;
        }
        if (r < heap.size() && heap.get(r).compareTo(heap.get(largest)) > 0) {
            largest = r;
        }
        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    /**
     * Descripton: The isEmpty() method is used to check if the heap is empty or not.
     * @return
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
