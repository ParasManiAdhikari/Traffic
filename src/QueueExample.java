import java.util.*;

public class QueueExample {
    public static void main(String[] args) {
        LinkedList<String> queue = new LinkedList<>();

        // add elements to the queue
        queue.add("apple");
        queue.add("banana");
        queue.add("cherry");

        // print the queue
        System.out.println("Queue: " + queue);

        // remove the element at the front of the queue
        String front = queue.remove();
        System.out.println("Removed element: " + front);

        // print the updated queue
        System.out.println("Queue after removal: " + queue);

        // add another element to the queue
        queue.add("date");

        // peek at the element at the front of the queue
        String peeked = queue.peek();
        System.out.println("Peeked element: " + peeked);

        // print the updated queue
        System.out.println("Queue after peek: " + queue);
        System.out.printf("poll the queue:%s \n" ,queue.poll());
        System.out.println(queue.size());
        System.out.println(queue);
     LinkedList<Integer> list = new LinkedList<>();
     list.add(1);
     list.add(2);
     list.add(3);
     list.add(4);
     list.add(5);
     System.out.println(list.add(list.remove(2)));
     System.out.println(list);
     list.add(2,3);
        System.out.println(list);




    }
}
