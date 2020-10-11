package comp2011.ass1;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 
 * @author yixin cao (October 1, 2020)
 *
 *         A simulation of the triage of the Queen Elizabeth Hospital. Each
 *         patient has been assigned a urgent level.
 */

class Patient implements Comparable {
    String name;
    String urgence;

    Patient(String name, String urgence) {
        this.name = name;
        this.urgence = urgence;
    }

    public String toString() {
        return null;
        // implement this.
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }
}

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : Elizebath.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-10-03
 * @Github ：https://github.com/songrise
 * @Descriptions: A simple utility class for array-based heap.
 **/
class MyArrayHeapUtil {
    private void Sheapify(T[] a, int i, int heapLength) {
        int l = left(i);
        int r = right(i);
        int smallest = -1;
        /**
         * 下面两个if条件句用来找到三个元素中的最小元素的位置smallest； s < heapLength说明l在数组内，i非叶子结点；
         */
        if (l < heapLength && a[i].compareTo(a[l]) > 0) {
            smallest = l;
        } else {
            smallest = i;
        }
        // r < heapLength说明r在数组内
        if (r < heapLength && a[smallest].compareTo(a[r]) > 0) {
            smallest = r;
        }
        // 如果i处元素不是最小的，就把i处的元素与最小处元素交换，交换会使元素下降
        if (i != smallest) {
            T temp = a[i];
            a[i] = a[smallest];
            a[smallest] = temp;
            // 交换元素后，以a[i]为根的树可能不在满足大根堆性质，于是递归调用该方法
            heapify(a, smallest, heapLength);
            Arrays.sort(a);
        }
    }

    public void Lheapify(T[] a, int i, int heapLength) {
        int l = left(i);
        int r = right(i);
        int largest = -1;
        /**
         * 下面两个if条件句用来找到三个元素中的最大元素的位置largest； l < heapLength说明l在数组内，i非叶子结点；
         */
        if (l < heapLength && a[i].compareTo(a[l]) < 0) {
            largest = l;
        } else {
            largest = i;
        }
        // r < heapLength说明r在数组内
        if (r < heapLength && a[largest].compareTo(a[r]) < 0) {
            largest = r;
        }
        // 如果i处元素不是最大的，就把i处的元素与最大处元素交换，交换会使元素下降
        if (i != largest) {
            T temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            // 交换元素后，以a[i]为根的树可能不在满足大根堆性质，于是递归调用该方法
            heapify(a, largest, heapLength);
        }
    }
}

public class Elizebath {
    private Patient[] patients;

    public static void enter(Patient patient) {

    }

    public static Patient leave() {
        return null;
    }

    public static void main(String[] args) {
        enter(new Patient("Peppa", "Semi-Urgent"));
        // enter (new Patient());
        // enter (new Patient());
        // enter (new Patient());
        // enter (new Patient());
        // enter (new Patient());
        System.out.println(leave());
        System.out.println(leave());
        // enter (new Patient());
        // enter (new Patient());
        // enter (new Patient());
        // enter (new Patient());
        // enter (new Patient());
        // enter (new Patient());
        System.out.println(leave());
        System.out.println(leave());
        System.out.println(leave());
    }

}
