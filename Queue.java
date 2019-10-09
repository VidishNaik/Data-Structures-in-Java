import java.util.Scanner;

public class Queue {
    public static void main(String[] args) {
        QueueADT queue = QueueADT.getInstance();
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning){
            System.out.println("Enter your choice:");
            System.out.println("0. Print Stack.");
            System.out.println("1. Enqueue.");
            System.out.println("2. Dequeue.");
            System.out.println("3. Front");
            System.out.println("4. Is queue Empty?");
            switch (sc.nextInt()){
                case 0:
                    queue.print();
                    break;
                case 1:
                    System.out.println("Enter the value to enqueue.");
                    queue.enqueue(sc.nextInt());
                    queue.print();
                    break;
                case 2:
                    queue.dequeue();
                    queue.print();
                    break;
                case 3:
                    queue.front();
                    break;
                case 4:
                    queue.isEmpty();
                    break;
                default:
                    keepRunning = false;
            }
        }
    }
}

class QueueADT extends List{
    private static QueueADT queue = null;
    static QueueADT getInstance(){
        if(queue == null){
            queue = new QueueADT();
        }
        return queue;
    }
    void print(){
        if(head == null){
            System.out.println("Queue is empty.");
            return;
        }
        LinkedList.Node temp = LinkedList.head;
        StringBuilder out = new StringBuilder();
        while(temp != null){
            out.append("->").append(temp.val);
            temp = temp.next;
        }
        System.out.println("Queue: " + out.toString().substring(2));
    }

    void enqueue(int val){
        this.insertAtEnd(val);
    }

    void dequeue(){
        this.deleteAtStart();
    }

    void front(){
        if(head == null)
        {
            queue.print();
            return;
        }
        System.out.println("Front: " + head.val);
    }

    void isEmpty(){
        System.out.println("Queue empty? " + (head == null));
    }
}
