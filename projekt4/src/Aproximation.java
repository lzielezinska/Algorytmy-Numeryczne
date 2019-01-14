/**
 * Created by Lucyna Zielezińska & Kacper Dondziak on 11.01.19.
 * source of truth: https://eti.pg.edu.pl/documents/176593/26763380/Wykl_AlgorOblicz_3.pdf?fbclid=IwAR0j0VlZl3hCzUWrsVMSRJH2pjIackIsInTwWYR7BUq3NX-QSmqO9Oysubs
 */
public class Aproximation {

    public static double getResultOfEquasion(double PolynomialCoefficeints[], double xValue){
        double result = 0.0;
        for (int i = 0; i < PolynomialCoefficeints.length; i++) result += PolynomialCoefficeints[i] * Math.pow(xValue, i);

        return  result;

    }
    public static void solveAproximationEquasionForGaussSeidelMethod(double argumnets[], double values[]){
        System.out.println("aaa");
        int degree = 2;
        double precision = 0.0000000001;
        MyMatrix resultMatrix = countAproximationPolynomial(argumnets,values,degree);
        //resultMatrix.printExtendedMatrix();
        IteratedMethod GaussSeidel = new IteratedMethod(resultMatrix, precision);
        System.out.println("****************************");
        GaussSeidel.gaussSeidelMethod();
        GaussSeidel.matrix.printExtendedMatrix();
    }



    public static void solveAproximationEquasionForGaussSparseMatrix(double argumnets[], double values[]){
        int degree = 2;
        MyMatrix resultMatrix = countAproximationPolynomial(argumnets,values,degree);
        //resultMatrix.printExtendedMatrix();
        resultMatrix.partChoiceGaussForSparseMatrix();
        System.out.println("****************************");
        resultMatrix.printExtendedMatrix();
    }


    public static void solveAproximationEquasionForGauss(double argumnets[], double values[]){
        int degree = 3;
        MyMatrix resultMatrix = countAproximationPolynomial(argumnets,values,degree);
        //resultMatrix.printExtendedMatrix();
        resultMatrix.partChoiceGauss();
        System.out.println("****************************");
        resultMatrix.printExtendedMatrix();

    }

    public static MyMatrix countAproximationPolynomial(double argumnets[], double values[], int degree){
        MyMatrix sMatrix = countSCoefficient(argumnets,degree);
        MyMatrix tMatrix = countTCoefficient(values,degree,sMatrix);
        MyMatrix resultMatrix = setResultMatrix(sMatrix.vector, tMatrix.vector);

        return resultMatrix;

    }

    private static MyMatrix countSCoefficient(double array[], int degree){
        MyMatrix sMatrix = new MyMatrix(array.length, 2*degree +1);
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < 2*degree+1; j++){
                sMatrix.matrix[i][j] = Math.pow(array[i], j);           //zapisuje kolejne potęgi x(i) do macierzy
                sMatrix.vector[j] += sMatrix.matrix[i][j];              //zapiuje sumę wszytskich potęg do wektora
            }
        }
        return sMatrix;
    }

    private static MyMatrix countTCoefficient(double array[], int degree, MyMatrix sMatrix){
        MyMatrix tMatrix = new MyMatrix(array.length, degree+1);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < degree+1; j++) {
                tMatrix.matrix[i][j] = array[i]*sMatrix.matrix[i][j];   //zapisuje iloczyn wartości funkcji i kolejnych potęgi x(i) do macierzy
                tMatrix.vector[j] += tMatrix.matrix[i][j];              //zapiuje sumę wszytskich wyników do wektora
            }
        }
        return tMatrix;
    }

    private static MyMatrix setResultMatrix(double sValues[], double tValues[]){
        MyMatrix resultMatrix = new MyMatrix(tValues.length, tValues.length);
        int counter = 0;
        for (int i = 0; i < tValues.length; i++) {
            for (int j = 0; j < tValues.length; j++) resultMatrix.matrix[i][j] = sValues[j+counter];
            resultMatrix.vector[i] = tValues[i];
            counter++;
        }
        return resultMatrix;
    }
}
