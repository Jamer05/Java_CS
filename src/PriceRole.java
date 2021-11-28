package src;

import java.io.Serializable;

public class PriceRole implements Serializable {
    public double price;
    public PriceRole(double price){
        this.price = price;
    }

}