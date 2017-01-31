package DAO;

import Models.User;

import java.util.List;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public interface UserDAO extends GenericDAO<User, Long> {

    List<User> getAll();
    User getUserByUsername(String username);

}
