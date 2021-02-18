
public class Main {

  /*  public static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%4d", ints[j]);
            }
            System.out.println();
        }
    }*/

    public static void main(String[] args) {
        long start;
        int[][] matrixA = Parallel.generateMatrix(5, 1000);
        int[][] matrixB = Parallel.generateMatrix(1000, 1000);

        /*printMatrix(matrixA);
        System.out.println();
        printMatrix(matrixB);*/

        int[][] matrixC;
        for (int i = 20; i <= 20; i++) {
            try {
                System.out.println("Thread count: " + i);

                start = System.currentTimeMillis();
                matrixC = Parallel.multiplyThreads(matrixA, matrixB, i);
                System.out.println("Threads: " + (System.currentTimeMillis() - start) + " ms");

                start = System.currentTimeMillis();
                matrixC = Parallel.multiplyStream(matrixA, matrixB, i);
                System.out.println("Stream: " + (System.currentTimeMillis() - start) + " ms");

                //printMatrix(matrixC);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

