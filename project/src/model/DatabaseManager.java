package model;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import model.Funcionario;

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
            System.out.println("\n*connection*");
            conn = DriverManager.getConnection(DB_URL,
                    DB_USER,
                    DB_PASSWORD);
            createDatabaseIfNotExists(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
                System.out.println("\ndatabase exists.");
            } else {
                System.out.println("\ndatabase does not exist. creating . . . ");
                createDatabaseIfNotExists(conn);
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
            e.printStackTrace();
        }
        return exists;
    }
    private static void createDatabaseIfNotExists(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            System.out.println("\n Creating Database if NOT EXISTS");

            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement.executeUpdate(createDatabaseQuery);

            System.out.println("\n Database Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTables() {
        try(Statement statement = conn.createStatement()) {

            String createFuncionariosTable = "CREATE TABLE IF NOT EXISTS funcionarios (" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    nome VARCHAR(255) NOT NULL," +
                    "    data_nascimento DATE," +
                    "    contacto INT," +
                    "    titulo VARCHAR(255)," +
                    "    genero VARCHAR(255)" +
                    ")";

            String createEdificiosTable = "CREATE TABLE IF NOT EXISTS edifıcios "+
                    "(id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    horario_abertura TIME," +
                    "    horario_fecho TIME," +
                    "    morada VARCHAR(255)," +
                    "    valor_base FLOAT," +
                    "    id_funcionario INT," +
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
                    "    id_edificios INT," +
                    "    valor FLOAT," +
                    "    description VARCHAR(255)," +
                    "    type VARCHAR(255)," +
                    "    date_report DATE,"+
                    "    FOREIGN KEY (id_edificios) REFERENCES edificios(id)" +
                    ")";

            String createValenciasTable = "CREATE TABLE IF NOT EXISTS valencias (" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    id_edificios INT," +
                    "    nome VARCHAR(255)," +
                    "    custo_anual FLOAT," +
                    "    type VARCHAR(255)," +
                    "    FOREIGN KEY (id_edificios) REFERENCES edificios(id)" +
                    ")";

            String createLogManagementTable = "CREATE TABLE IF NOT EXISTS log_management_edificios (" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    data_inicio DATE," +
                    "    data_fim DATE," +
                    "    id_edificio INT," +
                    "    id_funcionario INT," +
                    "    FOREIGN KEY (id_edificio) REFERENCES edificios(id)," +
                    "    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)" +
                    ")";
            statement.executeUpdate(createFuncionariosTable);
            //statement.executeUpdate(createEdificiosTable);
            /*statement.executeUpdate(createFaccoesTable);
            statement.executeUpdate(createDespesasTable);
            statement.executeUpdate(createValenciasTable);
            statement.executeUpdate(createLogManagementTable);*/

            System.out.println("Tables created successfully.");
            insertStaticData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStaticData() {
        /*Query verifica se os dados que vai inserir ja existem na tabela, se existem simplesmente ignora, sem accao implementada*/
        String insertFuncionariosQuery = "INSERT INTO funcionarios (nome, data_nascimento, contacto, titulo, genero) " +
                "SELECT ?, ?, ?, ?, ? WHERE NOT EXISTS " +
                "(SELECT * FROM funcionarios WHERE nome = ? AND data_nascimento = ? AND contacto = ? AND titulo = ? AND genero = ?)";
        Funcionario parametersFuncionarioMock = new Funcionario ("Joana Delfina", Date.valueOf("1985-04-1"), 123456789, "Sra.", "female");
        Funcionario parametersFuncionarioMock2 = new Funcionario ("Joao Alberto", Date.valueOf("2000-02-28"), 123456789, "Sr.", "male");
        insertFuncionario(insertFuncionariosQuery, parametersFuncionarioMock);
        insertFuncionario(insertFuncionariosQuery, parametersFuncionarioMock2);

        String insertEdificiosQuery = "INSERT INTO edificios (horario_abertura, horario_fecho, morada, valor_base, id_funcionario) VALUES (?, ?, ?, ?, ?)";
        Object[] parametersEdificiosMock ={"9","21","123 Rua da Perdiz",55000.50,1};
        //insertEdificios(insertEdificiosQuery, parametersEdificiosMock);

        String insertFaccoesQuery = "INSERT INTO faccoes (id_edificios, valor_base, permilagem, nome_proprietario, identificador, piso, contacto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] parametersFaccoesMock ={ 1, 1000.00, 0.2, "Antonio Menezes","A1","2-DTO",987654321};
        //insertFaccoes(insertFaccoesQuery, parametersFaccoesMock);

        String insertDespesasQuery = "INSERT INTO despesas (id_edificios, valor, description, type, date_report) VALUES (?, ?, ?, ?, ?)";
        Object[] parametersDespesasMock ={1, 150.00, "Eletricidade", "ordinaria", "2022-12-31"};
        //insertDespesas(insertDespesasQuery, parametersDespesasMock);

        String insertValenciasQuery = "INSERT INTO valencias (id_edificios, nome, custo_anual, type) VALUES (?, ?, ?, ?)";
        Object[] parametersValenciasMock ={1, "Piscina", 3000.00, "extraordinaria"};
        //insertValencias(insertValenciasQuery, parametersValenciasMock);

        String insertLogManagementEdificiosQuery = "INSERT INTO log_management_edificios (data_inicio, data_fim, id_edificio, id_funcionario) VALUES (?, ?, ?, ?)";
        Object[] parametersLogManagementMock ={"2022-01-01","2023-12-31",1,1};
        //insertLogManagement(insertLogManagementEdificiosQuery, parametersLogManagementMock);
    }

    /**
     * Insere um novo registo de Funcionário na base de dados utilizando a Query e parâmetros fornecidos.
     *
     * @param Query a consulta SQL a executar para a operação de inserção
     * @param parameters um array de parâmetros para definir na declaração preparada
     *                   parameters[0] - o nome do funcionário (String)
     *                   parameters[1] - a data de nascimento do funcionário (java.util.Date)
     *                   parameters[2] - o número de contato do funcionário (int)
     *                   parameters[3] - titulo a usar antes do nome, por exemplo: Sr, Dtr, etc (String)
     *                   parameters[4] - genero (String)
     * @throws SQLException ocorre um erro de acesso à base de dados ou se a query falhar
     */
    public void insertFuncionario(String Query, Funcionario parameters){

        try (PreparedStatement preparedStatement = conn.prepareStatement(Query)) {
            preparedStatement.setString(1, parameters.getNome());
            preparedStatement.setDate(2, parameters.getDataNascimento());
            preparedStatement.setInt(3, parameters.getContacto());
            preparedStatement.setString(4, parameters.getTitulo());
            preparedStatement.setString(5, parameters.getGenero());

            preparedStatement.setString(6, parameters.getNome());
            preparedStatement.setDate(7, parameters.getDataNascimento());
            preparedStatement.setInt(8, parameters.getContacto());
            preparedStatement.setString(9, parameters.getTitulo());
            preparedStatement.setString(10, parameters.getGenero());
            preparedStatement.executeUpdate();

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {//trigger false de alguma maneira, mesmo apos DROP table > data inserida  mostra failed....
                System.out.println("Funcionarios inserted successfully.");
            } else {
                System.out.println("Failed to insert Funcionarios, Already inserted?.");
            }
        }
        catch (SQLException e) {
        e.printStackTrace();
        }
    }
    /**
     * Serialise a data, so funcionario esta "completo"
     * */
    public void insertEdificios(String Query, Object[]  parameters){
        /*try (PreparedStatement preparedStatement = conn.prepareStatement(Query)) {
            preparedStatement.setTime(1, java.sql.Time.valueOf(parameters[0].toString()));
            preparedStatement.setTime(2, java.sql.Time.valueOf(parameters[1].toString()));
            preparedStatement.setString(3, parameters[2].toString());
            preparedStatement.setDouble(4, (double)parameters[3]);
            preparedStatement.setInt(5, (int)parameters[4]);
            preparedStatement.executeUpdate();

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Edificios inserted successfully.");
            } else {
                System.out.println("Failed to insert Edificios.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
    public void insertFaccoes(String Query, Object[] parameters){
       /* try (PreparedStatement preparedStatement = conn.prepareStatement(Query)) {
            preparedStatement.setInt(1, (int)parameters[0]);
            preparedStatement.setDouble(2, (double)parameters[1]);
            preparedStatement.setDouble(3, (double)parameters[2]);
            preparedStatement.setString(4, parameters[3].toString());
            preparedStatement.setString(5, parameters[4].toString());
            preparedStatement.setString(6, parameters[5].toString());
            preparedStatement.setInt(7, (int)parameters[6]);
            preparedStatement.executeUpdate();

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Faccoes inserted successfully.");
            } else {
                System.out.println("Failed to insert Faccoes.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
    public void insertDespesas(String Query, Object[] parameters){
       /* try (PreparedStatement preparedStatement = conn.prepareStatement(Query)) {
            preparedStatement.setInt(1, (int)parameters[0]);
            preparedStatement.setDouble(2, (double)parameters[1]);
            preparedStatement.setString(3, parameters[2].toString());
            preparedStatement.setString(4, parameters[3].toString());
            preparedStatement.executeUpdate();

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Despesas inserted successfully.");
            } else {
                System.out.println("Failed to insert Despesas.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
    public void insertValencias(String Query, Object[] parameters){
        /*try (PreparedStatement preparedStatement = conn.prepareStatement(Query)) {
            preparedStatement.setInt(1, (int)parameters[0]);
            preparedStatement.setString(2, parameters[1].toString());
            preparedStatement.setDouble(3, (double)parameters[2]);
            preparedStatement.setString(4, parameters[3].toString());
            preparedStatement.executeUpdate();

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Valencias inserted successfully.");
            } else {
                System.out.println("Failed to insert Valencias.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
    public void insertLogManagement(String Query, Object[] parameters){
        /*try (PreparedStatement preparedStatement = conn.prepareStatement(Query)) {
            preparedStatement.setString(1, parameters[0].toString());
            preparedStatement.setString(2, parameters[1].toString());
            preparedStatement.setInt(3, (int)parameters[2]);
            preparedStatement.setInt(4, (int)parameters[3]);
            preparedStatement.executeUpdate();

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("LogManagement inserted successfully.");
            } else {
                System.out.println("Failed to insert LogManagement.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
   public Funcionario[] getFuncionariosData(){
       try(Statement statement = conn.createStatement()) {
           Funcionario[] datas = new Funcionario[2];// grab number of rows and make this number dinamic
           int count = 0;
           String query = "SELECT " +
                   "nome, " +
                   "data_nascimento, " +
                   "contacto, " +
                   "titulo, " +
                   "genero " +
                   "from funcionarios " +
                   "GROUP BY id ";
           try {
               ResultSet dt = statement.executeQuery(query);
               while (dt.next()) {
                   datas[count] = new Funcionario(
                           dt.getString(1),
                           dt.getDate(2),
                           dt.getInt(3),
                           dt.getString(4),
                           dt.getString(5));
                   count++;

               }
           } finally {
               statement.close();
           }
           return datas;
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return new Funcionario[0];

   }
    public void closeConnection() {
        try {
            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}