package DAO;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public abstract class DAOFactory {

    public static final int MySQL = 1;

    public abstract NoteDAO getNoteDAO();

    public abstract UserDAO getUserDAO();

    public abstract VoteDAO getVoteDAO();

    public static DAOFactory getDAOFactory() {

        DAOFactory daoFactory = null;
        daoFactory = getDaoFactory(MySQL);

        return daoFactory;
    }

    private static DAOFactory getDaoFactory (int type) {

        switch (type) {
            case MySQL: return new MySqlDAOFactory();
        default: throw new NullPointerException();
        }
    }
}
