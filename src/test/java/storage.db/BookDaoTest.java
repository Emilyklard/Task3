package storage.db;

import dao.BookDao;
import entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import util.ConnectionManager;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookDaoTest {
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
    private static final int BOOK_SIZE = 9;
    private static final Long BOOK_ID = 3L;
    private static final String TEST_BOOK_NAME = "Новая книга";
    private static final Integer TEST_AUTHOR_ID = 3;
    private static final Long AUTHOR_ID = 2L;

    private final BookDao bookDao = BookDao.getInstance();

    @Test
    void findAll() {
        List<Book> result = bookDao.findAll();
        assertThat(result.size()).isEqualTo(BOOK_SIZE);
    }
    @Test
    void findById() {
        Book book = bookDao.findById(5L);
        assertEquals(book.getName(), "Разбуди в себе исполина" );
    }

    @Test
    void save() {
        Book book = new Book(0L, "Герой нашего времени", (short) 1840, (short) 340,
                TEST_AUTHOR_ID);
        Book result = bookDao.save(book);
        assertThat(result).isEqualTo(book);
    }

    @Test
    void update() {
        Book book = bookDao.findById(2L);
        book.setName(TEST_BOOK_NAME);
        bookDao.update(book);
        Book resultBook = bookDao.findById(2L);
        assertEquals(resultBook.getName(), TEST_BOOK_NAME);
    }

    @Test
    void delete() {
        Connection connection = ConnectionManager.open();
        bookDao.delete(BOOK_ID);
        List<Book> listResult = bookDao.findAll();
        assertThat(listResult).hasSize(BOOK_SIZE - 1);
        Book book = new Book(3L, "Java. Библиотека профессионала", (short) 2011, (short) 954, 1);
        bookDao.save(book);
    }

    @Test
    void findAllBooksByAuthorId() {
        List<Book> result = bookDao.findAllByBookId(AUTHOR_ID);
        assertThat(result.stream()
                .map(Book::getName)
                .collect(Collectors.toSet())).containsExactlyInAnyOrder("7 навыков высокоэффективных людей");
    }
}
