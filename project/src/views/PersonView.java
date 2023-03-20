package views;

import model.Data;
import model.Person;

import java.util.ArrayList;

public class PersonView {
    public static Person getPerson() {
        Person pessoa = null;
        boolean flag;
        String nome="";
        do{
            try {
                flag = false;
                nome = GeneralView.getText("Nome");
                Data data = DataView.getData();
                pessoa = new Person(nome, data);
            }catch (Exception e){
                flag = true;
                System.out.println("Atenção: "+ e.getMessage());
            }
        }while(flag);
        return pessoa;
    }

    public static void printPerson(Person pessoa) {
        System.out.print(pessoa.getNome()+ ", "+pessoa.getNascimento().getDia()+"/"+pessoa.getNascimento().getMes()+"/"+pessoa.getNascimento().getAno());
    }

    public static void printPeople(ArrayList<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).getIntroducao());
            System.out.print("\n");
        }
    }

    public static int menuPeople() {
        int op;
        do {
            System.out.println("\nMenu Pessoas");
            System.out.println("1 - Funcionarios");
            System.out.println("2 - Moradores");
            System.out.println("\n0 - Voltar");
            op = (int) GeneralView.getNumber("--->");
        } while (op < 0 || op > 2);
        return op;
    }

/*
    public static void mainPeople(Company company) {
        System.out.println("Gerir Pessoas");
        int op;
        do {
            op = menuPeople();
            switch (op) {
                case 0:
                    System.out.println("Volta para o menu anterior.");
                    break;
                case 1:
                    EmployeeView.mainEmployee(company);
                    break;
                case 2:
                    //mainMoradores(condominium);
                    break;
                default:
                    System.out.println("Opção Errada");
                    break;
            }
        } while (op != 0);
    }
*/

}
