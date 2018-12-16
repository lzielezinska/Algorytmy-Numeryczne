public class MatrixGenerator {
    public static Vector prepareVector(int size){
        int rowSize = ((size + 1) * (size + 2)) / 2;
        double[] resultVector = new double[rowSize];
        for(int i = 0; i < resultVector.length; i++){
            resultVector[i] = 0.0;
        }
        resultVector[resultVector.length - 1] = 1.0;
        Vector vector = new Vector(resultVector);
        return vector;
    }
    public static MyMatrix prepareMatrix(int size){
        int rowSize = ((size + 1) * (size + 2)) / 2;

        Vector vector = prepareVector(size);
        double[][] generatedMatrix = MatrixGenerator.generateMatrix(size);
        for(int i = 0; i < rowSize; i++){
            for(int t = 0; t < rowSize; t++){
                if(i == t){
                    generatedMatrix[i][t] -= 1.0;
                }
            }
        }
        for(int i = 0; i <= size; i++){
            for(int y = 0; y <= (size - i); y++){
                if(i == 0){
                    generatedMatrix[MatrixGenerator.findElement(i, y, size)][MatrixGenerator.findElement(i, y, size)] += 1.0;
                }
            }
        }
        generatedMatrix[rowSize - 1][rowSize - 1] += 1.0;
        MyMatrix matrix = new MyMatrix(rowSize, rowSize, vector);
        for(int i = 0; i < rowSize; i++){
            for(int t = 0; t < rowSize; t++){
                matrix.matrix[i][t] = generatedMatrix[i][t];
            }
        }

        return matrix;
    }
    public static double[][] generateMatrix(int n){
        int size = ((n + 1) * (n + 2)) / 2;
        int indexOfRow = 0;
        double[][] result = new double[size][size];
        for(int i = 0; i < size; i++){
            for(int t = 0; t < size; t++){
                result[i][t] = 0.0;
            }
        }
        for(int i = 0; i <= n; i++){
            for(int y = 0; y <= (n - i); y++){
                generateRow(result[indexOfRow], i, y, n);
                indexOfRow++;
            }
        }
        return result;
    }

    public static void generateRow(double[] row, int yes, int no, int n){
        int pPosition = getToPElement(yes, no, n);
        int nPosition = getToNElement(yes, no, n);
        int uPosition = getToUElement(yes, no, n);
        int thisPosition = getToThisElement(yes, no, n);
        if(pPosition >= 0){
            row[pPosition] = computeToPValue(yes, no, n);
        }
        if(nPosition >= 0){
            row[nPosition] = computeToNValue(yes, no, n);
        }
        if(uPosition >= 0){
            row[uPosition] = computeToUValue(yes, no, n);
        }
        if(thisPosition >= 0){
            row[thisPosition] = computeToThisValue(yes, no, n);
        }
    }

    public static double computeToPValue(int yes, int no, int n){
        double result;
        result = ((double)yes / n) * ((double)(n - (yes + no))/(n - 1));
        return result * 2.0;
    }
    public static double computeToNValue(int yes, int no, int n){
        double result;
        result = ((double)no / n) * ((double)(n - (yes + no))/(n - 1));
        return result * 2.0;
    }
    public static double computeToUValue(int yes, int no, int n){
        double result;
        result = ((double)yes / n) * ((double)(no)/(n - 1));
        return result * 2.0;
    }
    public static double computeToThisValue(int yes, int no, int n){
        double result;
        double u = (double)(n - (yes + no));
        double U = (u/n) * ((u - 1)/(n - 1));
        double N = ((double)no/n) * ((double)(no - 1)/(n - 1));
        double Y = ((double)yes/n) * ((double)(yes - 1)/(n - 1));
        result = U + N + Y;
        return result;
    }
    public static int findElement(int yes, int no, int n){
        int index = 0;
        for(int i = 0; i <= n; i++){
            for(int t = 0; t <= (n - i); t++){
                if(i == (yes) && t == no){
                    return index;
                }
                index++;
            }
        }
        return -1;
    }
    public static int getToUElement(int yes, int no, int n){
        if(yes == 0 || no == 0)
            return -1;
        return findElement(yes - 1, no - 1, n);
    }
    public static int getToPElement(int yes, int no, int n){
        if(yes == n || (yes + no) == n){
            return -1;
        }
        return findElement(yes + 1, no, n);
    }
    public static int getToNElement(int yes, int no, int n){
        if(no == n || (yes + no) == n){
            return -1;
        }
        return findElement(yes, no + 1, n);
    }
    public static int getToThisElement(int yes, int no, int n){
        return findElement(yes, no, n);
    }
}
