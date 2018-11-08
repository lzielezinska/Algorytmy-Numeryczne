
/**
 * Created by Lucyna on 02.11.18.
 * informatyka rok 3
 * tester programista
 * Algorytmy Numeryczne
 * Projekt 2
 */
public class MyMatrix <T extends Number> {
    private int rows;
    private int columns;
    private Class<T> type;
    private T matrix[][];

    public MyMatrix(int rows, int columns, Class<T> type){
        this.rows = rows;
        this.columns = columns;
        this.matrix = (T[][])new Number[rows][columns];
        this.type = type;
    }



    public static void fillMatrix(MyMatrix matrix){

        for(int i = 0; i<matrix.rows; i++){
            for(int j = 0; j<matrix.columns; j++){

                int a = ((-1)*(int)Math.pow(2,16));
                int b = (int)Math.pow(2,16)-1;
                int r = (int)(Math.random() * (b-a)+a);
                matrix.matrix[i][j] = r/Math.pow(2,16);
            }

        }
    }

    public static void printMatrix(MyMatrix matrix){
        for(int i = 0; i < matrix.rows; i++){
            for(int j = 0; j <matrix.columns; j++) System.out.print("| " + matrix.matrix[i][j] + " ");
            System.out.println("|");
        }
    }




    public MyMatrix addTwoRows(MyMatrix row1, MyMatrix row2){

        if(!(row1.rows == row2.rows && row1.columns == 1 && row2.columns==1)){
            System.out.println("You can't add this two rows, because of incorrect size of the matrix");
        }else{
            for (int i = 0; i < row1.rows; i++) {
                if(row1.type.equals(float.class) && row2.type.equals(float.class)) {
                    Float element1 = row1.matrix[i][0].floatValue();
                    Float element2 = row2.matrix[i][0].floatValue();
                    Float sum = element1 + element2;

                    row2.matrix[i][0]=sum;
                }else if(row1.type.equals(double.class) && row2.type.equals(double.class)){
                    Double element1 = row1.matrix[i][0].doubleValue();
                    Double element2= row2.matrix[i][0].doubleValue();
                    Double sum = element1 + element2;

                    row2.matrix[i][0]=(T)sum;
                }
            }
        }
    return row2;
    }

    public MyMatrix multiplayRowByValue(MyMatrix<T> row, MyMatrix<T> value) {

        for (int i = 0; i < row.rows; i++) {
            if(row.type.equals(float.class) && value.type.equals(float.class)){
                Float factor1 = row.matrix[i][0].floatValue();
                Float factor2 = value.matrix[0][0].floatValue();
                Float product = factor1 * factor2;

                row.matrix[i][0]=(T)product;

            }else if(row.type.equals(double.class) && value.type.equals(double.class)){
                Double factor1 = row.matrix[i][0].doubleValue();
                Double factor2 = value.matrix[0][0].doubleValue();
                Double product = factor1 * factor2;

                row.matrix[i][1]=(T)product;


            }
        }

        return row;
    }


    public MyMatrix devideRowByValue(MyMatrix<T> row, MyMatrix<T> value) {

        for (int i = 0; i < row.rows; i++) {
            if(row.type.equals(float.class) && value.type.equals(float.class)){
                Float dividend = row.matrix[i][0].floatValue();
                Float divisor = value.matrix[0][0].floatValue();
                Float quotion = dividend / divisor;

                row.matrix[i][0]=(T)quotion;

            }else if(row.type.equals(double.class) && value.type.equals(double.class)){
                Double dividend = row.matrix[i][0].doubleValue();
                Double divisor = value.matrix[0][0].doubleValue();
                Double quotion = dividend / divisor;

                row.matrix[i][1]=(T)quotion;


            }
        }

        return row;
    }

    public static void Gauss(MyMatrix matrix, MyMatrix vector ){
        if(matrix.rows == vector.rows && vector.columns == 1){
            if(matrix.type.equals(float.class) && vector.type.equals(float.class)){
                if((float)matrix.matrix[0][0] != 0){
                    for(int j = 0; j<matrix.columns; j++ ){
                        for(int i = 0; i<matrix.rows; i++){
                            (float)matrix.matrix[i] = (float)matrix.matrix[i]/ (float)matrix.matrix[i][j];
                        }

                    }
                }
            }

        }
    }



    public MyMatrix[] swapRows(MyMatrix[] matrix,int firstRow, int secondRow){
        MyMatrix temp = matrix[firstRow];
        matrix[firstRow] = matrix[secondRow];
        matrix[secondRow] = temp;

        return matrix;
    }


    public MyMatrix[][] swapColumns(MyMatrix[][] matrix, int firstColumn, int secondColumn, int size){
        for(int i = 0; i<size; i++){
            MyMatrix tmp = matrix[i][firstColumn];
            matrix[i][firstColumn] = matrix[i][secondColumn];
            matrix[i][secondColumn] = tmp;
        }
        return matrix;
    }








public static void main(String[] args){
 MyMatrix macierz = new MyMatrix(5,5, Double.class);
 fillMatrix(macierz);
 printMatrix(macierz);
}

}
