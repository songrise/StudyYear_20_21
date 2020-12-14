package Final;

class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    Pair(T1 first, T2 second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        Pair<T1, T2> pair = (Pair<T1, T2>) object;
        return pair.getFirst().equals(this.getFirst()) && pair.getSecond().equals(this.getSecond());
    }

    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }

}

class a18_12 {

    public static void main(String[] args) {
        Pair<Integer, Double> p = new Pair<>(12, 23.0);
        System.out.println(p.equals(new Pair<Integer, Float>(12, 23.0f)));
    }
}