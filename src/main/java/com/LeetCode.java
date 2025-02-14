package com;

import org.springframework.security.core.parameters.P;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class LeetCode {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode first = dummy;
            ListNode second = dummy;
            for (int i = 0; i <= n; i++) {
                first = first.next;
            }
//            while (first!=null){
//                System.out.println(first.val);
//                first = first.next;
//                second = second.next;
//            }
            second.next = second.next.next;
            return  dummy.next;
        }
    public ListNode addNode(ListNode head, int val) {
        if (head == null) {
            return new ListNode(val);
        }
        ListNode currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        return head;
    }

    // Helper method to print the linked list
    public void printList(ListNode head) {
        ListNode currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.val + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println("null");
    }

    public ListNode swapNodes(ListNode head, int k) {
            var currentNode = head;
           ArrayList<Integer> convertedArray = new ArrayList<>();
           int indexOfK = k-1;
//           int toSwap=0;
            while (currentNode != null){
                convertedArray.add(currentNode.val);
                currentNode = currentNode.next;
            }

            var result = convertedArray.size() - k;

//            for(int i = ;i<convertedArray.size();i++){
//                if(i == k){
//                    indexOfK = i;
//                }
//            }
//            var diff = convertedArray.size() - (indexOfK + 1);
            int toSwap = result ;
            System.out.println(indexOfK + "=" + result);
            int temp =  convertedArray.get(toSwap);
            convertedArray.set (toSwap,convertedArray.get(indexOfK));
            convertedArray.set(indexOfK,temp);
            var updater = 0;
            var dummy = head;
            while (dummy != null){
                dummy.val = convertedArray.get(updater);
                updater++;
                dummy = dummy.next;
            }
            return head;
    }
    public  static void main(String [] args){
        LeetCode solution = new LeetCode();
        ListNode head = null;

        // [7,9,6,6,7,8,3,0,9,5]
        head = solution.addNode(head, 1);
        head = solution.addNode(head, 2);
        head = solution.addNode(head, 3);
        head = solution.addNode(head, 4);
        head = solution.addNode(head, 5);
//        head = solution.addNode(head, 7);
//        head = solution.addNode(head, 9);
//        head = solution.addNode(head, 6);
//        head = solution.addNode(head, 6);
//        head = solution.addNode(head, 7);
//        head = solution.addNode(head, 8);
//        head = solution.addNode(head, 3);
//        head = solution.addNode(head, 0);
//        head = solution.addNode(head, 9);
//        head = solution.addNode(head, 5);

        solution.printList( solution.swapNodes(head,2));
    }

}


 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}