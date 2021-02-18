import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;


public abstract class Parallel {

    /*private final int[][] matrixA;
    private final int[][] matrixB;

    // matrixA =[m  * n], matrixB = [n * k]
    private final int m;
    private final int n;
    private final int k;

    public Parallel(int[][] matrixA, int[][] matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;

        this.m = matrixA.length;
        this.n = matrixB.length;
        this.k = matrixB[0].length;

    }*/

    public static int[][] generateMatrix(int rows , int columns) {
        Random r = new Random();
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < columns ; j++) {
                matrix[i][j] = r.nextInt(20);
            }
        }
        return matrix;
    }

    private static int[] multiplyVectorMatrix(int[] vector, int[][] matrix) {
        int[] result = new int[matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++)  {
            result[j] = 0;
            for (int k = 0 ; k < matrix.length; k++) {
                result[j] += vector[k] * matrix[k][j];
            }
        }
        return result;
    }

    private static void checkDimensions(int[][] matrixA, int[][] matrixB) throws Exception {
        if (matrixA[0].length != matrixB.length)  {
            throw new Exception("Error: invalid matrix dimensions");
        }
    }

    public static int[][] multiplyThreads(int[][] matrixA,int[][] matrixB, int nthreads) throws Exception {
        checkDimensions(matrixA, matrixB);

        int[][] matrixC = new int[matrixA.length][matrixB[0].length];
        int threadCount = Math.min(matrixA.length, nthreads);
        threadCount = Math.min(threadCount, Runtime.getRuntime().availableProcessors());

        ArrayList<MyThread> threadList = new ArrayList<>();

        int[] rowsPerThread = new int[threadCount];
        int baseRowsCount = matrixA.length / threadCount, rem = matrixA.length % threadCount;
        int currRow = 0;
        for (int i = 0; i < threadCount; i++) {
            rowsPerThread[i] = baseRowsCount;
            if (rem > 0) {
                rowsPerThread[i]++;
                rem--;
            }
            threadList.add(new MyThread(currRow, rowsPerThread[i], matrixA, matrixB, matrixC));
            currRow += rowsPerThread[i];
        }

        for(MyThread thread : threadList) {
            thread.start();
        }
        for(MyThread thread : threadList) {
            thread.join();
        }
        return matrixC;
    }

    public static int[][] multiplySequential(int[][] matrixA, int[][] matrixB) throws Exception {
        checkDimensions(matrixA, matrixB);

        int[][] matrixC = new int[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            matrixC[i] = multiplyVectorMatrix(matrixA[i], matrixB );
        }
        return matrixC;
    }

    public static int[][] multiplyStream(int[][] matrixA,int[][] matrixB, int nthreads)  throws Exception {
        checkDimensions(matrixA, matrixB);

        int threadCount = Math.min(matrixA.length, nthreads); //???
        threadCount = Math.min(threadCount, Runtime.getRuntime().availableProcessors());
        ForkJoinPool customThreadPool = new ForkJoinPool(threadCount);
        return customThreadPool.submit(() -> Arrays.stream(matrixA).parallel()
                .map(row -> multiplyVectorMatrix(row, matrixB))
                .toArray(int[][]::new)).get();

       /* return customThreadPool.submit(() -> Arrays.stream(matrixA).parallel()
            .map(row -> IntStream.range(0, matrixB[0].length)
            .map(i -> IntStream.range(0, matrixB.length)
            .map(j -> row[j] * matrixB[j][i])
            .sum())
            .toArray())
            .toArray(int[][]::new)).get();*/
    }

    static class MyThread extends Thread {

        private final int startRow;
        private final int rowCount;

        private final int[][] matrixA;
        private final int[][] matrixB;

        private final int[][] matrixC;

        public MyThread(int startRow, int rowCount, int[][] matrixA, int[][] matrixB, int[][] matrixC) {
            this.startRow = startRow;
            this.rowCount = rowCount;
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.matrixC = matrixC;
        }

        @Override
        public void run() {
            for (int i = startRow; i < startRow + rowCount; i++) {
                this.matrixC[i] = multiplyVectorMatrix(matrixA[i], matrixB);
            }
        }
    }
}

