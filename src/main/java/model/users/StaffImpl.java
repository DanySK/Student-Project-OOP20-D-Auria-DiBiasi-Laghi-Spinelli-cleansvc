package model.users;

public class StaffImpl extends PeopleImpl implements Staff {

    private Boolean admin;

    public StaffImpl(final String CF_PIVA, final String name, final String address, final String city, final int cap, final String tel, final String email, final Boolean admin) {
        super(CF_PIVA, name, address, city, cap, tel, email);
        this.admin = admin;
    }

    @Override
    public Boolean isAdmin() {
        return this.admin;
    }
}
