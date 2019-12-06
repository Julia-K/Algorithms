import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        long startTimeRadix, endTimeRadix, startTimeQuick, endTimeQuick;
        double totalTimeRadix,totalTimeQuick;
        int l = countLines();
        String[] tabSurname = new String[l]; //tab dla RadixSorta
        String[] tabSurname2 = new String[l]; // tab dla QuickSorta
        String[][] popularityAndSurnames = new String[l][2];

        readFromFile(popularityAndSurnames,tabSurname); //czytanie dla RadixSorta
        readFromFile(tabSurname2); //czytanie dla QuickSorta

        startTimeRadix = System.nanoTime();
        RadixSort.radixSort(tabSurname);
        endTimeRadix = System.nanoTime();

        startTimeQuick = System.nanoTime();
        QuickSort.quickSort(tabSurname2,0,tabSurname2.length-1);
        endTimeQuick = System.nanoTime();

        totalTimeRadix = (endTimeRadix-startTimeRadix)/1000000000.0;
        totalTimeQuick = (endTimeQuick-startTimeQuick)/1000000000.0;
        System.out.println("Radix Sort: " + totalTimeRadix);
        System.out.println("Quick Sort: " + totalTimeQuick);
        //printSortedTab(matchNumbers(popularityAndSurnames,tabSurname,l),l);
        writeToFile(matchNumbers(popularityAndSurnames,tabSurname2,l),l);
    }

    private static int countLines() throws IOException{
        int l = 0;
        BufferedReader readerLine = new BufferedReader(new FileReader("nazwiskaASCII.txt"));
        while (readerLine.readLine() != null) {
            l++;
        }
        readerLine.close();
        return l;
    }

    private static String[][] matchNumbers(String[][] popularityAndSurnames, String[] tabSurname, int l) {
        String[][] outTab = new String[l][2];
        for (int p = 0; p < l; p++) {
            for (int j = 0; j < l; j++) {
                if(popularityAndSurnames[j][1].equals(tabSurname[p])) {
                    outTab[p][1] = popularityAndSurnames[j][1];
                    outTab[p][0] = popularityAndSurnames[j][0];
                }
            }
        }
        return outTab;
    }

    private static void writeToFile(String[][] endT,int l) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("sortedOutput.txt"));
        for (int p = 0; p < l; p++) {
            writer.write(endT[p][0] + " " + endT[p][1]);
            if(p!=l-1) {
                writer.newLine();
            }
        }
        writer.close();
    }

    public static void printSortedTab (String[][] endTab, int l) {
        for (int p = 0; p < l; p++) {
            System.out.println(endTab[p][0] +" " + endTab[p][1]);
        }
    }

    private static void readFromFile(String[][] popularityAndSurnames, String[] tabSurname) throws IOException {
        int x = 0;
        String stich;
        BufferedReader readLine = new BufferedReader(new FileReader("nazwiskaASCII.txt"));

        while ((stich = readLine.readLine()) != null) {
            String[] tempNumAndSurname = stich.split("\\s");
            tabSurname[x] = tempNumAndSurname[1];
            popularityAndSurnames[x][0] = tempNumAndSurname[0];
            popularityAndSurnames[x][1] = tempNumAndSurname[1];
            x++;
        }
    }

    private static void readFromFile(String[] tabSurname) throws IOException {
        int x = 0;
        String stich;
        BufferedReader readLine = new BufferedReader(new FileReader("nazwiskaASCII.txt"));

        while ((stich = readLine.readLine()) != null) {
            String[] tempNumAndSurname = stich.split("\\s");
            tabSurname[x] = tempNumAndSurname[1];
            x++;
        }
    }
}
