package Services;

import DAO.DAOFactory;
import DAO.VoteDAO;
import Models.Vote;
import Models.VoteType;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class VoteService {

    public Vote addVote(long noteId, long userId, VoteType voteType) {

        Vote vote = new Vote();
        vote.setNoteId(noteId);
        vote.setUserId(userId);
        vote.setVoteType(voteType);

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = daoFactory.getVoteDAO();
        vote = voteDAO.create(vote);

        return vote;
    }

    public Vote updateVote(long noteId, long userId, VoteType voteType) {

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = daoFactory.getVoteDAO();

        Vote updateVote = voteDAO.getVoteByUserIdNoteId(userId, noteId);
        if (updateVote != null) {
            updateVote.setVoteType(voteType);
            voteDAO.update(updateVote);
        }
        return updateVote;
    }

    public Vote addOrUpdateVote(long noteId, long userId, VoteType voteType) {

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = daoFactory.getVoteDAO();

        Vote vote = voteDAO.getVoteByUserIdNoteId(userId, noteId);
        Vote resultVote;

        if (vote == null) {
            resultVote = addVote(noteId, userId, voteType);
        } else {
            resultVote = updateVote(noteId, userId, voteType);
        }
        return resultVote;

    }

    public Vote getVoteByDiscoveryUserId(long noteId, long userId) {

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = daoFactory.getVoteDAO();

        Vote vote = voteDAO.getVoteByUserIdNoteId(userId, noteId);
        return vote;
    }
}
