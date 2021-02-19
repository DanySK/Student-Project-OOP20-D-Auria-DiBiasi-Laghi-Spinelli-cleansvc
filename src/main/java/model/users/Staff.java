package model.users;

public interface Staff extends People {

    Boolean isAdmin();
    void setIsAdmin(final Boolean value);
 
}
