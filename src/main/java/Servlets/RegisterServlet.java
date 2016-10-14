package Servlets;

import Entities.User;
import Exceptions.AlreadyExistException;
import Exceptions.DBException;
import Interfaces.UserRepository;
import Repositories.sqlUserRepository;
import Service.Encryptor;
import Service.RegularExpressionTester;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ilmaz on 23.09.16.
 */
@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {
    private static String[] COUNTRIES = {"Kazan", "London", "Moscow", "Berlin", "Paris"};
    private String email;
    private String fname;
    private String sname;
    private String password;
    private String repassword;
    private String gender;
    private String country;
    private String about;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("countries", COUNTRIES);
        fillEnteredInformation(req);
        req.getRequestDispatcher("/WEB-INF/Views/Register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        email=req.getParameter("email");
        fname=req.getParameter("fname");
        sname=req.getParameter("sname");
        about=req.getParameter("about");
        password=req.getParameter("password");
        repassword=req.getParameter("repassword");
        gender=req.getParameter("gender");
        country=req.getParameter("country");

        RegularExpressionTester ret = new RegularExpressionTester();
        boolean f = true;

        if(!ret.isCorrectEmail(email)){
            f = false;
            email = null;
            showError(req, "email", "Invalid email!!!");
        }

        if(!ret.isCorrectName(fname)){
            f = false;
            fname = null;
            showError(req, "fname", "Invalid first name");
        }

        if(!ret.isCorrectName(sname)){
            f = false;
            sname = null;
            showError(req, "sname", "Invalid second name");
        }

        if(!ret.isCorrectPassword(password)){
            f = false;
            showError(req, "password", "Invalid password");
        } else {
            if(!password.equals(repassword)){
                f = false;
                showError(req, "repassword",  "Passwords don't equal");
            }
        }

        if(gender == null){
            f = false;
            showError(req, "gender", "Choose you gender");
        } else {
            if(!(gender.equals("M") || gender.equals("F"))){
                f = false;
                showError(req, "gender", "Choose you gender");
            }
        }

        if(country == null){
            f = false;
            showError(req, "country", "Choose you country");
        } else {
            if(country.equals("null")){
                f = false;
                showError(req, "country", "Choose you country");
            }
        }

        if(f){
            try {
                addUser(req);
                resp.sendRedirect("/user");
            } catch (DBException e) {
                e.printStackTrace();
                doGet(req,resp);
            } catch (AlreadyExistException e) {
                showError(req, "email", "This email already used");
                doGet(req,resp);
            }
        } else {
            doGet(req,resp);
        }

    }

    private void fillEnteredInformation(HttpServletRequest req){
        req.setAttribute("email", email);
        req.setAttribute("fname", fname);
        req.setAttribute("sname", sname);
        req.setAttribute("about", about);
        req.setAttribute("gender", gender);
        req.setAttribute("country", country);
    }

    private void showError(HttpServletRequest req, String name, String errMessage){
        req.setAttribute("err"+name, errMessage);
    }

    private void addUser(HttpServletRequest req) throws DBException, AlreadyExistException {
        UserRepository repository = new sqlUserRepository();
        User user = new User(email, fname+" "+sname, country, gender.equals("M"), Encryptor.getHash(password,email));
        repository.addUser(user);
        req.getSession().setAttribute("user", user);
    }
}
