package Controllers;

import Models.Note;
import Services.NoteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Piotr Urban on 08.02.2017.
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveNotes(req);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    private void saveNotes(HttpServletRequest request) {

        NoteService noteService = new NoteService();
        List<Note> notesList = noteService.getAllNotes(new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                int o1Count = o1.getUpVote() - o1.getDownVote();
                int o2Count = o2.getUpVote() - o2.getDownVote();

                if (o1Count < o2Count)
                    return 1;
                else if (o1Count > o2Count)
                    return -1;
                return 0;
            }
        });
        request.setAttribute("notes", notesList);
    }
}
