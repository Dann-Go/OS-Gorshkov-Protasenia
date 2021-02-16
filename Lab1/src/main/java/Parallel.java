public abstract class Parallel {

    public int[][] sumThreads(int nthreads, int[][] matrixA, int[][] matrixB ) throws Exception { // a = m  * n, b = n * k
        if (matrixA[0].length != matrixB.length)
        {
            throw new Exception("");
        }

        int[][] matrixC = new int[matrixA.length][matrixB.length];
        for (int i = 0; i < matrixC.length;i++){
            for (int j = 0; j < matrixC[0].length; j++)
            {
                for (int k = 0 ; k < i; k++)
                {
                    matrixC[i][j] = matrixA[i][k] * matrixB[k][j];
                }
            }
        }
    return matrixC;
    }

    class MyThread extends Thread {

        private int sum;

        private int[][] matrixA;
        private int[][] matrixB;

        public MyThread(int[][] matrixA, int[][] matrixB) {
            this.matrixA = matrixA;
            this.matrixB = matrixB;
        }

        @Override
        public void run() {

        }
    }
}
