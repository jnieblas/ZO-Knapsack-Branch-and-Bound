import java.util.*;

public class Algorithm {

    private HashMap<Integer, Item> itemsMap;
    private int capacity;
    private int numItems;
    private int index = 1;
    private Queue<Node> priorityQueue;
    private static Node best;
    private static int achievableProfit = 0;

    public Algorithm(HashMap<Integer, Item> itemList, int cap, int numI){
        this.itemsMap = itemList;
        this.capacity = cap;
        this.numItems = numI;

        this.priorityQueue = new PriorityQueue<>(20, (o1, o2) -> {
            if(o2.getBound() < o1.getBound()){
                return -1;
            } else if (o2.getBound() > o1.getBound()){
                return 1;
            }
            if(o2.getLevel() > o1.getLevel()){
                return -1;
            } else{
                return 1;
            }
        });
    }

    public void findBestNode(){
        System.out.println("\nBegin exploration of the possibilities tree:");

        Node first = new Node(this.index, 0, new ArrayList<>(), new ArrayList<>());
        first.setBound(this.capacity, this.numItems, this.itemsMap);
        this.priorityQueue.add(first);
        this.index++;

        Node current = priorityQueue.poll();
        best = first;

        do{
            System.out.println("\nExploring " + current.toString());

            if(current.getBound() < achievableProfit){
                System.out.println(String.format("         pruned, don't explore children " +
                        "because bound %.1f is smaller than known achievable profit %d",
                        current.getBound(), achievableProfit));
                current = priorityQueue.poll();
                continue;
            }

            Node left = current.getChildNode(this.index);
            left.addExcludedItem(current.getLevel() + 1);
            this.index++;
            left.setProfitWeight(this.itemsMap);
            left.setBound(this.capacity, this.numItems, this.itemsMap);
            System.out.println("      Left child is " + left.toString());
            if (isExplorableNode(left, capacity)) {
                priorityQueue.add(left);
            }

            Node right = current.getChildNode(this.index);
            right.addItem(current.getLevel() + 1);
            this.index++;
            right.setProfitWeight(this.itemsMap);
            right.setBound(this.capacity, this.numItems, this.itemsMap);
            System.out.println("      Right child is " + right.toString());
            if (isExplorableNode(right, capacity)) {
                priorityQueue.add(right);
            }

            current = priorityQueue.poll();
        } while(current != null);
    }

    public static Boolean isExplorableNode(Node node, int cap){
        if(node.getWeight() > cap){
            System.out.println("         pruned because too heavy");
            return false;
        }
        else if(node.getWeight() == cap){
            System.out.println("         hit capacity exactly so don't explore further");
            System.out.println("         note achievable profit of " + node.getProfit());

            achievableProfit = node.getProfit();
            if(node.getProfit() > best.getProfit()){
                best = node;
            }
            return false;
        } else{
            System.out.println("         explore further");

            if(node.getProfit() > best.getProfit()){
                best = node;
            }
            return true;
        }
    }

    public Node getBest(){ return best; }

}
