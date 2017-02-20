package DAO;

import DbUtil.ConnectionProvider;
import Models.Note;
import Models.User;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class NoteDAOImpl implements NoteDAO {

    private static final String create = "INSERT INTO note (name, description, url, date, up_vote, down_vote, user_id) "
            + "VALUES(:name, :description, :url, :date, :up_vote, :down_vote, :user_id)";
    private static final String readAll = "SELECT user.user_id, username, email, isActive, password, note_id, name, description, url, date, up_vote, down_vote " +
            "FROM note LEFT JOIN user ON note.user_id=user.user_id";
    private static final String readNote =
            "SELECT user.user_id, username, email, isActive, password, note_id, name, description, url, date, up_vote, down_vote "
                    + "FROM note LEFT JOIN user ON note.user_id=user.user_id WHERE note_id=:note_id";
    private static final String updateNote =
            "UPDATE note SET name=:name, description=:description, url=:url, user_id=:user_id, date=:date, up_vote=:up_vote, down_vote=:down_vote "
                    + "WHERE note_id=:note_id";

    private NamedParameterJdbcTemplate template;

    public NoteDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Note create(Note note) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", note.getName());
        map.put("description", note.getDescription());
        map.put("url", note.getUrl());
        map.put("date", note.getTimestamp());
        map.put("up_vote", note.getUpVote());
        map.put("down_vote", note.getDownVote());

        map.put("user_id", note.getUser().getId());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);
        int rows = template.update(create, sqlParameterSource, keyHolder);

        if (rows > 0)
            note.setId((Long) keyHolder.getKey());

        return note;
    }

    @Override
    public Note read(Long primaryKey) {

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("note_id", primaryKey);
        Note note = (Note) template.queryForObject(readNote,sqlParameterSource, new NotesRowMapper());

        return note;
    }

    @Override
    public boolean update(Note note) {

        boolean result = false;
        Map<String, Object> map = new HashMap<>();
        map.put("note_id", note.getId());
        map.put("name", note.getName());
        map.put("description", note.getDescription());
        map.put("url", note.getUrl());
        map.put("user_id", note.getUser().getId());
        map.put("date", note.getTimestamp());
        map.put("up_vote", note.getUpVote());
        map.put("down_vote", note.getDownVote());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);
        int update = template.update(updateNote, sqlParameterSource);

        if (update > 0){
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Note> getAll() {

        List<Note> notes = template.query(readAll, new NotesRowMapper());
        return notes;
    }

    private class NotesRowMapper implements RowMapper {
        @Override
        public Note mapRow(ResultSet resultSet, int row) throws SQLException {
            Note note = new Note();
            note.setId(resultSet.getLong("note_id"));
            note.setName(resultSet.getString("name"));
            note.setDescription(resultSet.getString("description"));
            note.setUrl(resultSet.getString("url"));
            note.setUpVote(resultSet.getInt("up_vote"));
            note.setDownVote(resultSet.getInt("down_vote"));
            note.setTimestamp(resultSet.getTimestamp("date"));

            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));

            note.setUser(user);
            return note;
        }
    }
}
