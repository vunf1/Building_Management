package model;

import exception.DataInvalidaException;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact implements Serializable {
    private int contact;

    public Contact(int contact) {
        checkContact(contact);
    }
    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        checkContact(contact);
    }

    private void checkContact(int contact) throws DataInvalidaException {
        if (eValidContact(contact) == true) {
            this.contact = contact;
        } else {
            throw new DataInvalidaException("Contato invalido");
        }
    }

    private boolean eValidContact(int contact){
        Pattern p = Pattern.compile("^\\d{9}$");
        Matcher m = p.matcher(Integer.toString(contact));
        return (m.matches());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "Contato=" + contact +
                '}';
    }
}
