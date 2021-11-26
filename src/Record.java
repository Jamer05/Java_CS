package src;

import java.util.ArrayList;
//import Collection
import java.util.Collection;
import java.util.List;
public class Record {

	private String name;
    private String address;
    private List<String>item;

    public void setName(String name) {
         this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setItem(Collection<String> equipment) {
        this.item = new ArrayList<String>(equipment);
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public List<String> getItem() {
        return new ArrayList<>(item);
    }
}