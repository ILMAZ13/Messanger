package Repositories;

import Entities.Message;
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
 * Created by ilmaz on 28.10.16.
 */
public class SqlMessageRepository {
    public boolean addMessage(Message message) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO messages SET id_s=?, id_o=?, text=?, time=?, is_readed=?");
            int i = 1;
            st.setInt(i++, message.getIdSender());
            st.setInt(i++, message.getIdOpponent());
            st.setString(i++, message.getText());
            st.setString(i++, message.getTime());
            st.setInt(i++, (message.isReaded() ? 1 : 0));
            st.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBException();
        }
        return true;
    }

    public List<Message> findLastMessagesBySenderId(int id) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        UserRepository repository = new SqlUserRepository();
        try {
            PreparedStatement st = conn.prepareStatement("select * from messages where id in (select MAX(id) from messages where id_s=? group by id_o) or id in (select MAX(id) from messages where id_o=? group by id_s) order by id DESC ");
            int i = 1;
            st.setInt(i++, id);
            st.setInt(i++, id);
            ResultSet resultSet = st.executeQuery();
            List<Message> messageList = new LinkedList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Message message = new Message(
                            resultSet.getInt("id_s"),
                            resultSet.getInt("id_o"),
                            resultSet.getString("text"),
                            resultSet.getString("time"),
                            resultSet.getInt("is_readed") == 1
                    );
                    int idOpp = message.getIdOpponent() == id ? message.getIdSender() : message.getIdOpponent();
                    message.setUser(repository.getUserById(idOpp));
                    messageList.add(message);
                }
            }
            return messageList;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public List<Message> findMessagesWithOpponent(int idS, int idO) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        UserRepository repository = new SqlUserRepository();
        try {
            PreparedStatement st = conn.prepareStatement("select * from messages where (id_s=? AND id_o=?) or (id_s=? AND id_o=?) order by id DESC");
            int i = 1;
            st.setInt(i++, idS);
            st.setInt(i++, idO);
            st.setInt(i++, idO);
            st.setInt(i++, idS);
            ResultSet resultSet = st.executeQuery();
            List<Message> messageList = new LinkedList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Message message = new Message(
                            resultSet.getInt("id_s"),
                            resultSet.getInt("id_o"),
                            resultSet.getString("text"),
                            resultSet.getString("time"),
                            resultSet.getInt("is_readed") == 1
                    );
                    message.setUser(repository.getUserById(message.getIdSender()));
                    messageList.add(message);
                }
            }
            return messageList;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
