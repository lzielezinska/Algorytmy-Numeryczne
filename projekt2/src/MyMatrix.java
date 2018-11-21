import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lucyna on 02.11.18.
 * informatyka rok 3
 * tester programista
 * Algorytmy Numeryczne
 * Projekt 2
 */



/****************************************************DEFINICJA KLASY***********************************************/

public class MyMatrix<T extends ANumber<T>> {
    private int rows;
    private int columns;
    private Class type;
    private T[][] matrix;
    public T[][] savedMatrix;
    public T[] vector;
    private T staticObject;
    private T[] savedVector;

    public MyMatrix(int rows, int columns, Class type) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = (T[][]) Array.newInstance(type,rows,columns);
        this.savedMatrix = (T[][]) Array.newInstance(type,rows,columns);;
        this.type = type;
        this.vector = (T[]) Array.newInstance(type, rows);
        this.savedVector =  (T[]) Array.newInstance(type, rows);
        try{
            this.staticObject = (T) TypeFabric.CreateNumber(type);
        } catch (Exception e){
        }
    }
    public MyMatrix(T[][] mat, T[] vec, int rows, int columns, Class type){
        this(rows,columns,type);
        this.matrix = mat;
        this.vector = vec;

        for(int i = 0;i < rows; i++){
            this.savedVector[i] = this.vector[i];
            for(int j = 0;j < rows; j++){
                this.savedMatrix[i][j] = this.matrix[i][j];
            }
        }
    }

    public void setDebugVaules(){
        this.rows = 4;
        this.columns = 4;
        this.matrix = (T[][]) Array.newInstance(type,rows,columns);
    }

    public ANumber[] getSavedVector(){
        return this.savedVector;
    }


    /***************************************************WYPEŁNANIE MACIERZY *********************************************************/
    public void fillMatrixAndVector() {
        T createdNumber;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                try{
                    createdNumber = (T)TypeFabric.CreateNumber(this.type);
                    this.matrix[i][j] = createdNumber;
                    this.savedMatrix[i][j] = createdNumber;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        for(int i = 0;i < this.vector.length; i++){
            try{
                createdNumber = (T)TypeFabric.CreateNumber(this.type);
                this.vector[i] = createdNumber;
                this.savedVector[i] = createdNumber;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public  void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.format("| % .4f ",this.matrix[i][j].doubleValue());
                //System.out.print("| " + this.matrix[i][j].toString());
            }
            System.out.format("|| % .6f", this.vector[i].doubleValue());
            System.out.println("");
        }
    }

    /********************************************REDUKCJA MACIERZY ZA POMOCĄ METODY GAUSSA-JORDANA***************************************/

    public ANumber[] gauss(){
        for (int i = 0; i < rows; i++) {
            get0(i,i);
        }
        this.reduceMatrix();
        return this.vector;
    }

    public ANumber[] partChoiceGauss(){
        for (int i = 0; i < rows; i++) {
            findBiggestValueInRow(i);
            get0(i,i);
        }
        this.reduceMatrix();
        return this.vector;
    }
    public ANumber[] fulChoiceGauss(){
        ArrayList<Integer> sequence = new ArrayList<>();

        for(int i = 0; i < this.columns; i++) {
            sequence.add(i);
        }
        for (int i = 0; i < rows; i++) {
            findBiggestElementInSubmatrix(i, sequence);
            get0(i,i);
        }
        this.reduceMatrix();
        this.vector = readCorrectValueOfComputedVector(this.vector,sequence);
        return this.vector;
    }
    /************************************************DOPROWADZANIE MACIERZY DO POSTACI JEDNOSTKOWEJ**************************************/

    public void get0(int xPos, int yPos){
        ANumber[] result = null;
        T temp[] = (T[]) Array.newInstance(type, this.columns); //Pomocnicza tablica
        T mulTemp[] = (T[]) Array.newInstance(type, this.columns); //Pomocnicza tablica
        T savedValueOfCurrentOperation = matrix[xPos][yPos];
        T help;
        T mulHelp;

        for(int i = 0; i < xPos; i++) {
            temp[i] = staticObject.returnZero();
        }
        for(int i = xPos; i < (this.columns); i++){    //Dzielę w celu uzyskania jednyki, przypisują podzieloną wartość do pomocniczej tablicy
            matrix[xPos][i] =  matrix[xPos][i].div(savedValueOfCurrentOperation);
            temp[i] = matrix[xPos][i];
        }
        this.vector[xPos] = this.vector[xPos].div(savedValueOfCurrentOperation);
        help =  this.vector[xPos];

        for(int y = xPos; y < this.rows -1; y++){
            mulTemp = (T[]) multiplayRowByValue(temp, matrix[y + 1][yPos]);
            mulHelp = help.mul(matrix[y+1][yPos]);
            mulHelp = mulHelp.changeSign();
            changeSingOfVector(mulTemp);
            matrix[y + 1] = (T[])addTwoRows(mulTemp, matrix[y + 1]);
            this.vector[y+1] = this.vector[y+1].add(mulHelp);
        }

    }

    private void reduceMatrix(){
        for (int i = this.rows - 2; i >= 0; i--){
            for (int y = i; y >= 0; y--){
                this.vector[y] =this.vector[y].sub(this.vector[i+1].mul(matrix[y][i + 1]));
                matrix[y][i + 1] = matrix[y][i + 1].sub(matrix[i + 1][i + 1].mul(matrix[y][i + 1]));
            }
        }
    }

    /*********************************************************DZIAŁANIA NA MACIERZACH**************************************************/

    public T[] mulMatrixVector(){
        T[] resultVector = (T[]) Array.newInstance(type, vector.length);
        T sum;
        T product;
        for (int i = 0; i < rows; i++) {
            sum = (T)staticObject.returnZero();
            product = (T)staticObject.returnZero();
            for (int j = 0; j < rows; j++) {
                product =(T) savedMatrix[i][j].mul((T)vector[j]);
                sum = (T) sum.add(product);
            }
            resultVector[i] = sum;
        }
        return resultVector;
    }


    public T[] addTwoRows(ANumber m1[], ANumber m2[]) {
        T sum[] = (T[]) Array.newInstance(this.type, m1.length);
        for (int i = 0; i < m1.length; i++) {
            sum[i] = (T) m1[i].add(m2[i]);
        }
        return sum;
    }

    public T[] multiplayRowByValue(ANumber[] row, ANumber value) {
        T factor[] = (T[]) Array.newInstance(this.type, row.length);
        for (int i = 0; i < row.length; i++) {
            factor[i] = (T) row[i].mul(value);
        }
        return factor;
    }

    public ANumber[] devideRowByValue(ANumber[] row, ANumber value) {
        ANumber quotion[] = new ANumber[row.length];
        for (int i = 0; i < row.length; i++) {
            quotion[i] = (ANumber) row[i].div(value);
        }
        return quotion;
    }

    private void changeSingOfVector(T[] vec){
        for (int i = 0; i < (this.rows); i++){
            vec[i] = (T) (vec[i].changeSign());
        }
    }

    /*************************************************FUNKCJE SWAP**************************************************************/

    public void swapRows(int firstRow, int secondRow) {
        T tempRow[] = matrix[firstRow];
        matrix[firstRow] = matrix[secondRow];
        matrix[secondRow] = tempRow;

        T tempElement = vector[firstRow];
        vector[firstRow] = vector[secondRow];
        vector[secondRow] = tempElement;
    }

    public void swapColumns(int firstColumn, int secondColumn) {
        for (int i = 0; i < rows; i++) {
            T tmp = matrix[i][firstColumn];
            matrix[i][firstColumn] = matrix[i][secondColumn];
            matrix[i][secondColumn] = tmp;
        }
    }


    /*****************************************POMOCNICZE METODY DO CZĘŚCIOWEGO I PEŁNEGO WYBORU*************************************************/

    public void findBiggestValueInRow(int xPos){

        int maxRow = xPos;
        for(int i = xPos; i< rows; i++){
            if(matrix[maxRow][xPos].compareTo(matrix[i][xPos])==1) maxRow = i;
        }
        swapRows(xPos, maxRow);
    }

    public void findBiggestElementInSubmatrix(int xPos, ArrayList<Integer> sequence){

        int maxRow = xPos;
        int maxColumn = xPos;
        for(int i = xPos; i< rows; i++){
            for(int j = xPos; j<columns;j++) {
                if (matrix[maxRow][maxColumn].compareTo(matrix[i][j]) == 1){
                    maxRow = i;
                    maxColumn = j;
                }
            }
        }
        swapRows(xPos, maxRow);
        swapColumns(xPos,maxColumn);
        Collections.swap(sequence, xPos, maxColumn);
    }

    private T[] readCorrectValueOfComputedVector(T[] resultVector, List<Integer> seq) {
        T[] newResultVector = (T[]) Array.newInstance(type, resultVector.length);

        for (int i = 0; i < seq.size(); i++) newResultVector[seq.get(i)] = resultVector[i];

        return newResultVector;
    }
    /********************************************METODY DO HIPOTEZ**************************************/

    public  double getNormInf(T[] res, T[] vec){
        T result = (T) res[0].abs().sub(vec[0].abs()).abs();
        T diff;
        for(int i = 0; i < vec.length; i++){
            diff = (T) res[i].abs().sub(vec[i].abs()).abs();
            //System.out.println(res[i] +" "+ vec[i]+" "+ diff);
            if(result.abs().compareTo(diff.abs()) == -1){
                result = diff.abs();
            }
        }
        return result.doubleValue();
    }


}

