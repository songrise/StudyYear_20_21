package comp2011.myImpl.dataStructure;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author yixin cao (November 20, 2020)
 *
 *         A naive implementation of hash table with linear probing, for the
 *         purpose of demonstration.
 * 
 *         This hash table can only store integers.
 * 
 *         A version with generics can be found at Section 10.2.4 of the
 *         textbook.
 */
public class LinearProbingHash {

    public static void main(String[] args) {
        // For demonstration, we use a small table with few elements.
        LinearProbingHash table = new LinearProbingHash(17);
        int[] subjects = { 2012, 4141, 4142, 4146, 4334, 4342, 4431, 4433, 4434, 4435, 4531, 4921 };
        table.batchAdd(subjects);
        table.delete(2012);
        // table.search();
        System.out.println(Arrays.toString(table.data));
        table.insert(2011);

        // loadFactorTest();
    }

    private int M;
    private int[] data;
    boolean[] tombstone;
    private int size = 0;

    LinearProbingHash(int M) {
        this.M = M;
        data = new int[M];
        tombstone = new boolean[M];
        // initial every entry to be -1.
        Arrays.fill(data, -1);
        // The following can be omitted, beacuse Java always do this during its
        // initialization.
        Arrays.fill(tombstone, false);
    }

    public void batchAdd(int[] labels) {
        if (labels == null) {
            return;
        }
        for (int i : labels) {
            insert(i);
        }
    }

    public void insert(int code) {
        // Actually, we should take cation once size/M >= 0.5.
        if (size == M) {
            System.out.println("overflow");
            return;
        }
        int i;
        for (i = hash(code); (!tombstone[i]) && data[i] != -1; i = (i + 1) % M)
            if (data[i] == code)
                break;
        data[i] = code;
        tombstone[i] = false;
        size++;
    }

    public int search(int code) {
        for (int i = hash(code); tombstone[i] || data[i] != -1; i = (i + 1) % M)
            if (code == data[i])
                return i;
        return -1; // meaning not found
    }

    public void delete(int code) {
        for (int i = hash(code); tombstone[i] || data[i] != -1; i = (i + 1) % M)
            if (code == data[i]) {
                tombstone[i] = true;
                data[i] = -1; // optional and suggested
                size--;
            }
    }

    int hash(int code) {
        return code % M;
    }

    int[] clusters() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] ans = { M };
        if (size == M)
            return ans;
        int last = M - 1;
        for (; data[last] != -1; last--)
            ;
        int i = 0;
        int c = M - 1 - last;
        while (i < last) {
            for (; i < last && (tombstone[i] || data[i] != -1); i++)
                c++;
            if (c > 1)
                list.add(c);
            c = 0;
            i++;
        }
        ans = new int[list.size()];
        for (int j = 0; j < list.size(); j++)
            ans[j] = list.get(j);
        return ans;
    }

    static void loadFactorTest() {
        int TURNS = 100;
        int RANGE = 1 << 30;
        System.out.println("RANGE: " + RANGE);
        SecureRandom random = new SecureRandom();

        System.out.println("Part I: testing of low load factor, 40/97.");
        for (int i = 0; i < TURNS; i++) {
            LinearProbingHash table = new LinearProbingHash(97);
            for (int j = 0; j < 40; j++) {
                table.insert(random.nextInt(RANGE));
            }
            System.out.println(Arrays.toString(table.clusters()));
        }

        System.out.println("\n\nPart II: testing of high load factor, 49/97.");
        for (int i = 0; i < TURNS; i++) {
            LinearProbingHash table = new LinearProbingHash(97);
            for (int j = 0; j < 49; j++) {
                table.insert(random.nextInt(RANGE));
            }
            System.out.println(Arrays.toString(table.clusters()));
        }
    }
}
