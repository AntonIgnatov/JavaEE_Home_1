<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Prog.kiev.ua</title>
  </head>
  <body>
    <% String login = (String)session.getAttribute("user_login"); %>

    <% if (login == null || "".equals(login)) { %>
        <form action="/login" method="POST">
            Login: <input type="text" name="login"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" />
        </form>
    <% } %>

  </body>
</html>
