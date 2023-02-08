package servlet;
import dao.BookDao;
import dto.CreateBookDto;
import entity.Book;
import service.BookService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private final BookService bookService = BookService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var authorId = Long.valueOf(req.getParameter("authorId"));

        req.setAttribute("books", bookService.findAllByBookId(authorId));
        req.getRequestDispatcher("WEB-INF/jsp/books.jsp")
        .forward(req, resp);
         }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        final String name = req.getParameter("name");
        final String year = req.getParameter("year");
        final String pages = req.getParameter("pages");
        String author = req.getParameter("author");

  /*       final Book book = new Book();
         book.setName(name);
         book.setYear(Short.valueOf(year));
         book.setPages(Short.valueOf(pages));
         book.setAuthor_id(Integer.valueOf(author));
         bookDao.save(book);
 */       var bookDto = CreateBookDto.builder()
                .name(req.getParameter("name"))
                .year(Short.valueOf(req.getParameter("year")))
                .pages(Short.valueOf(req.getParameter("pages")))
                .author(Integer.valueOf(req.getParameter("author")))
                .build();
        bookService.create(bookDto);


    }
}
