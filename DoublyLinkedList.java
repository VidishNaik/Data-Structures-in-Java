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

}
