package controllers;

import model.DatabaseManager;
import model.Funcionario;

import java.io.Serializable;

public class DatabaseController implements Serializable {

    String databaseName = "gestaocondominiosdb";
    DatabaseManager dbManager = new DatabaseManager(databaseName);
    public void main() {
        this.dbManager.setName(databaseName);
        this.dbManager.getConnection();
        this.dbManager.createTables();
        Funcionario[] data = this.dbManager.getFuncionariosData();
        //send this data to a view
        for (Funcionario dt : data // data to send out
        ) {
            System.out.println(dt.getNome()+" "
                    +dt.getDataNascimento()+" "
                    +dt.getTitulo()+" "
                    +dt.getGenero());
            }
        //this.dbManager.testData();
        this.dbManager.closeConnection();
        System.out.println("\ndatabase close connection");

    }
}
