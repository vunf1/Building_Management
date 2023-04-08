package model;

import Helpers.Helper;

import java.io.Serializable;
import java.sql.*;

public class Valencia implements Serializable {
    private int id;
    private int id_edificio;
    private String nome;
    private float custo_anual;
    private String type;

    public Valencia(int id_edificio, String nome, float custo_anual, String type) {
        this.id_edificio = id_edificio;
        this.nome = nome;
        this.custo_anual = custo_anual;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getIdEdificio() {
        return id_edificio;
    }

    public String getNome() {
        return nome;
    }

    public float getCustoAnual() {
        return custo_anual;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEdificio(int id_edificio) {
        this.id_edificio = id_edificio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCustoAnual(float custo_anual) {
        this.custo_anual = custo_anual;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean update(Connection conn) throws SQLException {
        String query = "UPDATE valencias SET nome = ?, custo_anual = ?, type = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setFloat(2, custo_anual);
            preparedStatement.setString(3, type);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Valencia updated successfully.");
                return true;
            } else {
                System.out.println("Failed to update Valencia.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean create(Connection conn) throws SQLException {
        String query = "INSERT INTO valencias (id_edificio, nome, custo_anual, type) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id_edificio);
            preparedStatement.setString(2, nome);
            preparedStatement.setFloat(3, custo_anual);
            preparedStatement.setString(4, type);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Helper.green("Valencia saved successfully.");
                return true;
            } else {
                Helper.purple("Valencia already exists.");
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("id_edificio")) {
                Helper.red("Invalid Edificio ID.");
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }

    public static Valencia getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM valencias WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id_edificio = rs.getInt("id_edificios");
                String nome = rs.getString("nome");
                float custo_anual = rs.getFloat("custo_anual");
                String type = rs.getString("type");
                Valencia data = new Valencia(id_edificio, nome, custo_anual, type);
                data.setId(id);
                return data;

            } else {
                return null;
            }
        }
    }
}

