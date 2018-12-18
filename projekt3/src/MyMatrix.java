/**
 * Created by Lucyna Zielezińska & Kacper Dondziak on 06.12.18.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**********************************************DEFINICJA KLASY****************************************************************/
public class MyMatrix {
    public int rows;
    public int columns;
    public double[][] matrix;
    public double[][] savedMatrix;
    public double vector[];
    public double savedVector[];



    public MyMatrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
        this.savedMatrix = new double[rows][columns];
        this.vector = new double[rows];
        this.savedVector = new double[rows];

    }

    public MyMatrix(double[][] m){
        this.matrix = m;
        this.rows = m.length;
        this.columns = m[0].length;
        this.savedMatrix = new double[rows][columns];
        this.vector = new double[m.length];
        this.savedVector = new double[m.length];
    }

    public void copyMatrix(){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.savedMatrix[i][j] = this.matrix[i][j];
            }
        }
    }

    public void fillMatrixAndVector() {
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
        for(int i = 0;i < this.vector.length; i++){
            try{
                createdNumber =  (Randomizer.generateRandomShort()/65536d);
                this.vector[i] = createdNumber;
                this.savedVector[i] = createdNumber;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public  void printExtendedMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.format("| % .4f ",this.matrix[i][j]);
            }
            System.out.format("|| % .4f", vector[i]);
            System.out.println("");
        }
    }

    /********************************************REDUKCJA MACIERZY ZA POMOCĄ METODY GAUSSA-JORDANA***************************************/

    public double[] gauss(){
        for (int i = 0; i < rows; i++) {
            getEchelonForm(i,i);
        }
        this.reduceMatrix();
        return vector;
    }

    public double[] partChoiceGauss(){
        for (int i = 0; i < rows; i++) {
            findBiggestValueInRow(i);
            getEchelonForm(i,i);
        }
        this.reduceMatrix();
        return vector;
    }
    public double[] partChoiceGaussForSparseMatrix(){
        for (int i = 0; i < rows; i++) {
            findBiggestValueInRow(i);
            getEchelonFormForSparseMatrix(i,i);
        }
        this.reduceMatrix();
        return vector;

    }

    public double[] fulChoiceGauss(){
        ArrayList<Integer> sequence = new ArrayList<>();

        for(int i = 0; i < this.columns; i++) {
            sequence.add(i);
        }
        for (int i = 0; i < rows; i++) {
            findBiggestElementInSubmatrix(i, sequence);
            getEchelonForm(i,i);
        }
        this.reduceMatrix();
        vector = readCorrectValueOfComputedVector(vector,sequence);
        return vector;
    }
    /************************************************DOPROWADZANIE MACIERZY DO POSTACI JEDNOSTKOWEJ**************************************/
    private void getEchelonForm(int xPos, int yPos){
        double temp[] = new double[this.columns]; //Pomocnicza tablica
        double savedValueOfCurrentOperation = matrix[xPos][yPos];
        double help;

        help = getLeading1(xPos, temp, savedValueOfCurrentOperation);

        for(int y = xPos; y < this.rows -1; y++){
                reduceColumn(yPos, temp, help, y);
        }

    }

    private void getEchelonFormForSparseMatrix(int xPos, int yPos){
        double temp[] = new double[this.columns]; //Pomocnicza tablica
        double savedValueOfCurrentOperation = matrix[xPos][yPos];
        double help;

        help = getLeading1(xPos, temp, savedValueOfCurrentOperation);

        for(int y = xPos; y < this.rows -1; y++){
            if(matrix[y][yPos]!=0)
            reduceColumn(yPos, temp, help, y);
        }

    }

    private double getLeading1(int xPos, double[] temp, double savedValueOfCurrentOperation) {
        double help;
        for(int i = 0; i < xPos; i++) {
            temp[i] = 0;
        }
        for(int i = xPos; i < (this.columns); i++){    //Dzielę w celu uzyskania jednyki, przypisują podzieloną wartość do pomocniczej tablicy
            matrix[xPos][i] =  matrix[xPos][i]/(savedValueOfCurrentOperation);
            temp[i] = matrix[xPos][i];
        }
        vector[xPos] = vector[xPos]/(savedValueOfCurrentOperation);
        help =  vector[xPos];
        return help;
    }

    private void reduceColumn(int yPos, double[] temp, double vectorValue, int y) {
        double[] mulTemp;
        double mulHelp;
        mulTemp =  multiplayRowByValue(temp, matrix[y + 1][yPos]);
        mulHelp = vectorValue*(matrix[y+1][yPos]);
        mulHelp = mulHelp*(-1);
        changeSingOfVector(mulTemp);
        matrix[y + 1] = addTwoRows(mulTemp, matrix[y + 1]);
        vector[y+1] = vector[y+1]+mulHelp;
    }

    private void reduceMatrix(){
        for (int i = this.rows - 2; i >= 0; i--){
            for (int y = i; y >= 0; y--){
                vector[y] =vector[y]-(vector[i+1]*matrix[y][i + 1]);
                matrix[y][i + 1] = matrix[y][i + 1]-(matrix[i + 1][i + 1]*matrix[y][i + 1]);
            }
        }
    }

    /*********************************************************DZIAŁANIA NA MACIERZACH**************************************************/

    private double[] addTwoRows(double m1[], double m2[]) {
        double sum[] = new double[m1.length];
        for (int i = 0; i < m1.length; i++) {
            sum[i] =  m1[i]+m2[i];
        }
        return sum;
    }

    private double[] multiplayRowByValue(double[] row, double value) {
        double factor[] = new double[row.length];
        for (int i = 0; i < row.length; i++) {
            factor[i] = row[i]*value;
        }
        return factor;
    }


    private void changeSingOfVector(double vec[]){
        for (int i = 0; i < (this.rows); i++){
            vec[i] = (-1)*vec[i];
        }
    }

    public double[] mulMatrixVector(){
        double[] resultVector = new double[vector.length];
        double sum;
        double product;
        for (int i = 0; i < rows; i++) {
            sum = 0;
            product = 0;
            for (int j = 0; j < rows; j++) {
                product =savedMatrix[i][j]*vector[j];
                sum =sum + product;
            }
            resultVector[i] = sum;
        }
        return resultVector;
    }


    /*************************************************FUNKCJE SWAP**************************************************************/

    public void swapRows(int firstRow, int secondRow) {
        double tempRow[] = matrix[firstRow];
        matrix[firstRow] = matrix[secondRow];
        matrix[secondRow] = tempRow;

        double tempElement = vector[firstRow];
        vector[firstRow] = vector[secondRow];
        vector[secondRow] = tempElement;
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
            if(Math.abs(matrix[maxRow][xPos]) < Math.abs(matrix[i][xPos])){
                maxRow = i;
            }
        }
        swapRows(xPos, maxRow);
    }

    private void findBiggestElementInSubmatrix(int xPos, ArrayList<Integer> sequence){

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


    public  double getNormInf(double[] res, double[] vec){
        double result =  Math.abs(Math.abs(res[0])-Math.abs(vec[0]));
        double diff;
        for(int i = 0; i < vec.length; i++){
            diff = Math.abs(Math.abs(res[i])-Math.abs(vec[i]));
            if(Math.abs(result) < Math.abs(diff)){
                result = Math.abs(diff);
            }
        }
        return result;
    }



}


