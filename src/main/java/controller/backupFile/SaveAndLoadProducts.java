package controller.backupFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Products;
import model.users.Company;
import model.users.CompanyImpl;

public class SaveAndLoadProducts implements SaveAndLoad<List<Products>> {

    private Company company = CompanyImpl.getInstance();
    private static final String FILE_PRODUCTS = "doc/Products.txt";
    private static final String CODE_STR = "CODE: ";

    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_PRODUCTS))) {
            for (final Products p : this.company.getProduct()) {
                w.write(CODE_STR + p.getCode());
                w.newLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Products> load() {
        final List<Products> productsList = new ArrayList<>();
        final List<String> codeList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE_PRODUCTS))) {
            r.lines().forEach(l -> {
                if (l.contains(CODE_STR)) {
                    codeList.add(l.substring(CODE_STR.length()));
                }
            });
            for (int i = 0; i < codeList.size(); i++) {
                //codeList.add(new ProductsImpl(codeList.get(i)));
            }
            return productsList;
        } catch (final IOException e) {
            return productsList;
        }
    }
}
