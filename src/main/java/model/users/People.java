package model.users;

public interface People {

    String getName();
    String getCFPIVA();
    String getAddress();
    String getCity();
    int getCAP();
    int getTel();
    String getEmail();

    void setAddress(String newAddress);
    void setCity(String newCity);
    void setCAP(int newCAP);
    void setTel(int newTel);
    void setEmail(String newEmail);
}
