package views;

import model.Person;

import java.util.ArrayList;

public class StatisticsView {
/*    public static void mainStatistics(Company company) {

        int op;
        do {
            op = menuStatistic();
            switch (op) {
                case 0:
                    System.out.println("Volta para o menu anterior.");
                    break;
                case 1:
                    System.out.println("Os 5 funcionarios mais velhos");
                    ArrayList<Employee> employees = company.getEmployeeList().getAll();
                    ArrayList<Person> people = new ArrayList<>();
                    people.addAll(employees);
                    //pessoas.addAll(moradores);
                    ArrayList<Person> olderPeopleList = getNOldestPeople(people, 5);
                    PersonView.printPeople(olderPeopleList);
                    break;
                case 2:

                    break;
                default:
                    System.out.println("Opção Errada");
                    break;
            }

        } while (op != 0);
    }*/


    public static int menuStatistic() {
        int op;
        do {
            System.out.println("\nMenu Estatística");
            System.out.println("1 - Top 5 pessoas mais velhas");
            System.out.println("2 - Procurar funcionario por nome");
            System.out.println("\n0 - Voltar");
            op = (int) GeneralView.getNumber("--->");
        } while (op < 0 || op > 2);
        return op;
    }
}
