package model;

import Helpers.Helper;

import java.io.Serializable;
import java.sql.*;

public class Faccao  implements Serializable {
    private int id;
    private int id_edificio;
    private float valor_base;
    private float permilagem;
    private String nome_proprietario;
    private String identificador;
    private String piso;
    private int contacto;

    public Faccao(int id_edificio, float valor_base, float permilagem, String nome_proprietario, String identificador, String piso, int contacto) {
        this.id_edificio = id_edificio;
        this.valor_base = valor_base;
        this.permilagem = permilagem;
        this.nome_proprietario = nome_proprietario;
        this.identificador = identificador;
        this.piso = piso;
        this.contacto = contacto;
    }

    public int getId() {
        return id;
    }

    public int getIdEdificio() {
        return id_edificio;
    }

    public float getValorBase() {
        return valor_base;
    }

    public float getPermilagem() {
        return permilagem;
    }

    public String getNome_proprietario() {
        return nome_proprietario;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getPiso() {
        return piso;
    }

    public int getContacto() {
        return contacto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEdificio(int id_edificio) {
        this.id_edificio = id_edificio;
    }

    public void setValorBase(float valor_base) {
        this.valor_base = valor_base;
    }

    public void setPermilagem(float permilagem) {
        this.permilagem = permilagem;
    }

    public void setNomeProprietario(String nome_proprietario) {
        this.nome_proprietario = nome_proprietario;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public boolean save(Connection conn) throws SQLException {
        String query = "UPDATE faccoes SET id_edificio=?, valor_base=?, permilagem=?, nome_proprietario=?, identificador=?, piso=?, contacto=? WHERE id=?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id_edificio);
            preparedStatement.setFloat(2, valor_base);
            preparedStatement.setFloat(3, permilagem);
            preparedStatement.setString(4, nome_proprietario);
            preparedStatement.setString(5, identificador);
            preparedStatement.setString(6, piso);
            preparedStatement.setInt(7, contacto);
            preparedStatement.setInt(8, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Faccoes updated successfully.");
                return true;
            } else {
                System.out.println("Failed to update Faccoes.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean create(Connection conn) throws SQLException {
        String query = "INSERT INTO faccoes (id_edificio, valor_base, permilagem, " +
                "nome_proprietario, identificador, piso, contacto) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id_edificio);
            preparedStatement.setFloat(2, valor_base);
            preparedStatement.setFloat(3, permilagem);
            preparedStatement.setString(4, nome_proprietario);
            preparedStatement.setString(5, identificador);
            preparedStatement.setString(6, piso);
            preparedStatement.setInt(7, contacto);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Helper.green("Faccoes created successfully.");
                return true;
            } else {
                Helper.red("Failed to create Faccoes.");
                return false;
            }
        } catch (SQLException e) {
            Helper.red(e.getMessage());
        }
        return false;
    }
    public static Faccao getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM faccoes WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id_edificio = rs.getInt("id_edificios");
                float valor_base = rs.getFloat("valor_base");
                float permilagem = rs.getFloat("permilagem");
                String nome_proprietario = rs.getString("nome_proprietario");
                String identificador = rs.getString("identificador");
                String piso = rs.getString("piso");
                int contacto = rs.getInt("contacto");
                return new Faccao(id_edificio, valor_base, permilagem, nome_proprietario, identificador, piso, contacto);
            } else {
                return null;
            }
        }
    }
}