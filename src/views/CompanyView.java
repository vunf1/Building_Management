package views;

import model.Company;
import model.Contact;

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
        String nome;
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
        } while (flag);
        return null;
    }

    public static Company createCompany() {
        String nome = GeneralView.getText("Digite o nome da Empresa");
        Contact contact = ContactView.getContact();
        Company company = new Company(nome, contact);
        return company;
    }
}
