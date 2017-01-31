package Models;

import java.sql.Timestamp;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class Note {

    private long id;
    private String name;
    private String description;
    private String url;
    private Timestamp timestamp;
    private User user;
    private int upVote;
    private int downVote;

    public Note() {}

    public Note(long id, String name, String description, String url, Timestamp timestamp, User user, int upVote, int downVote) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.timestamp = timestamp;
        this.user = user;
        this.upVote = upVote;
        this.downVote = downVote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", upVote=" + upVote +
                ", downVote=" + downVote +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (getId() != note.getId()) return false;
        if (getUpVote() != note.getUpVote()) return false;
        if (getDownVote() != note.getDownVote()) return false;
        if (!getName().equals(note.getName())) return false;
        if (!getDescription().equals(note.getDescription())) return false;
        if (getUrl() != null ? !getUrl().equals(note.getUrl()) : note.getUrl() != null) return false;
        if (!getTimestamp().equals(note.getTimestamp())) return false;
        return getUser().equals(note.getUser());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + getTimestamp().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getUpVote();
        result = 31 * result + getDownVote();
        return result;
    }
}
