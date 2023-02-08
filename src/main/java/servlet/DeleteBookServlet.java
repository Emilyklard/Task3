package servlet;
import dao.BookDao;
import util.Utils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookServlet extends HttpServlet {
    private final BookDao bookDao = BookDao.getInstance();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (Utils.idIsNumber(req)) {
            Long book = Long.valueOf(req.getParameter("id"));
            bookDao.delete(book);
        }
        resp.sendRedirect(req.getContextPath() + "/authors");
    }
}
