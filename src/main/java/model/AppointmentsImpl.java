package model;

import model.users.Clients;

public class AppointmentsImpl implements Appointments {

    private String date;
    private String hour;
    private Clients client;

    public AppointmentsImpl(final String date, final String hour, final Clients client) {
        this.date = date;
        this.hour = hour;
        this.client = client;
    }
    /**
     * 
     */

    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * 
     */

    @Override
    public String getHour() {
        return this.hour;
    }

    @Override
    public Clients getClient() {
        return this.client;
    }

}
