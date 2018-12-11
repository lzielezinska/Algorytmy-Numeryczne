/**
 * Created by Lucyna Zielezińska & Kacper Dondziak on 06.12.18.
 */


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**********************************************DEFINICJA KLASY****************************************************************/
public class MyMatrix {
    public int rows;
    public int columns;
    public double[][] matrix;
    public double[][] savedMatrix;

    public MyMatrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
        this.savedMatrix = new double[rows][columns];
    }

    public void fillMatrix() {
        double createdNumber = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                try{
                    createdNumber =  (Randomizer.generateRandomShort()/65536d);
                    this.matrix[i][j] = createdNumber;
                    this.savedMatrix[i][j] = createdNumber;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public  void printExtendedMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.format("| % .4f ",this.matrix[i][j]);
            }
            System.out.format("|| % .6f", Vector.vector[i]);
            System.out.println("");
        }
    }

    /********************************************REDUKCJA MACIERZY ZA POMOCĄ METODY GAUSSA-JORDANA***************************************/

    public double[] gauss(){
        for (int i = 0; i < rows; i++) {
            get0(i,i);
        }
        this.reduceMatrix();
        return Vector.vector;
    }

    public double[] partChoiceGauss(){
        for (int i = 0; i < rows; i++) {
            findBiggestValueInRow(i);
            get0(i,i);
        }
        this.reduceMatrix();
        return Vector.vector;
    }
    public double[] fulChoiceGauss(){
        ArrayList<Integer> sequence = new ArrayList<>();

        for(int i = 0; i < this.columns; i++) {
            sequence.add(i);
        }
        for (int i = 0; i < rows; i++) {
            findBiggestElementInSubmatrix(i, sequence);
            get0(i,i);
        }
        this.reduceMatrix();
        Vector.vector = readCorrectValueOfComputedVector(Vector.vector,sequence);
        return Vector.vector;
    }
    /************************************************DOPROWADZANIE MACIERZY DO POSTACI JEDNOSTKOWEJ**************************************/

    public void get0(int xPos, int yPos){
        double[] result = null;
        double temp[] = new double[this.columns]; //Pomocnicza tablica
        double mulTemp[] =  new double[this.columns];  //Pomocnicza tablica
        double savedValueOfCurrentOperation = matrix[xPos][yPos];
        double help;
        double mulHelp;

        for(int i = 0; i < xPos; i++) {
            temp[i] = 0;
        }
        for(int i = xPos; i < (this.columns); i++){    //Dzielę w celu uzyskania jednyki, przypisują podzieloną wartość do pomocniczej tablicy
            matrix[xPos][i] =  matrix[xPos][i]/(savedValueOfCurrentOperation);
            temp[i] = matrix[xPos][i];
        }
        Vector.vector[xPos] = Vector.vector[xPos]/(savedValueOfCurrentOperation);
        help =  Vector.vector[xPos];

        for(int y = xPos; y < this.rows -1; y++){
            mulTemp =  multiplayRowByValue(temp, matrix[y + 1][yPos]);
            mulHelp = help*(matrix[y+1][yPos]);
            mulHelp = mulHelp*(-1);
            changeSingOfVector(mulTemp);
            matrix[y + 1] = addTwoRows(mulTemp, matrix[y + 1]);
            Vector.vector[y+1] = Vector.vector[y+1]+mulHelp;
        }

    }

    private void reduceMatrix(){
        for (int i = this.rows - 2; i >= 0; i--){
            for (int y = i; y >= 0; y--){
                Vector.vector[y] =Vector.vector[y]-(Vector.vector[i+1]*matrix[y][i + 1]);
                matrix[y][i + 1] = matrix[y][i + 1]-(matrix[i + 1][i + 1]*matrix[y][i + 1]);
            }
        }
    }

    /*********************************************************DZIAŁANIA NA MACIERZACH**************************************************/

    public double[] mulMatrixVector(){
        double[] resultVector = new double[Vector.vector.length];
        double sum;
        double product;
        for (int i = 0; i < rows; i++) {
            sum = 0;
            product = 0;
            for (int j = 0; j < rows; j++) {
                product =(this.savedMatrix[i][j]*(Vector.vector[j]));
                sum = sum+product;
            }
            resultVector[i] = sum;
        }
        return resultVector;
    }


    public double[] addTwoRows(double m1[], double m2[]) {
        double sum[] = new double[m1.length];
        for (int i = 0; i < m1.length; i++) {
            sum[i] =  m1[i]+m2[i];
        }
        return sum;
    }

    public double[] multiplayRowByValue(double[] row, double value) {
        double factor[] = new double[row.length];
        for (int i = 0; i < row.length; i++) {
            factor[i] = row[i]*value;
        }
        return factor;
    }

    public double[] devideRowByValue(double[] row, double value) {
        double quotion[] = new double[row.length];
        for (int i = 0; i < row.length; i++) {
            quotion[i] =  row[i]/value;
        }
        return quotion;
    }

    private void changeSingOfVector(double vec[]){
        for (int i = 0; i < (this.rows); i++){
            vec[i] = (-1)*vec[i];
        }
    }

    /*************************************************FUNKCJE SWAP**************************************************************/

    public void swapRows(int firstRow, int secondRow) {
        double tempRow[] = matrix[firstRow];
        matrix[firstRow] = matrix[secondRow];
        matrix[secondRow] = tempRow;

        double tempElement = Vector.vector[firstRow];
        Vector.vector[firstRow] = Vector.vector[secondRow];
        Vector.vector[secondRow] = tempElement;
    }

    public void swapColumns(int firstColumn, int secondColumn) {
        for (int i = 0; i < rows; i++) {
            double tmp = matrix[i][firstColumn];
            matrix[i][firstColumn] = matrix[i][secondColumn];
            matrix[i][secondColumn] = tmp;
        }
    }


    /*****************************************POMOCNICZE METODY DO CZĘŚCIOWEGO I PEŁNEGO WYBORU*************************************************/

    public void findBiggestValueInRow(int xPos){

        int maxRow = xPos;
        for(int i = xPos; i< rows; i++){
            if(matrix[maxRow][xPos] < matrix[i][xPos]) maxRow = i;
        }
        swapRows(xPos, maxRow);
    }

    public void findBiggestElementInSubmatrix(int xPos, ArrayList<Integer> sequence){

        int maxRow = xPos;
        int maxColumn = xPos;
        for(int i = xPos; i< rows; i++){
            for(int j = xPos; j<columns;j++) {
                if (matrix[maxRow][maxColumn] < matrix[i][j]){
                    maxRow = i;
                    maxColumn = j;
                }
            }
        }
        swapRows(xPos, maxRow);
        swapColumns(xPos,maxColumn);
        Collections.swap(sequence, xPos, maxColumn);
    }

    private double[] readCorrectValueOfComputedVector(double[] resultVector, List<Integer> seq) {
        double[] newResultVector =new double[resultVector.length];

        for (int i = 0; i < seq.size(); i++) newResultVector[seq.get(i)] = resultVector[i];

        return newResultVector;
    }
    /********************************************METODY DO HIPOTEZ**************************************/

//    public  double getNormInf(T[] res, T[] vec){
//        T result = (T) res[0].abs().sub(vec[0].abs()).abs();
//        T diff;
//        for(int i = 0; i < vec.length; i++){
//            diff = (T) res[i].abs().sub(vec[i].abs()).abs();
//            //System.out.println(res[i] +" "+ vec[i]+" "+ diff);
//            if(result.abs().compareTo(diff.abs()) == -1){
//                result = diff.abs();
//            }
//        }
//        return result.doubleValue();
//    }



}


