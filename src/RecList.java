package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RecList extends ArrayList {
    private static RecList recList;

    private RecList() {
        super();
    }
    public void saveRef(String ref1){
        RefShit ref = new RefShit(ref1);
        add(ref);
        saved(this);
    }   

    public static RecList getInstance() {

        File file = new File("ref.txt");
        File file2 = file.getAbsoluteFile();
        if (file2.exists() && (file2) != null) {
            try {
                FileInputStream fis = new FileInputStream(file2);
                ObjectInputStream ois = new ObjectInputStream(fis);
                recList = (RecList) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            recList = new RecList();
        }
        
        return recList;
    }

    public void saved(RecList list) {
        try {

            FileOutputStream out = new FileOutputStream("ref.txt");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(list);
            oos.close();
            out.close();
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
