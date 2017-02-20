package Services;

import DAO.DAOFactory;
import DAO.NoteDAO;
import Models.Note;
import Models.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class NoteService {


    public void add(String name, String description, String url, User user) {
        Note note = createNote(name, description, url, user);
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        NoteDAO noteDAO = daoFactory.getNoteDAO();
        noteDAO.create(note);
    }

    private Note createNote(String name, String description, String url, User user) {
        Calendar calendar = Calendar.getInstance();

        Note note = new Note();
        note.setName(name);
        note.setDescription(description);
        note.setUrl(url);
        note.setTimestamp(new Timestamp(calendar.getTime().getTime()));
        note.setUser(user);

        return note;
    }

    public Note getDiscoveryById(long noteId) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        NoteDAO noteDAO = daoFactory.getNoteDAO();

        Note note = noteDAO.read(noteId);
        return note;
    }

    public boolean updateNote(Note note) {
        boolean result = false;

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        NoteDAO noteDAO = daoFactory.getNoteDAO();
        result = noteDAO.update(note);

        return result;
    }

    public List<Note> getAllNotes() {
        return getAllNotes();
    }

    public List<Note> getAllNotes(Comparator<Note> comparator) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        NoteDAO noteDAO = daoFactory.getNoteDAO();
        List<Note> notes = noteDAO.getAll();
        if (comparator != null && notes != null) {
            notes.sort(comparator);
        }
        return notes;
    }
}
