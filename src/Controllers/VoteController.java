package Controllers;

import Models.Note;
import Models.User;
import Models.Vote;
import Models.VoteType;
import Services.NoteService;
import Services.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Piotr Urban on 14.02.2017.
 */

@WebServlet("/vote")
public class VoteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user != null){
            VoteType voteType = VoteType.valueOf(req.getParameter("vote"));
            long userID = user.getId();
            long noteID = Long.parseLong(req.getParameter("note_id"));

            updateVote(userID, noteID, voteType);
        }
        resp.sendRedirect("/home");
    }

    private void updateVote(long userId, long noteId, VoteType voteType) {

        VoteService voteService = new VoteService();
        Vote existingVote = voteService.getVoteByDiscoveryUserId(noteId, userId);
        Vote newVote = voteService.addOrUpdateVote(noteId, userId, voteType);

        if (existingVote != newVote || !newVote.equals(existingVote)) {
            updateNote(noteId, existingVote, newVote);
        }

    }

    private void updateNote(long noteId, Vote oldVote, Vote newVote) {

        NoteService noteService = new NoteService();
        Note note = noteService.getDiscoveryById(noteId);
        Note updateNote = null;

        if (oldVote == null && newVote != null) {
            updateNote = addNoteVote(note, newVote.getVoteType());
        } else if (oldVote != null && newVote != null) {
            updateNote = removeNoteVote(note, oldVote.getVoteType());
            updateNote = addNoteVote(note, newVote.getVoteType());
        }
        noteService.updateNote(updateNote);
    }

    private Note addNoteVote(Note note, VoteType voteType) {
        if (voteType == VoteType.voteUp) {
            note.setUpVote(note.getUpVote() + 1);
        } else if (voteType == VoteType.voteDown) {
            note.setDownVote(note.getDownVote() + 1);
        }
        return note;
    }

    private Note removeNoteVote(Note note, VoteType voteType) {
        if (voteType == VoteType.voteUp) {
            note.setUpVote(note.getUpVote() - 1);
        } else if (voteType == VoteType.voteDown) {
            note.setDownVote(note.getDownVote() - 1);
        }
        return note;
    }
}
