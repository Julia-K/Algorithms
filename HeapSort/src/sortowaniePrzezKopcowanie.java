import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;

public class sortowaniePrzezKopcowanie {

    public static void heapSort(int[] tab) {
        buildHeap(tab);
        int temp;
        int n = tab.length;
        for(int i=tab.length-1; i>0;i--) {
            temp = tab[i];
            tab[i] = tab[0];
            tab[0] = temp;
            heapify(tab,--n,0);
        }
    }

    private static void buildHeap(int[] tab) {
        int n = tab.length;
        for(int i = (n/2)-1; i>=0; i--) {
            heapify(tab,n,i);
        }
    }

    ///////////////////////////////////////////
    ////////// HEAPIFY REKURENCYJNIE //////////
    ///////////////////////////////////////////
    /*public static void heapify(int[] tab, int heapSize, int parentIndex) {
        int maxIndex;
        int leftChild = 2*parentIndex+1;
        int rightChild = 2*parentIndex+2;
        if(leftChild < heapSize && tab[leftChild] > tab[parentIndex]) {
            maxIndex = leftChild;
        } else {
            maxIndex = parentIndex;
        }
        if(rightChild < heapSize && tab[rightChild] > tab[maxIndex]) {
            maxIndex = rightChild;
        }
        if(maxIndex != parentIndex) {
            int temp = tab[maxIndex];
            tab[maxIndex] = tab[parentIndex];
            tab[parentIndex] = temp;
            heapify(tab,heapSize,maxIndex);
        }
    }*/
    /////////////////////////////////////////
    ////////// HEAPIFY ITERACYJNIE //////////
    /////////////////////////////////////////
   private static void heapify(int[] tab, int heapSize, int parentIndex) {
       int maxIndex;
       while (parentIndex<=heapSize) {
           int leftChild = 2*parentIndex+1;
           int rightChild = 2*parentIndex+2;
           if(leftChild < heapSize && tab[leftChild] > tab[parentIndex]) {
               maxIndex = leftChild;
           } else {
               maxIndex = parentIndex;
           }
           if(rightChild < heapSize && tab[rightChild] > tab[maxIndex]) {
               maxIndex = rightChild;
           }
           if(maxIndex!=parentIndex) {
               int temp = tab[maxIndex];
               tab[maxIndex] = tab[parentIndex];
               tab[parentIndex] = temp;
               parentIndex = maxIndex;
           } else {
               break;
           }
       }
   }


    public static void main(String[] args) throws IOException{
        RandomAccessFile fileReader = new RandomAccessFile("input.txt","r");
        int n = 0;
        int i = 0;
        String linia;

        while (fileReader.readLine()!=null) {
            n++;
        }

        int[] tab = new int[n];
        fileReader.seek(0);

        while ((linia = fileReader.readLine()) != null) {
            int numer = Integer.parseInt(linia);
            tab[i] = numer;
            i++;
        }
        fileReader.close();

        System.out.print("Elementy w tablicy: ");
        for (int x : tab) {
            System.out.print(x + " ");
        }

        heapSort(tab);

        Writer outputFile = new FileWriter("output.txt");
        System.out.print("\nPosortowane: ");
        for(int x : tab) {
            outputFile.write(x+"\n");
            System.out.print(x + " ");
        }
        outputFile.close();

    }
}
