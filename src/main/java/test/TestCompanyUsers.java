package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import model.users.ClientsImpl;
import model.users.StaffImpl;
import controller.CompanyImpl;

public class TestCompanyUsers {

    private static CompanyImpl company = CompanyImpl.getInstance();
    private static final String CF = "DBSVSS96S62D000U";
    private static final String NAME = "Vanessa Di Biasi";
    private static final String ADDRESS = "Via Roma 44";
    private static final String CITY = "Roma";
    private static final int CAP = 12345;
    private static final String TEL = "3331234567";
    private static final String EMAIL = "vanessa@unibo.it";
    private static final Boolean ADMIN = true;
    private static final int MQ = 200;
    private ClientsImpl client = null;
    private StaffImpl staff = null;

    @Before
    public void testClientsImpl() {
        client = new ClientsImpl(CF, NAME, ADDRESS, CITY, CAP, TEL, EMAIL, MQ);
    }

    @Before
    public void testStaffImpl() {
        staff = new StaffImpl(CF, NAME, ADDRESS, CITY, CAP, TEL, EMAIL, ADMIN);
    }

    @Test
    public void testClient() {
        assertTrue(client.getCFPIVA().equals(CF));
        assertTrue(client.getName().equals(NAME));
        assertTrue(client.getAddress().equals(ADDRESS));
        assertTrue(client.getCity().equals(CITY));
        assertTrue(Integer.compare(client.getCAP(), CAP) == 0);
        assertTrue(client.getTel().equals(TEL));
        assertTrue(client.getEmail().equals(EMAIL));
        assertTrue(Integer.compare(client.getMqStructure(), MQ) == 0);
    }

    @Test
    public void testStaff() {
        assertTrue(staff.getCFPIVA().equals(CF));
        assertTrue(staff.getName().equals(NAME));
        assertTrue(staff.getAddress().equals(ADDRESS));
        assertTrue(staff.getCity().equals(CITY));
        assertTrue(Integer.compare(staff.getCAP(), CAP) == 0);
        assertTrue(staff.getTel().equals(TEL));
        assertTrue(staff.getEmail().equals(EMAIL));
        assertTrue(staff.isAdmin());
    }

    @Test
    public void testCompanyClients() {
        assertTrue(company.getClients().isEmpty());
        company.addClient(client);
        assertFalse(company.getClients().isEmpty());
        assertTrue(company.getClients().get(0).equals(company.searchClient(CF).get()));
        company.removeClient(client);
        assertTrue(company.getClients().isEmpty());
    }

    @Test
    public void testCompanyStaff() {
        assertTrue(company.getStaff().isEmpty());
        company.addStaff(staff);
        assertFalse(company.getStaff().isEmpty());
        assertTrue(company.getStaff().get(0).equals(company.searchStaffbyCF(CF).get()));
        assertTrue(company.getStaff().get(0).equals(company.searchStaffbyEmail(EMAIL).get()));
        company.removeStaff(staff);
        assertTrue(company.getStaff().isEmpty());
    }
}
