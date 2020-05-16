<html>
<head><title>Tema 10 Landing Page</title></head>
<body>
<h3> Welcome to Tema 10: Servlets </h3>
<h2> This is a JSP </h2>
  <%
    double num = Math.random();
    if (num > 0.50) {
  %>
      <h2>Number is bigger than 0.50 !</h2><p>(<%= String.format("%.2f", num) %>)</p>
  <%
    } else {
  %>
      <h2>Number is smaller than 0.50 </h2><p>(<%= String.format("%.2f", num) %>)</p>
  <%
    }
  %>
  <a href="<%= request.getRequestURI() %>"><h4>Click here to try again</h4></a>
</body>
</html>
