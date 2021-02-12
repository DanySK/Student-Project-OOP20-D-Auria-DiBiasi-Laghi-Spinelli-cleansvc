package controller.backupFile;

import java.util.List;

import model.Products;
import model.users.Clients;
import model.users.Staff;

public interface SaveAndLoad {
    void saveStaff();
    List<Staff> loadStaff();
    void saveClient();
    List<Clients> loadClient();
    void saveProduct();
    List<Products> loadProduct();

}