package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class Utility implements UtilityInterface {
    private final static String VOUCHER = "voucher.txt";  
    public void tableDisplay() {
        System.out.println("--------------------------------");
        System.out.println("Per Hour      | Per Bundle   ");
        System.out.println("--------------------------------");
        
        System.out.println("+-----------+--------------+");
        System.out.printf("|Equipment   |Price and pcs |\n");
        System.out.println("+-----------+--------------+");
        System.out.println("+-----------+--------------+");
        System.out.println(" Videoke    |Php 35  |1 pc |");
        System.out.println(" Chairs     |Php 5   |10pcs|");
        System.out.println(" Tables     |Php 10  |4pcs |");
        System.out.println("+-----------+--------------+");
    }

    public void saveFile(String receipt) {

        try {
            File file = new File("receipt.txt");
            FileWriter fw = new FileWriter(file);
            fw.write(receipt);
            fw.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiptDisplay() throws FileNotFoundException, IOException{
       BufferedReader br = new BufferedReader(new FileReader("receipt.txt"));
         String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        
    }

    public static void voucherAuth(String inVoucher) throws IOException {
        // read voucher.txt validate the inVoucher
        File file = new File(VOUCHER);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals(inVoucher)) {
                System.out.println("Voucher is valid");
                break;
            } else {
                System.out.println("Voucher is invalid");
                break;
            }
        }
        br.close();
        fr.close();
    }
    public static boolean confirmEquipment(String item){
        String equipment [] = {"videoke","tables","chairs"};
        for (String i : equipment){
            if (i.equals(item)){
                return true;
            }
        }
        return false;
    }

    public static double equivalentPrice(ArrayList <String> equipment) {
        double price =0;
        switch(Arrays.toString(equipment.toArray())){
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
    public static double compute(int [] items){
        double total = 0;
        for (int i = 0; i < items.length; i++) {
            total += items[i];
            
        }
        System.out.println("Total: " + total);
        return total;
    }
    public static double receipt(int count,ArrayList<String> equipment){
        double total = 0;
        total = compute(items(count, equipment));
        System.out.println("Total: " + total);
        return total;
    }
    public static int[] items(int count, ArrayList<String> equipment) {
        Scanner in = new Scanner(System.in);
        int order_count[] = new int[count];

        for(int i =0;i < order_count.length; i++){
            order_count[i]+=equivalentPrice(equipment);
        }
        in.close();
        return order_count;
        
    }


}