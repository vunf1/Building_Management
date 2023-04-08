package Helpers;

import java.io.Serializable;
import java.util.Random;

import static java.lang.System.out;

public class Helper implements Serializable {
    //ANSI codes to print colored text to the console.
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static void red(String s){
        out.println(ANSI_RED + "\n-" + s + ANSI_RESET);
    }
    public static void blue(String s){
        out.println(ANSI_BLUE + "\n-" + s + ANSI_RESET);
    }
    public static void white(String s){
        out.println(ANSI_WHITE + "\n-" + s + ANSI_RESET);
    }
    public static void green(String s){
        out.println(ANSI_GREEN + "\n-" + s + ANSI_RESET);
    }
    public static void purple(String s){
        out.println(ANSI_PURPLE + "\n-" + s + ANSI_RESET);
    }
    public static int generateRandomNumber4Digits(int n){ // random integer between 0 and 9999
        Random random = new Random();
        int number = random.nextInt(10000);
        if(number == n){
            generateRandomNumber4Digits(number);
        } else {
            return number;
        }
        return number;
    }
    /**
     Retorna string com os números divididos em grupos de 3 separados por espaços. (better for UI)
     @param number (Object) converted to string
     @return uma string representando o número de entrada com os dígitos divididos em grupos de 3 separados por espaços
     */
    public static String splitIntoGroup3Each(Object number) {// prints "123 456 789"
        //Converte input para uma string
        String digitsOnly = String.valueOf(number).replaceAll("[^\\d]", "");
        String[] groups = digitsOnly.split("(?<=\\G.{3})");
        String result = String.join(" ", groups);
        return result;
    }
    /**
     Retorna string  apenas com os dígitos da string inserida.
     @param input java.lang.String()
     @return Uma string com apenas os numeros da string de entrada.
     */
    public static String getNumbers(String input) { // prints "123" from "Hi 123 you there?"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // Verifica se o caracter é um dígito
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
