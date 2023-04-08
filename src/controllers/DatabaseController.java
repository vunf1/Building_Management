package controllers;

import Helpers.Helper;
import model.DatabaseManager;
import model.Funcionario;

import java.io.Serializable;
import java.sql.Connection;

public class DatabaseController implements Serializable {

    String databaseName = "gestaocondominiosdb";
    DatabaseManager dbManager = new DatabaseManager(databaseName);
    public void main() {
        Connection conn = this.dbManager.getConnection();
        this.dbManager.createTables();
        this.dbManager.closeConnection();
        Helper.red("\nclose connection");

    }
}
