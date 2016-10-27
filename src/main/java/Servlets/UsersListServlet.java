package Servlets;

import Entities.User;
import Exceptions.DBException;
import Interfaces.UserRepository;
import Repositories.sqlUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by ilmaz on 25.10.16.
 */
@WebServlet("/users")
public class UsersListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =  req.getSession();
        User user = (User) session.getAttribute("user");

        req.setAttribute("test", req.getAttribute("email"));

        if(user == null){
            resp.sendRedirect("/main");
        } else {
            try {
                List<User> userList = getAllUsers();
                req.setAttribute("usersList", userList);
            } catch (DBException e) {
                e.printStackTrace();
            }

            req.getRequestDispatcher("/WEB-INF/views/UsersList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        req.setAttribute("test", email);
        req.getRequestDispatcher("/WEB-INF/views/UsersList.jsp").forward(req, resp);
    }

    private List<User> getAllUsers() throws DBException {
        UserRepository repository = new sqlUserRepository();
        return repository.getUsersList();
    }
}
