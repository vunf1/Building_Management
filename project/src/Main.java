import controllers.Controller;
import controllers.DatabaseController;
import mock.MockData;
import model.DatabaseManager;
import repository.FilesOperation;
import model.Company;
import views.CompanyView;
import views.GeneralView;
import model.Contact;

public class Main {

    public static void main(String[] args) {
        GeneralView.writeText( "Inicializando aplicação de Gestão de Empresas...");
        Company company = null;
        //int option = GeneralView.loadMenu();
        /*switch (option){
            case 1:
                company = CompanyView.createCompany();
                break;
            case 2:
                company = FilesOperation.carregarDados();
                System.out.println(company.getNome());
                System.out.println(company.getContact());
                break;
            case 3:
                System.out.println("MOCK");
                Contact contacto = new Contact(999999999);
                company = new Company("ABCD",contacto);
                MockData mock = new MockData();
                mock.generateData(company);
                break;
            case 0:
                System.out.println("Exiting");
                break;
            default:
                GeneralView.writeText("Not an option");
        }*/

        /*if (company != null){
            Controller controller = new Controller(company);
            controller.run();
        }

        FilesOperation.guardarDados(company);*/
        DatabaseController db = new DatabaseController();
        db.main();
    }


}