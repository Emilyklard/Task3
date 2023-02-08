package dao;
import lombok.Getter;
import lombok.SneakyThrows;
import entity.Author;
import util.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
@Getter
public class AuthorDao implements Dao<Integer, Author> {
    private static final AuthorDao INSTANCE = new AuthorDao();
    private static final String FIND_ALL = """
SELECT * FROM author""";
    private static final String SAVE_SQL = "INSERT INTO author (first_name, last_name) VALUES " + "(?, ?)";
    private static final String DELETE_AUTHOR_BY_ID = """
DELETE from author where id=(?)""";
    private static final String FIND_AUTHOR_BY_ID = """
SELECT * from author where id=(?)""";
    private static final String UPDATE_AUTHOR_BY_ID = """
UPDATE author set first_name =(?), last_name =(?) where id=(?)""";
    private AuthorDao(){}
    @Override
    public List<Author> findAll() {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(buildAuthor(resultSet));
            }
            return authors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author findById(Integer id)  {
        Author author = null;
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_AUTHOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    author = new Author();
                    author.setId(Integer.parseInt(rs.getString("id")));
                    author.setFirst_name(rs.getString("first_name"));
                    author.setLast_name(rs.getString("last_name"));
                }
            }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return author;
        }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(DELETE_AUTHOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int update(Author entity) {
        int rowsUpdated = 0;
        try (var connection = ConnectionManager.open();
             PreparedStatement pst = connection.prepareStatement(UPDATE_AUTHOR_BY_ID)) {
            pst.setString(1, entity.getFirst_name());
            pst.setString(2, entity.getLast_name());
            pst.setInt(3, entity.getId());
            rowsUpdated = pst.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return rowsUpdated;
    }

    @Override
    @SneakyThrows
    public Author save(Author entity) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getFirst_name());
            preparedStatement.setObject(2, entity.getLast_name());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
            return entity;
        }
    }
    public void clearAuthors() {
        try {
            String sql = "DELETE FROM author";
            var connection = ConnectionManager.open();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("AuthorDao: clearAuthor sql exception.");
        }
    }

    public static AuthorDao getInstance(){
        return INSTANCE;
    }
    private Author buildAuthor(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("first_name", String.class),
                resultSet.getObject("last_name", String.class));
    }
}
