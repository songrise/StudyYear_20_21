package comp2011.lec12;

import java.util.ArrayList;
import java.util.List;

import comp2011.lec4.LinkedList;

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
	private List<Integer>[] data;
	private final int size;

	@SuppressWarnings("unchecked")
	public SeparateChainning(int size) {
		this.size = size;
		data = new ArrayList[size];
		for (int i = 0; i < size; i++)
			data[i] = new ArrayList<Integer>();
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

	public static void main(String[] args) {
		SeparateChainning table = new SeparateChainning(13);
		table.insert(14);
		table.insert(41);
		table.insert(40);
		table.delete(14);
		System.out.println(table);
	}
}
