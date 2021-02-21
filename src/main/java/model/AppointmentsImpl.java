package model;

public class AppointmentsImpl implements Appointments {

    private String date;
    private String hour;

    public AppointmentsImpl(final String date, final String hour) {
        this.date = date;
        this.hour = hour;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public String getHour() {
        return this.hour;
    }

}
