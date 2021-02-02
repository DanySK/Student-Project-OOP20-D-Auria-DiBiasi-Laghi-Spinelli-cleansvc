package model.users;

public class PeopleImpl implements People {

    private String CF_PIVA;
    private String nome;
    private String indirizzo;
    private String citta;
    private String cap;
    private String telefono;
    private String email;
    
    public PeopleImpl(String CF_PIVA, String nome, String indirizzo, String citta, String cap, String telefono, String email) {
        this.CF_PIVA = CF_PIVA;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.cap = cap;
        this.telefono = telefono;
        this.email = email;
    }
    
    @Override
    public String getNome() {
        return this.nome;
    }
    @Override
    public String getCFPIVA() {
        return this.CF_PIVA;
    }
    @Override
    public String getIndirizzo() {
        return this.indirizzo;
    }
    @Override
    public String getCitta() {
        return this.citta;
    }
    @Override
    public String getCAP() {
        return this.cap;
    }
    @Override
    public String getTelefono() {
        return this.telefono;
    }
    @Override
    public String getEmail() {
        return this.email;
    }
    //if the client/employee change his contacts
    @Override
    public void setIndirizzo(String newIndirizzo) {
        this.indirizzo = newIndirizzo;
    }
    @Override
    public void setCitta(String newCitta) {
        this.citta = newCitta;
    }
    @Override
    public void setCAP(String newCAP) {
        this.cap = newCAP;
    }
    @Override
    public void setTelefono(String newTel) {
        this.telefono = newTel;
    }
    @Override
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }
}
