package model.users;

public class PeopleImpl implements People {

    private String CF_PIVA;
    private String name;
    private String address;
    private String city;
    private int cap;
    private String tel;
    private String email;

    public PeopleImpl(String CF_PIVA, String name, String address, String city, int cap, String tel, String email) {
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
    public int getCAP() {
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
}
