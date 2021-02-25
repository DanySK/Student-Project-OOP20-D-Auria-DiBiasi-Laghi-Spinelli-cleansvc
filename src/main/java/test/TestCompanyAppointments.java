package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import model.AppointmentsImpl;
import model.users.Clients;
import model.users.ClientsImpl;
import controller.CompanyImpl;

public class TestCompanyAppointments {

    private static CompanyImpl company = CompanyImpl.getInstance();
    private String DATE = "2021-04-30";
    private String HOUR = "15:00";
    private final Clients CLIENT = new ClientsImpl("01772160402", "Emporio da Mario", "Via Antonio Montanari 11/13", "Meldola", 47014, "3467529933", "emporiodamario@libero.it", 150);
    private double TIME = 95.0;
    private double EARN = 102.0;
    private AppointmentsImpl appointment = null;

    @Before
    public void testAppointmentsImpl() {
        appointment = new AppointmentsImpl(DATE, HOUR, CLIENT, TIME, EARN);
    }

    @Test
    public void testAppointment() {
        assertTrue(appointment.getDate().equals(DATE));
        assertTrue(appointment.getHour().equals(HOUR));
        assertTrue(appointment.getClient().equals(CLIENT));
        assertTrue(Double.compare(appointment.getTime(), TIME) == 0);
        assertTrue(Double.compare(appointment.getEarn(), EARN) == 0);
    }

    @Test
    public void testCompanyAppointments() {
        assertTrue(company.getAppointment().isEmpty());
        company.addAppointment(appointment);
        assertFalse(company.getAppointment().isEmpty());
        assertTrue(company.getAppointment().get(0).equals(company.searchAppointment(DATE, HOUR).get()));
        company.removeAppointment(appointment);
        assertTrue(company.getAppointment().isEmpty());
    }
}
