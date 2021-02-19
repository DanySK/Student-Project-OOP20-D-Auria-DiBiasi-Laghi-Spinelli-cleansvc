package controller.backupFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Products;
import controller.Company;
import controller.CompanyImpl;

public class SaveAndLoadProducts implements SaveAndLoad {

    private Company company = CompanyImpl.getInstance();
    private static final String FILE_PRODUCTS = "doc/Products.txt";
    private static final String CODE_STR = "CODE: ";

    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_PRODUCTS))) {
            for (final Products p : this.company.getProduct()) {
                w.write(CODE_STR + p.getName());
                w.newLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        final List<String> codeList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE_PRODUCTS))) {
            r.lines().forEach(l -> {
                if (l.contains(CODE_STR)) {
                    codeList.add(l.substring(CODE_STR.length()));
                }
            });
            for (int i = 0; i < codeList.size(); i++) {
                //this.company.addProduct(new ProductImpl(codeList.get(i)));
            }
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
