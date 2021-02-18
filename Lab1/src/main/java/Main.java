import javax.sql.rowset.spi.SyncFactory;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int[][] matrixA = Parallel.generateMatrix(1000, 500);
        int[][] matrixB = Parallel.generateMatrix(500, 1000);

        int[][] matrixC ;
        for (int i = 1 ; i < 10 ; i++) {
            try {

                System.out.println("Threads " + i);
                start = System.currentTimeMillis();
                matrixC = Parallel.multiplyThreads(matrixA, matrixB, i);

                System.out.println("Threads: " + (System.currentTimeMillis() - start) + " ms");
                start = System.currentTimeMillis();

                matrixC = Parallel.multiplyStream(matrixA, matrixB, i);
                System.out.println("Stream: " + (System.currentTimeMillis() - start) + " ms");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

