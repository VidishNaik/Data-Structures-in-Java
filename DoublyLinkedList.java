import java.util.Scanner;

public class DoublyLinkedList {
    private static class Node {
        private int val;
        private Node next, prev;

        Node(int val) {
            this.next = null;
            this.prev = null;
            this.val = val;
        }
    }

    private static Node head = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning) {
            int pos = -1;
            int val = -1;
            System.out.println("Enter choice:");
            System.out.println("0. Print the current list.");
            System.out.println("1. Insert Node in the end.");
            System.out.println("2. Insert Node at the start.");
            System.out.println("3. Insert Node at nth position.");
            System.out.println("4. Delete Node at the end.");
            System.out.println("5. Delete Node at the first position.");
            System.out.println("6. Delete Node at Nth position");
            System.out.println("7. Delete Node by value.");
            System.out.println("8. Reverse the list using iteration.");
            System.out.println("9. Reverse the list using recursion.");
            switch (sc.nextInt()) {
                case 0:
                    printList();
                    break;
                case 1:
                    System.out.println("Enter the value to be inserted:");
                    insertAtEnd(sc.nextInt());
                    printList();
                    break;
                case 2:
                    System.out.println("Enter the value to be inserted:");
                    insertAtStart(sc.nextInt());
                    printList();
                    break;
                case 3:
                    System.out.println("Enter the value to be inserted followed by position.");
                    val = sc.nextInt();
                    pos = sc.nextInt();
                    if (pos == 1)
                        insertAtStart(val);
                    else
                        insertAtNth(val, pos - 1);
                    printList();
                    break;
                case 4:
                    deleteAtEnd();
                    printList();
                    break;
                case 5:
                    deleteAtStart();
                    printList();
                    break;
                case 6:
                    System.out.println("Enter the position of the node to delete.");
                    pos = sc.nextInt();
                    if(pos == 1)
                        deleteAtStart();
                    else
                        deleteAtNth(pos - 1);
                    printList();
                    break;
                case 7:
                    System.out.println("Enter the value to be deleted.");
                    val = sc.nextInt();
                    deleteByValue(val);
                    printList();
                    break;
                case 8:
                    reverseByIteration();
                    printList();
                    break;
                case 9:
                    reverseByRecursion(head);
                    printList();
                    break;
                default:
                    keepRunning = false;
            }
        }
    }

    private static void printList() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        StringBuilder out = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            out.append(temp.val).append("<->");
            temp = temp.next;
        }
        System.out.println("List: " + out.substring(0, out.length() - 3) + ".");
    }

    private static void insertAtEnd(int val) {
        if (head == null) {
            head = new Node(val);
            head.next = null;
            head.prev = null;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(val);
        temp.next.prev = temp;
        temp.next.next = null;
    }

    private static void insertAtStart(int val) {

        if (head == null) {
            head = new Node(val);
            head.prev = null;
            head.next = null;
            return;
        }
        Node temp = new Node(val);
        temp.next = head;
        temp.prev = null;
        head.prev = temp;
        head = temp;
    }

    private static void insertAtNth(int val, int pos) {
        Node temp = head;
        while (pos > 1 && temp.next != null) {
            temp = temp.next;
            pos -= 1;
        }
        if (temp.next == null && pos != 1) {
            System.out.println("Position is beyond the length of the list. Do you wish to insert at the end of the list?\n1. Yes\n2. No");
            if (new Scanner(System.in).nextInt() == 1) {
                temp.next = new Node(val);
                return;
            }
        }
        Node newNode = new Node(val);
        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next = newNode;
    }

    private static void deleteAtEnd() {
        if(head == null){
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.prev.next = null;
        temp.prev = null;
    }

    private static void deleteAtStart() {
        if(head == null){
            return;
        }
        if(head.next == null){
            head = null;
            return;
        }
        head.next.prev = null;
        head = head.next;
    }

    private static void deleteAtNth(int pos) {
        if(head == null){
            return;
        }
        Node temp = head;
        while (pos > 1 && temp.next != null){
            temp = temp.next;
            pos -= 1;
        }
        if(temp.next == null || pos != 1){
            System.out.println("Position is beyond the length of the list. Do you wish to delete the node at the end of the list?\n1. Yes\n2. No");
            if (new Scanner(System.in).nextInt() == 1) {
                temp.prev.next = null;
                temp.prev = null;
            }
        }
        else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            temp.prev = null;
            temp.next = null;
        }

    }

    private static void deleteByValue(int val) {
        Node temp = head;
        while(temp != null){
            if(temp.val == val){
                if(temp.prev == null){
                    head = temp.next;
                    temp.next = null;
                }
                else if (temp.next == null){
                    temp.prev.next = null;
                    temp.prev = null;
                }
                else {
                temp.next.prev = temp.prev;
                temp.prev.next = temp.next;
                temp.next = null;
                temp.prev = null;
                }
                return;
            }
            temp = temp.next;
        }
        System.out.println(val + " not found in the list.");
    }
    private static void reverseByIteration() {
        Node temp = head;
        Node placeHolder = null;
        while(temp.next != null){
            placeHolder = temp.next;
            temp.next = temp.prev;
            temp.prev = placeHolder;
            temp = temp.prev;
        }
        head = temp;
        temp.next = temp.prev;
        temp.prev = null;
    }

    private static void reverseByRecursion(Node current){
        if(current.next == null){
            head = current;
            current.next = current.prev;
            current.prev = null;
            return;
        }
        reverseByRecursion(current.next);
        Node placeHolder = current.next;
        current.next = current.prev;
        current.prev = placeHolder;
    }
}
