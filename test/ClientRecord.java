package test;
import java.io.Serializable;
import java.util.*;

public class ClientRecord implements Serializable{
    private final ArrayList price;
    private final ArrayList quantity;

    String name;
    String address;
    String email;
    int age;
    //i remove totalGPAST from the constructor
    public ClientRecord (String name,int age,String address,String email){
        price=new ArrayList();
        quantity=new ArrayList();
        name = name;
        age= age;

    }
    
    public ClientRecord(){
    	price=new ArrayList();
        quantity=new ArrayList();
    }
    
    // i created a method to retrieve the gpa
    public double getPrice(int total){
        double tprice=0;
        tprice=(double) price.get(total);
        return tprice;
    }
    
    public void setGPA(double clientPrice){
        price.add(clientPrice);
    }
    public double getQuantity(int clientQuantity){
        int q =0;
        q=(int) quantity.get(clientQuantity);
        return q;
    }
    public void setQuantity(double clientQuantity){
        quantity.add(clientQuantity);
    }
}
