/**
 * Created by Lucyna Zielezińska & Kacper Dondziak on 06.12.18.
 */
public class IteratedMethod {
    public MyMatrix matrix;
    boolean precision = false;
    private int iterator = 0;

    private static final double expectedPrecision = 0001;
    private static final int maxIteration = 100;

    public int getIterator() {
        return iterator;
    }


    public IteratedMethod(MyMatrix matrix) {
        this.matrix = matrix;
    }

    public void jacobiMethod() {
        cleanMatrix();
        double nextVector[] = new double[matrix.vector.length];
        double previousVector[] = new double[matrix.vector.length];
        nextVector = setResoultVector(nextVector);

        iterator = 0;
        while ((iterator < maxIteration) && (precision!=true)){
            previousVector = saveValuesOfCurrentVector(nextVector,previousVector);
            for(int i = 0; i < nextVector.length; i++){
                nextVector[i] = solve(matrix.matrix[i], previousVector, i );
            }
            if(getError(previousVector, nextVector) < expectedPrecision) precision = true;
            iterator++;
        }
        for(int i = 0; i< previousVector.length; i++) {
            matrix.vector[i]= nextVector[i];
        }


    }

    public void gaussSeidelMethod() {
       // cleanMatrix();
        double nextVector[] = new double[matrix.vector.length];
        double previousVector[] = new double[matrix.vector.length];
        nextVector = setResoultVector(nextVector);

        iterator = 0;
        while ((iterator < maxIteration) && (precision!=true)){
            previousVector = saveValuesOfCurrentVector(nextVector,previousVector);
            for(int i = 0; i < nextVector.length; i++){
                nextVector[i] = solve(matrix.matrix[i], nextVector, i );
            }
            if(getError(previousVector, nextVector) < expectedPrecision) precision = true;
            iterator++;
        }
        for(int i = 0; i< previousVector.length; i++) {
            matrix.vector[i]= nextVector[i];
        }


    }

    private double solve(double CurrentRow[], double nextVector[], int leadingElementPos){
        double sum = sumElements(CurrentRow,nextVector, leadingElementPos);
        double valueOfLeadingElement = CurrentRow[leadingElementPos];
        double solution = (1/valueOfLeadingElement)*((matrix.vector[leadingElementPos]-sum));

        return solution;

    }

    private double sumElements(double CurrentRow[], double nextVector[], int leadingElementPos){
        double solution = 0;
        for(int i = 0; i < CurrentRow.length; i++ ){
            if(i!=leadingElementPos) solution += (CurrentRow[i] * nextVector[i]);
        }

        return solution;

    }
    private double[] setResoultVector(double resoultVector[]){
        for(int i = 0; i< resoultVector.length; i++) resoultVector[i] = 0;
        return resoultVector;
    }
    private double[] saveValuesOfCurrentVector(double nextVector[], double previousVector[]){
        for(int i = 0; i< previousVector.length; i++) previousVector[i] = nextVector[i];
        return previousVector;
    }

    private void cleanMatrix(){
        for (int i = 0; i < matrix.rows; i++) {
            matrix.findBiggestValueInRow(i);
        }
    }

    public static double getError(double previousVector[], double nextVector[]) {
        double maximumValue = 0;
        double temporaryValue = 0;

        for (int i = 0; i < previousVector.length; i++) {
            temporaryValue = Math.abs(nextVector[i]-previousVector[i]);
            if (maximumValue < temporaryValue) maximumValue = temporaryValue;
        }
        return maximumValue;
    }

}
