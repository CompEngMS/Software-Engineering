package it.warehouse.webservice;

import it.warehouse.bookinfo.BookInfo;

import javax.jws.WebService;

@WebService
public class BookSellerInfo {
    public BookInfo getBookSellers(int id){
        BookInfo imp = new  BookInfo(id);
        return imp;
    }
}
