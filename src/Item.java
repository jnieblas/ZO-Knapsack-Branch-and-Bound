public class Item {
    private int profit;
    private int weight;

    public Item(int p, int w){
        this.profit = p;
        this.weight = w;
    }

    public int getProfit(){
        return this.profit;
    }

    public int getWeight(){
        return this.weight;
    }
}