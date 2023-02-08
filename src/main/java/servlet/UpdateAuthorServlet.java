package servlet;
import dao.AuthorDao;
import entity.Author;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateAuthorServlet extends HttpServlet {
    private final AuthorDao authorDao = AuthorDao.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        final String id = req.getParameter("id");
        final String first_name = req.getParameter("first_name");
        final String last_name = req.getParameter("last_name");

        final Author author = authorDao.findById(Integer.parseInt(id));
        author.setFirst_name(first_name);
        author.setLast_name(last_name);
        authorDao.update(author);

        resp.sendRedirect(req.getContextPath() + "/authors");
    }

       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp)
               throws ServletException, IOException {
           final String id = req.getParameter("id");
       req.setAttribute("author", authorDao.findById(Integer.valueOf(id)));
        req.getRequestDispatcher("/WEB-INF/jsp/update.jsp")
                .forward(req, resp);
    }
}
