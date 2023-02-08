package dao;
import entity.Book;
import util.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class BookDao implements Dao<Long, Book> {
    private static final BookDao INSTANCE = new BookDao();
    private static final String FIND_ALL = """
SELECT *
FROM book
""";
    private static final String FIND_ALL_BY_BOOK_ID = """
SELECT *
FROM book
WHERE author_id = ?
""";
    private static final String FIND_BOOK_BY_ID = """
SELECT * FROM book WHERE id = ?
""";
    private static final String UPDATE_BOOK_BY_ID = """
UPDATE book set name =(?), year =(?), pages =(?) WHERE id= ?""";
    private static final String DELETE_BOOK_BY_ID = """
DELETE from book where id=(?)""";
    private static final String SAVE_BOOK_SQL = "INSERT INTO book (name, year, pages, author_id ) VALUES (?, ?, ?, ?)";
    private BookDao(){}

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public List<Book> findAllByBookId(Long bookId){
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_BOOK_ID)) {
            preparedStatement.setObject(1, bookId);
            var resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List <Book> findAll(){
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Book findById(Long id) {
        Book book = null;
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_BOOK_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    book = new Book();
                    book.setId(Long.parseLong(rs.getString("id")));
                    book.setName(rs.getString("name"));
                    book.setYear(rs.getShort("year"));
                    book.setPages(rs.getShort("pages"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(DELETE_BOOK_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int update(Book entity) {
        int rowsUpdated = 0;
        try (var connection = ConnectionManager.open();
             PreparedStatement pst = connection.prepareStatement(UPDATE_BOOK_BY_ID)) {
            pst.setString(1, entity.getName());
            pst.setShort(2, entity.getYear());
            pst.setShort(3, entity.getPages());
            pst.setLong(4, entity.getId());
            rowsUpdated = pst.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return rowsUpdated;
    }

    @Override
    public Book save(Book entity) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SAVE_BOOK_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getYear());
            preparedStatement.setObject(3, entity.getPages());
            preparedStatement.setObject(4, entity.getAuthor_id());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Long.class));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void clearBooks() {
        try {
            String sql = "DELETE FROM book";
            var connection = ConnectionManager.open();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("BookDao: clearBook sql exception.");
        }
    }
    public static BookDao getInstance(){
        return INSTANCE;
}
    private Book buildBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getObject("id", java.lang.Long.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("year", Short.class),
                resultSet.getObject("pages", Short.class),
                resultSet.getObject("author_id", Integer.class));
    }
}

