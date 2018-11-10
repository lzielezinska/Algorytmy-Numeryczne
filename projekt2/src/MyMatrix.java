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
        //TODO Here make gauss method body.
        return resultVector;
    }

    public ANumber[] mulMatrixVector(ANumber[] vector){
        ANumber[] resultVector = (T[]) Array.newInstance(type, vector.length);
        //TODO Here add multiplying Matrix * Vector method body.
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
            row[i] = (ANumber) row[i].mul(value);
        }
        return factor;
    }

    public ANumber[] devideRowByValue(ANumber[] row, ANumber value) {
        ANumber quotion[] = new ANumber[row.length];
        for (int i = 0; i < row.length; i++) {
            row[i] = (ANumber) row[i].div(value);
        }
        return quotion;

    }

    public ANumber[] swapRows(ANumber[] matrix, int firstRow, int secondRow) {
        ANumber temp = matrix[firstRow];
        matrix[firstRow] = matrix[secondRow];
        matrix[secondRow] = temp;

        return matrix;
    }


    public ANumber[][] swapColumns(ANumber[][] matrix, int firstColumn, int secondColumn, int size) {
        for (int i = 0; i < size; i++) {
            ANumber tmp = matrix[i][firstColumn];
            matrix[i][firstColumn] = matrix[i][secondColumn];
            matrix[i][secondColumn] = tmp;
        }
        return matrix;
    }

}
