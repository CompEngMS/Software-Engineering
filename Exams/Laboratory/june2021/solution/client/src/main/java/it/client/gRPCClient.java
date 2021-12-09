package it.client;

import common.gRPCService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class gRPCClient {
    BookServiceGrpc.BookServiceBlockingStub stub;
    ManagedChannel channel;

    gRPCClient() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext()
                .build();
        this.stub = BookServiceGrpc.newBlockingStub(this.channel);
    }

    public void getAllBooks() {
        MyServiceRequest request = MyServiceRequest.newBuilder().build();
        MyServiceResponse response = stub.getAllBooks(request);
        response.getBooksList().forEach(response1 -> {
            System.out.println("ID " + response1.getBookId() +
                    " Title " + response1.getBookTitle() +
                    " Year " + response1.getYear() +
                    " Author ID " + response1.getAuthorId());
        });
    }

    public BookInfoResponse getBookInfo(int id) {
        MyServiceRequest request = MyServiceRequest.newBuilder().build();
        BookInfoRequest request1 = BookInfoRequest.newBuilder()
                .setBookId(id)
                .build();

       return stub.getBookInfo(request1);
    }

    public void closeConnection() {
        this.channel.shutdown();
    }
}
