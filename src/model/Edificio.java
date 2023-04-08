package model;
import Helpers.Helper;

import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

public class Edificio  implements Serializable {
    private int id;
    private Time horario_abertura;
    private Time horario_fecho;
    private String morada;
    private float valor_base;
    private int id_funcionario;
    private int id_company;

    public Edificio(int id, Time horario_abertura, Time horario_fecho, String morada, float valor_base, int id_funcionario, int id_company) {
        this.id = id;
        this.horario_abertura = horario_abertura;
        this.horario_fecho = horario_fecho;
        this.morada = morada;
        this.valor_base = valor_base;
        this.id_funcionario = id_funcionario;
        this.id_company = id_company;
    }

    public int getId() {
        return id;
    }

    public Time getHorarioAbertura() {
        return horario_abertura;
    }

    public Time getHorarioFecho() {
        return horario_fecho;
    }

    public String getMorada() {
        return morada;
    }

    public float getValorBase() {
        return valor_base;
    }

    public int getIdFuncionario() {
        return id_funcionario;
    }

    public int getIdCompany() {
        return id_company;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHorarioAbertura(Time horario_abertura) {
        this.horario_abertura = horario_abertura;
    }

    public void setHorarioFecho(Time horario_fecho) {
        this.horario_fecho = horario_fecho;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setValorBase(float valor_base) {
        this.valor_base = valor_base;
    }

    public void setIdFuncionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public void setIdCompany(int id_company) {
        this.id_company = id_company;
    }

    public boolean save(Connection conn) throws SQLException {
        if (Objects.isNull(id)) {Helper.red("Can't update Edifício, ID is empty"); return false;}
        String query = "UPDATE edifícios SET horario_abertura = ?, horario_fecho = ?, morada = ?, " +
                "valor_base = ?, id_funcionario = ?, id_company = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setTime(1, horario_abertura);
            preparedStatement.setTime(2, horario_fecho);
            preparedStatement.setString(3, morada);
            preparedStatement.setFloat(4, valor_base);
            preparedStatement.setInt(5, id_funcionario);
            preparedStatement.setInt(6, id_company);
            preparedStatement.setInt(7, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Helper.green("Edifício updated successfully.");
                return true;
            } else {
                Helper.purple("Failed to update Edifício.");
                return false;
            }
        }
        catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }
    public boolean create(Connection conn) throws SQLException {
        String query = "INSERT INTO edifícios (id, horario_abertura, horario_fecho, morada, " +
                "valor_base, id_funcionario, id_company) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setTime(2, horario_abertura);
            preparedStatement.setTime(3, horario_fecho);
            preparedStatement.setString(4, morada);
            preparedStatement.setFloat(5, valor_base);
            preparedStatement.setInt(6, id_funcionario);
            preparedStatement.setInt(7, id_company);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Helper.green("Edifício saved successfully with ID: " + id );
                return true;
            } else {
                Helper.purple("Failed to save Edifício.");
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("id")) {
                Helper.red("Edifício ID already exists.");
            } else if (errorMessage.contains("id_funcionario")) {
                Helper.red("Invalid Funcionário ID.");
            } else if (errorMessage.contains("id_company")) {
                Helper.red("Invalid Company ID.");
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }

    public static Edificio getById(Connection conn, int id_edificio, int id_company) throws SQLException {
        String query;
        if (Objects.isNull(id_company))
        {  query = "SELECT * FROM edifícios WHERE id = ?";}
        else {query = "SELECT * FROM edifícios WHERE id-company = ?";}

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id_edificio);
            preparedStatement.setInt(2, id_company);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                Time horarioAbertura = rs.getTime("horario_abertura");
                Time horarioFecho = rs.getTime("horario_fecho");
                String morada = rs.getString("morada");
                float valorBase = rs.getFloat("valor_base");
                int idFuncionario = rs.getInt("id_funcionario");
                id_company = rs.getInt("id_company");
                return new Edificio(id_edificio, horarioAbertura, horarioFecho, morada, valorBase, idFuncionario, id_company);
            } else {
                return null;
            }
        }
    }
}