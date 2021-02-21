package model.users;

public class StaffImpl extends PeopleImpl implements Staff {

    private String admin;

    public StaffImpl(String CF_PIVA, String name, String address, String city, String cap, String tel, String email, String admin) {
        super(CF_PIVA, name, address, city, cap, tel, email);
        this.admin = admin;
    }

    @Override
    public String getIsAdmin() {
        return this.admin;
    }

    @Override
    public void setIsAdmin(final String value) {
        this.admin = value;
    }

}
