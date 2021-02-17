import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ParallelTest {

    @Test
    void Test1() {
        int[][] matrixA = Parallel.generateMatrix(1000, 1000);
        int[][] matrixB = Parallel.generateMatrix(1000, 1000);
        try {
            assertTrue(Arrays.deepEquals(Parallel.multiplyThreads(matrixA, matrixB, 10), Parallel.multiplySequential(matrixA, matrixB)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void Test2() {
        int[][] matrixA = Parallel.generateMatrix(1000, 1000);
        int[][] matrixB = Parallel.generateMatrix(1000, 1000);
        try {
            assertTrue(Arrays.deepEquals(Parallel.multiplyStream(matrixA, matrixB, 10), Parallel.multiplySequential(matrixA, matrixB)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void Tes3() {
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