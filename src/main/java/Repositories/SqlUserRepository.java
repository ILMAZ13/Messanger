package Repositories;

import Entities.User;
import Exceptions.AlreadyExistException;
import Exceptions.DBException;
import Interfaces.UserRepository;
import Service.SQLDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ilmaz on 10.10.16.
 */
public class SqlUserRepository implements UserRepository {

    public boolean addUser(User user) throws DBException, AlreadyExistException {
        Connection conn = SQLDatabase.getConnection();
        //ToDo: replace to get code from documentation
        if(!containsUser(user)) {
            try {
                PreparedStatement st = conn.prepareStatement("INSERT INTO users SET email=?, name=?, password=?, is_male=?, city=?");
                int i = 1;
                st.setString(i++, user.getEmail());
                st.setString(i++, user.getName());
                st.setString(i++, user.getPassword());
                st.setInt(i++, user.isMale() ? 1 : 0);
                st.setString(i++, user.getCity());
                st.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DBException();
            }
        } else {
            throw new AlreadyExistException();
        }
        return false;
    }

    @Override
    public User findByEmail(String email, String password) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement("SELECT * FROM users WHERE email=?");
            int i = 1;
            st.setString(i++, email);
            ResultSet resultSet = st.executeQuery();
            if(resultSet != null){
                if(resultSet.next()){
//                    if(resultSet.getString("password").equals(Encryptor.getHash(password, email))) {
                        User user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("name"),
                                resultSet.getString("city"),
                                resultSet.getInt("is_male") == 1,
                                resultSet.getString("password"),
                                resultSet.getString("f_singer")
                        );
                        return user;
//                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return null;
    }

    @Override
    public List<User> getUsersList() throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = st.executeQuery();
            List<User> userList = new LinkedList<>();
            if(resultSet != null){
                while (resultSet.next()){
                    User user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("name"),
                            resultSet.getString("city"),
                            resultSet.getInt("is_male") == 1,
                            resultSet.getString("password"),
                            resultSet.getString("f_singer")
                    );
                    userList.add(user);
                }
                return userList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return null;
    }

    @Override
    public boolean containsUser(User user) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement("SELECT * FROM users WHERE email=?");
            int i = 1;
            st.setString(i++, user.getEmail());
            ResultSet resultSet = st.executeQuery();
            if(resultSet == null){
                return false;
            } else {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    @Override
    public boolean delUser(String email, String password) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement("DELETE FROM users WHERE email=? AND password=?");
            int i = 1;
            st.setString(i++, email);
            st.setString(i++, password);
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    @Override
    public boolean updateUser(User user) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        //ToDo: replace to get code from documentation
        if(containsUser(user)) {
            try {
                PreparedStatement st = conn.prepareStatement("UPDATE users SET name=?, password=?, is_male=?, city=?, f_singer=? WHERE email=?");
                int i = 1;
                st.setString(i++, user.getName());
                st.setString(i++, user.getPassword());
                st.setInt(i++, user.isMale() ? 1 : 0);
                st.setString(i++, user.getCity());
                st.setString(i++, user.getfSinger());
                st.setString(i++, user.getEmail());
                st.execute();
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DBException();
            }
        }
        return false;
    }

    @Override
    public int getIdByEmail(String email) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement("SELECT * FROM users WHERE email=?");
            int i = 1;
            st.setString(i++, email);
            ResultSet resultSet = st.executeQuery();
            if(resultSet != null){
                if(resultSet.next()){
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return -1;
    }

    @Override
    public User getUserById(int id) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement("SELECT * FROM users WHERE id=?");
            int i = 1;
            st.setInt(i++, id);
            ResultSet resultSet = st.executeQuery();
            if(resultSet != null){
                if(resultSet.next()){
                    User user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("name"),
                            resultSet.getString("city"),
                            resultSet.getInt("is_male") == 1,
                            resultSet.getString("password"),
                            resultSet.getString("f_singer")
                    );
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return null;
    }
}
