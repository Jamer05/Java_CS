package test;

import java.util.*;

public class Total {
    
    public double receipt(int count){
        double total = 0;
        total = compute(items(count));
        
        return total;
    }
    public double total(double[] values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }
    public int[] items(int count) {
        Scanner in = new Scanner(System.in);
        String unit[] = new String[count]; 
        int price[] = new int[count];
        for (int i = 0; i < count; i++) {
            System.out.println("Enter item " + (i + 1));
            unit[i] = in.nextLine();
        }
        for(int i =0;i < price.length; i++){
            price[i]=equivalentPrice(unit[i]);
        }
        return price;
        
    }
    public double compute(int [] items){
        double total = 0;
        for (int i = 0; i < items.length; i++) {
            total += items[i];
        }
        return total;
    }
    public int equivalentPrice(String unit) {
        int price =0;
        switch(unit){
            case "tables":
                price = 10;
                break;
            case "chairs":
                price = 5;
                break;
            case "videoke":
                price = 35;
                break;
        }
        return price;
    }

}