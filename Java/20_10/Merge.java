import java.util.Arrays;

public class Merge {

    public static int[] merge(int[] a, int[] b) {
        int i = 0, ai = 0, bi = 0;
        int[] ans = new int[a.length + b.length];
        while (ai < a.length && bi < b.length) {
            if (a[ai] < b[bi]) {
                ans[i++] = a[ai++];
            } else {
                ans[i++] = b[bi++];
            }
        }
        while (ai < a.length) {
            ans[i++] = a[ai++];
        }
        while (bi < b.length) {
            ans[i++] = b[bi++];
        }

        return ans;
    }

    public static int[] merge2(int[] a, int[] b) {
        int i = 0, ai = 0, bi = 0;
        int[] ans = new int[a.length + b.length];
        while (ai < a.length && bi < b.length) {
            if (a[ai] < b[bi]) {
                ans[i++] = a[ai++];
            } else {
                ans[i++] = b[bi++];
            }
        }
        while (ai < a.length) {
            ans[i++] = a[ai++];
        }
        while (bi < b.length) {
            ans[i++] = b[bi++];
        }

        return ans;
    }

    public static int[] Rmerge(int[] a, int[] b, int ia, int ib) {
        if (ia == a.length) {
            return Rmerge(a, b, ia, ib + 1);
        } else if (ib == b.length) {
            return Rmerge(a, b, ia + 1, ib);
        } else {
            if (condition) {
                
            } else {
                
            }
        }
    }

    public static void main(String[] args) {
        int[] a = { 3, 4, 7, 9 };
        int[] b = { 1, 2, 6, 10, 11 };
        Arrays.binarySearch(a, fromIndex, toIndex, key)
        System.out.println(Arrays.toString(merge(a, b)));
        
    }
}
