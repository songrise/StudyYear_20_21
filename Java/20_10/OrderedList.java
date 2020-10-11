
/**
 * A simple ordered list data structure For comp 2011 a1 propose only.
 * 
 * @author : Ruixiang JIANG (Songrise)
 */
interface orderedList<T> {
    T popMax();

    T peekMax();

    void insert(T newNode);

    int len();
}

/**
 * A simple ordered list data structure Implemented with java.util.LinkedList
 * guarantee O(1) for peekMax() and popMax() operation
 * 
 * @author : Ruixiang JIANG (Songrise)
 */

class OrderedLinkedList<T extends Comparable<T>> implements orderedList<T> {
    private java.util.LinkedList<T> list;

    OrderedLinkedList() {
        list = new LinkedList<T>();
    }

    @Override
    public T popMax() {
        try {
            return list.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public T peekMax() {
        return list.peekFirst();
    }

    /***
     * insert the newNode to the list, keep the list ordered.
     * 
     * @param newNode
     */
    @Override
    public void insert(T newNode) {
        int i = 0;// point to current list node
        if (list.size() == 0) {
            list.add(newNode);
            return;
        }

        for (T p : list) {
            if (p.compareTo(newNode) < 0) {
                break;
            }
            i++;
        }
        list.add(i, newNode);
    }

    @Override
    public int len() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }

}