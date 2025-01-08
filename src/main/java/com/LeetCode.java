package com;

import org.springframework.security.core.parameters.P;

import javax.swing.*;
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
            while (first!=null){
                System.out.println(first.val);
                first = first.next;
                second = second.next;
            }
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

        currentNode.next = new ListNode(val);
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

    public  static void main(String [] args){
        LeetCode solution = new LeetCode();
        ListNode head = null;
        head = solution.addNode(head, 1);
        head = solution.addNode(head, 2);
        head = solution.addNode(head, 3);
        head = solution.addNode(head, 4);
        head = solution.addNode(head, 5);

        System.out.print("Original List: ");
        solution.printList(head);

        solution  .printList(    solution.removeNthFromEnd(head,2));
    }

}


 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}