package it.exam.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import it.exam.db.DBManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8081)
                .addService(new gRPCServer())
                .build();
        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }
}
