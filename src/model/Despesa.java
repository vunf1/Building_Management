package model;

import Helpers.Helper;

import java.io.Serializable;
import java.sql.*;

public class Despesa  implements Serializable {
    private int id;
    private int id_edificio;
    private float valor;
    private String description;
    private String type;
    private Date date_report;

    public Despesa(int id_edificio, float valor, String description, String type, Date date_report) {
        this.id_edificio = id_edificio;
        this.valor = valor;
        this.description = description;
        this.type = type;
        this.date_report = date_report;
    }

    public int getId() {
        return id;
    }

    public int getIdEdificio() {
        return id_edificio;
    }

    public float getValor() {
        return valor;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Date getDateReport() {
        return date_report;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEdificio(int id_edificio) {
        this.id_edificio = id_edificio;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateReport(Date date_report) {
        this.date_report = date_report;
    }

    public boolean save(Connection conn) throws SQLException {
        String query = "UPDATE despesas SET valor = ?, description = ?, type = ?, date_report = ? WHERE id_edificios = ? AND id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setFloat(1, valor);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, type);
            preparedStatement.setDate(4, date_report);
            preparedStatement.setInt(5, id_edificio);
            preparedStatement.setInt(6, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Helper.green("Despesa updated successfully.");
                return true;
            } else {
                Helper.purple("Failed to update Despesa.");
                return false;
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }

    public boolean create(Connection conn) throws SQLException {
        String query = "INSERT INTO despesas (id, id_edificio, valor, description, type, date_report) " +
                "VALUES (?, ?, ?, ?, ?, ?) ";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id_edificio);
            preparedStatement.setFloat(3, valor);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, type);
            preparedStatement.setDate(6, date_report);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Helper.green("Despesa created successfully. ID: "+ id);
                return true;
            } else {
                Helper.purple("Despesa not created. Try Again");
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("id")) {
                Helper.red("Invalid ID.");
            } else if (errorMessage.contains("id_edificio")) {
                Helper.red("Invalid Edificio ID.");
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }

    public static Despesa getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM despesas WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id_edificio = rs.getInt("id_edificios");
                float valor = rs.getFloat("valor");
                String description = rs.getString("description");
                String type = rs.getString("type");
                Date date_report = rs.getDate("date_report");
                Despesa data = new Despesa(id_edificio, valor, description, type, date_report);
                data.setId(id);
                return data;
            } else {
                return null;
            }
        }
    }
}