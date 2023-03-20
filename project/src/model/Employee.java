package model;

import exception.EmployeeInvalidNumberException;

import java.io.Serializable;

public class Employee extends Person implements Serializable {
    private long numberEmployee;
    public Employee(String nome, Data nascimento, long numberEmployee) {
        super(nome, nascimento);
        setNumberEmployee(numberEmployee);
    }

    public Employee(Employee employee) {
        super(employee);
        setNumberEmployee(employee.numberEmployee);
    }

    public long getNumberEmployee() {
        return numberEmployee;
    }

    public void setNumberEmployee(long numberEmployee) {
        if(validNumber(numberEmployee)) {
            this.numberEmployee = numberEmployee;
        }else {
            throw new EmployeeInvalidNumberException(numberEmployee + ": Numero Funcionario inválido");
        }
    }

    private boolean validNumber(long number) {
        return number >= 0 && number <= 9999;
    }

    public String getIntroducao(){
        String intro;
        intro = super.getIntroducao()+" - "+numberEmployee ;
        return intro;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "funcionario=" + numberEmployee +
                ", nome='" + getNome() + '\'' +
                ", nascimento=" + getNascimento().toString() +
                '}';
    }
}
