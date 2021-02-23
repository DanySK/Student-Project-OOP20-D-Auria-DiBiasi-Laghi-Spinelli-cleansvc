package controller;

import java.util.List;
import java.util.Optional;

import model.Appointments;
import model.Products;
import model.step.enumerations.StepType;
import model.users.Clients;
import model.users.Staff;

public interface Company {

    void addStaff(final Staff s);
    void removeStaff(final Staff s);
    Optional<Staff> searchStaffbyCF(final String CFStaff);
    Optional<Staff> searchStaffbyEmail(final String emailStaff);
    List<Staff> getStaff();

    void addClient(final Clients c);
    void removeClient(final Clients c);
    Optional<Clients> searchClient(final String CF_PIVA);
    List<Clients> getClients();

    void addProduct(final Products p);
    void removeProduct(final Products p);
    Optional<Products> searchProduct(final String codeProduct);
    List<Products> getProducts();
    Optional<List<Products>> getProductsByStepType(StepType stepType);

    void addAppointment(Appointments a);
    void removeAppointment(Appointments a);
    Optional<Appointments> searchAppointment(String date, String hour);
    List<Appointments> getAppointment();
}