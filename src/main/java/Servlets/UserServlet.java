package Servlets;

import Entities.Message;
import Entities.User;
import Exceptions.DBException;
import Interfaces.UserRepository;
import Repositories.SqlMessageRepository;
import Repositories.SqlUserRepository;
import Service.WeatherAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
            resp.sendRedirect("/main");
        } else {
            fillUserInformation(req, resp, user);
            req.getRequestDispatcher("/WEB-INF/views/User.jsp").forward(req, resp);
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
                resp.sendRedirect("/main");
                break;
            case "delete me":
                HttpSession session = req.getSession();
                User user = (User) session.getAttribute("user");
                UserRepository repository = new SqlUserRepository();
                try {
                    repository.delUser(user.getEmail(), user.getPassword());
                    req.getSession().setAttribute("user", null);
                    resp.sendRedirect("/main");
                } catch (DBException e) {
                    //ToDO: add reaction on error
                    e.printStackTrace();
                }
                break;
            case "set/change":
                String singer = req.getParameter("ch_singer");
                HttpSession session1 = req.getSession();
                User user1 = (User) session1.getAttribute("user");
                user1.setfSinger(singer);
                UserRepository repository1 = new SqlUserRepository();
                try {
                    repository1.updateUser(user1);
                    doGet(req,resp);
                } catch (DBException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }
        }
    }

    private void fillUserInformation(HttpServletRequest req, HttpServletResponse resp, User user){
        req.setAttribute("name", user.getName());
        req.setAttribute("gender", user.isMale() ? "Male" : "Female");
        req.setAttribute("city", user.getCity());
        req.setAttribute("weather", WeatherAPI.getTemperature(user.getCity()));
        try {
            List<Message> messages = getLastMessages(user.getId());
            req.setAttribute("messages", messages);
        } catch (DBException e) {
            e.printStackTrace();
        }
//        if(user.getfSinger() != null) {
//            req.setAttribute("singer", user.getfSinger());
//            req.setAttribute("listeners", LastFmAPI.getListenersCount(user.getfSinger()));
//        }
    }

    private List<Message> getLastMessages(int id) throws DBException {
        SqlMessageRepository repository = new SqlMessageRepository();
        return repository.findLastMessagesBySenderId(id);
    }

}
