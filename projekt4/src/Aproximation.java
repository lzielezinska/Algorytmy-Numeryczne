/**
 * Created by Lucyna Zielezińska & Kacper Dondziak on 11.01.19.
 */
public class Aproximation {

    public static double solveAproximationEquasion(double xValue, double argumnets[], double values[], int degree){
        double result = 0.0;
        double resultVector[] = countAproximationPolynomial(argumnets,values,degree);

        for (int i = 0; i < resultVector.length; i++) result += resultVector[i] * Math.pow(xValue, i);

        return result;
    }

    private static double[] countAproximationPolynomial(double argumnets[], double values[], int degree){
        MyMatrix sMatrix = countSCoefficient(argumnets,degree);
        MyMatrix tMatrix = countTCoefficient(values,degree,sMatrix);
        MyMatrix resultMatrix = setResultMatrix(sMatrix.vector, tMatrix.vector);
        resultMatrix.partChoiceGaussForSparseMatrix();

        return resultMatrix.vector;

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
