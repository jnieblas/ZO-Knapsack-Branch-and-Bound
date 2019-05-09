public class Item {
    private int profit;
    private int weight;

    public Item(int profit, int weight){
        this.profit = profit;
        this.weight = weight;
    }

    public int getProfit(){
        return this.profit;
    }

    public int getWeight(){
        return this.weight;
    }
}