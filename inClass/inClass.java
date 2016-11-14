package inClass;

public class inClass {
    public double[] movingAverage(double[] arr, int window) {
        if (arr.length < window)
            return null;

        double[] returnArr = new double[arr.length];
        for (int i = 0; i < arr.length; i++) { //i is arr index
            for (int j = 0; j < window; j++) { //j is window index

                // suppose i = 4, j = 2
                if (i + j >= arr.length) {
                    returnArr[i] += 0.0;
                } else {
                    returnArr[i] += arr[i + j];
                }
                returnArr[i] /= window;
            }
            return returnArr;
        }
    }
