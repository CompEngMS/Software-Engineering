package it.warehouse;

import it.warehouse.webservice.BookSellerInfo;

import javax.xml.ws.Endpoint;

public class SoapServer {
    public static void main(String[] args) {
        BookSellerInfo implementor = new BookSellerInfo();
        String address = "http://0.0.0.0:8080/WSInterface";
        Endpoint.publish(address, implementor);
        while(true) {}
    }
}
