package views;

import exception.DataInvalidaException;
import model.Data;

public class DataView {

    public static Data getData() {
        boolean flag;
        Data data = null;
        do {
            try {
                flag = false;
                System.out.println("Data");

                int dia = (int) GeneralView.getNumber("Dia");
                int mes = (int) GeneralView.getNumber("Mes");
                int ano = (int) GeneralView.getNumber("Ano");

                data = new Data(dia, mes, ano);
            } catch (DataInvalidaException e) {
                flag = true;
                System.out.println("Atenção: "+ e.getMessage());
            }
        } while (flag);
        return data;
    }
}
