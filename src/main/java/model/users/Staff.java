package model.users;

public interface Staff extends People {

    String getIsAdmin();
    void setIsAdmin(final String value);
 
}
