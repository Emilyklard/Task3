package service;
import dao.BookDao;
import dto.BookDto;
import dto.CreateBookDto;
import mapper.CreateBookMapper;

import java.util.List;
import static java.util.stream.Collectors.toList;
public class BookService {
    private final CreateBookMapper createBookMapper = CreateBookMapper.getInstance();
    private static final BookService INSTANCE = new BookService();
    private BookService(){}
    private final BookDao bookDao = BookDao.getInstance();
    public List<BookDto> findAllByBookId(Long bookId){
        return bookDao.findAllByBookId(bookId).stream()
                .map(book -> new BookDto(
                        book.getId(), book.getName(), book.getYear(), book.getPages(), book.getAuthor_id()
                ))
                .collect(toList());
    }
   public static BookService getInstance(){
        return INSTANCE;
    }

    public Long create(CreateBookDto bookDto){
        var bookEntity = createBookMapper.mapFrom(bookDto);
        bookDao.save(bookEntity);
        return bookEntity.getId();

    }
}
