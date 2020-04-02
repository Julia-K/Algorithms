import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LCS {
    public static int[][] lcs(char[] first, char[] second) {
        int size1 = first.length;
        int size2 = second.length;
        char[][] tab = new char[size1+1][size2+1];
        int[][] numbers = new int[size1+1][size2+1];

        for (int[] row: numbers) {
            Arrays.fill(row,0);
        }

        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                if (first[i - 1] == second[j - 1]) {
                    numbers[i][j] = numbers[i - 1][j - 1] + 1;
                    tab[i][j] = '\\';
                } else if (numbers[i - 1][j] >= numbers[i][j - 1]) {
                    numbers[i][j] = numbers[i - 1][j];
                    tab[i][j] = '|';
                } else {
                    numbers[i][j] = numbers[i][j - 1];
                    tab[i][j] = '-';
                }
            }
        }

        System.out.print("      ");
        for (int i = 0; i < size2; i++) {
            System.out.print(second[i]+"  ");
        }
        System.out.print("\n   ");
        for (int i = 0; i <= size2; i++) {
            System.out.print("0  ");
        }
        System.out.println();

        for (int i = 1; i <= size1; i++) {
            System.out.print(first[i-1]);
            System.out.print("  0");
            for (int j = 1; j <= size2; j++) {
                System.out.print(" " + tab[i][j] + numbers[i][j]);
            }
            System.out.printf("%n");
        }

        print(tab,first,numbers,size2);
        return numbers;
    }

    public static void print(char[][] tab, char[] first, int[][] num, int size2) {
        int x = first.length;
        int y = size2;
        int index = num[x][y];
        char[] justOne = new char[num[x][y]+1];

        while (x>0 && y > 0) {
            if(tab[x][y] == '\\') {
                justOne[index] = first[x-1];
                x--;
                y--;
                index--;
            } else if (tab[x][y] == '|') {
                x--;
            } else if (tab[x][y] == '-') {
                y--;
            }
        }

        System.out.print("\nIteracyjnie jeden najdłuższy podciąg(lcs): \n"+ " ");
        for (char c : justOne) {
            System.out.print(c);
        }
        System.out.println("\n-------------------------");
    }

    public static void totalLCSs(int[][] num, char[] first, char[] second, int firstLength, int secondLength, Set<String> allLCS, String s) {
        if (firstLength == 0 || secondLength == 0) {
            allLCS.add(s);
        } else if (first[firstLength - 1] == second[secondLength - 1]) {
            s = first[firstLength - 1] + s;
            totalLCSs(num, first, second, firstLength - 1, secondLength - 1, allLCS, s);
        } else if (num[firstLength - 1][secondLength] == num[firstLength][secondLength - 1]) {
            totalLCSs(num, first, second, firstLength - 1, secondLength, allLCS, s);
            totalLCSs(num, first, second, firstLength, secondLength - 1, allLCS, s);
        } else if (num[firstLength - 1][secondLength] > num[firstLength][secondLength - 1]) {
            totalLCSs(num, first, second, firstLength - 1, secondLength, allLCS, s);
        } else {
            totalLCSs(num, first, second, firstLength, secondLength - 1, allLCS, s);
        }
    }

    public static void main(String[] args) {
        Set<String> allLCS = new HashSet<>();
        String first = "abcbdab";
        String second = "bdcaba";
        int[][] numbers = lcs(first.toCharArray(),second.toCharArray());
        totalLCSs(numbers, first.toCharArray(), second.toCharArray(), first.length(), second.length(), allLCS,"");

        System.out.println("Rekurencyjnie wszystkie najdłuższe(totalLCSs):");
        for (String xd : allLCS) {
            System.out.println(" "+xd);
        }
    }

}
