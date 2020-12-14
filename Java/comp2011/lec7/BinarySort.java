package comp2011.lec7;

import java.util.Arrays;

public class BinarySort {
    // sort arr such that all female students come before male students.
    public static void binarySort(Student[] arr) {
        // border cases?
        int lowIndex = 0, highIndex = arr.length - 1;
        while (lowIndex < highIndex) {
            while (lowIndex < highIndex && arr[lowIndex].gender == 0)
                lowIndex++;
            while (lowIndex < highIndex && arr[highIndex].gender == 1)
                highIndex--;
            Student temp = arr[lowIndex];
            arr[lowIndex] = arr[highIndex];
            arr[highIndex] = temp;
        }
        // int count = 0;
        // for (int i = 0; i < arr.length; i++) { }
    }

    public static void main(String[] args) {
        int size = 10;
        Student[] a = new Student[size];
        System.out.println(Arrays.toString(a));
        binarySort(a);
        System.out.println("after sorting");
        System.out.println(Arrays.toString(a));
    }
}

class Student {
    String id;
    String surname;
    String givenName;
    char gender; // 'F' or 'M'

    public String toString() {
        return (gender == 'F' ? "Miss " : "Mr. ") + surname + " " + givenName + " (" + id + ")";
    }
}
