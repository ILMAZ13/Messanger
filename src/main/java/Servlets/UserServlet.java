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

/**
 * Created by ilmaz on 28.09.16.
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =  req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            resp.sendRedirect("/login");
        } else {
            req.setAttribute("name", user.getName());
            req.setAttribute("gender", user.isMale() ? "Male" : "Female");
            req.setAttribute("city", user.getCity());
            req.getRequestDispatcher("/WEB-INF/Views/User.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btnValue = req.getParameter("button");
        reactOnButtonClicked(btnValue, req,resp);
    }

    private void reactOnButtonClicked(String textFromButton, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch (textFromButton){
            case "logout":
                req.getSession().setAttribute("user", null);
                resp.sendRedirect("/login");
                break;
            case "delete me":
                HttpSession session = req.getSession();
                User user = (User) session.getAttribute("user");
                UserRepository repository = new sqlUserRepository();
                try {
                    repository.delUser(user.getEmail(), user.getPassword());
                    req.getSession().setAttribute("user", null);
                    resp.sendRedirect("/login");
                } catch (DBException e) {
                    //ToDO: add reaction on error
                    e.printStackTrace();
                }
                break;
        }
    }

}
