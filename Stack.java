import java.util.Scanner;

public class Stack {
    public static void main(String[] args) {
        StackADT stack = StackADT.getInstance();
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning){
            System.out.println("Enter your choice:");
            System.out.println("0. Print Stack.");
            System.out.println("1. Push to stack.");
            System.out.println("2. Pop from stack.");
            System.out.println("3. Peek");
            System.out.println("4. Is stack Empty?");
            switch (sc.nextInt()){
                case 0:
                    stack.print();
                case 1:
                    System.out.println("Enter the value to push:");
                    stack.push(sc.nextInt());
                    stack.print();
                    break;
                case 2:
                    stack.pop();
                    stack.print();
                    break;
                case 3:
                    stack.peek();
                    break;
                case 4:
                    stack.isEmpty();
                    break;
                default:
                    keepRunning = false;
            }
        }
    }
}
class StackADT extends List{
    private static StackADT stack = null;
    static StackADT getInstance(){
        if(stack == null){
            stack = new StackADT();
        }
        return stack;
    }
    void print(){
        if(head == null){
            System.out.println("Stack is empty.");
            return;
        }
        LinkedList.Node temp = LinkedList.head;
        StringBuilder out = new StringBuilder();
        while(temp != null){
            out.append("->").append(temp.val);
            temp = temp.next;
        }
        System.out.println("Stack: " + out.toString().substring(2));
    }
    void push(int val){
        this.insertAtStart(val);
    }

    void pop(){
        if(head == null){
            return;
        }
        System.out.println("Popped element: " + head.val);
        LinkedList.Node temp = head;
        head = head.next;
        temp.next = null;
    }
    void peek(){
        if(head == null){
            stack.print();
            return;
        }
        System.out.println("Element at top of stack: " + head.val);
    }
    void isEmpty(){
        System.out.println("Stack empty? " + (head == null));
    }
}