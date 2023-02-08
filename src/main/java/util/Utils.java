package util;
import dao.AuthorDao;
import entity.Author;
import javax.servlet.http.HttpServletRequest;
public class Utils {

    public static boolean idIsNumber(HttpServletRequest request) {
        final String id = request.getParameter("id");
        return id != null &&
                (id.length() > 0) &&
                id.matches("[+]?\\d+");
    }

    public static boolean requestIsValid(HttpServletRequest request) {
        final String name = request.getParameter("name");
        final String age = request.getParameter("age");

        return name != null && name.length() > 0 &&
                age != null && age.length() > 0 &&
                age.matches("[+]?\\d+");
    }

    public static Author createStubUser(final int id,
                                      final String first_name,
                                      final String last_name) {
        Author author = new Author();
        author.setId(id);
        author.setFirst_name(first_name);
        author.setLast_name(last_name);
        return author;
    }

    public static boolean idIsInvalid(final String id, AuthorDao authorDao) {
        return !(id != null &&
                authorDao.findById(Integer.parseInt(id)) != null);
    }
}
