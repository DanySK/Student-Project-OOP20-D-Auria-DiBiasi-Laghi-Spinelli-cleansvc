package model.users;

public interface Staff extends People {

    String isAdmin();
    void setIsAdmin(final String value);
 
}
