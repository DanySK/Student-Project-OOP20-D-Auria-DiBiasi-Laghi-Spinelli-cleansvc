package controller.backupFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Company;
import controller.CompanyImpl;
import model.Appointments;
import model.AppointmentsImpl;

public class SaveAndLoadAppointments implements SaveAndLoad {
    private Company company = CompanyImpl.getInstance();
    private static final String FILE_APPOINTMENTS = "doc/Appointments.txt";
    private static final String DATE_STR = "DATE: ";
    private static final String HOUR_STR = "HOUR: ";

    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_APPOINTMENTS))) {
            for (final Appointments a : this.company.getAppointment()) {
                w.write(DATE_STR + a.getDate());
                w.newLine();
                w.write(HOUR_STR + a.getHour());
                w.newLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        final List<String> dateList = new ArrayList<>();
        final List<String> hourList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE_APPOINTMENTS))) {
            r.lines().forEach(l -> {
                if (l.contains(DATE_STR)) {
                    dateList.add(l.substring(DATE_STR.length()));
                }
                if (l.contains(HOUR_STR)) {
                    hourList.add(l.substring(HOUR_STR.length()));
                }
            });
            for (int i = 0; i < dateList.size(); i++) {
                this.company.addAppointment(new AppointmentsImpl(dateList.get(i), hourList.get(i)));
            }
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
