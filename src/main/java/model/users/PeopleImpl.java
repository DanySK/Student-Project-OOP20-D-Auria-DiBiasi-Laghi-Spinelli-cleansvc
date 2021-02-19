package model.users;

public class PeopleImpl implements People {

    private String CF_PIVA;
    private String name;
    private String address;
    private String city;
    private String cap;
    private String tel;
    private String email;

    public PeopleImpl(String CF_PIVA, String name, String address, String city, String cap, String tel, String email) {
        this.CF_PIVA = CF_PIVA;
        this.name = name;
        this.address = address;
        this.city = city;
        this.cap = cap;
        this.tel = tel;
        this.email = email;
    }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getCFPIVA() {
        return this.CF_PIVA;
    }
    @Override
    public String getAddress() {
        return this.address;
    }
    @Override
    public String getCity() {
        return this.city;
    }
    @Override
    public String getCAP() {
        return this.cap;
    }
    @Override
    public String getTel() {
        return this.tel;
    }
    @Override
    public String getEmail() {
        return this.email;
    }
    /*
     * methods for the change of client/employee's contacts
     */
    @Override
    public void setAddress(String newAddress) {
        this.address = newAddress;
    }
    @Override
    public void setCity(String newCity) {
        this.city = newCity;
    }
    @Override
    public void setCAP(String newCAP) {
        this.cap = newCAP;
    }
    @Override
    public void setTel(String newTel) {
        this.tel = newTel;
    }
    @Override
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }
}
