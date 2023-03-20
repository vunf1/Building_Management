package views;

import exception.DataInvalidaException;
import model.Contact;

public class ContactView {
    public static Contact getContact() {
        boolean flag;
        Contact contact = null;
        do {
            try {
                flag = false;
                int val = (int) GeneralView.getNumber("Contato");
                contact = new Contact(val);
            } catch (DataInvalidaException e) {
                flag = true;
                System.out.println("Atenção: "+ e.getMessage());
            }
        } while (flag);
        return contact;
    }
}
