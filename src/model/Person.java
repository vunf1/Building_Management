package model;

import exception.NamePersonInvalidException;

import java.io.Serializable;

public class Person implements Serializable {
    private String nome;
    private Data nascimento;

    public Person() {
    }

    public Person(String nome, Data nascimento) {
        setNome(nome);
        setNascimento(nascimento);
    }

    public Person(Person p) {
        setNome(p.getNome());
        setNascimento(p.getNascimento());
    }

    public String getNome() {
        return nome;
    }

    public Data getNascimento() {
        Data data = new Data(nascimento);
        return data;
    }

    protected void setNome(String nome) throws NamePersonInvalidException {
        if (eNomeValido(nome)) {
            this.nome = nome;
        } else {
            throw new NamePersonInvalidException(nome + ": Nome inválido");
        }
    }

    private boolean eNomeValido(String nome){
        if(nome == null){
            return false;
        }
        if(nome.length() < 3){
            return false;
        }
        for(int i=0;i<nome.length();i++){
            if(nome.charAt(i) >= '0' &&  nome.charAt(i) <= '9')
                return false;
        }
        return true;
    }

    public void setNascimento(Data nascimento) {
        this.nascimento = nascimento;
    }


    public String getIntroducao(){
        String intro;
        intro = nome + ",nascimento=" + nascimento +" ";
        return intro;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                '}';
    }
}
