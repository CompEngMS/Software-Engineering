package it.client;

import common.gRPCService.BookInfoResponse;
import it.client.generated.BookInfo;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        gRPCClient gRPCClient = new gRPCClient();
        SoapClient soapClient = new SoapClient();

        int choose = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter 0 to exit");
            System.out.println("Enter 1 to get all books");
            System.out.println("Enter 2 to get info about one book");
            System.out.println("Enter 3 to get the book's sellers");
            choose = scanner.nextInt();

            if(choose==1)
                gRPCClient.getAllBooks();
            else if(choose==2){
                System.out.println("Enter book id");
                int bookId = scanner.nextInt();
                BookInfoResponse response = gRPCClient.getBookInfo(bookId);
                if(response!=null && response.getHasResponse())
                    System.out.println(response.getBookInfo().getBookTitle() + " di " + response.getAuthorInfo().getName() + " nato nel " + response.getAuthorInfo().getYearOfBirth());
                else
                    System.out.println("BOOK NOT FOUND");
            }
            else if(choose==3){
                System.out.println("Enter book id");
                int bookId = scanner.nextInt();
                BookInfo info = soapClient.getSellers(bookId);
                System.out.println(info.getPrice()+" €");
                info.getSellerList().forEach(seller -> {
                    System.out.println(seller.getName()+" "+seller.getEstimatedDelivery()+" "+seller.getQuantity());
                });
            }

        }while(choose!=0);

        gRPCClient.closeConnection();


    }
}
