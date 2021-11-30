package src;

import java.util.List;
public class Record {

	private String name;
    private String address;
    private String item;
    private double price;
    private double total;
    private String refNmuber;
    private String phoneNumber;
    private int days;

    public void setName(String name) {
         this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setItem(List <String> itemEquipment) {
        this.item = itemEquipment.toString();
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setRefNmuber(String refNmuber) {
        this.refNmuber = refNmuber;
    }
    public void setPhoneNumber(String ph) {
        this.phoneNumber = ph;
    }
    public void setDays(int days) {
        this.days = days;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getItem() {
        return item;
    }
    public double getPrice() {
        return price;
    }
    public double getTotal() {
        return total;
    }
    public String getRefNmuber() {
        return refNmuber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public int getDays() {
        return days;
    }
}