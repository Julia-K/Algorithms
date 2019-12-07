public class QuickSort {
    public static void quickSort(String[] A, int p, int r) {
        String x = A[r];
        int st = p;
        int en = r;
        String tempChar;

        while (en >= st) {
            while (A[st].compareTo(x) < 0) {
                st++;
            }

            while (A[en].compareTo(x) > 0) {
                en--;
            }
            if(st <= en) {
                tempChar = A[st];
                A[st] = A[en];
                A[en] = tempChar;
                st++;
                en--;
            }
        }

        if(p<en) {
            quickSort(A,p,en);
        }
        if(st < r) {
            quickSort(A,st,r);
        }
    }
}
