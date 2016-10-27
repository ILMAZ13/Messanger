package Interfaces;

import Entities.User;
import Exceptions.AlreadyExistException;
import Exceptions.DBException;

import java.util.List;

/**
 * Created by ilmaz on 28.09.16.
 */
public interface UserRepository {
    boolean addUser(User user) throws DBException, AlreadyExistException;
    User findByEmail(String email, String password) throws DBException;
    List<User> getUsersList() throws DBException;
    boolean containsUser(User user) throws DBException;
    boolean delUser(String email, String password) throws DBException;
    boolean updateUser(User user) throws DBException;
}
