package model.users;

public interface People {

    String getName();
    String getCFPIVA();
    String getAddress();
    String getCity();
    int getCAP();
    String getTel();
    String getEmail();

    void setAddress(String newAddress);
    void setCity(String newCity);
    void setCAP(int newCAP);
    void setTel(String newTel);
    void setEmail(String newEmail);
}
