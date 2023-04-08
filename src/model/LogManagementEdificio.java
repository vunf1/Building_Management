package model;
import Helpers.Helper;

import java.io.Serializable;
import java.sql.*;

public class LogManagementEdificio implements Serializable {
    private int id;
    private Date data_inicio;
    private Date data_fim;
    private String description;
    private int id_edificio;
    private int id_funcionario;

    public LogManagementEdificio(Date data_inicio, Date data_fim, String description, int id_edificio, int id_funcionario) {
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.description = description;
        this.id_edificio = id_edificio;
        this.id_funcionario = id_funcionario;
    }

    public int getId() {
        return id;
    }

    public Date getDataInicio() {
        return data_inicio;
    }

    public Date getDataFim() {
        return data_fim;
    }

    public String getDescription() {
        return description;
    }

    public int getIdEdificio() {
        return id_edificio;
    }

    public int getIdFuncionario() {
        return id_funcionario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataInicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public void setDataFim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdEdificio(int id_edificio) {
        this.id_edificio = id_edificio;
    }

    public void setIdFuncionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public void save(Connection conn) throws SQLException {
        String query = "UPDATE log_management_edificios SET data_inicio = ?, data_fim = ?, description = ?, id_edificio = ?, id_funcionario = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setDate(1, data_inicio);
            preparedStatement.setDate(2, data_fim);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, id_edificio);
            preparedStatement.setInt(5, id_funcionario);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        }

    }

    public boolean create(Connection conn) throws SQLException {
        String query = "INSERT INTO log_management_edificios (data_inicio, data_fim, description, id_edificio, id_funcionario) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setDate(1, data_inicio);
            preparedStatement.setDate(2, data_fim);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, id_edificio);
            preparedStatement.setInt(5, id_funcionario);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Helper.green("Log management saved successfully.");
                return true;
            } else {
                Helper.purple("Log management already exists.");
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("id_edificio")) {
                Helper.red("Invalid Edificio ID.");
            } else if (errorMessage.contains("id_funcionario")) {
                Helper.red("Invalid Funcionario ID.");
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }

    public LogManagementEdificio getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM log_management_edificios WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Date dataInicio = rs.getDate("data_inicio");
                Date dataFim = rs.getDate("data_fim");
                String description = rs.getString("description");
                int idEdificio = rs.getInt("id_edificio");
                int idFuncionario = rs.getInt("id_funcionario");
                LogManagementEdificio data = new LogManagementEdificio(dataInicio, dataFim, description, idEdificio, idFuncionario);
                data.setId(id);
                return data;
            } else {
                return null;
            }
        }
    }
}
