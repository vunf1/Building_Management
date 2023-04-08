package model;

import Helpers.Helper;

import java.io.Serializable;
import java.sql.Date;

import java.sql.*;
import java.util.Objects;

public class Funcionario implements Serializable {
    private int id;
    private String nome;
    private Date data_nascimento;
    private int contacto;
    private String titulo;
    private String genero;
    private int idcompany;

    public Funcionario(int id, String nome, Date data_nascimento, int contacto, String titulo, String genero,int idcompany) {

        this.id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.contacto = contacto;
        this.titulo = titulo;
        this.genero = genero;
        this.idcompany = idcompany;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento() {
        return data_nascimento.toString();
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getIdCompany() {
        return idcompany;
    }

    public void setIdCompany(int idCompany) {
        this.idcompany = idcompany;
    }
    public boolean save(Connection conn) throws SQLException {
        if (Objects.isNull(id)) {Helper.red("Can't update Funcionario ID is empty"); return false;}
        String query = "UPDATE funcionarios SET nome = ?, data_nascimento = ?, contacto = ?, titulo = ?, genero = ?, id_company = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setDate(2, data_nascimento);
            preparedStatement.setInt(3, contacto);
            preparedStatement.setString(4, titulo);
            preparedStatement.setString(5, genero);
            preparedStatement.setInt(6, idcompany);
            preparedStatement.setInt(7, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                Helper.green("Funcionario updated successfully.");
            } else {
                Helper.red("Failed to update Funcionario.");
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }
    public boolean create(Connection conn) throws SQLException {
        String query = "INSERT INTO funcionarios (id, nome, data_nascimento, contacto, titulo, genero, id_company) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE  contacto = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nome);
            preparedStatement.setDate(3, data_nascimento);
            preparedStatement.setInt(4, contacto);
            preparedStatement.setString(5, titulo);
            preparedStatement.setString(6, genero);
            preparedStatement.setInt(7, idcompany);
            preparedStatement.setString(8, nome);
            preparedStatement.setInt(9, contacto);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Helper.green("Funcionario saved successfully.");
                return true;
            } else {
                Helper.purple("Funcionario already exists.");
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("id_edificios")) {
                Helper.red("Invalid Edificio ID.");
            } else if (errorMessage.contains("id_company")) {
                Helper.red("Invalid Company ID.");
            } else if (errorMessage.contains("contacto")) {
                Helper.red("Contact already exists.");
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }


    public static Funcionario getById(Connection conn, int id) throws SQLException {
        if (Objects.isNull(id)) {throw new SQLException("Company id is empty");}
        String query = "SELECT * FROM funcionarios WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                Date data_nascimento = rs.getDate("data_nascimento");
                int contact = rs.getInt("contact");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                int id_company = rs.getInt("idcompany");
                Funcionario funcionario = new Funcionario(id, nome, data_nascimento, contact, titulo, genero, id_company);
                return funcionario;
            } else {
                return null;
            }
        }
    }
    public String infoDisplay() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + data_nascimento +
                ", contacto=" + contacto +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
