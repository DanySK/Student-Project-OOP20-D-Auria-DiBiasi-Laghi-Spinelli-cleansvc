package model.users;

public class ClientsImpl extends PeopleImpl implements Clients {

    private int mqStructure;

    public ClientsImpl(String CF_PIVA, String nome, String indirizzo, String citta, String cap, String telefono, String email, int mqStructure) {
        super(CF_PIVA, nome, indirizzo, citta, cap, telefono, email);
        this.mqStructure = mqStructure;
    }
    
    @Override
    public int getmqStructure() {
        return this.mqStructure;
    }

}
