import java.lang.reflect.Array;

/**
 * Created by Lucyna on 02.11.18.
 * informatyka rok 3
 * tester programista
 * Algorytmy Numeryczne
 * Projekt 2
 */
public class MyMatrix<T extends ANumber<T>> {
    private int rows;
    private int columns;
    private Class type;
    private T[][] matrix;
    private T[] vector;

    public MyMatrix(int rows, int columns, Class type) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = (T[][]) Array.newInstance(type,rows,columns);
        this.type = type;
        this.vector = (T[]) Array.newInstance(type, rows);
    }

    public void setDebugVaules(){
        this.rows = 4;
        this.columns = 4;
        this.matrix = (T[][]) Array.newInstance(type,rows,columns);
    }

    public ANumber[] gauss(){
        ANumber[] resultVector = (T[]) Array.newInstance(type, rows);
        for(int i=0;i<vector.length;i++) resultVector[i] = vector[i];
        //TODO Here make gauss method body.

        for (int i = 0; i < rows-1; i++) {
            for (int j = i; j < columns-1; j++) {
                resultVector = get0(i,j, resultVector);
            }
        }

        return resultVector;
    }
    public ANumber[] partChoiceGauss(){
        ANumber[] resultVector = (T[]) Array.newInstance(type, rows);
        for(int i=0;i<vector.length;i++) resultVector[i] = vector[i];

        //TODO Here make part choice gauss method body.
        for (int i = 0; i < rows-1; i++) {
            resultVector = findBiggestValueInRow(i,resultVector);
            for (int j = i; j < columns-1; j++) {
                get0(i, j, resultVector);
            }
        }
        return resultVector;
    }
    public ANumber[] fulChoiceGauss(){
        ANumber[] resultVector = (T[]) Array.newInstance(type, rows);
        for(int i=0;i<vector.length;i++) resultVector[i] = vector[i];

        //TODO Here make full choice gauss method body.
        for (int i = 0; i < rows-1; i++) {
            findBiggestElementInSubmatrix(i, resultVector);
            for (int j = i; j < columns-1; j++) {
                get0(i, j, resultVector);
            }
        }

        return resultVector;
    }

    public ANumber[] mulMatrixVector(ANumber[] vector){
        ANumber[] resultVector = (T[]) Array.newInstance(type, vector.length);
        //TODO Here add multiplying Matrix * Vector method body.

        for (int i = 0; i < rows; i++) {
            ANumber sum, product;
            for (int j = 0; j < rows; j++) {
                product =(ANumber)matrix[i][j].mul((T)vector[j]);
                sum = sum.add(product);
            }
            resultVector[i] = sum;
        }
        return resultVector;
    }

    public void fillMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                try{
                    this.matrix[i][j] = (T)TypeFabric.CreateNumber(this.type);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public  void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.format("| % .16f ",this.matrix[i][j].doubleValue());
            }
            System.out.println("|");
        }
    }

    public ANumber[] addTwoRows(ANumber m1[], ANumber m2[]) {
        ANumber sum[] = new ANumber[m1.length];
        for (int i = 0; i < m1.length; i++) {
            sum[i] = (ANumber) m1[i].add(m2[i]);
        }
        return sum;
    }

    public ANumber[] multiplayRowByValue(ANumber[] row, ANumber value) {
        ANumber factor[] = new ANumber[row.length];
        for (int i = 0; i < row.length; i++) {
            factor[i] = (ANumber) row[i].mul(value);
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

    public ANumber[] swap(int firstRow, int secondRow, ANumber resultVector[]) {
        ANumber temp = resultVector[firstRow];
        resultVector[firstRow] = resultVector[secondRow];
        resultVector[secondRow] = temp;

        return resultVector;
    }

    public void swapRows(int firstRow, int secondRow) {
        T temp[] = matrix[firstRow];
        matrix[firstRow] = matrix[secondRow];
        matrix[secondRow] = temp;
    }

    public void swapColumns(int firstColumn, int secondColumn) {
        for (int i = 0; i < rows; i++) {
            T tmp = matrix[i][firstColumn];
            matrix[i][firstColumn] = matrix[i][secondColumn];
            matrix[i][secondColumn] = tmp;

        }
    }


    public ANumber[] get0(int xPos, int yPos, ANumber resultVector[]){

        T temp[] = new T[this.columns]; //Pomocnicza tablica
        T help; //pomocnicza zmienna
        for(int i = 0; i < (this.columns); i++){    //Dzielę w celu uzyskania jednyki, przypisują podzieloną wartość do pomocniczej tablicy
            matrix[xPos][i] =  matrix[xPos][i].div(matrix[xPos][yPos]);
            temp[i] = matrix[xPos][i];
        }
        help = vector[xPos].div(matrix[xPos][yPos]);
        help =help.mul(matrix[xPos-1][yPos]);
        temp = (T[]) multiplayRowByValue(temp, matrix[xPos-1][yPos]); //Mnoże pomocnicza macierz przez pierwszą liczę w redukowanym wierszu

        //Zmieniam znak
        for (int i = 0; i < (this.rows); i++) temp[i] = (T) (temp[i].sign(temp[i]));
        help = help.sign(help);
        vector[xPos-1] = vector[xPos].add(help);
        matrix[xPos-1] = (T[])addTwoRows(temp, matrix[xPos-1]);      //Dodaje pomocnicza macierz do macierzy redukawanej
    return resultVector;

    }

    public ANumber[] findBiggestValueInRow(int xPos, ANumber resultVector[]){

        int maxRow = xPos;
        for(int i = 0; i< rows; i++){
            if(matrix[maxRow][xPos].compareTo(matrix[i][xPos])==1) maxRow = i;
        }
        swapRows(xPos, maxRow);
        return swap(xPos, maxRow,resultVector);
    }

    public void findBiggestElementInSubmatrix(int xPos,  ANumber resultVector[]){

        int maxRow = xPos;
        int maxColumn = xPos;
        for(int i = 0; i< rows; i++){
            for(int j = 0; j<columns;j++) {
                if (matrix[maxRow][maxColumn].compareTo(matrix[i][j]) == 1){
                    maxRow = i;
                    maxColumn = j;
                }
            }
        }
        swapRows(xPos, maxRow);
        swapColumns(xPos,maxColumn);

    }

}
