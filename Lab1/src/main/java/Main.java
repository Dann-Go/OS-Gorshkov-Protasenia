import javax.sql.rowset.spi.SyncFactory;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int[][] matrixA = Parallel.generateMatrix(1000, 1000);
        int[][] matrixB = Parallel.generateMatrix(1000, 1000);

        int[][] matrixC = Parallel.multiplySequential(matrixA, matrixB);
        System.out.println("Sequential: " + (System.currentTimeMillis() - start) + " ms");

        try {
            start = System.currentTimeMillis();
            matrixC = Parallel.multiplyThreads(matrixA, matrixB,6);

            System.out.println("Threads: " + (System.currentTimeMillis() - start) + " ms");
            start = System.currentTimeMillis();

            matrixC = Parallel.multiplyStream(matrixA, matrixB, 6);
            System.out.println("Stream: " + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

