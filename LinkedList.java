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
            System.out.println("Enter choice:");
            System.out.println("0. Print the current list.");
            System.out.println("1. Insert Node in the end.");
            System.out.println("2. Insert Node at the start.");
            System.out.println("3. Insert Node at nth position.");
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
                    int val = sc.nextInt();
                    int pos = sc.nextInt();
                    if(pos == 1)
                        insertAtStart(val);
                    else
                        insertAtNth(val, pos - 1);
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
}
