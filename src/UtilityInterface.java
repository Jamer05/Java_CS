package src;

import java.io.*;  
import java.util.List;

public interface UtilityInterface {

    public void receiptDisplay()throws FileNotFoundException, IOException;
    public void saveFile(String reciept);
    public void tableDisplay();
    public boolean confirmEquipment(String item); 
    public double equivalentPrice(String i);
    public double totalPrice(List<String> itemEquipment, int quantity);
    public void countEquipment(String item, List<String> itemEquipmentForCalulation);

}