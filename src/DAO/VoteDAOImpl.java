package DAO;

import Models.Vote;

import java.util.List;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class VoteDAOImpl implements VoteDAO {

    @Override
    public Vote getVoteByUserIdNoteId(long userId, long NoteId) {
        return null;
    }

    @Override
    public Vote create(Vote newObject) {
        return null;
    }

    @Override
    public Vote read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(Vote updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }
}
