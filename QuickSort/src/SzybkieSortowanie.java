import java.util.Random;

public class SzybkieSortowanie {

    private static void bubbleSort(int[] A,int p, int r) {
        int temp;

        for (int i = p; i <= r; i++) {
            for (int j = i+1; j < r; j++) {
                if (A[i] > A[j]) {
                    temp = A[j];
                    A[j] = A[i];
                    A[i] = temp;
                }
            }
        }
    }

    private static int partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p-1;

        for (int j = p; j<=r; j++) {
            if(A[j]<=x) {
                i++;
                int temp = A[i];
                A[i]=A[j];
                A[j]=temp;
            }
        }

        if(i<r) {
            return i;
        }
            return i-1;
    }

    private static void quickSort(int[] A, int p, int r) {
        if(p<r) {
            int q = partition(A,p,r);
            quickSort(A,p,q);
            quickSort(A,q+1,r);
        }
    }

    private static void quickSort2(int[] A, int p, int r) {
        int c = 10;
        if(p<r) {
            if(r - p + 1 < c) {
                bubbleSort(A,p,r);
            } else {
                int q = partition(A,p,r);
                quickSort2(A,p,q);
                quickSort2(A,q+1,r);
            }
        }
    }

    private static int[] randFill(int n) {
        Random random = new Random();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = random.nextInt(n+1);
        }
        return A;
    }

    private static int[] descFill(int n) {
        int[] A = new int[n];
        int length = n;
        for (int i = 0; i < length; i++) {
            A[i] = n;
            n--;
        }
        return A;
    }

    public static void main(String[] args) {
        long startTime1, endTime1, startTime2, endTime2;
        double totalTime1,totalTime2;
        int[] X = {1,9,413,0,-4,1,0,54,0};

        System.out.print("Przed sortowaniem: ");
        for (int x : X) {
            System.out.print(x + " ");
        }
        quickSort(X,0,X.length-1);
        System.out.print("\nPo sortowaniu: ");
        for (int x : X) {
            System.out.print(x+" ");
        }

        System.out.println("\n\nDANE LOSOWE");
        System.out.println("| rozmiar tablicy | czas - algorytm 1 | czas - algorytm 2 |");
        System.out.println(" ----------------- ------------------- -------------------");
        for (int n = 10; n <= 25000; n*=2) {
           int[] A = randFill(n);
           int[] B = A.clone();

           startTime1 = System.nanoTime();
           quickSort(B,0,B.length-1);
           endTime1 = System.nanoTime();

           startTime2 = System.nanoTime();
           quickSort2(A,0,A.length-1);
           endTime2 = System.nanoTime();

           totalTime1 = (endTime1-startTime1)/100000000.0;
           totalTime2 = (endTime2 - startTime2)/100000000.0;
           System.out.format("%17d %19f %19f\n",n,totalTime1,totalTime2);
        }

        System.out.println("\n\nDANE NIEKORZYSTNE");
        System.out.println("| rozmiar tablicy | czas - algorytm 1 | czas - algorytm 2 |");
        System.out.println(" ----------------- ------------------- -------------------");
        for (int n = 10; n <= 20000; n*=2) {
            int[] A = descFill(n);
            int[] B = A.clone();

            startTime1 = System.nanoTime();
            quickSort(B,0,B.length-1);
            endTime1 = System.nanoTime();

            startTime2 = System.nanoTime();
            quickSort2(A,0,A.length-1);
            endTime2 = System.nanoTime();

            totalTime1 = (endTime1-startTime1)/100000000.0;
            totalTime2 = (endTime2 - startTime2)/100000000.0;
            System.out.format("%17d %19f %19f\n",n,totalTime1,totalTime2);
        }
    }
}


