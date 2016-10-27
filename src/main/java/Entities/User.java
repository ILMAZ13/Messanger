package Entities;

/**
 * Created by ilmaz on 28.09.16.
 */
public class User {
    private int id;
    private String email;
    private String name;
    private String city;
    private boolean isMale;
    private String password;
    private String fSinger;

    public String getEmail() {
        return email;
    }

    public User(String email, String name, String city, boolean isMale, String password) {
        this.email = email;
        this.name = name;
        this.city = city;
        this.isMale = isMale;
        this.password = password;
    }

    public User(int id, String email, String name, String city, boolean isMale, String password, String fSinger) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.city = city;
        this.isMale = isMale;
        this.password = password;
        this.fSinger = fSinger;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public boolean isMale() {
        return isMale;
    }

    public String getPassword() {
        return password;
    }

    public String getfSinger() {
        return fSinger;
    }

    public void setfSinger(String fSinger) {
        this.fSinger = fSinger;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return ((User)o).getEmail().equals(this.getEmail());
    }
}
