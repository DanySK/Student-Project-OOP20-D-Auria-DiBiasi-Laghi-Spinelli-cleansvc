package model.users;

public class StaffImpl extends PeopleImpl implements Staff {

    private boolean admin;
    
    public StaffImpl(String CF_PIVA, String name, String address, String city, String cap, String tel, String email, boolean admin) {
        super(CF_PIVA, name, address, city, cap, tel, email);
        this.admin = admin;
    }
    
    @Override
    public Boolean isAdmin() {
        return this.admin;
    }

    @Override
    public void setIsAdmin(final Boolean value) {
        this.admin = value;
    }

}
