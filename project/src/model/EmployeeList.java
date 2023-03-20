package model;

import exception.EmployeeDuplicatedNumberException;
import exception.NonExistElement;

import java.io.Serializable;
import java.util.ArrayList;
import model.Employee;

public class EmployeeList implements Serializable {

    private ArrayList<Employee> employees;

    public EmployeeList() {
        employees = new ArrayList<>();
    }

    private Employee getEmployeeByNumber(long number){
        for (int i = 0; i < employees.size(); i++) {
            Employee p = employees.get(i);
            if (p.getNumberEmployee() == number) {
                return p;
            }
        }
        return null;
    }
    public boolean insert(Employee employee){
        Employee p = getEmployeeByNumber(employee.getNumberEmployee());
        if (p== null) {
            return employees.add(employee);
        }else{
            throw new EmployeeDuplicatedNumberException(employee.getNumberEmployee()+"");
        }
    }
    public Employee remove(long number){
        Employee p = getEmployeeByNumber(number);
        if (p != null) {
            employees.remove(p);
            return p;
        }else{
            throw new NonExistElement(number+"");
        }
    }
    public void change(long number, Employee employee){
        Employee p = getEmployeeByNumber(number);
        if (p != null) {
            p.setNome(employee.getNome());
            p.setNascimento(employee.getNascimento());
            p.setNumberEmployee(employee.getNumberEmployee());
        }else{
            throw new NonExistElement(number+"");
        }
    }
    public Employee get(long number) {
        Employee p = getEmployeeByNumber(number);
        if (p != null) {
            return p;
        }else{
            throw new NonExistElement(number +"");
        }
    }
    public ArrayList<Employee> getAll() {
        return (ArrayList<Employee>) employees.clone();
    }
    public ArrayList<Person> getNOldestPeople(int n) {
        boolean flag;
        ArrayList<Person> lista = new ArrayList<>();
        if (n <= 0) {
            return lista;
        }
        for(Person person: employees){
            flag = false;
            for (int i = 0; i < lista.size(); i++){
                if (person.getNascimento().eAnterior(lista.get(i).getNascimento())) {
                    lista.add(i, person);
                    flag = true;
                    break;
                }
            }
            if(flag == false){
                lista.add(person);
            }
        }

        for (int i = lista.size() - 1; i > n - 1; i--) {
            lista.remove(i);
        }

        return lista;
    }

    public ArrayList<Person> searchByName(String query) {
        ArrayList<Person> lista = new ArrayList<>();
        if (employees.size() < 1) {
            return lista;
        }

        for(Person person: employees){
            if(person.getNome().toLowerCase().contains(query.toLowerCase())){
                lista.add(person);
            }
        }
        return lista;
    }
}
