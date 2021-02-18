import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ParallelTest {

    @Test
    public void Test1() {
        int[][] matrixA = Parallel.generateMatrix(100, 200);
        int[][] matrixB = Parallel.generateMatrix(200, 1000);
        try {
            assertTrue(Arrays.deepEquals(Parallel.multiplyThreads(matrixA, matrixB, 10), Parallel.multiplySequential(matrixA, matrixB)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void Test2() {
        int[][] matrixA = Parallel.generateMatrix(100, 200);
        int[][] matrixB = Parallel.generateMatrix(200, 1000);
        try {
            assertTrue(Arrays.deepEquals(Parallel.multiplyStream(matrixA, matrixB, 10), Parallel.multiplySequential(matrixA, matrixB)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void Test3() {
        int[][] matrixA;
        int[][] matrixB;
        matrixA = Parallel.generateMatrix(30, 30);
        matrixB = Parallel.generateMatrix(30, 30);
        try {
            assertTrue(Arrays.deepEquals(Parallel.multiplyThreads(matrixA, matrixB, 5), Parallel.multiplySequential(matrixA, matrixB)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}