public class RadixSort {
   public static void radixSort(String[] tabSurname) {
        int max = 0;

        //szukamy najdluzszego nazwiska
        for (int i = 0; i < tabSurname.length; i++) {
            if(tabSurname[i].length()-1 > max) {
                max = tabSurname[i].length()-1;
            }
        }

        for (int i = max; i >= 0; i--) {
            countingSort(tabSurname,i);
        }

    }
    public static void countingSort(String[] tabSurname,int i) {
        int tabLength = tabSurname.length;
        int[] alphabet = new int[257];
        String[] endTab = new String[tabLength];
        int charI;

        //zliczanie wystapien znakow
        for (int p = 0; p < tabLength; p++) {
            if(tabSurname[p].length()-1 < i) {
                charI = 0;
            } else {
                charI = tabSurname[p].charAt(i)+1;
            }
            alphabet[charI]++;
        }

        //zliczanie mniejszych lub rownych znakow od danego znaku
        for (int p = 1; p < alphabet.length; p++) {
            alphabet[p] += alphabet[p-1];
        }

        //wypelnianie tablicy endTab posortowanymi napisami z tabSurname i
        //zmniejszanie liczników znaków
        for (int p = tabLength-1; p >= 0; p--) {
            if(tabSurname[p].length()-1 < i) {
                charI = 0;
            } else {
                charI = tabSurname[p].charAt(i)+1;
            }
            endTab[alphabet[charI]-1]=tabSurname[p];
            alphabet[charI]--;
        }

        for (int p = 0; p < tabLength; p++) {
            tabSurname[p] = endTab[p];
        }
    }
}