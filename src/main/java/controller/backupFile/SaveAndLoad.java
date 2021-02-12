package controller.backupFile;

import java.util.List;

import model.Products;
import model.users.*;

public interface SaveAndLoad {
    
    public void saveStaff();
    public List<Staff> loadStaff();
    public void saveClient();
    public List<Clients> loadClient();
    public void saveProduct();
    public List<Products> loadProduct();

}