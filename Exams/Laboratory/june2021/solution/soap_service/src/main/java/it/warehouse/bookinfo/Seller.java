package it.warehouse.bookinfo;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Seller")
public class Seller {
    public Seller(){}

    private int quantity;
    private String name;
    private String estimatedDelivery;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public void setEstimatedDelivery(String estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }
}
