package model.users;

public class StaffImpl extends PeopleImpl implements Staff {

    private Boolean admin;

    public StaffImpl(String CF_PIVA, String name, String address, String city, int cap, int tel, String email, Boolean admin) {
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
