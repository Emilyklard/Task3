package storage.db;

import dao.AuthorDao;
import entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import util.ConnectionManager;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorDaoTest {
    @Container
    public final PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("book_repository")
                    .withUsername("book_repository")
                    .withPassword("40984098");

    @BeforeEach
    public void setUp() {
        POSTGRESQL_CONTAINER.start();
    }
    private final AuthorDao authorDao = AuthorDao.getInstance();
    private static final int TEST_AUTHOR_ID = 5;
    private static final String TEST_AUTHOR_FIRSTNAME = "test";
    private static final int AUTHORS_SIZE = 7;
    private static final int AUTHOR_ID = 7;


    @Test
    void test() {
        assertTrue(POSTGRESQL_CONTAINER.isRunning());
    }
    @Test
    void findById() {
        String firstName1 = authorDao.findById(1).getFirst_name();
        assertEquals(firstName1, "Кей");
    }

    @Test
    void save() {
        Author author = new Author(8, "Джек", "Лондон");
        Author result = authorDao.save(author);
        assertThat(result).isEqualTo(author);
    }

    @Test
    void findAll() {
        Connection connection = ConnectionManager.open();
        List<Author> authorList = authorDao.findAll();
        assertThat(authorList.size()).isEqualTo(AUTHORS_SIZE);
    }

    @Test
    void update() {
        Connection connection = ConnectionManager.open();
        Author authorById = authorDao.findById(TEST_AUTHOR_ID);
        authorById.setFirst_name(TEST_AUTHOR_FIRSTNAME);
        authorDao.update(authorById);
        Author resultAuthor = authorDao.findById(TEST_AUTHOR_ID);
        assertEquals(resultAuthor.getFirst_name(), TEST_AUTHOR_FIRSTNAME);
    }

    @Test
    void delete() {
        Connection connection = ConnectionManager.open();
        authorDao.delete(AUTHOR_ID);
        List<Author> listResult = authorDao.findAll();
        assertEquals(listResult.size(), 6);
        Author author = new Author(7,"Джоан","Роулинг");
        authorDao.save(author);
    }
}
