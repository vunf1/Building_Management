package model;
import Helpers.Helper;

import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

public class DatabaseManager implements Serializable {
    private static String DATABASE_NAME = "gestaocondominiosdb";
    private static String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "aDmin.123";
    private Connection conn;
    public void setName(String name) {
        DATABASE_NAME = name;
        DB_URL = DB_URL+name;
    }

    public DatabaseManager(String dbName) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to the database using static name if null encounter
            if (Objects.isNull(dbName)) setName("gestaocondominiosdb");
            conn = getConnection();
            System.out.println("\n*DB Connected\n*");
            createDatabaseIfNotExists(conn);
        } catch (ClassNotFoundException e) {
            Helper.red(e.getMessage());
        }
    }
    public Connection getConnection() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the MySQL server
            conn = DriverManager.getConnection(DB_URL,
                    DB_USER,
                    DB_PASSWORD);

            // Check if the database exists
            if (databaseExists(DATABASE_NAME)) {
                Helper.green("\nDatabase exists.");
            } else {
                Helper.red("\nDatabase does not exist. trying to create . . . ");
                createDatabaseIfNotExists(conn);
                insertStaticData(conn);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public boolean databaseExists(String databaseName) {
        boolean exists = false;
        try (Statement statement = conn.createStatement()) {
            String checkDatabaseQuery = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + databaseName + "'";
            ResultSet resultSet = statement.executeQuery(checkDatabaseQuery);

            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return exists;
    }
    private static void createDatabaseIfNotExists(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            Helper.white("\n Creating Database if NOT EXISTS");

            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement.executeUpdate(createDatabaseQuery);

            Helper.green("\n Database Created");
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
    }
    public void createTables() {
        try(Statement statement = conn.createStatement())
        {
            String createCompanyTable = "CREATE TABLE IF NOT EXISTS company (" +
                    "    id  INT PRIMARY KEY," +
                    "    nome VARCHAR(255) NOT NULL," +
                    "    address VARCHAR(255)," +
                    "    contacto INT," +
                    "    email VARCHAR(255)" +
                    ")";

            String createFuncionariosTable = "CREATE TABLE IF NOT EXISTS funcionarios (" +
                    "    id INT PRIMARY KEY," +
                    "    nome VARCHAR(255) NOT NULL," +
                    "    data_nascimento DATE," +
                    "    contacto INT," +
                    "    titulo VARCHAR(255)," +
                    "    genero VARCHAR(255)," +
                    "    id_company INT," +
                    "    FOREIGN KEY (id_company) REFERENCES company(id)" +
                    ")";

            String createEdificiosTable = "CREATE TABLE IF NOT EXISTS edificios "+
                    "(id INT PRIMARY KEY," +
                    "    horario_abertura TIME," +
                    "    horario_fecho TIME," +
                    "    morada VARCHAR(255)," +
                    "    valor_base FLOAT," +
                    "    id_funcionario INT," +
                    "    id_company INT," +
                    "    FOREIGN KEY (id_company) REFERENCES company(id)," +
                    "    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)" +
                    ")";

            String createFaccoesTable = "CREATE TABLE IF NOT EXISTS faccoes  "+
                    "(" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    id_edificios INT," +
                    "    valor_base FLOAT," +
                    "    permilagem FLOAT," +
                    "    nome_proprietario VARCHAR(255)," +
                    "    identificador VARCHAR(255)," +
                    "    piso VARCHAR(255)," +
                    "    contacto INT," +
                    "    FOREIGN KEY (id_edificios) REFERENCES edificios(id)" +
                    ")";

            String createDespesasTable = "CREATE TABLE IF NOT EXISTS despesas (" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    id_edificio INT," +
                    "    valor FLOAT," +
                    "    description VARCHAR(255)," +
                    "    type VARCHAR(255)," +
                    "    date_report DATE,"+
                    "    FOREIGN KEY (id_edificio) REFERENCES edificios(id)" +
                    ")";

            String createValenciasTable = "CREATE TABLE IF NOT EXISTS valencias (" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    id_edificio INT," +
                    "    nome VARCHAR(255)," +
                    "    custo_anual FLOAT," +
                    "    type VARCHAR(255)," +
                    "    FOREIGN KEY (id_edificio) REFERENCES edificios(id)" +
                    ")";

            String createLogManagementTable = "CREATE TABLE IF NOT EXISTS log_management_edificios (" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    data_inicio DATE," +
                    "    data_fim DATE," +
                    "    description VARCHAR(255)," +
                    "    id_edificio INT," +
                    "    id_funcionario INT," +
                    "    FOREIGN KEY (id_edificio) REFERENCES edificios(id)," +
                    "    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)" +
                    ")";

            statement.executeUpdate(createCompanyTable);
            statement.executeUpdate(createFuncionariosTable);
            statement.executeUpdate(createEdificiosTable);
            statement.executeUpdate(createFaccoesTable);
            statement.executeUpdate(createDespesasTable);
            statement.executeUpdate(createValenciasTable);
            statement.executeUpdate(createLogManagementTable);

            Helper.green("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStaticData(Connection conn) throws SQLException {
        int id_company = 1100;
        int id_company2 = 1111;
        int id_funcionario = 2200;
        int id_funcionario2 = 2222;
        int id_edificio = 3300;
        int id_edificio2 = 3333;
// Company
        Company parametersCompanyMock = new Company (id_company,"FriZal","Rua do La", 911234567,"no-reply@company.com");
        parametersCompanyMock.create(conn);

// Funcionarios
       /*Funcionario parametersFuncionarioMock = new Funcionario (id_funcionario,"Joana Delfina",
               java.sql.Date.valueOf("1/4/1995"), 123456789, "Sra.", "female",id_company);
       Funcionario parametersFuncionarioMock2 = new Funcionario (id_funcionario2,"Joao Alberto",
               java.sql.Date.valueOf("28/2/2001"), 223456987, "Sr.", "male",id_company);
       parametersFuncionarioMock.create(conn);
       parametersFuncionarioMock2.create(conn);*/

// Edificios
        /*Edificio parametersEdificiosMock =new Edificio(id_edificio,Time.valueOf("14:00:00"),
                Time.valueOf("14:30:00"),"123 Rua da Perdiz", 55000.50f, id_funcionario,id_company);
        parametersEdificiosMock.create(conn);*/

// Faccoes
        /*Faccao parametersFaccoesMock = new Faccao(id_edificio, 1000.00f, 0.2f, "Antonio Menezes",
                "A1","2-DTO",987654321);
        parametersFaccoesMock.create(conn);*/

// Despesas
        /*Despesa parametersDespesasMock =new Despesa(id_edificio, 150.00f, "Eletricidade",
                "ordi", Date.valueOf("2022-12-31"));
        parametersDespesasMock.create(conn);*/

// Valencias
        /*Valencia parametersValenciasMock = new Valencia(id_edificio, "Piscina", 3000.00f, "extra");
        parametersValenciasMock.create(conn);*/

// LogManagement Edificios
        /*LogManagementEdificio parametersLogManagementMock = new LogManagementEdificio(Date.valueOf("2022-01-01"),
                Date.valueOf("2023-12-31"),"Good manager, change of career make person leave.",
                id_edificio, id_funcionario);
        parametersLogManagementMock.create(conn);*/
    }
    public void closeConnection() {
        try {
            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
    }

}