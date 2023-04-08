package model;

import Helpers.Helper;

import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

public class Company implements Serializable {
    private int id;
    private String nome;
    private String address;
    private int contact;
    private String email;

    public Company(int id, String nome, String address, int contact, String email) {
        this.id = id;
        this.nome = nome;
        this.address = address;
        this.contact = contact;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAddress() {
        return address;
    }

    public int getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean update(Connection conn) throws SQLException {
        if (Objects.isNull(id)) {Helper.red("Can't update Company id is empty"); return false;}
        String query = "UPDATE company SET nome = ?, address = ?, contact = ?, email = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, contact);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) { Helper.green("Company updated successfully.");return true;}
            else {Helper.purple("Failed to update Company.");return false;}
        }
        catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }

    public boolean create(Connection conn) throws SQLException {
        String query = "INSERT INTO company (id, nome, address, contact, email) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, address);
            preparedStatement.setInt(4, contact);
            preparedStatement.setString(5, email);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Helper.green("Company saved successfully with IDS: " + id );
                Helper.red("Save CompanyID, required to access data. ID: " + id );
                return true;
            } else {
                Helper.purple("Company already exists.");
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("id")) {
                Helper.red("ID already exists.");
            } else if (errorMessage.contains("contacto")) {
                Helper.red("Contacto already exists in database. Try other number;");
            } else if (errorMessage.contains("email")) {
                Helper.red("Email already exists in database. Try other email.");
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }

    public Company getById(Connection conn, int id) throws SQLException {
        if (Objects.isNull(id)) {throw new SQLException("Company id is empty");}
        String query = "SELECT * FROM company WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String address = rs.getString("address");
                int contact = rs.getInt("contact");
                String email = rs.getString("email");
                this.id= id;
                this.nome= nome;
                this.contact= contact;
                this.address= address;
                this.email= email;
                //Company company = new Company(id, nome, address, contact, email);
                //return company;
            } else {
                return null;
            }
        }
        catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return null;
    }

}