package pl.wojtek120.warsztaty1.zadanie4;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Wpisz jaki rzut chcesz wykonac w odpowiedniej notacji");
        System.out.println("Wpisz 'q' aby zamknąć program");
        boolean quit = false;

        while (!quit) {
            quit = rollDice();
        }

    }

    public static boolean rollDice() {
        Scanner scanner = new Scanner(System.in);
        int numberOfRolls, sidesOfDice = 1, modifier = 0;
        String[] strings;
        char modifierSign = 'x';


        String rollString = scanner.nextLine();

        try {
            if (rollString.charAt(0) == 'q') {
                return true;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Podaj poprawny zapis rzutu kośćmi");
            return false;
        }

        if (rollString.replaceAll("D", "").length() != rollString.length() - 1) {
            System.out.println("Podaj poprawny zapis rzutu kośćmi");
            return false;
        }

        if (rollString.charAt(0) != 'D') {
            strings = rollString.split("D");
            try {
                numberOfRolls = Integer.valueOf(strings[0]);
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawny zapis rzutu kośćmi");
                return false;
            }
        } else {
            numberOfRolls = 1;
            strings = rollString.split("D");
        }

        for (int i = 0; i < strings[strings.length - 1].length(); i++) {
            if (strings[strings.length - 1].charAt(i) == '+' || strings[strings.length - 1].charAt(i) == '-') {
                modifierSign = strings[strings.length - 1].charAt(i);
            }
        }


        if (modifierSign == '+') {
            try {
                strings = strings[strings.length - 1].split("\\+");
                sidesOfDice = Integer.valueOf(strings[0]);
                modifier = Integer.valueOf(strings[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Podaj poprawny zapis rzutu kośćmi");
            }
        } else if (modifierSign == '-') {
            try {
                strings = strings[strings.length - 1].split("-");
                sidesOfDice = Integer.valueOf(strings[0]);
                modifier = -Integer.valueOf(strings[1]);
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawny zapis rzutu kośćmi");
            }
        } else {
            try {
                sidesOfDice = Integer.valueOf(strings[strings.length - 1]);
                modifier = 0;
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawny zapis rzutu kośćmi");
            }
        }

        if (sidesOfDice != 3 && sidesOfDice != 4 && sidesOfDice != 6 && sidesOfDice != 8
                && sidesOfDice != 10 && sidesOfDice != 12 && sidesOfDice != 20 && sidesOfDice != 100) {
            System.out.println("Typy kostek występujące w grach to");
            System.out.println("D3, D4, D6, D8, D10, D12, D20, D100.");
            return false;
        }

        getResult(numberOfRolls, sidesOfDice, modifier);

        return false;
    }

    public static void getResult(int numberOfRolls, int sidesOfDice, int modifier) {
        Random random = new Random();
        int result = 0;

        for (int i = 0; i < numberOfRolls; i++) {
            int dice = random.nextInt(sidesOfDice) + 1;
            System.out.println("Rzut nr " + (i + 1) + ". = " + dice);
            result += dice;
        }

        result += modifier;

        System.out.println("Wynik = " + result);
    }
}
