package zad6_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static String[] countAndReadLines(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int numberOfLines = 0;
        String[] wordsTable;
        int tempHelper = 0;
        String line;

        while (reader.readLine() != null) {
            numberOfLines++;
        }

        reader = new BufferedReader(new FileReader(file));
        wordsTable = new String[numberOfLines];

        while ((line = reader.readLine()) != null) {
            wordsTable[tempHelper] = line;
            tempHelper++;
        }
        reader.close();

        return wordsTable;
    }

    private static void hashWord(String[] words, int[] tab, int x) {
        int i;
        for (String word : words) {

            i = hash(word, x);
            tab[i]++;
        }
    }

    private static int hash(String word, int x) {
        int hash = 256 * (int) word.charAt(0) + word.charAt(1);
        int wordLength = word.length();
        int hashCode;
            for (int i = 2; i < wordLength;) {
                hashCode = 256 * (int) word.charAt(i++);
                if(i<wordLength) {
                    hashCode += (int) word.charAt(i++);
                }
                hash ^= hashCode;
            }

            return hash % x;
        }

    private static void test(String[] words, int[] tab, int x) {
        double average = 0;
        int maximum = 0;
        int notZer = 0;
        int zero = 0;

        hashWord(words, tab, x);

        for (int i = 0; i < tab.length;i++) {
            if(tab[i] == 0) {
                zero++;
            } else {
                average += tab[i];
                notZer++;
            }

            if(tab[i] > maximum) {
                maximum = tab[i];
            }
        }

        average = average/notZer;
        average *= 100;
        average = Math.round(average);
        average /= 100;

        System.out.println("Ilosc zerowych pozycji: " + zero);
        System.out.println("Srednia: " + average);
        System.out.println("Maksymalna wartosc niezerowych pozycji: "+maximum);
    }



    public static void main(String[] args) {
        try {
            int m;
            String[] data = countAndReadLines("3700.txt");
            System.out.println("KORZYSTNE: \n");
            m = 1801;
            int[] tab = new int[1801];
            Arrays.fill(tab, 0);
            test(data, tab, 1801);
            System.out.println("-------------------------------");
            m = 1500;
            int[] tab1 = new int[1500];
            Arrays.fill(tab1, 0);
            test(data, tab1, 1500);
            System.out.println("-------------------------------");
            m = 1847;
            int[] tab2 = new int[1847];
            Arrays.fill(tab2, 0);
            test(data, tab2, 1847);

            System.out.println("\n\nNIEKORZYSTNE: \n");
            m = 1779;
            int[] tab3 = new int[m];
            Arrays.fill(tab3, 0);
            test(data, tab3, m);
            System.out.println("-------------------------------");
            m = 1440;
            int[] tab4 = new int[m];
            Arrays.fill(tab4, 0);
            test(data, tab4, m);
            System.out.println("-------------------------------");
            m = 1811;
            int[] tab5 = new int[m];
            Arrays.fill(tab5, 0);
            test(data, tab5, m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
