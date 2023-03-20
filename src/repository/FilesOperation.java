package repository;

import model.Company;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class FilesOperation {
    static String CONDOMINIUM_DATA = "condominium_data.dat";
    public static Company carregarDados(){
        Company company = new Company();
        Path file = Paths.get(CONDOMINIUM_DATA);
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(file.toString()));
            company = (Company) o.readObject();
            o.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return company;
    }

    public static void guardarDados(Company company) {
        Path file = Paths.get(CONDOMINIUM_DATA);
        System.out.println("guardarDados");
        try{
            ObjectOutputStream o = new ObjectOutputStream(Files.newOutputStream(file, CREATE));
            o.writeObject(company);
            o.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
