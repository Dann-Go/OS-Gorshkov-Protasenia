import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ParallelTest {

    @Test
    public void ThreadsCorrectTest() {
        int[][] matrixA = Parallel.generateMatrix(100, 200);
        int[][] matrixB = Parallel.generateMatrix(200, 100);
        try {
            assertTrue(Arrays.deepEquals(Parallel.multiplyThreads(matrixA, matrixB, 10),
                                         Parallel.multiplySequential(matrixA, matrixB)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamCorrectTest() {
        int[][] matrixA = Parallel.generateMatrix(100, 200);
        int[][] matrixB = Parallel.generateMatrix(200, 100);
        try {
            assertTrue(Arrays.deepEquals(Parallel.multiplyStream(matrixA, matrixB, 10),
                                         Parallel.multiplySequential(matrixA, matrixB)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int[][] matrixA = Parallel.generateMatrix(800, 1000);
    int[][] matrixB = Parallel.generateMatrix(1000, 800);

    @Test
    public void ThreadsTimeTest1() {
        try {
            Parallel.multiplyThreads(matrixA, matrixB, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ThreadsTimeTest2() {
        try {
            Parallel.multiplyThreads(matrixA, matrixB, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ThreadsTimeTest4() {
        try {
            Parallel.multiplyThreads(matrixA, matrixB, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ThreadsTimeTest8() {
        try {
            Parallel.multiplyThreads(matrixA, matrixB, 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ThreadsTimeTest12() {
        try {
            Parallel.multiplyThreads(matrixA, matrixB, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void StreamTimeTest1() {
        try {
            Parallel.multiplyStream(matrixA, matrixB, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamTimeTest2() {
        try {
            Parallel.multiplyStream(matrixA, matrixB, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamTimeTest4() {
        try {
            Parallel.multiplyStream(matrixA, matrixB, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamTimeTest8() {
        try {
            Parallel.multiplyStream(matrixA, matrixB, 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamTimeTest12() {
        try {
            Parallel.multiplyStream(matrixA, matrixB, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}