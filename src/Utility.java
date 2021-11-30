package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;

public class Utility{
    private final static String VOUCHER = "voucher.txt";  
    public void tableDisplay() {
        System.out.println("--------------------------------");
        System.out.println("|Per Day[1]   | Per Bundle     |");
        System.out.println("--------------------------------");
        
        System.out.println("+-----------+--------------+");
        System.out.printf("|Equipment   |Price and pcs|\n");
        System.out.println("+-----------+--------------+");
        System.out.println("+-----------+--------------+");
        System.out.println(" Videoke    |Php 350 |1 pc |");
        System.out.println(" Chairs     |Php 200 |10pcs|");
        System.out.println(" Tables     |Php 250 |4pcs |");
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


    public void receiptDisplay() throws FileNotFoundException, IOException{
        
       BufferedReader br = new BufferedReader(new FileReader("receipt.txt"));
         String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        br.close();
        
    }
    //display the receipt depends on the reference number
    public void receiptDisplay(String referenceNumber) throws FileNotFoundException, IOException{
        
       BufferedReader br = new BufferedReader(new FileReader("receipt.txt"));
         String line = br.readLine();
            while (line != null) {
                if(line.contains(referenceNumber)){
                    System.out.println(line);
                }
                line = br.readLine();
            }
        br.close();
        
    }//read the file and display the receipt depends on the reference number

    public boolean voucherAuth(String inVoucher) throws IOException {
        // read voucher.txt validate the inVoucher
        File file = new File(VOUCHER);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals(inVoucher)) {
                return true;
            } else {
                return false;
            }
        }
        br.close();
        fr.close();
        return false;
    }

    public  boolean confirmEquipment(String item){
        String equipment [] = {"videoke","tables","chairs"};
        for (String i : equipment){
            if (i.equals(item)){
                return true;
            }
        }
        return false;
    }

    public double equivalentPrice(String i) {
        double price =0;
        switch(i){
            case "tables":
                price = 250;
                break;
            case "chairs":
                price = 200;
                break;
            case "videoke":
                price = 350;
                break;
        }
        return price;
    }
    public double totalPrice(List<String> itemEquipment, int quantity,int days){
        double price =0;
        for (String i : itemEquipment){
            price += equivalentPrice(i);
            days*=price;
        }
        return days;
    }
    public double compute(int [] items){
        double total = 0;
        for (int i = 0; i < items.length; i++) {
            total += items[i];
 
        }

        return total;
    }

    public double computeCash(double price, double cash) {
        double change = cash - price;

        return change; 
    }

    public void countEquipment(String item, List<String> itemEquipmentForCalulation) {
        int count = 0;
        for(String itemEquipment: itemEquipmentForCalulation){
            if(itemEquipment.equals(item)){
                count++;
            }
        }
        //if user selected not repeated item in the list
        if(count < 2){
            
        }else{
            System.out.println("[NOTE] You have been selected twice or more of"+": " + item);
        }
    
    }
    public String generateRandomCode() {
        String randomNumber = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int randomNumberLength = 8;
        for (int i = 0; i < randomNumberLength; i++) {
            int index = (int) (Math.random() * alphabet.length());
            randomNumber += alphabet.charAt(index);
        }
        return randomNumber;
    }
    //show the date and time
    public void showDateTime(){
        java.util.Date date = new java.util.Date();
        System.out.println("[DATE] " + date);
    }

    //calculate the user cash input in the total of equipment

    /**
    public  double receipt(int count,String equipment1[]){
        double total = 0;
        for(int i = 0; i < equipment1.length; i++){
            total = compute(items(count,equipment1));
            System.out.println("Total: " + total);
        }

        return total;
    }
    **/
    /**public static int[] items(int count, String equipment[]) {
        Scanner in = new Scanner(System.in);
        int order_count[] = new int[count];

        for(int i =0;i < order_count.length; i++){
            order_count[i]=(int) equivalentPrice(equipment[i]);
        }
        in.close();
        return order_count;
        
    }
    */


}