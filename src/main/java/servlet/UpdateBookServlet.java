package servlet;
import dao.BookDao;
import entity.Book;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class UpdateBookServlet extends HttpServlet {
    private final BookDao bookDao = BookDao.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        final String id = req.getParameter("id");
        final String name = req.getParameter("name");
        final String year = req.getParameter("year");
        final String pages = req.getParameter("pages");

             final Book book = bookDao.findById(Long.parseLong(id));
        book.setName(name);
        book.setYear(Short.valueOf(year));
        book.setPages(Short.valueOf(pages));
        bookDao.update(book);

        resp.sendRedirect("/authors");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String id = req.getParameter("id");
      req.setAttribute("book", bookDao.findById(Long.valueOf(id)));
        req.getRequestDispatcher("/WEB-INF/jsp/updateBook.jsp")
                .forward(req, resp);
    }
}
