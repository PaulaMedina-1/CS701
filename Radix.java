public class Radix {
    private int maximum(int points[][], int length) {
        int max = points[0][0];
        for (int i = 1; i < length; i++) {
            if (points[i][0] > max) {
                max = points[i][0];
            }
        }
        return max;
    }

    private int[][] generatePointMatrix(Point[] points) {
        int[][] pointMatrix = new int[2][points.length];
        int[] xValues = new int[points.length];
        int[] yValues = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            xValues[i] = points[i].getx();
            yValues[i] = points[i].gety();
        }
        for (int i = 0; i <= points.length; i++) {
            pointMatrix[i][0] = xValues[i];
            pointMatrix[i][1] = yValues[i];
        }
        return pointMatrix;
    }

    private void countingSort(Point[] points, int length, int xVal) {
        int[][] pointMatrix = generatePointMatrix(points);
        int output[] = new int[length];

        int countpointMatrix[] = new int[10];
        for (int j = 0; j < countpointMatrix.length; j++) {
            countpointMatrix[j] = 0;
        }

        for (int i = 0; i < length; i++) {
            countpointMatrix[(pointMatrix[i][0] / xVal) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            countpointMatrix[i] += countpointMatrix[i - 1];

        }

        for (int i = length - 1; i >= 0; i--) {
            output[countpointMatrix[(pointMatrix[i][0] / xVal) % 10] - 1] = pointMatrix[i][0];
            countpointMatrix[(pointMatrix[i][0] / xVal) % 10]--;
        }
        for (int i = 0; i < length; i++) {
            pointMatrix[i][0] = output[i];

        }
    }


    public void radixSort(Point[] points, int length) {
        int[][] pointMatrix = new int[points.length][];
        for (int i = 0; i < length; i++) {
            int maximum = maximum(pointMatrix, length);
        }
    }
}
