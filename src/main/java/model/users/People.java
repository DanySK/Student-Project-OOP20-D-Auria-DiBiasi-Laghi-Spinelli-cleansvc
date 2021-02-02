package model.users;

public interface People {
    
    String getNome();
    String getCFPIVA();
    String getIndirizzo();
    String getCitta();
    String getCAP();
    String getTelefono();
    String getEmail();
    void setIndirizzo(String newIndirizzo);
    void setCitta(String newCitta);
    void setCAP(String newCAP);
    void setTelefono(String newTel);
    void setEmail(String newEmail);
}
