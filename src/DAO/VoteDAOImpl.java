package DAO;

import DbUtil.ConnectionProvider;
import Models.Vote;
import Models.VoteType;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class VoteDAOImpl implements VoteDAO {

    private static final String create = "INSERT INTO vote(note_id, user_id, date, type) VALUES (:note_id, :user_id, :date, :type)";
    private static final String read = "SELECT vote_id, note_id, user_id, date, type FROM vote WHERE vote_id = :vote_id";
    private static final String updateVote = "UPDATE vote SET date=:date, type=:type WHERE vote_id=:vote_id";
    private static final String readByDiscovery = "SELECT vote_id, note_id, user_id, date, type " +
            "FROM vote WHERE user_id = :user_id AND note_id = :note_id";

    private NamedParameterJdbcTemplate template;

    public VoteDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Vote getVoteByUserIdNoteId(long userId, long noteId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("note_id", noteId);

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);
        Vote vote = null;
        try {
        vote = template.queryForObject(readByDiscovery, sqlParameterSource, new VoteRowMapper());
        } catch(EmptyResultDataAccessException e) {

        }
        return vote;
    }

    @Override
    public Vote create(Vote vote) {

        Map<String, Object> map = new HashMap<>();
        map.put("note_id", vote.getNoteId());
        map.put("user_id", vote.getUserId());
        map.put("date", vote.getDate());
        map.put("type", vote.getVoteType().toString());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);

        int update = template.update(create, sqlParameterSource, keyHolder);

        if (update > 0) {
            vote.setId((Long) keyHolder.getKey());
        }
        return vote;
    }

    @Override
    public Vote read(Long primaryKey) {

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("vote_id", primaryKey);
        Vote vote = template.queryForObject(read, sqlParameterSource, new VoteRowMapper());
        return vote;
    }

    @Override
    public boolean update(Vote vote) {

        boolean result = false;
        Map<String, Object> map = new HashMap<>();
        map.put("date", vote.getDate());
        map.put("type", vote.getVoteType().toString());
        map.put("vote_id", vote.getId());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);
        int update = template.update(updateVote, sqlParameterSource);

        if (update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    private class VoteRowMapper implements RowMapper<Vote> {

        @Override
        public Vote mapRow(ResultSet resultSet, int i) throws SQLException {
            Vote vote = new Vote();
            vote.setId(resultSet.getLong("vote_id"));
            vote.setUserId(resultSet.getLong("user_id"));
            vote.setNoteId(resultSet.getLong("note_id"));
            vote.setDate(resultSet.getTimestamp("date"));
            vote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
            return vote;
        }
    }
}
