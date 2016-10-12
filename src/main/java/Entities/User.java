package Entities;

/**
 * Created by ilmaz on 28.09.16.
 */
public class User {
    private String email;
    private String name;
    private String city;
    private boolean isMale;
    private String password;

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

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return ((User)o).getEmail().equals(this.getEmail());
    }
}
