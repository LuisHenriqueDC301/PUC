
import java.util.Date;
import java.util.Random;

/**
 * QuickSort
 */
public class QuickSort {

    public static void main(String[] args) {
        int n = (args.length < 1) ? 1000 : Integer.parseInt(args[0]);
        double inicio, fim;

        int[] array = new int[5000];
        gerarArray(array, "d");

        // Teste QuickSort com pivô fixo (primeiro)
        inicio = now();
        QuickSortFirstPivot(array.clone(), 0, array.length - 1);
        fim = now();
        System.out.println("QuickSort (Primeiro Pivô): " + (fim - inicio) / 1000.0 + " s.");

        // Teste QuickSort com pivô fixo (último)
        inicio = now();
        QuickSortLastPivot(array.clone(), 0, array.length - 1);
        fim = now();
        System.out.println("QuickSort (Último Pivô): " + (fim - inicio) / 1000.0 + " s.");

        // Teste QuickSort com pivô aleatório
        inicio = now();
        QuickSortRandomPivot(array.clone(), 0, array.length - 1);
        fim = now();
        System.out.println("QuickSort (Pivô Aleatório): " + (fim - inicio) / 1000.0 + " s.");

        // Teste QuickSort com mediana de três
        inicio = now();
        QuickSortMedianOfThree(array.clone(), 0, array.length - 1);
        fim = now();
        System.out.println("QuickSort (Mediana de Três): " + (fim - inicio) / 1000.0 + " s.");
    }

    public static long now() {
        return new Date().getTime();
    }

    public static void gerarArray(int[] array, String command) {
        if (command.equals("c")) { // Gera um array crescente
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }
        }

        if (command.equals("d")) { // Gera um array decrescente
            for (int i = array.length; i <= 0; i--) {
                array[i] = i;
            }
        }

        if (command.equals("c-")) { // Gera um array parcialmente crescente
            int middle = array.length / 4;
            for (int i = 0; i < middle; i++) {
                array[i] = i;
            }
            for (int i = middle; i < array.length; i++) {
                array[i] = (int) (Math.random() * array.length);
            }
        }

        if (command.equals("d-")) { // Gera um array parcialmente decrescente
            int middle = array.length / 4;
            for (int i = 0; i < middle; i++) {
                array[i] = array.length - 1 - i;
            }
            for (int i = middle; i < array.length; i++) {
                array[i] = (int) (Math.random() * array.length);
            }
        }

        if (command.equals("r")) { // Gera um array com elementos aleatórios
            Random rand = new Random();
            for (int i = 0; i < array.length; i++) {
                array[i] = rand.nextInt(array.length);
            }
        }
    }

    public static void imprimir(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void QuickSortFirstPivot(int[] array, int esq, int dir) {
        int i = esq, j = dir, pivo = array[esq];
        while (i <= j) {
            while (array[i] < pivo)
                i++;
            while (array[j] > pivo)
                j--;
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            QuickSortFirstPivot(array, esq, j);
        if (i < dir)
            QuickSortFirstPivot(array, i, dir);
    };

    public static void QuickSortLastPivot(int[] array, int esq, int dir) {
        int i = esq, j = dir, pivo = array[dir];
        while (i <= j) {
            while (array[i] < pivo)
                i++;
            while (array[j] > pivo)
                j--;
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            QuickSortLastPivot(array, esq, j);
        if (i < dir)
            QuickSortLastPivot(array, i, dir);
    };

    public static int aleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static void QuickSortRandomPivot(int[] array, int esq, int dir) {
        int i = esq, j = dir, pivo = array[aleatorio(esq, dir)];
        while (i <= j) {
            while (array[i] < pivo)
                i++;
            while (array[j] > pivo)
                j--;
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            QuickSortRandomPivot(array, esq, j);
        if (i < dir)
            QuickSortRandomPivot(array, i, dir);
    };

    public static int medianaDeTres(int[] array, int esq, int dir) {
        int meio = (esq + dir) / 2;
        if (array[meio] < array[esq]) {
            swap(array, meio, esq);
        }
        if (array[meio] < array[dir]) {
            swap(array, meio, dir);
        }
        if (array[esq] < array[meio]) {
            swap(array, esq, meio);
        }
        return array[meio];
    }

    public static void QuickSortMedianOfThree(int[] array, int esq, int dir) {
        int i = esq, j = dir;
        int pivo = medianaDeTres(array, esq, dir);
        while (i <= j) {
            while (array[i] < pivo)
                i++;
            while (array[j] > pivo)
                j--;
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            QuickSortMedianOfThree(array, esq, j);
        if (i < dir)
            QuickSortMedianOfThree(array, i, dir);
    };
}