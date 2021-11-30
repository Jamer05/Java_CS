package admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import src.ConList;
import src.PriceRole;
import src.RecList;
import src.RefShit;

public class Admin {
    public void forAdmin()throws IOException, InterruptedException {
        RecList recordList = RecList.getInstance();
        Scanner sc = new Scanner(System.in);
        boolean isBack = false;
        // dont let user input wrong reference number
        do{
            try {
                System.out.print("Enter the reference number:");
                String referenceNumber = sc.nextLine();
                recordList = RecList.getInstance();

                for (int i = 0; i < recordList.size(); i++) {
                    RefShit ref = (RefShit) recordList.get(i);
                    if (ref.refMe.equals(referenceNumber)) {
                        // read the file of receipt
                        File file = new File("receipt.txt");
                        BufferedReader br1 = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = br1.readLine()) != null) {
                            System.out.println(line);
                            
                        }
                        isBack = true;
                        br1.close();
                    } // if ref.refMe is not equal to the reference number ask again
                    if (!ref.refMe.equals(referenceNumber)) {
                        System.out.println("Invalid reference number");
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        isBack = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }while (!isBack);
        final double CONDUCT_PER_HOUR = 2.35;
        System.out.print("Is client late:(yes or no) ");
        String late = sc.nextLine();
        while (!late.equals("yes") && !late.equals("no")) {
            System.out.print("Invalid input, please enter yes or no:");
            late = sc.nextLine();
        }
        if (late.equals("yes")) {
             //dont let user inputMismatchException in entering the hours late
            int hoursLate = 0;
            do {
                try {
                    System.out.print("Enter the number of hours late:");
                     hoursLate = sc.nextInt();
                    if (hoursLate < 0) {
                        System.out.println("Invalid input, please enter a positive number");
                    }

                }catch (NumberFormatException e) {
                    System.out.println("Invalid input, please enter a positive number");
                    isBack = false;
                }
            }while (hoursLate < 0);
            double strict = CONDUCT_PER_HOUR * hoursLate;
            computeConducted(strict);
            System.out.println("Charge: "+strict);
            while (hoursLate < 0) {
                System.out.print("Invalid input, please enter a positive number:");
                hoursLate = sc.nextInt();
            }
        } else if (late.equals("no")) {
            System.out.println("Charge: 0");
            
        }
        System.exit(0);  
        sc.close();
    }
    public void computeConducted(double conduct){
        ConList conList = ConList.getInstance();
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i <1; i++){
            PriceRole priceRole = (PriceRole) conList.get(i);;
            priceRole.price += conduct;

        }
        
    }
}
