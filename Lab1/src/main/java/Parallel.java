import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public abstract class Parallel {

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

        int threadCount = Math.min(matrixA.length, nthreads);
        threadCount = Math.min(threadCount, Runtime.getRuntime().availableProcessors());

        ArrayList<MultiplyThread> threadList = new ArrayList<>();

        int[] rowsPerThread = new int[threadCount];
        int baseRowsCount = matrixA.length / threadCount, rem = matrixA.length % threadCount;
        int currRow = 0;
        for (int i = 0; i < threadCount; i++) {
            rowsPerThread[i] = baseRowsCount;
            if (rem > 0) {
                rowsPerThread[i]++;
                rem--;
            }
            threadList.add(new MultiplyThread(currRow, rowsPerThread[i], matrixA, matrixB));
            currRow += rowsPerThread[i];
        }

        for(MultiplyThread thread : threadList) {
            thread.start();
        }
        for(MultiplyThread thread : threadList) {
            thread.join();
        }

        int[][] matrixC = threadList.stream()
                .map(MultiplyThread::getResult).flatMap(Arrays::stream).toArray(int[][]::new);
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

        ForkJoinPool customThreadPool = new ForkJoinPool(nthreads);
        return customThreadPool.submit(() -> Arrays.stream(matrixA).parallel()
                .map(row -> multiplyVectorMatrix(row, matrixB))
                .toArray(int[][]::new)).get();

    }

    static class MultiplyThread extends Thread {

        private final int startRow;
        private final int rowCount;

        private final int[][] matrixA;
        private final int[][] matrixB;

        public int[][] getResult() {
            return result;
        }

        private final int[][] result;

        public MultiplyThread(int startRow, int rowCount, int[][] matrixA, int[][] matrixB) {
            this.startRow = startRow;
            this.rowCount = rowCount;
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.result = new int[rowCount][];
        }

        @Override
        public void run() {
            for (int i = 0; i < rowCount; i++) {
                this.result[i] = multiplyVectorMatrix(matrixA[startRow + i], matrixB);
            }
        }
    }
}

