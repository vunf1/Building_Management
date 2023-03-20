package views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralView {
    public static long getNumber(String label) {
        Scanner kbd = new Scanner(System.in);
        boolean flag;
        long number = -1;
        do {
            System.out.print(label + ": ");
            try {
                flag = false;
                number = kbd.nextLong();
            } catch (InputMismatchException e) {
                flag = true;
            }
            kbd.nextLine();
        } while (flag);
        return number;
    }

    public static int getNumberInt(String label) {
        Scanner kbd = new Scanner(System.in);
        boolean flag;
        int number = -1;
        do {
            System.out.print(label + ": ");
            try {
                flag = false;
                number = kbd.nextInt();
            } catch (InputMismatchException e) {
                flag = true;
            }
            kbd.nextLine();
        } while (flag);
        return number;
    }

    public static String getText(String label) {
        Scanner kbd = new Scanner(System.in);
        String text = "";
        System.out.print(label + ": ");
        text = kbd.nextLine();
        return text;
    }
    public static void writeText(String text) {
        System.out.print(text);
    }

    public static int loadMenu() {
        int op;
        do {
            System.out.println("\nMenu Inicial");
            System.out.println("1 - Criar Empresa");
            System.out.println("2 - Carregar Empresa da base de dados");
            System.out.println("3 - Criar empresa Generica");
            op = (int) GeneralView.getNumber("Indique a opcao");
        } while (op < 1 || op > 3);
        return op;
    }

    public static int generalMenu() {
        int option;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("1 - Gerir Empresa");
            System.out.println("2 - Gerir Funcionarios");
            System.out.println("5 - Estatísticas");
            System.out.println("\n0 - Sair");
            option = (int) GeneralView.getNumber("--->");
        } while (option < 0 || option > 6);
        return option;
    }
}
