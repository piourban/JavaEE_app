package DAO;

import DbUtil.ConnectionProvider;
import Models.Note;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class NoteDAOImpl implements NoteDAO {

    private static final String create = "INSERT INTO content (name, description, url, date, up_vote, down_vote, user_id) "
            + "VALUES(:name, :description, :url, :date, :up_vote, :down_vote, :user_id);";

    private NamedParameterJdbcTemplate template;

    public NoteDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Note create(Note note) {

        Note resultNote = null;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name",note.getName());
        map.put("description", note.getDescription());
        map.put("url", note.getUrl());
        map.put("date", note.getTimestamp());
        map.put("up_vote", note.getUpVote());
        map.put("down_vote", note.getDownVote());
        map.put("user_id", note.getUser().getId());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);
        int rows = template.update(create,sqlParameterSource,keyHolder);

        if (rows>0)
            resultNote.setId((Long) keyHolder.getKey());

        return resultNote;
    }

    @Override
    public Note read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(Note updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Note> getAll() {
        return null;
    }
}
