package mock;

import model.Company;
import model.Employee;
import model.Data;
import model.EmployeeList;

import java.util.Random;

public class MockData {

    private int FUNC_NR = 5;
    private String[] initial_employees_names = {"Funcionario A","Funcionario B","Funcionario C","Funcionario D","Funcionario E","Funcionario F"};
    private long[] initial_employees_codes = {1111,2222,3333,4444,5555,6666};

    private Data genDate(){
        Random gen = new Random(System.currentTimeMillis());
        int day = 1 + (gen.nextInt(Integer.MAX_VALUE) % 27);
        int month = 1 + (gen.nextInt(Integer.MAX_VALUE) % 12);
        int year = 1970 + (gen.nextInt(Integer.MAX_VALUE) % 40);
        Data dt = new Data(day,month,year);
        return dt;
    }

    private void  insertEmployees(Company company){
        EmployeeList employees = null;
        for (int  i = 0; i < this.initial_employees_names.length;i++){
            try{
                Data dt = genDate();
                Employee employee = new Employee(this.initial_employees_names[i],dt, this.initial_employees_codes[i]);
                employees = company.getEmployeeList();
                employees.insert(employee);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void  generateData(Company company){
        insertEmployees(company);
    }

}
