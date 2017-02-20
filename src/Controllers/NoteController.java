package Controllers;

import Models.User;
import Services.NoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Piotr Urban on 30.01.2017.
 */

@WebServlet("/add")
public class NoteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getUserPrincipal() != null) {
            req.getRequestDispatcher("/WEB-INF/new.jsp").forward(req, resp);
        } else {
            resp.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("inputName");
        String description = req.getParameter("inputDescription");
        String url = req.getParameter("inputUrl");

        User user = (User) req.getSession().getAttribute("user");
        if (req.getUserPrincipal() != null) {
            NoteService noteService = new NoteService();
            noteService.add(name, description, url, user);
            resp.sendRedirect("/home");
        } else {
            resp.sendError(403);
        }
    }
}
