package library;

import java.sql.*;
import java.util.List;

public class DBBookDao implements BookDao {


    private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "magda123";

    private PreparedStatement saveStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement getAllStmt;
    private PreparedStatement dropTable;
    private PreparedStatement alterInc;

    private Connection connection;
    private List<Book> books;

    public DBBookDao() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
            saveStmt = connection.prepareStatement("INSERT INTO books(title, author, year, isbn) VALUES(?,?,?,?)");
            updateStmt = connection.prepareStatement("UPDATE books SET title=?, author=?, year=? WHERE isbn = ?");
            deleteStmt = connection.prepareStatement("DELETE FROM books WHERE isbn = ?");
            getAllStmt = connection.prepareStatement("SELECT * FROM books");
            dropTable = connection.prepareStatement("DELETE FROM books");
            alterInc = connection.prepareStatement("ALTER TABLE books AUTO_INCREMENT = 1");
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono drivera");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Nie ustanowiono połączenia");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void save(Book book) {
        try {
            saveStmt.setString(1, book.getTitle());
            saveStmt.setString(2, book.getAuthor());
            saveStmt.setInt(3, book.getYear());
            saveStmt.setString(4, book.getIsbn());
            saveStmt.executeUpdate();
            System.out.println("Książka zapisana");
        } catch (SQLException e) {
            System.out.println("Nie można zapisać książki");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {

        try {
//            UPDATE books SET title=?, author=?, year=? WHERE isbn = ?");
            updateStmt.setString(1,book.getTitle());
            updateStmt.setString(2, book.getAuthor());
            updateStmt.setInt(3, book.getYear());
            updateStmt.setString(4, book.getIsbn());
            updateStmt.executeUpdate();
            System.out.println("Książka zaktualizowana");
        } catch (SQLException e) {
            System.out.println("nie można było zaktualizować książki");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {

        try {
            deleteStmt.setString(1, book.getIsbn());
            deleteStmt.executeUpdate();
            System.out.println("Książka usunięta");
        } catch (SQLException e) {
            System.out.println("Nie można było usunąć książki");
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        Book book = new Book();
        try {
            ResultSet result = getAllStmt.executeQuery();
            while (result.next()){

                book.setId(result.getLong("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setYear(result.getInt("year"));
                book.setIsbn(result.getString("isbn"));
                System.out.println(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void dropTable() {

        try {
            dropTable.executeUpdate();
            alterInc.executeUpdate();
            System.out.println("Tabela wyczyszczona");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
