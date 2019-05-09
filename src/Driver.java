import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        String fileName;
        Scanner s = new Scanner(System.in);
        HashMap<Integer, Item> itemList = new HashMap<>();

        try{
            fileName = args[0];
        } catch(Exception e){
            System.out.println("Enter a file name: ");
            fileName = s.next();
        }

        File file = new File(fileName);

        try{
            String line;
            BufferedReader input = new BufferedReader(new FileReader(file));
            int capacity = Integer.parseInt(input.readLine());
            int items = Integer.parseInt(input.readLine());

            System.out.println("Capacity of knapsack is " + capacity);
            System.out.println("Items are:");

            for(int i = 1; i <= items; i++){
                line = input.readLine();
                System.out.println(String.format("%d: %s", i, line));
                String[] lineSplit = line.split(" ");

                int profit = Integer.parseInt(lineSplit[0]);
                int weight = Integer.parseInt(lineSplit[1]);

                itemList.put(i, new Item(profit, weight));
            }

            input.close();

            Algorithm a1 = new Algorithm(itemList, capacity, items);
            Node best = a1.findBestNode();
            System.out.println("\nBest node: " + best.toString());
        } catch (NumberFormatException e){
            System.out.println("File " + fileName + " is empty.");
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
