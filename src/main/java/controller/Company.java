package controller;

import java.util.List;
import java.util.Optional;

import model.Products;
import model.users.Clients;
import model.users.Staff;

public interface Company {
    
    public void addStaff(final Staff s);
    public void removeStaff(final Staff s);
    public Optional<Staff> searchStaff(final String emailStaff);
    public List<Staff> getStaff();
    
    public void addClient(final Clients c);
    public void removeClient(final Clients c);
    public Optional<Clients> searchClient(final String CF_PIVA);
    public List<Clients> getClient();
    
    public void addProduct(final Products p);
    public void removeProduct(final Products p);
    public Optional<Products> searchProduct(final String nameProduct);
    public List<Products> getProduct();
}
