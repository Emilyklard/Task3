package servlet;
import dao.AuthorDao;
import util.Utils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAuthorServlet extends HttpServlet {
    private final AuthorDao authorDao = AuthorDao.getInstance();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (Utils.idIsNumber(req)) {
            Integer author = Integer.valueOf(req.getParameter("id"));
            authorDao.delete(author);
        }
        resp.sendRedirect(req.getContextPath() + "/authors");
    }
}
