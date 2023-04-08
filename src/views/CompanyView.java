package views;

import Helpers.Helper;
import exception.DataInvalidaException;
import model.Company;
import model.Contact;
import model.DatabaseManager;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CompanyView {

    public static int menuCompany() {
        System.out.println("Gerir Empresa de Condominios");
        int op;
        do {
            System.out.println("\nMenu Condominium");
            System.out.println("1 - Alterar dados Empresa");
            System.out.println("\n0 - Voltar");
            op = (int) GeneralView.getNumber("--->");
        } while (op < 0 || op > 1);
        return op;
    }

    public static Company changeCompany(Company original) {
        /*String nome;
        boolean flag;
        do{
            try {
                flag = false;
                System.out.println("NOME original:"+original.getNome());
                nome = GeneralView.getText("Nome");
                if(!nome.isEmpty()){
                    original.setNome(nome);
                }
                System.out.println("Contato original:"+original.getContact());
                Contact contact = ContactView.getContact();
                original.setContact(contact);
                return original;
            } catch (Exception e) {
                flag = true;
                System.out.println("Atenção: " + e.getMessage());
            }
        } while (flag);*/
        return null;
    }

    public static Company createCompany() {
        Helper.blue("Insert data for new company:");
        int id = Helper.generateRandomNumber4Digits(0);
        Helper.white("ID: ");Helper.red(" ->"+id);
        Scanner scanner = new Scanner(System.in);

        Helper.blue("Name: ");
        String nome = scanner.nextLine();

        Helper.blue("Address: ");
        String address = scanner.nextLine();

        Helper.blue("Contact: ");
        int contact = 0;
        while (contact == 0) {
            try {
                contact = new Contact(scanner.nextInt()).getContact();

            } catch (InputMismatchException e) {
                Helper.red("Invalid input. Please enter a number: ");
                scanner.nextLine(); // consume the invalid input
            } catch (DataInvalidaException e) {
                Helper.red("Invalid input. Please enter a number: ");
                scanner.nextLine(); // consume the invalid input
            }
        }
        scanner.nextLine(); // consume the newline character after the input


        Helper.blue("Email: ");
        String email = scanner.nextLine();

        Company company = new Company(id, nome, address, contact,email);
        //try {
            Helper.purple("CREATING COMPANY MOCK");
            //company.create(new DatabaseManager(null).getConnection());
        //} catch (SQLException e) {
            //Helper.red(e.getMessage());
        //}
        return null;
    }
}
