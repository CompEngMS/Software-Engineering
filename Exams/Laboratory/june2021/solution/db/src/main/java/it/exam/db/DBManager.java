package it.exam.db;

import it.exam.objects.Author;
import it.exam.objects.Book;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DBManager {
    private Connection connection;

    public DBManager() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");

        this.connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        statement.executeUpdate("drop table if exists books");
        statement.executeUpdate("drop table if exists authors");

        statement.executeUpdate("create table authors (" +
                "id integer primary key, " +
                "name text, " +
                "yearOfBirth varchar(4))");
        statement.executeUpdate("create table books (" +
                "id integer primary key," +
                " title text," +
                " year varchar(4)," +
                " authorId integer, " +
                "foreign key (authorId) references authors(id))");

        statement.executeUpdate("insert into authors values (1,'Alessandro Manzoni',1550)");
        statement.executeUpdate("insert into books values (1,'I Promessi sposi',1600,1)");
        statement.executeUpdate("insert into authors values (2,'Luigi Rossi',1950)");
        statement.executeUpdate("insert into books values (2,'Due papaveri',1965,2)");
        statement.executeUpdate("insert into books values (3,'Ciao mondo',1970,2)");
        statement.executeUpdate("insert into books values (4,'Le storie di Mr. Nessuno',1975,2)");
    }

    public Statement getStatement() throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.setQueryTimeout(30);
        return statement;
    }


    public Author getAuthorByBookId(int bookId) throws SQLException {
        ResultSet resultSet = this.getStatement().executeQuery("select authorId from books WHERE id=" + bookId);
        if (resultSet.next()) {
            int authorId = resultSet.getInt("authorId");
            ResultSet resultAuthor = this.getStatement().executeQuery("select * from authors WHERE id=" + authorId);
            if (resultAuthor.next()) {
                return new Author(resultAuthor.getInt("id"),
                        resultAuthor.getString("name"),
                        resultAuthor.getInt("yearOfBirth"));
            }
            return null;

        }
        return null;
    }

    public Book getBookByBookId(int bookId) throws SQLException {
        ResultSet resultSet = this.getStatement().executeQuery("select * from books WHERE id=" + bookId);
        if (resultSet.next()) {
            return new Book(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("year")
            );
        }
        return null;
    }
}
