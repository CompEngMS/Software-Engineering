package it.client;


import it.client.generated.BookInfo;
import it.client.generated.BookSellerInfo;
import it.client.generated.BookSellerInfoService;

public class SoapClient {

    public BookInfo getSellers(int bookId){
        BookSellerInfoService service = new BookSellerInfoService();
        BookSellerInfo port = service.getBookSellerInfoPort();
        return port.getBookSellers(bookId);
    }

}
