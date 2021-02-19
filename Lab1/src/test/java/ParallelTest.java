import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ParallelTest {
    private final int[][] matrixA = { {1, 4, 6, 8, 9},
                                      {2, 6, 8, 42, 3},
                                      {3, 16, 18, 2, 23}};

    private final int[][] matrixB = { {1, 4, 6, 8, 9},
                                      {2, 6, 8, 42, 3},
                                      {3, 16, 18, 2, 23},
                                      {33, 12, 8, 22, 45},
                                      {31, 14, 1, 23, 3} };


    @Test
    public void ThreadsCorrectTest() {

        try {
            assertTrue(Arrays.deepEquals(Parallel.multiplyThreads(matrixA, matrixB, 2),
                                         Parallel.multiplySequential(matrixA, matrixB)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamCorrectTest() {

        try {
            assertTrue(Arrays.deepEquals(Parallel.multiplyStream(matrixA, matrixB, 10),
                                         Parallel.multiplySequential(matrixA, matrixB)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int[][] matrixC = Parallel.generateMatrix(800, 1000);
    int[][] matrixD = Parallel.generateMatrix(1000, 800);

    @Test
    public void ThreadsTimeTest1() {
        try {
            Parallel.multiplyThreads(matrixC, matrixD, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ThreadsTimeTest2() {
        try {
            Parallel.multiplyThreads(matrixC, matrixD, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ThreadsTimeTest4() {
        try {
            Parallel.multiplyThreads(matrixC, matrixD, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ThreadsTimeTest8() {
        try {
            Parallel.multiplyThreads(matrixC, matrixD, 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ThreadsTimeTest12() {
        try {
            Parallel.multiplyThreads(matrixC, matrixD, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void StreamTimeTest1() {
        try {
            Parallel.multiplyStream(matrixC, matrixD, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamTimeTest2() {
        try {
            Parallel.multiplyStream(matrixC, matrixD, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamTimeTest4() {
        try {
            Parallel.multiplyStream(matrixC, matrixD, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamTimeTest8() {
        try {
            Parallel.multiplyStream(matrixC, matrixD, 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StreamTimeTest12() {
        try {
            Parallel.multiplyStream(matrixC, matrixD, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}