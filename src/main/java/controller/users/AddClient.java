package controller.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import model.users.Clients;
import model.users.ClientsImpl;
import model.users.Company;
import model.users.CompanyImpl;
import view.ClientsView;

public class AddClient {
    
    private final ClientsView clientsview;
    private final Company company;
    
    public AddClient(final ClientsView view) {
       this.clientsview = view;
       this.clientsview.submitClient(new submitListener());
       this.company = CompanyImpl.getSingleton();
       
    }
    
    private class submitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                addC();
            } catch (ParseException pe) {
                //error message
            }
        }
    }

    public void addC() throws ParseException {
        final Clients c = new ClientsImpl(this.clientsview.getCFPIVA(),this.clientsview.getName(), this.clientsview.getAddress(), this.clientsview.getCity(), this.clientsview.getCAP(), this.clientsview.gettel(), this.clientsview.getemail(), this.clientsview.getmq());
        this.company.addClient(c);
        /*
         * try {
         *      CompanyImpl.getInstance().saveClient();
         * } catch (IOException e) {
         *      //error message
         * }
         */
    }

}
