package model.users;

public class ClientsImpl extends PeopleImpl implements Clients {

    private int mqStructure;

    public ClientsImpl(String CF_PIVA, String name, String address, String city, String cap, String tel, String email, int mqStructure) {
        super(CF_PIVA, name, address, city, cap, tel, email);
        this.mqStructure = mqStructure;
    }

    @Override
    public int getMqStructure() {
        return this.mqStructure;
    }

}
