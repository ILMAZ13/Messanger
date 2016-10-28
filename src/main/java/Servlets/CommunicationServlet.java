package Servlets;

import Entities.Message;
import Entities.User;
import Exceptions.DBException;
import Interfaces.UserRepository;
import Repositories.SqlMessageRepository;
import Repositories.SqlUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ilmaz on 28.10.16.
 */
@WebServlet("/messages")
public class CommunicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");



        if (user == null) {
            resp.sendRedirect("/main");
        } else {
            String email = req.getParameter("email");
            session.setAttribute("oppEmail", email);
            try {
                fillUserInformation(req, resp, user, email);
                req.getRequestDispatcher("/WEB-INF/views/CommunicationPage.jsp").forward(req, resp);
            } catch (DBException e) {
                resp.sendRedirect("/users");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String text = req.getParameter("text");
        if (text != null) {
            if (text.length() > 0) {
                Date date = new Date(System.currentTimeMillis());
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
                String dateFormatted = formatter.format(date);
                int idOpponent = 0;
                try {
                    UserRepository userRepository = new SqlUserRepository();
                    idOpponent = userRepository.getIdByEmail((String) session.getAttribute("oppEmail"));
                    if (idOpponent == -1) {
                        resp.sendRedirect("/users");
                    }
                    SqlMessageRepository repository = new SqlMessageRepository();
                    Message message = new Message(
                            user.getId(),
                            idOpponent,
                            text,
                            dateFormatted
                    );
                    repository.addMessage(message);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }
        }
        doGet(req, resp);
    }

    private void fillUserInformation(HttpServletRequest req, HttpServletResponse resp, User user, String email) throws DBException {
        req.setAttribute("user", user);
        SqlMessageRepository repository = new SqlMessageRepository();
        UserRepository userRepository = new SqlUserRepository();
        User opponent = userRepository.findByEmail(email, "");
        if (opponent == null) {
            throw new DBException();
        } else {
            List<Message> messages = repository.findMessagesWithOpponent(user.getId(), opponent.getId());
            req.setAttribute("messages", messages);
            req.setAttribute("opponent", opponent);
        }
    }
}
