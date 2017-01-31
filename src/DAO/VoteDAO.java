package DAO;

import Models.Vote;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public interface VoteDAO extends GenericDAO<Vote, Long> {

    public Vote getVoteByUserIdNoteId(long userId, long NoteId);
}
