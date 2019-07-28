package pl.wojtek120.warsztaty1.zadanie1;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Zgadnij liczbe z zakresu od 0 do 100");

        Random random = new Random();
        int randomNumber = random.nextInt(101);


        System.out.println("Podaj liczbę");
        int number = getInt();

        while (number != randomNumber) {
            if (number < randomNumber) {
                System.out.println("Liczba za mala");
            } else {
                System.out.println("Liczba za duza");
            }
            number = getInt();
        }

        System.out.println("Gratuluje!");
    }

    public static int getInt(){
        Scanner scanner = new Scanner(System.in);

         while (!scanner.hasNextInt()){
             scanner.next();
             System.out.println("Podaj liczbe w poprawnym formacie");
         }

         return scanner.nextInt();
    }
}
