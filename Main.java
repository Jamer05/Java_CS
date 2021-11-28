
/**
 * @author Hemerson G. Ramiro Jr.
 * @version 1.0
 * @category TPS for Java , "Equipment and Appliances Rental Service"
 * @since 23/11/2021
 */
import java.io.*;

import src.ConList;
import src.PriceRole;
import src.RecList;
import src.Record;
import src.Utility;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Date;

import admin.Admin;

public class Main {

    private static Set<String> equipment = new LinkedHashSet<String>();
    private static List<String> itemEquipment;
    private static List<String> itemEquipmentForCalulation;
    private static int quantity;
    private static String item;
    private static double cash;

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Admin admin = new Admin();
        RecList recordList = RecList.getInstance();
        Record record = new Record();
        ConList conList = ConList.getInstance();
        PriceRole priceRole;

        boolean isBack = false;
        Utility utility = new Utility();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("Welcome to Equipment and Appliances Rental Service");
        // show the file of receipt if user enter the reference number
        System.out.print("Press 'O' To rent, Press 'R' to return an item: ");
        String option = sc.next();
            switch(option){
                case "O": case "o":{
                    break;
                }
                case "R": case "r":{
                    //read the ref.txt file
                    admin.forAdmin();
                }
            }

        System.out.print("Please enter your name: ");
        String name = sc.nextLine();
        // dont let user skip this step
        while (name.isEmpty()) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.print("Please enter your name: ");
            name = sc.nextLine();
        }
        System.out.print("Please enter your address: ");
        String address = sc.nextLine();
        while (address.isEmpty()) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.print("Please enter your address: ");
            address = sc.nextLine();
        }

        // if user input a mismatch ask again
        while (!isBack) {
            try {
                System.out.print("Please enter how many equipment you want to rent: ");
                quantity = sc.nextInt();
                // don't let quantity greater than 3
                while (quantity > 3) {
                    System.out.println("You can only rent 3 equipment at a time");
                    System.out.print("Please enter how many equipment you want to rent: ");
                    quantity = sc.nextInt();
                }

                itemEquipment = new ArrayList<String>(equipment);
                itemEquipmentForCalulation = new ArrayList<String>(equipment);

                for (int i = 0; i < quantity; i++) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    utility.tableDisplay();
                    System.out.print("Please enter equipment " + (i + 1) + ": ");
                    item = sc.next().toLowerCase();

                    if (!itemEquipment.contains(item)) {
                        itemEquipment.add(item + "->Php" + utility.equivalentPrice(item));
                    }
                    itemEquipmentForCalulation.add(item);

                    while (!utility.confirmEquipment(item)) {
                        itemEquipment.remove(item + "->Php" + utility.equivalentPrice(item));

                        System.err.println("Invalid input");
                        System.out.print("Please enter equipment " + (i + 1) + ": ");
                        item = sc.next().toLowerCase();

                        if (!itemEquipment.contains(item)) {
                            itemEquipment.add(item + "->Php" + utility.equivalentPrice(item));
                        }
                        itemEquipmentForCalulation.add(item);
                    }
                    if (utility.confirmEquipment(item)) {
                        System.out.print("Are you sure you want to rent this: (Y/N) ");
                        String answer = sc.next().toLowerCase();

                        while (answer.equals("n")) {
                            itemEquipment.remove(item + "->Php" + utility.equivalentPrice(item));
                            itemEquipmentForCalulation.remove(item);
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            utility.tableDisplay();

                            System.out.print("Please enter equipment " + (i + 1) + ": ");
                            item = sc.next().toLowerCase();

                            if (utility.confirmEquipment(item)) {
                                System.out.print("Are you sure you want to rent this: (Y/N) ");
                                answer = sc.next().toLowerCase();

                                if (!itemEquipment.contains(item)) {
                                    itemEquipment.add(item + "->Php" + utility.equivalentPrice(item));
                                }
                                itemEquipmentForCalulation.add(item);
                            }
                        }
                    }

                    isBack = true;
                }
                // print the itemEquipment all

            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Error: Wrong input");
            }

        }
        // if user inputmismatch ask again
        int N = Integer.MAX_VALUE;
        // while cash is a number
        while (N > 0) {
            try {
                System.out.println("Total: "+utility.totalPrice(itemEquipmentForCalulation, quantity));
                System.out.print("Please enter your cash: ");
                cash = sc.nextDouble();
                N = 0;
                // if cash is less than the total price
                while (cash < utility.totalPrice(itemEquipmentForCalulation, quantity)) {
                    System.out.println("You don't have enough money");
                    System.out.print("Please enter your cash: ");
                    cash = sc.nextDouble();
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Error: Wrong input");
                N = Integer.MAX_VALUE;
            }
        }
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.print("Do you have a voucher code? ");
        String voucherCode = sc.next().toLowerCase();
        // dont let user skip this step
        while (!voucherCode.equals("y") && !voucherCode.equals("n")) {
            System.out.print("Do you have a voucher code? ");
            voucherCode = sc.next().toLowerCase();
        }
        if (voucherCode.equalsIgnoreCase("yes") || voucherCode.equalsIgnoreCase("Y")) {
            record = new Record();
            Date date = new Date();

            voucherCode = sc.nextLine().toLowerCase();
            System.out.print("Please enter your voucher code: ");
            String code = sc.next();
            utility.voucherAuth(code);
            // ask again if utility.voucherAuth(code) return false
            while (!utility.voucherAuth(code)) {
                System.out.print("[Invalid]Please enter your voucher code: ");
                code = sc.next();
                utility.voucherAuth(code);
            }
            final double DISCOUNT = 5;
            double sales = 0.0;
            sales = 100 - DISCOUNT;
            double discountedItem = utility.totalPrice(itemEquipmentForCalulation, quantity) * sales / 100;
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            date.setTime(System.currentTimeMillis());
            date.setDate(date.getDate() + 1);
            record.setName(name);
            record.setAddress(address);
            record.setItem(itemEquipment);
            record.setPrice(discountedItem);
            record.setTotal(utility.computeCash(record.getPrice(), cash));
            // write the record to the file
            System.out.println("-------------------------------------------------------------");
            utility.countEquipment(item, itemEquipmentForCalulation);
            String codeGen = utility.generateRandomCode();
            record.setRefNmuber(codeGen);
            recordList.saveRef(codeGen);
            conList.conduct(discountedItem);
            System.out.print("Reference Code: " + codeGen);
            System.out.println("\n-------------------------------------------------------------");
            String receipt = "Name: " + record.getName() + "\nAddress: " + record.getAddress() + "\nItem: "
                    + record.getItem() + "\nPrice: Php" + record.getPrice()
                    + "\n-------------------------------------------------------------" + "\nCash: Php" + cash
                    + "\n-------------------------------------------------------------" + "\nChange: Php"
                    + record.getTotal() + "\nRef: " + codeGen +"\n"+"Date:"+date.getDate()+"/"+date.getMonth()+"/"+date.getYear()+"\n"
                    +"Time: "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()
                    + "\n-------------------------------------------------------------";
            utility.saveFile(receipt);
            utility.receiptDisplay();
            System.out.println("-------------------------------------------------------------");

        }

        else if (voucherCode.equalsIgnoreCase("no") || voucherCode.equalsIgnoreCase("N")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            imperialMergingOFClientReceiptLol(name, address, item, quantity, voucherCode);
            System.out.println("Thank you for using our service");
        } else {
            System.out.println("Invalid input");
        }
    }
    public static void imperialMergingOFClientReceiptLol(String name, String address, String item, int quantity,
            String voucherCode) throws IOException {
            Record record = new Record();
            Utility utility = new Utility();
            Date date = new Date();

            RecList recordList = RecList.getInstance();
            record.setName(name);
            record.setAddress(address);
            record.setItem(itemEquipment);
            record.setPrice(utility.totalPrice(itemEquipmentForCalulation, quantity));
            record.setTotal(utility.computeCash(record.getPrice(), cash));
            // write the record to the file
            System.out.println("-------------------------------------------------------------");
            utility.countEquipment(item, itemEquipmentForCalulation);
            String codeGen = utility.generateRandomCode();
            record.setRefNmuber(codeGen);
            recordList.saveRef(codeGen);
            System.out.print("Reference Code: " + codeGen);
            System.out.println("\n-------------------------------------------------------------");
            String receipt = "Name: " + record.getName() + "\nAddress: " + record.getAddress() + "\nItem: "
                    + record.getItem() + "\nPrice: Php" + record.getPrice()
                    + "\n-------------------------------------------------------------" + "\nCash: Php" + cash
                    + "\n-------------------------------------------------------------" + "\nChange: Php"
                    + record.getTotal() + "\nRef: " + codeGen +date.getDate()+"\n"+date.getMonth()+"/"+date.getYear()+date.getTime();
            utility.saveFile(receipt);
            utility.receiptDisplay();
            System.out.println("-------------------------------------------------------------");
    }

}