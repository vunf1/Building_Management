package controllers;

import model.Company;
import model.Person;
import views.*;

import java.util.ArrayList;


public class Controller {
    private Company model;
    public Controller(Company company) {
        this.model = company;
    }

    public void run() {
        int op = -1;
        do {
            op = GeneralView.generalMenu();
            switch (op) {
                case 1:
                    //runCompany(model);
                    //CompanyView.mainCompany(model);
                    break;
                case 2:
                    //runPeople(model);
                    //PersonView.mainPeople(model);
                    break;
                case 5:
                    //runStatistics(model);
                    //StatisticsView.mainStatistics(model);
                    break;
                default:
                    GeneralView.writeText("Nice to have You! See you Soon!!");
                    break;
            }
        } while (op != 0);

    }
    private void createCompany(Company company) {

    }


    private void runCompany(Company company) {
        int op;
        do {
            op = CompanyView.menuCompany();
            switch (op) {
                case 0:
                    GeneralView.writeText("Volta para o menu anterior.");
                    break;
                case 1:
                    Company b = CompanyView.changeCompany(company);
                    if(b!= null) {
                        GeneralView.writeText("Alterado");
                    }
                    break;
                default:
                    GeneralView.writeText("Opção Errada");
                    break;
            }
        } while (op != 0);
    }

    private void runPeople(Company company) {
        GeneralView.writeText("Gerir Pessoas");
        int op;
        do {
            op = PersonView.menuPeople();
            switch (op) {
                case 0:
                    GeneralView.writeText("Volta para o menu anterior.");
                    break;
                case 1:
                    //EmployeeView.mainEmployee(company);
                    break;
                case 2:
                    //mainMoradores(condominium);
                    break;
                default:
                    GeneralView.writeText("Opção Errada");
                    break;
            }
        } while (op != 0);
    }

    public static void runStatistics(Company company) {

        int op;
        do {
            op = StatisticsView.menuStatistic();
            switch (op) {
                case 0:
                    GeneralView.writeText("Volta para o menu anterior.");
                    break;
                case 1:
                    GeneralView.writeText("Os 5 funcionarios mais velhos");
                    //ArrayList<Person> olderPeopleList = company.getEmployeeList().getNOldestPeople(5);
                    //PersonView.printPeople(olderPeopleList);
                    break;
                case 2:
                    GeneralView.writeText("Procurar funcionario por nome");
                    String query = GeneralView.getText("Introduza parte do nome");
                    //ArrayList<Person> foundPeopleList = company.getEmployeeList().searchByName(query);
                    //PersonView.printPeople(foundPeopleList);
                    break;
                default:
                    GeneralView.writeText("Opção Errada");
                    break;
            }

        } while (op != 0);
    }

}
