/**
 * @author Hemerson G. Ramiro Jr.
 * @version 1.0
 * @category TPS for Java , "Equipment and Appliances Rental Service"
 * @since 23/11/2021
 */
import src.*;
import java.io.*;
import src.Voucher;
import src.Record;
import src.Utility;
import test.Total;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static ArrayList <String> equipment;
    public static int quantity;
    public static void main(String[] args) throws IOException {
    
        Scanner sc = new Scanner(System.in);
        boolean voucher = false;

        boolean isBack = false;
        Utility utility = new Utility();
        Total total = new Total();


        System.out.println("Welcome to Equipment and Appliances Rental Service");
        System.out.print("Please enter your name: ");
        String name = sc.nextLine();
        System.out.print("Please enter your address: ");
        String address = sc.nextLine();
        System.out.print("Please enter your email: ");
        String email = sc.nextLine();

        utility.tableDisplay();

        //if user input a mismatch  ask again
        while(!isBack){
            try{
                System.out.print("Please enter how many equipment you want to rent: ");
                quantity = sc.nextInt();
                equipment = new ArrayList<String>(quantity);

                for(int i=0;i<quantity;i++){
                    System.out.print("Please enter equipment "+(i+1)+": ");
                    String item = sc.next();
                    equipment.add(item);
                    while(!utility.confirmEquipment(item)){
                        
                        System.err.println("Invalid input");
                        equipment.remove(item);
                        System.out.print("Please enter the equipment ");
                        item = sc.next().toLowerCase();
                        equipment.add(item);
                        if (utility.confirmEquipment(item)){
                            System.out.print("Are you sure you want to rent this: Y/N");
                            String answer = sc.next().toLowerCase();
                            while(answer.equals("n")){
                                System.out.print("Please enter the equipment ");
                                item = sc.next().toLowerCase();
                                if (utility.confirmEquipment(item)){
                                    System.out.print("Are you sure you want to rent this: Y/N");
                                    answer = sc.next().toLowerCase();
                                    equipment.add(item);
                                }
                            }   
                        }
        
                    }
                    isBack = true;
                }
            }catch(InputMismatchException e){
                sc.next();
                System.out.println("test");
            }
        }

        Record record = new Record();
        record.setName(name);
        record.setAddress(address);
        record.setItem(equipment);
        //write the record to the file
  
        String receipt = "Name: "+record.getName()+"\nAddress: "+record.getAddress()+"\nItem: "+record.getItem();
        utility.saveFile(receipt);
        utility.receiptDisplay();   
        Utility.receipt(quantity,equipment);
        System.out.print("Do you have a voucher code? ");
        String voucherCode = sc.next().toLowerCase();
  
        while (!voucher) {
            try{
                voucherCode = sc.nextLine().toLowerCase();
                if (voucherCode.equalsIgnoreCase("yes") || voucherCode.equalsIgnoreCase("Y")) {
                    System.out.print("Please enter your voucher code: ");
                    String code = sc.next();
                    voucher = true;
                } 
            sc.next();
            }catch(NoSuchElementException e){
                System.out.println("Invalid input");
            }
        }
    }
}
    



    

    

    

    

    






