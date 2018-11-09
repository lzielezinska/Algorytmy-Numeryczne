
/**
 * Created by Lucyna on 02.11.18.
 * informatyka rok 3
 * tester programista
 * Algorytmy Numeryczne
 * Projekt 2
 */
public class MyMatrix  {
    private int rows;
    private int columns;
    private Class<ANumber> type;
    private ANumber matrix[][];


    public MyMatrix(int rows, int columns, Class<ANumber> type){
        this.rows = rows;
        this.columns = columns;
        this.matrix = (ANumber[][]) new ANumber[rows][columns];
        this.type = type;
    }



    private MyMatrix(ANumber[][] vector) {
        this.rows = vector.length;
        this.columns = vector[0].length;

        this.matrix=vector;
    }



   /public static void fillMatrix(MyMatrix matrix){

        for(int i = 0; i<matrix.rows; i++){
            for(int j = 0; j<matrix.columns; j++){

                int a = ((-1)*(int)Math.pow(2,16));
                int b = (int)Math.pow(2,16)-1;
                int r = (int)(Math.random() * (b-a)+a);
                matrix.matrix[i][j] = (T)r/Math.pow(2,16);
            }

        }
    }

    public static void printMatrix(MyMatrix matrix){
        for(int i = 0; i < matrix.rows; i++){
            for(int j = 0; j <matrix.columns; j++) System.out.print("| " + matrix.matrix[i][j] + " ");
            System.out.println("|");
        }
    }

    public ANumber[] addTwoRows(ANumber m1[], ANumber m2[]) {

            ANumber sum[] = new ANumber[m1.length];
            for(int i=0; i<m1.length; i++){
                sum[i] = (ANumber) m1[i].add(m2[i]);
            }

    return sum;
    }

    public ANumber[] multiplayRowByValue(ANumber[] row, ANumber value){
        ANumber factor[] = new ANumber[row.length];
        for(int i=0; i<row.length; i++){
            row[i] = (ANumber) row[i].mul(value);
        }
        return factor;
    }

    public ANumber[] devideRowByValue(ANumber[] row, ANumber value){
        ANumber quotion[] = new ANumber[row.length];
        for(int i=0; i<row.length; i++){
            row[i] = (ANumber) row[i].div(value);
        }
        return quotion;

    }


    public ANumber[] swapRows(ANumber[] matrix,int firstRow, int secondRow){
        ANumber temp = matrix[firstRow];
        matrix[firstRow] = matrix[secondRow];
        matrix[secondRow] = temp;

        return matrix;
    }


    public ANumber[][] swapColumns(ANumber[][] matrix, int firstColumn, int secondColumn, int size){
        for(int i = 0; i<size; i++){
            ANumber tmp = matrix[i][firstColumn];
            matrix[i][firstColumn] = matrix[i][secondColumn];
            matrix[i][secondColumn] = tmp;
        }
        return matrix;
    }

}
