package pl.wojtek120.warsztaty1.zadanie2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int []numbers = getSixRandomNumbers();
        int []yourNumbers = getAllNumbers();

        System.out.println("Twoje liczby to:");
        System.out.println(Arrays.toString(yourNumbers));

        System.out.println("Wylosowane liczby to:");
        System.out.println(Arrays.toString(numbers));

        int result = howManyEquals(yourNumbers,numbers);

        if(result > 2){
            System.out.println("Wylosowales " + result);
        }

    }

    public static int [] getAllNumbers(){
        int [] numbers = new int[6];

        for (int i = 0; i < numbers.length; i++){
            numbers[i] = getInt();

            while(findInArray(numbers, numbers[i], i)) {
                System.out.println("Tą liczbę już podałeś");
                numbers[i] = getInt();
            }
        }

        Arrays.sort(numbers);

        return numbers;
    }

    public static int getInt(){
        Scanner scanner = new Scanner(System.in);
        int numbers;

        System.out.println("Podaj liczbę");
        do {
            while (!scanner.hasNextInt())  {
                scanner.next();
                System.out.println("Podaj liczbe w poprawnym formacie z zakresu od 1 do 49");
            }

            numbers = scanner.nextInt();

            if(!(numbers > 0 && numbers < 50)){
                System.out.println("Podaj liczbe w poprawnym formacie z zakresu od 1 do 49");
            }
        } while (!(numbers > 0 && numbers < 50));

        return numbers;
    }

    public static boolean findInArray(int[] tab, int number, int position){
        for(int i = 0; i < tab.length; i++){
            if(number == tab[i] && i != position){
                return true;
            }
        }

        return false;
    }

    public static int[] getSixRandomNumbers(){
        int[] tab = new int[6];
        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(arr));

        for(int i = 0; i < tab.length; i++){
            tab[i] = arr[i];
        }

        return tab;
    }

    public static int howManyEquals(int []tab1, int []tab2){
        int count = 0;
        for(int i = 0; i < tab1.length; i++){
            for (int j = 0; j < tab2.length; j++){
                if(tab1[i] == tab2[i]){
                    count++;
                }
            }
        }

        return count;
    }
}
