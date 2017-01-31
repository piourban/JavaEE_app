package DAO;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class MySqlDAOFactory extends DAOFactory {

    @Override
    public NoteDAO getNoteDAO() {
        return new NoteDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() { return new UserDAOImpl(); }

    @Override
    public VoteDAO getVoteDAO() {
        return new VoteDAOImpl();
    }
}
