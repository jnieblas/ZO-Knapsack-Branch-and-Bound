import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private int num, level, profit, weight;
    private double bound;
    private ArrayList<Integer> items;

    public Node(int nodeNum, int level, ArrayList<Integer> items){
        this.num = nodeNum;
        this.level = level;
        this.profit = 0;
        this.weight = 0;
        this.bound = 0.0;
        this.items = items;
    }

    public void setProfitWeight(HashMap<Integer, Item> itemsMap){
        for(int i : this.items){
            Item item = itemsMap.get(i);
            this.profit += item.getProfit();
            this.weight += item.getWeight();
        }
    }

    public void addItem(int i){
        this.items.add(i);
    }

    public void setBound(int capacity, int numItems, HashMap<Integer, Item> itemList){
        int runningWeight = this.weight;
        int totalProfit = this.profit;

        if(runningWeight >= capacity){
            this.bound = totalProfit;
            return;
        }

        for(int i = 1; i <= numItems; i++){
            if(!this.items.contains(i)){
                Item item = itemList.get(i);
                runningWeight += item.getWeight();

                if(runningWeight > capacity){
                    // profit per weight unit * units that fit into capacity
                    totalProfit += ((double) item.getProfit() / item.getWeight())
                            * (item.getWeight() - (runningWeight - capacity));
                    break;
                }

                totalProfit += item.getProfit();
            }
        }

        this.bound = totalProfit;
    }

    public String toString(){
        return String.format("<Node %d:   items: %s level: %d profit: %d weight: %d bound: %.1f>",
                this.num, this.items.toString(), this.level, this.profit, this.weight, this.bound);
    }

    public int getNum(){
        return this.num;
    }

    public int getLevel(){
        return this.level;
    }

    public int getProfit(){
        return this.profit;
    }

    public int getWeight(){
        return this.weight;
    }

    public double getBound(){
        return this.bound;
    }

    public ArrayList<Integer> getItems(){
        return this.items;
    }
}
