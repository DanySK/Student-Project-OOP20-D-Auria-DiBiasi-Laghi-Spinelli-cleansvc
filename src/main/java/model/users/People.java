package model.users;

public interface People {
    
    String getName();
    String getCFPIVA();
    String getAddress();
    String getCity();
    String getCAP();
    String getTel();
    String getEmail();
    
    void setAddress(String newAddress);
    void setCity(String newCity);
    void setCAP(String newCAP);
    void setTel(String newTel);
    void setEmail(String newEmail);
    
}
