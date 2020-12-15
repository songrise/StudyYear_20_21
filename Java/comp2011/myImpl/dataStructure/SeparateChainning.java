package comp2011.myImpl.dataStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yixin cao (November 20, 2020)
 *
 *         A naive implementation of hash table with separate chaining.
 * 
 *         This hash table can only store integers.
 * 
 *         A version with generics can be found at Section 10.2.4 of the
 *         textbook.
 */
public class SeparateChainning {

    public static void main(String[] args) {
        SeparateChainning table = new SeparateChainning(7);
        int[] a = { 1001, 1003, 1011, 2011, 2021, 2411, 2421, 3011, 3334, 3438, 4011, 4432 };
        table.batchAdd(a);
        System.out.println(table);
    }

    private List<Integer>[] data;
    private final int size;

    @SuppressWarnings("unchecked")
    public SeparateChainning(int size) {
        this.size = size;
        data = new ArrayList[size];
        for (int i = 0; i < size; i++)
            data[i] = new ArrayList<Integer>();
    }

    public void batchAdd(int[] labels) {
        if (labels == null) {
            return;
        }
        for (int i : labels) {
            insert(i);
        }
    }

    // very stupid one.
    public int hash(int key) {
        return key % size;
    }

    public void insert(int key) {
        int h = hash(key);
        data[h].add(key); // O(1) time.
    }

    public void delete(int key) {
        int h = hash(key);
        data[h].remove(data[h].indexOf(key));
        // to make it work, we need to add delete() to class LinkedList.

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
            sb.append("slot " + i + ": " + data[i] + '\n');
        return sb.toString();
    }

}
