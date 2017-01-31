package DAO;

import DbUtil.ConnectionProvider;
import Models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class UserDAOImpl implements UserDAO {

    private final static String create = "insert into user (username, email, isActive, password) values (:username, :email, :isActive, :password)";
    private final static String role = "insert into user_role (username) values (:username)";
    private final static String read = "SELECT user_id, username, email, password, isActive FROM user WHERE user_id = :id";
    private final static String readByName = "SELECT user_id, username, email, password, isActive FROM user WHERE username = :username";

    private NamedParameterJdbcTemplate template;

    public UserDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public User create(User user) {

        User temporaryUser = new User(user);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        int rows = template.update(create, sqlParameterSource, keyHolder);

        if (rows > 0) {

            temporaryUser.setId((Long) keyHolder.getKey());
            setPrivigiles(temporaryUser);
        }
        return temporaryUser;
    }

    private void setPrivigiles(User user) {

        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        template.update(role, sqlParameterSource);
    }

    @Override
    public User read(Long primaryKey) {

        User resultUser;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", primaryKey);
        resultUser = template.queryForObject(read, sqlParameterSource, new UserRowMapper());

        return resultUser;
    }


    @Override
    public boolean update(User updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }


    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }

    }

}
