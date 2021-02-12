package controller.backupFile;

import java.io.*;
import java.util.*;

import model.Products;
import model.users.Clients;
import model.users.Company;
import model.users.CompanyImpl;
import model.users.Staff;

public class SaveAndLoadImpl implements SaveAndLoad {
    private Company company = CompanyImpl.getInstance();
    private static final String FILE_STAFF = "src/main/java/doc/Staff.txt";
    private static final String FILE_CLIENTS = "src/main/java/doc/Clients.txt";
    private static final String FILE_PRODUCTS = "src/main/java/doc/Products.txt";
    private static final String CFPIVA_STR = "CFPIVA: ";
    private static final String NAME_STR = "NAME: ";
    private static final String ADDRESS_STR = "ADDRESS: ";
    private static final String CITY_STR = "CITY: ";
    private static final String CAP_STR = "CAP: ";
    private static final String TEL_STR = "TEL: ";
    private static final String EMAIL_STR = "EMAIL: ";
    private static final String ADMIN_STR = "ADMIN: ";

    @Override
    public void saveStaff() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_STAFF))) {
            for (final Staff s : this.company.getStaff()) {
                w.write(CFPIVA_STR + s.getCFPIVA());
                w.newLine();
                w.write(NAME_STR + s.getName());
                w.newLine();
                w.write(ADDRESS_STR + s.getAddress());
                w.newLine();
                w.write(CITY_STR + s.getCity());
                w.newLine();
                w.write(CAP_STR + s.getCAP());
                w.newLine();
                w.write(TEL_STR + s.getTel());
                w.newLine();
                w.write(EMAIL_STR + s.getEmail());
                w.newLine();
                int varAdmin = s.isAdmin() ? 1 : 0;
                w.write(ADMIN_STR + varAdmin);
                w.newLine();
                /*w.write("MPYZTB73S59F342U");
                w.newLine();
                w.write("Giorgio Verdi");
                w.newLine();
                w.write("Via Garibaldi, 31");
                w.newLine();
                w.write("Cesena");
                w.newLine();
                w.write("47521");
                w.newLine();
                w.write("3333333333");
                w.newLine();
                w.write("giorver@gmail.com");
                w.newLine();
                //int varAdmin = s.isAdmin() ? 1 : 0;
                w.write("0");
                w.newLine();*/
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Staff> loadStaff() {
        final List<Staff> staffList = new ArrayList<>();
        final List<String> cfPIvaList = new ArrayList<>();
        final List<String> nameList = new ArrayList<>();
        final List<String> addressList = new ArrayList<>();
        final List<String> cityList = new ArrayList<>();
        final List<String> capList = new ArrayList<>();
        final List<String> telList = new ArrayList<>();
        final List<String> emailList = new ArrayList<>();
        final List<Integer> adminList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE_STAFF))) {
            r.lines().forEach(l -> {
                if (l.contains(CFPIVA_STR)) {
                    cfPIvaList.add(l.substring(CFPIVA_STR.length()));
                }
                if (l.contains(NAME_STR)) {
                    nameList.add(l.substring(NAME_STR.length()));
                }
                if (l.contains(ADDRESS_STR)) {
                    addressList.add(l.substring(ADDRESS_STR.length()));
                }
                if (l.contains(CITY_STR)) {
                    cityList.add(l.substring(CITY_STR.length()));
                }
                if (l.contains(CAP_STR)) {
                    capList.add(l.substring(CAP_STR.length()));
                }
                if (l.contains(TEL_STR)) {
                    telList.add(l.substring(TEL_STR.length()));
                }
                if (l.contains(EMAIL_STR)) {
                    emailList.add(l.substring(EMAIL_STR.length()));
                }
                if (l.contains(ADMIN_STR)) {
                    adminList.add(Integer.parseInt(l.substring(ADMIN_STR.length())));
                }
            });
            //DA IMPLEMENTARE
            for (int i = 0; i < cfPIvaList.size(); i++) {
                /*staffList.add(company.getCinema().createFilm(titleList.get(i), lengthList.get(i), genreList.get(i),
                        over14List.get(i), threeDimList.get(i)));*/
            }
            return staffList;
        } catch (final IOException e) {
            return staffList;
        }
    }

    @Override
    public void saveClient() {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Clients> loadClient() {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public void saveProduct() {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Products> loadProduct() {
        return null;
        // TODO Auto-generated method stub
    }
}
