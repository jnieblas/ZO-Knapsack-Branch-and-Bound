import java.util.ArrayList;

public class Node {
    private int num, level, profit, weight;
    private double bound;
    private ArrayList<Integer> items;

    public Node(int n, int l){
        this.num = n;
        this.level = l;
        this.profit = 0;
        this.weight = 0;
        this.bound = 0.0;
        items = new ArrayList<>();
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
