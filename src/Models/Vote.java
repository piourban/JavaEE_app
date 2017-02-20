package Models;

import java.sql.Timestamp;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class Vote {

    private long id;
    private long noteId;
    private long userId;
    private Timestamp date;
    private VoteType voteType;

    public Vote() {}

    public Vote(long id, long noteId, long userId, Timestamp date, VoteType voteType) {
        this.id = id;
        this.noteId = noteId;
        this.userId = userId;
        this.date = date;
        this.voteType = voteType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", discoveryId=" + noteId +
                ", userId=" + userId +
                ", date=" + date +
                ", voteType=" + voteType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;

        Vote vote = (Vote) o;

        if (getId() != vote.getId()) return false;
        if (getNoteId() != vote.getNoteId()) return false;
        if (getUserId() != vote.getUserId()) return false;
        if (!getDate().equals(vote.getDate())) return false;
        return getVoteType().equals(vote.getVoteType());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getNoteId() ^ (getNoteId() >>> 32));
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getVoteType().hashCode();
        return result;
    }
}
