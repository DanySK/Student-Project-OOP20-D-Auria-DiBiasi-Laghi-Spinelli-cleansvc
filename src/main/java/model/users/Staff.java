package model.users;

public interface Staff extends People {
    
    void setIsAdmin(Boolean value);
    
    Boolean isAdmin();
}
