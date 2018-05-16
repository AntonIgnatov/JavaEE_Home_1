package net.bigmir.venzor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.*;

public class Statistic extends HttpServlet {
    static final String TEMPLATE = "<html>" +
            "<head><title>Prog.kiev.ua</title></head>" +
            "<body><h1>%s`s answers are:</h1><br/>" +
            "<h1>Questin 1: yes: %s no: %s</h1><br/>" +
            "<h1>Questin 2: yes: %s no: %s</h1><br/>" +
            "<br/><br/><br/>" +
            "<body><h1>All answers are:</h1><br/>" +
            "<h1>Questin 1: yes: %s no: %s</h1><br/>" +
            "<h1>Questin 2: yes: %s no: %s</h1><br/>" +
            "Click this link to <a href=\"/statistic?a=exit\">logout</a>" +
            "</body></html>";

    private AtomicInteger q1y = new AtomicInteger(0);
    private AtomicInteger q2y = new AtomicInteger(0);
    private AtomicInteger q1n = new AtomicInteger(0);
    private AtomicInteger q2n = new AtomicInteger(0);
    private String name;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException{
        String que1 = request.getParameter("quest1");
        String que2 = request.getParameter("quest2");
        HttpSession session = request.getSession(false);
        User curUser = (User) session.getAttribute("user");

            if (que1.equals("yes")) {
                q1y.getAndIncrement();
                curUser.incAnswer(0);
            } else {
                q1n.getAndIncrement();
                curUser.incAnswer(1);
            }
            if (que2.equals("yes")) {
                q2y.getAndIncrement();
                curUser.incAnswer(2);
            } else {
                q2n.getAndIncrement();
                curUser.incAnswer(3);
            }

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println(String.format(TEMPLATE, curUser.getLogin(),
                curUser.getAnswer(0),
                curUser.getAnswer(1),
                curUser.getAnswer(2),
                curUser.getAnswer(3),
                q1y, q1n, q2y, q2n));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null)) {
            session.removeAttribute("user");
        }
        response.sendRedirect("index.jsp");
    }


}
