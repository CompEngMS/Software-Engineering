package it.exam.server;

import common.gRPCService.*;
import io.grpc.stub.StreamObserver;
import it.exam.db.DBManager;
import it.exam.objects.Author;
import it.exam.objects.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class gRPCServer extends BookServiceGrpc.BookServiceImplBase {
    DBManager db = null;

    gRPCServer() throws SQLException, ClassNotFoundException {
        this.db = new DBManager();
    }

    @Override
    public void getAllBooks(MyServiceRequest request, StreamObserver<MyServiceResponse> responseObserver) {
        try {
            ResultSet rs = db.getStatement().executeQuery("select * from books");
            MyServiceResponse.Builder responseBuilder = MyServiceResponse.newBuilder();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                int authorId = rs.getInt("authorId");

                Response.Builder bookBuilder = Response.newBuilder()
                        .setBookId(id)
                        .setBookTitle(title)
                        .setYear(year)
                        .setAuthorId(authorId);

                responseBuilder.addBooks(bookBuilder);
            }

            //MyServiceResponse response = responseBuilder.build();
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
    }

    @Override
    public void getBookInfo(BookInfoRequest request, StreamObserver<BookInfoResponse> responseObserver) {
        int bookId = request.getBookId();
        try {
            Book book = this.db.getBookByBookId(bookId);
            Author author = this.db.getAuthorByBookId(bookId);

            BookInfoResponse.Builder responseBuilder = BookInfoResponse.newBuilder();
            if (author != null)
                responseBuilder.setAuthorInfo(
                        common.gRPCService.Author.newBuilder()
                                .setName(author.getName())
                                .setAuthorId(author.getId())
                                .setYearOfBirth(author.getYearOfBirth())
                                .build()
                );

            if (book != null)
                responseBuilder.setBookInfo(Response.newBuilder()
                        .setAuthorId(author.getId())
                        .setBookId(book.getId())
                        .setBookTitle(book.getName())
                        .setYear(book.getYear())
                );


            responseBuilder.setHasResponse(book != null && author != null);
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
