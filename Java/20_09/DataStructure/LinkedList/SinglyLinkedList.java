/**
* -*- coding : utf-8 -*-
* @FileName  : SinglyLinkedList.java
* @Author    : Ruixiang JIANG (Songrise)
* @Time      : 2020-09-16
* @Github    ：https://github.com/songrise
* @Descriptions: None
**/

package DataStructure.LinkedList;

class ListNode {
    int val = 0;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    // ListNode getNext(){
    // if (this.next != null){
    // return this.next == null
    // }
}

public class SinglyLinkedList {

    private ListNode head = null;
    private ListNode tail = head;

    private int length = 0;

    // void initList() {
    // this.head = new ListNode();
    // }
    void append(int val) {
        ListNode newNode = new ListNode(val);
        this.tail.next = newNode;
        this.tail = newNode;
        this.length++;
    }

    boolean delete(int index) {
        if (index > this.len() - 1) {
            return false;
        } 
        else {
            ListNode crt = head; //?pointer
            for(int i = 0 , i < index, i++)
            return true
        } 
    }

    int len() {
        return this.length;
    }

}
