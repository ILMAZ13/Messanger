package Entities;

/**
 * Created by ilmaz on 28.10.16.
 */
public class Message {
    private int idSender;
    private int idOpponent;
    private String text;
    private String time;
    private User user;
    private boolean isReaded;

    public Message(int idSender, int idOpponent, String text, String time) {
        this.idSender = idSender;
        this.idOpponent = idOpponent;
        this.text = text;
        this.time = time;
        this.isReaded = false;
    }

    public Message(int idSender, int idOpponent, String text, String time, boolean isReaded) {
        this.idSender = idSender;
        this.idOpponent = idOpponent;
        this.text = text;
        this.time = time;
        this.isReaded = isReaded;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setReaded() {
        isReaded = true;
    }

    public int getIdSender() {
        return idSender;
    }

    public int getIdOpponent() {
        return idOpponent;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public boolean isReaded() {
        return isReaded;
    }
}
