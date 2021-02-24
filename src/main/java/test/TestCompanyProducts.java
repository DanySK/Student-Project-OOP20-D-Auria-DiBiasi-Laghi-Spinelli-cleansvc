package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import controller.CompanyImpl;
import controller.ProcessImpl;
import model.ProductsImpl;
import model.step.enumerations.StepType;

public class TestCompanyProducts {

    private static ProcessImpl process = ProcessImpl.getInstance();
    private static CompanyImpl company = CompanyImpl.getInstance();
    private static final String CODE = "A01";
    private static final StepType STEP = process.getStepTypeList().get(0);
    private static final String NAME = "CleanerPRO";
    private static final String DESCRIPTION = "Pulizia ordinaria";
    private static final double PRICE = 15.5;
    private static final double USAGE = 30.0;
    private ProductsImpl product = null;

    @Before
    public void testProductImpl() {
        product = new ProductsImpl(CODE, STEP, NAME, DESCRIPTION, PRICE, USAGE);
    }

    @Test
    public void testProduct() {
        assertTrue(product.getCode().equals(CODE));
        assertTrue(product.getName().equals(NAME));
        assertTrue(product.getDescription().equals(DESCRIPTION));
        assertTrue(product.getStepType().equals(STEP));
        assertTrue(Double.compare(product.getPricePerLitre(), PRICE) == 0);
        assertTrue(Double.compare(product.getLitersPer500Mq(), USAGE) == 0);
    }

    @Test
    public void testCompany() throws IOException {
        assertTrue(company.getProducts().isEmpty());
        company.addProduct(product);
        assertFalse(company.getProducts().isEmpty());
        assertTrue(company.getProducts().get(0).equals(company.searchProduct(CODE).get()));
        company.removeProduct(product);
        assertTrue(company.getProducts().isEmpty());
    }
}
