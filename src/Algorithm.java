import java.util.*;

public class Algorithm {

    private HashMap<Integer, Item> itemList;
    private int capacity;
    private int numItems;
    private Queue<Node> priorityQueue;
    private int index = 1;

    public Algorithm(HashMap<Integer, Item> itemList, int capacity, int numItems){
        this.itemList = itemList;
        this.capacity = capacity;
        this.numItems = numItems;

        priorityQueue = new PriorityQueue<>(20, (o1, o2) -> {
            if(o1.getBound() == o2.getBound()){
                return 0;
            } else{
                return 1;
            }
        });
    }

    public Node findBestNode(){
        System.out.println("\nBegin exploration of the possibilities tree:");

        Boolean found = false;

        Node first = new Node(index, 0, new ArrayList<>());
        // typically call setProfitWeight, but this is first, so both = 0
        first.setBound(this.capacity, this.numItems, this.itemList);
        this.priorityQueue.add(first);
        this.index++;

        Node current = first;
        Node best = first;

        // while loop
        // get left node, level = current level + 1
        // get right node, level = current level + 1
        // if either node is too heavy, prune
        // else, add to priority queue
        // if weight == cap, break and return
        // if
//        while(found != true){
//            Node left = new Node(index, current.getLevel() + 1);
//        }
        return best;
    }
}
