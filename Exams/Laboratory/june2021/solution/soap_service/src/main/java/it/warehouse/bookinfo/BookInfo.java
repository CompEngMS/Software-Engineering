package it.warehouse.bookinfo;

import javax.xml.bind.annotation.XmlType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@XmlType(name = "BookInfo")
public class BookInfo {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Random random;

    //Data that you want to expose must be parameters, with getter and setter
    int id;
    List<Seller> sellerList;
    double price;


    //When the object is created, the parameters are filled
    public BookInfo(int id) {
        this.random = new Random(id);
        this.id = id;
        this.sellerList = this.generateSellerList();
        this.price = this.generateBookPrice();
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Seller> getSellerList() {
        return sellerList;
    }

    public void setSellerList(List<Seller> sellerList) {
        this.sellerList = sellerList;
    }

    // The price is generated randomly, based on the id of the book
    private double generateBookPrice() {
        float price = Math.abs(random.nextFloat() % 10) + 10;
        return (Math.round(price*100)/100.00);
    }

    // The seller list is generated randomly, based on the id of the book
    private List<Seller> generateSellerList() {
        List<Seller> sellerList = new LinkedList<>();
        Calendar c = Calendar.getInstance();

        for (int i = 0; i < 1 + Math.abs(random.nextInt() % 9); i++) {
            c.setTime(new Date());
            c.add(Calendar.DATE, 1 + (random.nextInt() % 10));

            Seller seller = new Seller();
            seller.setQuantity(Math.abs(random.nextInt() % 10));
            seller.setEstimatedDelivery(dateFormat.format(c.getTime()));
            seller.setName("SellerName" + i);

            sellerList.add(seller);
        }
        return sellerList;
    }


}
