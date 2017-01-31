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
import java.util.Date;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class NoteService {


    public void add (String name, String description, String url, User user){

        Note note = createNote(name,description,url,user);
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
        User copyUser = new User(user);
        note.setUser(copyUser);

        return note;
    }
}
