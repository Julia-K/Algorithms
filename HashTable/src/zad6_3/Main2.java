package zad6_3;

import java.io.*;
import java.util.Scanner;

public class Main2 {
    private static int hashWord(String word, int size) {
        int wordValue = 256 * (int) word.charAt(0) + word.charAt(1);
        int hashCode;
        for(int j=2; j<word.length(); j++){
            hashCode=256*(int)word.charAt(j++);
            if(j<word.length()) {
                hashCode += word.charAt(j);
            }
            wordValue ^= hashCode;
        }
        return wordValue % size;
    }

    private static int hashS(int key, int index, int m){
        return ((key%m) + index) % m;
    }

    private static int countLines(String name) throws FileNotFoundException {
        int lines = 0;
        File file = new File(name);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            scanner.nextLine();
            lines++;
        }
        scanner.close();
        return lines;
    }

    private static void readAndFill(String name, String[] surnames, int[] amount, int lines) throws FileNotFoundException {
        int counter = 0;
        File file = new File(name);
        Scanner scanner = new Scanner(file);
        while(counter < lines) {
            amount[counter] = scanner.nextInt();
            surnames[counter] = scanner.next();
            counter++;
        }
        scanner.close();
    }

    private static void hashAll(int[] amount, String[] surnames, String[] hashTab, int size, boolean first) {
        int key;
        int j = 0;
        int from = 0;
        if(!first) {
            j = (int)(size*0.4);
            from = size;
        }
        while(j < size*0.8) {
            key = hashWord(surnames[j], size);
            if(hashTab[key] == null || hashTab[key].equals("DEL")) {
                hashTab[key] = amount[j+from] + " " + surnames[j+from];
            } else {
                int x;
                int index = 0;
                boolean condition = false;
                while(!condition) {
                    x = hashS(key,index,size);
                    if(x < 0) x += size-1;
                    if(hashTab[x] == null || hashTab[x].equals("DEL")) {
                        hashTab[x] = amount[j+from] + " " + surnames[j+from];
                        condition= true;
                    }
                    index++;
                }
            }
            j++;
        }
    }

    private static void delete(String[] hashTab, int size) {
        int j = 0;
        int i = 0;
        while(i < size*0.4) {
            if(hashTab[j] != null && !hashTab[j].equals("DEL")) {
                hashTab[j] = "DEL";
                i++;
            }
            j++;
        }
    }

    private static void test(int[] amount, String[] surname, String[] hashTab, int size, boolean isSmallTable) {
        hashAll(amount, surname, hashTab, size, true);
        delete(hashTab, size);
        hashAll(amount, surname, hashTab, size, false);

        int i = 0;
        int counter = 0;
        int nullValues = 0;
        while(hashTab.length != i) {
            if(hashTab[i] == ("DEL")) {
                counter++;
            }
            if(hashTab[i] == null) {
                nullValues++;
            }
            i++;
        }
        if(isSmallTable) {
            System.out.println("Dla małej tablicy:\n");
            for(String element : hashTab) {
                System.out.println(element);
            }
        }
        System.out.println("\nDEL: " + counter);
        System.out.println("null: " + nullValues + "\n");
    }

    public static void main(String[] args) throws FileNotFoundException {
        int m = 20;
        String[] surname = new String[2*m];
        int[] amount = new int[2*m];
        String[] hashTab = new String[m];
        readAndFill("nazwiskaASCII.txt", surname, amount, 2*m);
        test(amount, surname, hashTab, m, true);
        System.out.println("---------------------------------");

        System.out.println("Dla dużej tablicy: ");
        int sizeBig = countLines("nazwiskaASCII.txt");
        String[] surnameBig = new String[sizeBig];
        int[] amountBig = new int[sizeBig];
        String[] hashTabBig = new String[sizeBig/2];
        readAndFill("nazwiskaASCII.txt", surnameBig, amountBig, sizeBig);
        test(amountBig, surnameBig, hashTabBig, sizeBig/2, false);

    }
}
