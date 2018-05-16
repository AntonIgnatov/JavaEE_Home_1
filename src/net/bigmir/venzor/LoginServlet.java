package net.bigmir.venzor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    static final User[] users  = {
            new User("ivanov", "ivanov"),
            new User("petrov", "petrov"),
            new User("sidorov", "sidorov"),
            new User("tkach", "tkach")};


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean f = false;
         for (int i = 0; i < users.length; i++) {

            if (users[i].getLogin().equals(login) && users[i].getPassword().equals(password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", users[i]);
                f = true;
                response.sendRedirect("form.html");
                break;
            }
        }
        if (!f){
            response.sendRedirect("index.jsp");
        }


    }

}
