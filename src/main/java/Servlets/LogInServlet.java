package Servlets;

import Entities.User;
import Exceptions.DBException;
import Interfaces.UserRepository;
import Repositories.SqlUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ilmaz on 07.10.16.
 */
@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user != null){
            resp.sendRedirect("/user");
        } else {
            req.getRequestDispatcher("/WEB-INF/views/LogIn.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ToDo: add tests
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email !=null && password != null) {
            try{
                User user = getUser(email, password);
                if(user != null){
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect("/user");
                } else {
                    req.setAttribute("wrong", "User not found");
                    doGet(req, resp);
                }
            } catch (DBException e) {
                e.printStackTrace();
                req.setAttribute("wrong", "DB Error");
                doGet(req, resp);
            }
        }
    }

    private User getUser(String email, String password) throws DBException {
        UserRepository repository = new SqlUserRepository();
        return repository.findByEmail(email, password);
    }
}
