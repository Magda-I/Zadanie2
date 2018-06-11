package library;

import java.util.List;

public interface BookDao {

    public List<Book> getAllBooks();
    public void save(Book book);
    public void update(Book book);
    public void delete(Book book);
    public void dropTable();
}
