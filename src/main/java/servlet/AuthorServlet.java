package servlet;
import dao.AuthorDao;
import dto.CreateAuthorDto;
import service.AuthorService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {
    private final AuthorService authorService = AuthorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authors", authorService.findAll());
        req.getRequestDispatcher("WEB-INF/jsp/authors.jsp")
                .forward(req, resp);
    }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
           req.setCharacterEncoding(StandardCharsets.UTF_8.name());
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            var authorDto = CreateAuthorDto.builder()
                    .first_name(req.getParameter("first_name"))
                    .last_name(req.getParameter("last_name"))
                    .build();
            authorService.create(authorDto);
            resp.sendRedirect("/authors");
    }
 }
