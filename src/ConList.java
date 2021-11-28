package src;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class ConList extends ArrayList {
    
    private static ConList conlis;
    public void conduct(double price){
        PriceRole priceRole = new PriceRole(price);
        add(priceRole);
        saved(this);
    }
    public static ConList getInstance() {

        File file = new File("price.txt");
        File file2 = file.getAbsoluteFile();
        if (file2.exists() && (file2) != null) {
            try {
                FileInputStream fis = new FileInputStream(file2);
                ObjectInputStream ois = new ObjectInputStream(fis);
                conlis = (ConList) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            conlis = new ConList();
        }
        
        return conlis;
    }

    public void saved(ConList conList) {
        try {
            FileOutputStream out = new FileOutputStream("price.txt");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(conList);
            oos.close();
            out.close();
        } catch (SecurityException | IOException e) {
            e.printStackTrace();// Drink coffee now!
        }
    }
}
