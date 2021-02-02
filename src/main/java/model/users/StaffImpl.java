package model.users;

public class StaffImpl extends PeopleImpl implements Staff {

    private boolean admin;
    
    public StaffImpl(String CF_PIVA, String nome, String indirizzo, String citta, String cap, String telefono, String email,
            boolean admin) {
        super(CF_PIVA, nome, indirizzo, citta, cap, telefono, email);
        this.admin = admin;
    }
    
    @Override
    public Boolean isAdmin() {
        return this.admin;
    }

    @Override
    public void setIsAdmin(Boolean value) {
        this.admin = value;
    }

}
