package pl.wojtek120.warsztaty1.zadanie3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int []guessedNumbers = new int[11];
        int number;

        System.out.println("Pomyśl liczbę od 0 do 1000, a ja ją zgadnę w max 10 próbach");

        number =  guessTheNumber(0, 1000, guessedNumbers);

        if(number == -1){
            System.out.println("Nie oszukuj");
        } else{
            System.out.println("Liczba o ktróej myślałeś to " + number);
        }

    }

    public static int guessTheNumber(int min, int max, int []guessedNumbers) {
        Scanner scanner = new Scanner(System.in);
        int guess = (max - min) / 2 + min;


        if(max - min == 1){

            if(guess == 0){
                return 0;
            } else if (guess == 999) {
                return 1000;
            }
        }

        if(isInTab(guess, guessedNumbers)){
            return -1;
        }

        addToTab(guess, guessedNumbers);



        System.out.println("Zgaduję " + guess);

        System.out.println();
        System.out.println("Wpisz:");
        System.out.println("z - jeśli zgadłem");
        System.out.println("d - jeśli liczba jest za duża");
        System.out.println("m - jeśli liczba jest za mała");


        while (true) {
            String str = scanner.nextLine();
            char c = 'x';
            if (str.length() != 0) {
                c = str.charAt(0);
            }


            switch (c) {
                case 'd':
                    return guessTheNumber(min, guess, guessedNumbers);

                case 'm':
                    return guessTheNumber(guess, max, guessedNumbers);

                case 'z':
                    return guess;

                default:
                    System.out.println("Podaj prawidłowy znak");
                    break;
            }
        }

    }

    public static boolean isInTab(int number, int []tab){
        for (int value : tab) {
            if (value == number) {
                return true;
            }
        }

        return false;
    }

    public static void addToTab(int number, int []tab){
        for(int i = 0; i < tab.length; i++){
            if(tab[i] == 0){
                tab[i] = number;
                break;
            }
        }
    }

}
