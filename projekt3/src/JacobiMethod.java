/**
 * Created by Lucyna Zielezińska & Kacper Dondziak on 06.12.18.
 */
public class JacobiMethod {
    MyMatrix matrix;
    Vector vector;
    boolean precision = false;

    private static final double expectedPrecision = 0.0001;
    private static final int maxIteration = 10;

    public JacobiMethod(MyMatrix matrix, Vector vector) {
    this.matrix = matrix;
    this.vector = vector;
    }


    public void jacobiMethod() {
        double nextVector[] = new double[this.vector.getLength()];
        double previousVector[] = new double[this.vector.getLength()];
        nextVector = setResoultVector(nextVector);

        int iterator = 0;
        while ((precision!=true) && (iterator < maxIteration)){
            previousVector = saveValuesOfCurrentVector(nextVector,previousVector);
            for(int i = 0; i<matrix.columns; i++){
                solve(matrix.matrix[i],nextVector, i );
            }
            if(getError(previousVector, nextVector) < expectedPrecision) precision = true;
            iterator++;
        }

        for(int i = 0; i< previousVector.length; i++) this.vector.vector[i]= nextVector[i];

    }

    private double solve(double CurrentRow[], double resoultVector[], int leadingElementPos){
        double sum = sumElements(CurrentRow,leadingElementPos);
        double valueOfLeadingElement = CurrentRow[leadingElementPos];
        double solution = (1/valueOfLeadingElement)*(resoultVector[leadingElementPos]-sum);

        return solution;

    }

    private double sumElements(double CurrentRow[], int leadingElementPos){
        double solution = 0;
        for(int i = 0; i < CurrentRow.length; i++ ){
            if(i==leadingElementPos){
                i++;
            }else{
                solution += CurrentRow[i];
            }

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

    private double getError(double previousVector[], double nextVector[]) {
        double maximumValue = nextVector[0];
        double temporaryValue = 0;

        for (int i = 0; i < previousVector.length; i++) {
            temporaryValue = Math.abs(nextVector[i]-previousVector[i]);
            if (maximumValue < temporaryValue) maximumValue = temporaryValue;
        }
        return maximumValue;
    }

}
