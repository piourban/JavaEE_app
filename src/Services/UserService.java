package Services;

import DAO.DAOFactory;
import DAO.UserDAO;
import Models.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class UserService {

    public void addUser(String username, String email, String password) {
        String md5Pass = encryptPassword(password);

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setActive(true);
        user.setPassword(md5Pass);


        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        userDao.create(user);
    }

    private String encryptPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes());
        String md5Password = new BigInteger(1, digest.digest()).toString(16);
        return md5Password;
    }

    public User getUserById(long userId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.read(userId);
        return user;
    }

    public User getUserByUsername(String username) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.getUserByUsername(username);
        return user;
    }
}
