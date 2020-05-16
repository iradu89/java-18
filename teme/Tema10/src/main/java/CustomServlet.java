import lombok.extern.log4j.Log4j2;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
@WebServlet("/CustomServlet")
public class CustomServlet extends HttpServlet {

    public void init(ServletConfig servletConfig) {
        log.info("Servlet has been initialized");
    }

    public void destroy(){
        log.info("Servlet has been destroyed");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        customResponse(res, "GET");
        log.info("Servlet is alive and just processed a GET request");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        customResponse(res, "POST");
        log.info("Servlet is alive and just processed a POST request");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        customResponse(res, "PUT");
        log.info("Servlet is alive and just processed a PUT request");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        customResponse(res, "DELETE");
        log.info("Servlet is alive and just processed a DELETE request");
    }

    private void customResponse(HttpServletResponse res, String type) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h3> " + type + " method works! </h3>");
    }
}
