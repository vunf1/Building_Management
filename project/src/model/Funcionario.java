package model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Funcionario implements Serializable {
    private String nome;
    private Date dataNascimento;
    private int contacto;
    private String titulo;
    private String genero;

    public Funcionario(String nome, Date dataNascimento, int contacto, String titulo, String genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.contacto = contacto;
        this.titulo = titulo;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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
    public String infoDisplay() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", contacto=" + contacto +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
