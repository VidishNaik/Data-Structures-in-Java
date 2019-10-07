import java.util.Scanner;

class Node{
    int val;
    Node next;
    Node(int val){
         this.val = val;
         this.next = null;
     }
     Node(int val, Node next){
         this.val = val;
         this.next = next;
     }
}

public class LinkedList {
    private static Node head = null;
    public static void main(String[] args) {
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(6);
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;
        while(keepRunning){
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
            switch (sc.nextInt()){
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
                    if(pos == 1)
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
                    reverseByRecursion(head, null);
                    printList();
                    break;
                default:
                    keepRunning = false;
                    break;
            }
        }
    }

    private static void printList(){
        if(head == null){
            System.out.println("List is empty.");
            return;
        }
        StringBuilder out = new StringBuilder();
        Node temp = head;
        while (temp != null){
            out.append(temp.val).append("->");
            temp = temp.next;
        }
        System.out.println("List: " + out.substring(0, out.length() - 2) + ".");
    }
    private static void insertAtEnd(int val){
        if(head == null)
        {
            head = new Node(val);
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new Node(val);
    }

    private static void insertAtStart(int val){
       head = new Node(val, head);
    }

    private static void insertAtNth(int val, int pos){
        Node temp = head;
        while(pos > 1 && temp.next != null){
            temp = temp.next;
            pos -= 1;
        }
        if(temp.next == null && pos != 1){
            System.out.println("Position is beyond the length of the list. Do you wish to insert at the end of the list?\n1. Yes\n2. No");
            switch (new Scanner(System.in).nextInt()){
                case 1:
                    temp.next = new Node(val);
                    return;
                case 2:
                    return;
            }
        }
        Node newNode = new Node(val);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    private static void deleteAtEnd(){
        Node temp = head;
        if(head == null)
            return;
        if(head.next == null)
        {
            head = null;
            return;
        }
        while(temp.next.next != null){
            temp = temp.next;
        }
        temp.next = null;
    }

    private static void deleteAtStart(){
        if(head == null)
            return;
        if(head.next == null)
        {
            head = null;
            return;
        }
        head = head.next;

    }

    private static void deleteAtNth(int pos){
        if(head == null)
            return;
        Node temp = head;
        Node prev = head;
        while (pos > 1 && temp.next != null){
            prev = temp;
            temp = temp.next;
            pos -= 1;
        }
        if(temp.next == null || pos != 1){
            System.out.println("Position is beyond the length of the list. Do you wish to delete the node at the end of the list?\n1. Yes\n2. No");
            switch (new Scanner(System.in).nextInt()){
                case 1:
                    prev.next = null;
                    break;
                case 2:
                    break;
            }
        }
        else {
            temp.next = temp.next.next;
        }
    }

    private static void deleteByValue(int val){
        Node temp = head;
        Node prev = temp;
        int pos = 1;
        while(temp != null){
            if(temp.val == val){
                prev.next = temp.next;
                System.out.println("Node with value " + val + " deleted at position " + pos);
            }
            prev = temp;
            temp = temp.next;
            pos += 1;
        }
        System.out.println("Element not found in the list.");
    }

    private static void reverseByIteration(){
        Node temp = head;
        Node prev = null;
        Node after = head.next;
        while(after != null){
            temp.next = prev;
            prev = temp;
            temp = after;
            after = after.next;
        }
        temp.next = prev;
        head = temp;
    }
    private static void reverseByRecursion(Node current, Node prev){
        if(current == null){
            head = prev;
            return;
        }
        reverseByRecursion(current.next, current);
        current.next = prev;
    }
}
