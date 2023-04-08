package views;

import model.Company;
import model.Funcionario;
import model.EmployeeList;
import model.Person;

import java.util.ArrayList;

public class EmployeeView {

    public static Funcionario getEmployee() {
        Funcionario employee = null;
        boolean flag;
        long employeeNumber = -1;
        Person p = PersonView.getPerson();
        do{
            try {
                flag = false;
                employeeNumber = GeneralView.getNumber("Numero Funcionario");
                //employee = new Funcionario(p.getNome(), p.getNascimento(), employeeNumber);
            }catch (Exception e){
                flag = true;
                System.out.println("Atenção: "+ e.getMessage());
            }
        }while(flag);
        return employee;
    }

    public static void mainEmployee(Company company) {
        Funcionario f = null;
        EmployeeList employees = null;
        long number;
        int op;
        do {
            op = menuEmployees();
            switch (op) {
                case 0:
                    System.out.println("Volta para o menu anterior.");
                    break;
                case 1:
                    System.out.println("\nAdicionar\n");
                    try {/*
                        f = EmployeeView.getEmployee();
                        employees = company.getEmployeeList();
                        employees.insert(f);*/
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {/*
                        System.out.println("\nPesquisar\n");
                        number = GeneralView.getNumber("Numero funcionario a pesquisar");
                        employees = company.getEmployeeList();
                        f = employees.get(number);
                        printEmployee(f);*/
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                   //employees = company.getEmployeeList();
                    //ArrayList<Funcionario> list = employees.getAll();
                    //printListEmployees(list);
                    break;
                case 4:
                    try {/*
                        System.out.println("\nAlterar\n");
                        number = GeneralView.getNumber("Numero funcionario a alterar");
                        employees = company.getEmployeeList();
                        f = employees.get(number);
                        printEmployee(f);
                        f = EmployeeView.getEmployee();
                        employees.change(number, f);*/
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {/*
                        System.out.println("\nEliminar\n");
                        number = GeneralView.getNumber("Numero funcionario a eliminar");
                        employees = company.getEmployeeList();
                        f = employees.get(number);
                        printEmployee(f);
                        Employee p = employees.remove(number);
                        System.out.println(p.getNome());
                        System.out.println(p);*/
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opção Errada");
                    break;
            }
        } while (op != 0);
    }

    private static int menuEmployees() {
        int op;
        do {
            System.out.println("\nMenu Funcionarios");
            System.out.println("1 - Inserir");
            System.out.println("2 - Pesquisar");
            System.out.println("3 - Listar");
            System.out.println("4 - Alterar");
            System.out.println("5 - Eliminar");
            System.out.println("\n0 - Voltar");
            op = (int) GeneralView.getNumber("--->");
        } while (op < 0 || op > 5);
        return op;
    }
/*
    public static void printEmployee(Employee employee) {
        PersonView.printPerson(employee);
        System.out.println(", " + employee.getNumberEmployee());
    }

    public static void printListEmployees(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            printEmployee(employees.get(i));
        }
    }
*/
}
